CREATE TABLE IF NOT EXISTS orders (
    comment_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    customer_name varchar(50) NOT NULL,
    customer_phone varchar(16) NOT NULL,
    customer_email varchar(50) NOT NULL,
    amount int NOT NULL,
    delivery_address varchar(300) NOT NULL,
    delivery_date TIMESTAMP WITHOUT TIME ZONE,
    status varchar(20) NOT NULL
    );