-- Shtimi i tabeles cars --

create table cars(
serial_num integer primary key,
car_name varchar(30) not null,
price real not null,
manufacturer varchar(30),
max_speed real,
year_made integer,
car_image varchar(200) not null,
inserted_on date not null,
updated_on date not null
);