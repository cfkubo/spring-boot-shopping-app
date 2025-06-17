## Project Structure

The project is organized as follows:

```
shopping-simulation-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── shopping
│   │   │               ├── ShoppingSimulationApp.java
│   │   │               ├── config
│   │   │               │   └── VaadinConfig.java
│   │   │               ├── controller
│   │   │               │   ├── ApiOrderController.java
│   │   │               │   └── ProductController.java
│   │   │               ├── model
│   │   │               │   ├── NewProduct.java
│   │   │               │   ├── NewOrder.java
│   │   │               │   └── OrderItem.java
│   │   │               ├── repository
│   │   │               │   ├── NewProductRepository.java
│   │   │               │   ├── NewOrderRepository.java
│   │   │               │   └── OrderItemRepository.java
│   │   │               ├── service
│   │   │               │   ├── OrderService.java
│   │   │               │   └── ProductService.java
│   │   │               └── ui
│   │   │                   ├── MainView.java
│   │   │                   ├── CartView.java
│   │   │                   └── CheckoutView.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── db
│   │           └── schema.sql
├── pom.xml
└── README.md
```
