-- Shtimi i tabeles cars --

create table cars(
serial_num integer primary key,
car_name varchar(30) not null,
price real not null,
manufacturer varchar(30),
maxSpeed real,
yearMade integer,
carImage varchar(200) not null,
insertedOn date not null,
updatedOn date not null
);