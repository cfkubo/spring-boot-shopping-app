#!/bin/bash

# filepath: loadtest-orders.sh

API_URL="http://localhost:8081/api/products"
ORDER_URL="http://localhost:8081/api/orders"
ORDER_COUNT=10 # Number of orders to place

# Fetch products and extract the first two product IDs
PRODUCT_IDS=($(curl -s $API_URL | jq '.[0:2][] | .productId'))
if [ ${#PRODUCT_IDS[@]} -lt 2 ]; then
  echo "Not enough products found to place orders."
  exit 1
fi

PRODUCT1=${PRODUCT_IDS[0]}
PRODUCT2=${PRODUCT_IDS[1]}

echo "Using product IDs: $PRODUCT1, $PRODUCT2"

for i in $(seq 1 $ORDER_COUNT); do
  ORDER_JSON=$(cat <<EOF
{
  "customerId": 123,
  "customerName": "arul demo$i",
  "customerEmail": "ak.demo$i@example.com",
  "orderDate": "$(date -u +"%Y-%m-%dT%H:%M:%SZ")",
  "totalAmount": 59.97,
  "currency": "USD",
  "orderStatus": "NEW",
  "shippingAddress": "123 Main St",
  "billingAddress": "123 Main St",
  "paymentMethod": "Credit Card",
  "transactionId": "TXN123456$i",
  "shippingCost": 5.00,
  "discountAmount": 0.00,
  "estimatedDeliveryDate": "2025-06-20T12:00:00",
  "actualDeliveryDate": null,
  "notes": "Please deliver between 9am-5pm",
  "orderItems": [
    {
      "productId": $PRODUCT1,
      "quantity": 2,
      "price": 19.99
    },
    {
      "productId": $PRODUCT2,
      "quantity": 1,
      "price": 19.99
    }
  ]
}
EOF
)
  curl -s -o /dev/null -w "Order $i: HTTP %{http_code}\n" -X POST $ORDER_URL \
    -H "Content-Type: application/json" \
    -d "$ORDER_JSON"
done