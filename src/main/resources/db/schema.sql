CREATE TABLE new_products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    currency VARCHAR(3) DEFAULT 'USD',
    stock_quantity INT DEFAULT 0 CHECK (stock_quantity >= 0),
    category VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(150),
    release_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    weight_kg NUMERIC(8, 3),
    dimensions_cm VARCHAR(100),
    rating_avg NUMERIC(2, 1) CHECK (rating_avg >= 0 AND rating_avg <= 5.0),
    review_count INT DEFAULT 0 CHECK (review_count >= 0),
    material VARCHAR(100),
    warranty_months INT,
    last_updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE new_orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255),
    order_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    total_amount NUMERIC(10, 2) NOT NULL,
    currency VARCHAR(3) DEFAULT 'USD',
    order_status VARCHAR(50) DEFAULT 'Pending' CHECK (order_status IN ('Pending', 'Processing', 'Shipped', 'Delivered', 'Cancelled', 'Returned')),
    shipping_address TEXT,
    billing_address TEXT,
    payment_method VARCHAR(100),
    transaction_id VARCHAR(255),
    shipping_cost NUMERIC(8, 2) DEFAULT 0.00,
    discount_amount NUMERIC(8, 2) DEFAULT 0.00,
    estimated_delivery_date DATE,
    actual_delivery_date DATE,
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
    order_item_id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price_at_purchase NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
        REFERENCES new_orders (order_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES new_products (product_id)
        ON DELETE RESTRICT
);