# Shopping Simulation Application

This project is a Spring Boot application that simulates a shopping experience using Vaadin for the user interface. Users can browse products, add them to their cart, and place orders. The application also provides REST API endpoints for managing products and orders, allowing for performance testing and integration with other systems.


## Features

- **Product Browsing**: Users can view a list of available products with details such as name, description, price, and stock.
- **Shopping Cart**: Users can add products to their cart, view the cart, and modify quantities.
- **Checkout Process**: Users can enter shipping and payment information to complete their orders.
- **REST API**: The application exposes RESTful endpoints for managing products and orders, facilitating integration and performance testing.

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven
- PostgreSQL database

### Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   cd shopping-simulation-app
   ```

2. Configure the database connection in `src/main/resources/application.properties`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Create the database schema by running the SQL script located in `src/main/resources/db/schema.sql`.

4. Build the project using Maven:

```
mvn clean package -Pproduction -Dvaadin.force.production.build=true
```


```
jar tvf target/shopping-simulation-app-1.0-SNAPSHOT.jar | grep META-INF/VAADIN/
```

   ```
   mvn clean install
   ```

5. Run the application:
   ```
   mvn spring-boot:run
   ```

6. Access the application at `http://localhost:8080`.

## API Endpoints

- **Products**
  - `GET /api/products`: Retrieve a list of products.
  - `GET /api/products/{id}`: Retrieve details of a specific product.

- **Orders**
  - `POST /api/orders`: Place a new order.
  - `GET /api/orders/{id}`: Retrieve details of a specific order.

## Interacting with the API

You can interact with the REST API using tools like `curl` or Postman.

### Products

- **Get all products**
  ```sh
  curl -X GET http://localhost:8080/api/products
  ```

- **Get a product by ID**
  ```sh
  curl -X GET http://localhost:8080/api/products/1
  ```

- **Create a new product**
  ```sh
  curl -X POST http://localhost:8080/api/products \
    -H "Content-Type: application/json" \
    -d '{
      "productName": "Sample Product",
      "description": "A new product",
      "price": 19.99,
      "stockQuantity": 100
    }'
  ```

- **Delete a product**
  ```sh
  curl -X DELETE http://localhost:8080/api/products/1
  ```

```
curl -c cookies.txt -X POST "http://localhost:8081/api/cart/add?productId=4&quantity=10"
```
Product added to cart%
                                                             
```
curl -b cookies.txt  -X GET "http://localhost:8081/api/cart"
```

[{"id":null,"order":null,"productId":4,"quantity":10,"price":4048.06}]%     

### Orders

```
# Add to cart (write cookies)
curl -c cookies.txt -X POST "http://localhost:8081/api/cart/add?productId=4&quantity=10"

# Check cart (read cookies)
curl -b cookies.txt -X GET "http://localhost:8081/api/cart"

# Place order (read cookies)
curl -b cookies.txt -X POST http://localhost:8081/api/orders/from-cart \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 144,
    "customerName": "arul vannala",
    "customerEmail": "arul@braodcom.com",
    "orderDate": "2025-06-14T12:00:00",
    "totalAmount": 40480.60,
    "currency": "USD",
    "orderStatus": "NEW",
    "shippingAddress": "123 Main St",
    "billingAddress": "123 Main St",
    "paymentMethod": "Credit Card",
    "transactionId": "TXN123456",
    "shippingCost": 5.00,
    "discountAmount": 0.00,
    "estimatedDeliveryDate": "2025-06-20T12:00:00",
    "actualDeliveryDate": null,
    "notes": "Please deliver between 9am-5pm"
  }'
```


- **Place a new order**
  ```sh
  curl -X POST http://localhost:8081/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 123,
    "customerName": "John Doe",
    "customerEmail": "john@example.com",
    "orderDate": "2025-06-14T12:00:00",
    "totalAmount": 59.97,
    "currency": "USD",
    "orderStatus": "NEW",
    "shippingAddress": "123 Main St",
    "billingAddress": "123 Main St",
    "paymentMethod": "Credit Card",
    "transactionId": "TXN123456",
    "shippingCost": 5.00,
    "discountAmount": 0.00,
    "estimatedDeliveryDate": "2025-06-20T12:00:00",
    "actualDeliveryDate": null,
    "notes": "Please deliver between 9am-5pm",
    "orderItems": [
      {
        "productId": 1,
        "quantity": 2,
        "price": 19.99
      },
      {
        "productId": 2,
        "quantity": 1,
        "price": 19.99
      }
    ]
  }'
  ```

- **Get an order by ID**
  ```sh
  curl -X GET http://localhost:8080/api/orders/1
  ```

- **Get all orders**
  ```sh
  curl -X GET http://localhost:8080/api/orders
  ```

---

Replace IDs and field values as needed for your data.



```
docker exec postgres env PGPASSWORD=postgres pg_dump -U postgres -d postgres > mydatabase_backup.sql
```

```
PGPASSWORD=postgres docker exec -i postgres psql -U postgres -c "CREATE DATABASE restored_db;"
```

```
PGPASSWORD=postgres docker exec -i postgres psql -U postgres -d restored_db < mydatabase_backup.sql
```

## Deploying to Cloud Foundry

You can deploy this application to Cloud Foundry using the following steps:

### 1. Build the Application

Build the production JAR with Vaadin frontend:

```sh
mvn clean package -Pproduction -Dvaadin.force.production.build=true
```

### 2. Verify the Build

Check that the Vaadin frontend resources are packaged in the JAR:

```sh
jar tvf target/shopping-simulation-app-1.0-SNAPSHOT.jar | grep META-INF/VAADIN/
```

You should see a list of VAADIN resources if the build was successful.

### 3. Create a postgresdb

```
cf create-service postgres on-demand-plan postgresdb
```

### 3. Prepare the Manifest File

You can leverage the existing `manifest.yml` in your project root with content similar to:

```yaml
---
applications:
- name: pg-shopping-app 
  memory: 1G
  instances: 1
  path: target/shopping-simulation-app-1.0-SNAPSHOT.jar
  buildpack: java_buildpack_offline
  services:
  - postgresdb

  env:
    JAVA_OPTS: -Djava.security.egd=file:///dev/urandom
    SPRING_PROFILES_ACTIVE: cloud,on-demand
    JBP_CONFIG_OPEN_JDK_JRE: '{ jre: { version: 21.+ } }'
    JBP_CONFIG_SPRING_AUTO_RECONFIGURATION: '{ enabled: false }'
    VAADIN_PRODUCTION_MODE: true
```

### 4. Push to Cloud Foundry

Log in to your Cloud Foundry environment and push the app:

```sh
cf login
cf push
```

### 5. Verify Deployment

After deployment, check your app status:

```sh
cf apps
```

Visit the route provided by Cloud Foundry to access your application.

---

**Note:**  
- Make sure your database (e.g., PostgreSQL) is accessible from Cloud Foundry and credentials are set via environment variables or `application.properties`.
- Adjust the manifest as needed for your environment (memory, services, etc.).

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.# spring-boot-shopping-app
