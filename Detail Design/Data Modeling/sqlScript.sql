create table transaction (
	transaction_id INT NOT NULL AUTO_INCREMENT,
    card_id VARCHAR(64) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount INT NOT NULL,
    order_id int not null,
    command VARCHAR(16) NOT NULL,
    content VARCHAR(255),
    PRIMARY KEY (transaction_id)
);

create table rental_orders (
	order_id INT NOT NULL AUTO_INCREMENT,
    card_id VARCHAR(64) NOT NULL,
    bike_id VARCHAR(32) NOT NULL,
    start_time DATETIME,
    return_time DATETIME,
    rental_fees int,
    is_return BOOL default FALSE,
    PRIMARY KEY (order_id)
);
create table bike (
    bike_id VARCHAR(32) NOT NULL,
    station_id VARCHAR(32) NOT NULL,
    bike_name VARCHAR(64),
    bike_type INT NOT NULL,
    available BOOLEAN NOT NULL,
    bike_description VARCHAR(500),
    img_path VARCHAR(255),
    PRIMARY KEY (bike_id)
);
create table station (
    station_id VARCHAR(32) NOT NULL,
    station_name VARCHAR(64),
    dock_no int not null,
    free_dock int not null,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (station_id)
);
create table card(
	card_id VARCHAR(64) NOT NULL,
    card_holder_name VARCHAR(50) NOT NULL,
    date_expired LONG NOT NULL,
    cvv_code varchar(10),
    PRIMARY KEY (card_id)
);

ALTER TABLE transaction
ADD FOREIGN KEY (card_id) REFERENCES card(card_id);
ALTER TABLE bike
ADD FOREIGN KEY (station_id) REFERENCES station(station_id);
ALTER TABLE rental_orders
ADD FOREIGN KEY (bike_id) REFERENCES bike(bike_id);
ALTER TABLE transaction
ADD FOREIGN KEY (order_id) REFERENCES rental_orders(order_id);
