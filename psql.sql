DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS new_orders;
DROP TABLE IF EXISTS new_products; -- If you want to recreate products as well

select * from order_items;

select * from new_orders;

select * from new_products;

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
    weight_kg NUMERIC(8, 3), -- Weight in kilograms, up to 3 decimal places
    dimensions_cm VARCHAR(100), -- e.g., "10x5x2 cm (LxWxH)"
    rating_avg NUMERIC(2, 1) CHECK (rating_avg >= 0 AND rating_avg <= 5.0), -- Average rating out of 5
    review_count INT DEFAULT 0 CHECK (review_count >= 0),
    material VARCHAR(100), -- e.g., "Quantum Alloy", "Bio-plastic", "Enchanted Wood"
    warranty_months INT, -- Warranty period in months
    last_updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE new_orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT, -- Assuming a separate customers table might exist, or could be NULL
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255),
    order_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    total_amount NUMERIC(10, 2) NOT NULL,
    currency VARCHAR(3) DEFAULT 'USD',
    -- FIX: Added 'NEW' to the list of allowed order_status values
    order_status VARCHAR(50) DEFAULT 'Pending' CHECK (order_status IN ('NEW', 'Pending', 'Processing', 'Shipped', 'Delivered', 'Cancelled', 'Returned')),
    shipping_address TEXT,
    billing_address TEXT,
    payment_method VARCHAR(100),
    transaction_id VARCHAR(255), -- Unique ID from payment gateway
    shipping_cost NUMERIC(8, 2) DEFAULT 0.00,
    discount_amount NUMERIC(8, 2) DEFAULT 0.00,
    estimated_delivery_date DATE,
    actual_delivery_date DATE,
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Table to link orders to products (Order Items/Details)
CREATE TABLE order_items (
    order_item_id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    -- FIX: Changed column name from price_at_purchase to price
    price NUMERIC(10, 2) NOT NULL,
    -- Foreign key constraints
    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
        REFERENCES new_orders (order_id)
        ON DELETE CASCADE, -- If an order is deleted, its items are also deleted
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES new_products (product_id)
        ON DELETE RESTRICT -- Prevent deleting a product if it's part of an order item
);



-- Insert 50 random, creative products into new_products table
INSERT INTO new_products (
    product_name,
    description,
    price,
    currency,
    stock_quantity,
    category,
    manufacturer,
    release_date,
    is_active,
    weight_kg,
    dimensions_cm,
    rating_avg,
    review_count,
    material,
    warranty_months
)
SELECT
    -- Product Name: Creative combinations
    CASE floor(random() * 7)
        WHEN 0 THEN (ARRAY['Quantum','Bio-Luminescent','Aetherial','Temporal','Cosmic','Dream-Weave'])[floor(random()*6)+1] || ' ' || (ARRAY['Stabilizer','Synthesizer','Luminary','Chronometer','Conduit','Echo-Box'])[floor(random()*6)+1]
        WHEN 1 THEN (ARRAY['Starlight','Void-Forged','Whisper-Soft','Chrono-Shift','Sonic-Wave','Glimmering'])[floor(random()*6)+1] || ' ' || (ARRAY['Compass','Attenuator','Chronosculptor','Flux Capacitor','Reality Bender','Dream Catcher'])[floor(random()*6)+1]
        WHEN 2 THEN (ARRAY['Ancient','Forbidden','Mystical','Cybernetic','Neo-Organic','Galactic'])[floor(random()*6)+1] || ' ' || (ARRAY['Tome','Essence','Module','Generator','Crystal','Fabric'])[floor(random()*6)+1]
        ELSE (ARRAY['Hyper-Sensory','Sub-Atomic','Aural','Kinetic','Trans-Dimensional','Neural'])[floor(random()*6)+1] || ' ' || (ARRAY['Amplifier','Harmonizer','Imager','Resonator','Simulator','Interface'])[floor(random()*6)+1]
    END AS product_name,

    -- Description: Short, evocative, and sometimes whimsical
    CASE floor(random() * 5)
        WHEN 0 THEN 'Unlocks the secrets of forgotten realms. Essential for advanced temporal research.'
        WHEN 1 THEN 'Experience true silence and focus. Perfect for digital detox retreats or deep meditation.'
        WHEN 2 THEN 'Harvest starlight for sustainable energy. Powers small habitats or personal devices.'
        WHEN 3 THEN 'A truly immersive reality experience, indistinguishable from genuine existence.'
        WHEN 4 THEN 'Navigates the most treacherous wormholes. Never get lost in the cosmic ocean again.'
        ELSE 'Enhances cognitive functions and creativity. Caution: may induce sporadic insights.'
    END AS description,

    -- Price: Random between 10.00 and 9999.99
    (random() * 9990 + 10)::NUMERIC(10, 2) AS price,

    -- Currency: Mostly USD, some EUR or GBP
    CASE floor(random() * 3)
        WHEN 0 THEN 'USD'
        WHEN 1 THEN 'EUR'
        ELSE 'GBP'
    END AS currency,

    -- Stock Quantity: Random between 0 and 500
    floor(random() * 501)::INT AS stock_quantity,

    -- Category: Diverse and futuristic
    (ARRAY['Quantum Devices','Interstellar Travel Gear','Mythical Artifacts','Dream & Consciousness','Bio-Luminescent Flora','Digital Detox','Temporal Curiosities','Neural Enhancements','Aetheric Tools'])[floor(random()*9)+1] AS category,

    -- Manufacturer: Fictional, quirky company names
    CASE floor(random() * 6)
        WHEN 0 THEN 'Aether Dynamics Inc.'
        WHEN 1 THEN 'Chronos Laboratories'
        WHEN 2 THEN 'Nebula Weavers Corp.'
        WHEN 3 THEN 'Bio-Sculpt Industries'
        WHEN 4 THEN 'DreamScape Innovations'
        ELSE 'Universal Paradox Co.'
    END AS manufacturer,

    -- Release Date: Random date within the last 5 years
    (NOW() - (random() * (INTERVAL '5 year')))::DATE AS release_date,

    -- Is Active: 90% active, 10% inactive
    (random() < 0.9) AS is_active,

    -- Weight (kg): Random between 0.05 and 50.00 kg
    (random() * 49.95 + 0.05)::NUMERIC(8, 3) AS weight_kg,

    -- Dimensions (cm): Random string in "LxWxH cm" format
    floor(random() * 100 + 1)::TEXT || 'x' || floor(random() * 50 + 1)::TEXT || 'x' || floor(random() * 30 + 1)::TEXT || ' cm',

    -- Rating Average: Random between 2.5 and 5.0, in 0.1 increments
    (floor(random() * 26) / 10.0 + 2.5)::NUMERIC(2, 1) AS rating_avg,

    -- Review Count: Random between 0 and 1000
    floor(random() * 1001)::INT AS review_count,

    -- Material: Creative and exotic
    (ARRAY['Quantum Alloy','Bio-Polymer','Chrono-Crystal','Enchanted Wood','Astral Silk','Void Iron','Dreamweave Composite','Sonic Ceramic','Neural Fiber'])[floor(random()*9)+1] AS material,

    -- Warranty Months: Random between 6 and 60 months (0.5 to 5 years)
    floor(random() * 55 + 6)::INT AS warranty_months
FROM
    generate_series(1, 500); -- Generates 50 rows of data

-- Optional: Update last_updated_at for a few random products to simulate recent activity
UPDATE new_products
SET last_updated_at = NOW() - (random() * (INTERVAL '1 month'))
WHERE product_id IN (SELECT product_id FROM new_products ORDER BY random() LIMIT 10);



-- Add a trigger to automatically update the 'updated_at' column in 'new_orders'
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_order_timestamp
BEFORE UPDATE ON new_orders
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();