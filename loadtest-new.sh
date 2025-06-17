#!/bin/bash

API_URL="http://localhost:8081/api/products"
CART_ADD_URL="http://localhost:8081/api/cart/add"
CART_GET_URL="http://localhost:8081/api/cart"
ORDER_URL="http://localhost:8081/api/orders/from-cart"
ORDER_COUNT=10000 # Number of concurrent orders to place

# Fetch products and extract the first product ID
PRODUCT_IDS=($(curl -s $API_URL | jq '.[0:2][] | .productId'))
if [ ${#PRODUCT_IDS[@]} -lt 1 ]; then
  echo "Not enough products found to place orders."
  exit 1
fi

PRODUCT1=${PRODUCT_IDS[0]}

echo "Using product ID: $PRODUCT1"

place_order() {
  IDX=$1
  COOKIE_FILE="cookies_$IDX.txt"

  # Add to cart (write cookies)
  curl -s -c $COOKIE_FILE -X POST "$CART_ADD_URL?productId=$PRODUCT1&quantity=10" > /dev/null

  # (Optional) Check cart (read cookies)
  curl -s -b $COOKIE_FILE -X GET "$CART_GET_URL" > /dev/null

  # Place order (read cookies)
  ORDER_JSON=$(cat <<EOF
{
  "customerId": $((1000 + IDX)),
  "customerName": "User $IDX",
  "customerEmail": "user$IDX@example.com",
  "orderDate": "$(date -u +"%Y-%m-%dT%H:%M:%SZ")",
  "totalAmount": 40480.60,
  "currency": "USD",
  "orderStatus": "NEW",
  "shippingAddress": "123 Main St",
  "billingAddress": "123 Main St",
  "paymentMethod": "Credit Card",
  "transactionId": "TXN123456$IDX",
  "shippingCost": 5.00,
  "discountAmount": 0.00,
  "estimatedDeliveryDate": "2025-06-20T12:00:00",
  "actualDeliveryDate": null,
  "notes": "Please deliver between 9am-5pm"
}
EOF
)
  curl -s -b $COOKIE_FILE -X POST $ORDER_URL \
    -H "Content-Type: application/json" \
    -d "$ORDER_JSON" -o /dev/null -w "Order $IDX: HTTP %{http_code}\n"

  rm -f $COOKIE_FILE
}

# Run orders concurrently
for i in $(seq 1 $ORDER_COUNT); do
  place_order $i &
done

wait
echo "All concurrent orders placed."