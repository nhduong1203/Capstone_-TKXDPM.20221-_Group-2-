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

insert into station (station_id, station_name, dock_no, free_dock, address) values ('S001', 'EcoBike Orsay', 30, 21, '15, Rue Georges Clemenceau 91400, Orsay, France');
insert into station (station_id, station_name, dock_no, free_dock, address) values ('S002', 'EcoBike 91120 Palaiseau', 30, 21, 'Route de Saclay, 91120 Palaiseau, France');
insert into station (station_id, station_name, dock_no, free_dock, address) values ('S003', 'EcoBike 75005 Paris', 30, 21, '45 Rue d Ulm, 75005 Paris, France');
insert into station (station_id, station_name, dock_no, free_dock, address) values ('S004', 'EcoBike 75009 Paris', 30, 21, 'Place de Opera, 75009 Paris, France');
insert into station (station_id, station_name, dock_no, free_dock, address) values ('S005', 'EcoBike 75007 Paris', 30, 21, 'Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France');
insert into station (station_id, station_name, dock_no, free_dock, address) values ('S006', 'EcoBike 75001 Paris', 30, 21, 'Rue de Rivoli, 75001 Paris, France');
insert into station (station_id, station_name, dock_no, free_dock, address) values ('S007', 'EcoBike 75004 Paris', 30, 21, '6 Parvis Notre-Dame - Pl. Jean-Paul II, 75004 Paris, France');
insert into station (station_id, station_name, dock_no, free_dock, address) values ('S008', 'EcoBike 78000 Versailles', 30, 21, 'Place d Armes, 78000 Versailles, France');

insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB001', 'S001', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB002', 'S001', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB003', 'S001', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB004', 'S002', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB005', 'S002', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB006', 'S002', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB007', 'S003', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB008', 'S003', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('RB009', 'S003', 'Road Bike', 1, TRUE, 'The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.', 'img/bike/road_bikes/demo.png');

insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB001', 'S001', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB002', 'S001', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB003', 'S001', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB004', 'S002', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB005', 'S002', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB006', 'S002', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB007', 'S003', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB008', 'S003', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('TB009', 'S003', 'Tandem Bike', 2, TRUE, 'The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.', 'img/bike/tandem_bikes/demo.png');

insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB001', 'S001', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB002', 'S001', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB003', 'S001', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB004', 'S002', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB005', 'S002', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB006', 'S002', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB007', 'S003', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB008', 'S003', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');
insert into bike (bike_id, station_id, bike_name, bike_type, available, bike_description, img_path) values ('ETB009', 'S003', 'Electric Tandem Bike', 3, TRUE, 'The behavioral design pattern are design patterns that identify common communication patterns among objects. By doing so, these patterns increase flexibility in carrying out communication.', 'img/bike/e_tandem_bikes/demo.png');


