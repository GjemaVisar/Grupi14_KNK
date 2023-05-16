create table sales(
	id int not null auto_increment,
    user_id int not null,
    car_id int not null,
    data_blerjes date not null,
    price real not null,
    primary key(id),
    foreign key(user_id) references users(id),
	foreign key(car_id) references cars(numri_serik)
);
