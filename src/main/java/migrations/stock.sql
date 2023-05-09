-- Tabela e stockut --

create table stock(
numri_serik integer primary key,
c_name varchar(30) not null,
car_model varchar(30),
price_c real not null,
color varchar(30),
max_speed real,
year_c integer,
car_image varchar(200) not null,
inserted_on date not null,
updated_on date not null
);
