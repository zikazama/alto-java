-- ================================================
-- Skrip SQL untuk Aplikasi Ecommerce Order System
-- ================================================

-- 1. Membuat Database
CREATE DATABASE IF NOT EXISTS ecommerce_orders
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 2. Menggunakan Database yang Telah Dibuat
USE ecommerce_orders;

-- 3. Membuat Tabel 'orders'
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 1),
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0.00),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 4. Menambahkan Indeks untuk Kolom 'product_id' dan 'user_id'
CREATE INDEX idx_product_id ON orders(product_id);
CREATE INDEX idx_user_id ON orders(user_id);

-- 5. (Opsional) Menambahkan Constraint Unik Jika Diperlukan
-- Contoh: Jika kombinasi product_id dan user_id harus unik
-- ALTER TABLE orders ADD CONSTRAINT uq_product_user UNIQUE (product_id, user_id);

-- 6. (Opsional) Menambahkan Trigger untuk Menjaga Konsistensi Data
-- Contoh: Memastikan status hanya bernilai tertentu
DELIMITER //
CREATE TRIGGER before_orders_insert
BEFORE INSERT ON orders
FOR EACH ROW
BEGIN
    IF NEW.status NOT IN ('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid status value';
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_orders_update
BEFORE UPDATE ON orders
FOR EACH ROW
BEGIN
    IF NEW.status NOT IN ('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid status value';
    END IF;
END;
//
DELIMITER ;
