
create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values('Петя'), ('Саша'), ('Марина'), ('Татьяна');
insert into devices(name, price) values('Mobile phone', 20560.33), ('Computer', 85983.77), ('Pleer', 3500), ('Foto', 23566);
insert into devices_people(device_id, people_id) values(1, 1), (1, 3), (2, 2), (3, 1), (3, 4), (3, 3), (4, 2);

/*Средняя цена устройств*/
select 
	d.name as device, 
	avg(d.price) as avg_price 
from 
	devices as d 
group by 
	d.name		
order by device
;

/*Средняя цена устройств пользователей > 5000*/
select 
	p.name as fio, 
	avg(d.price) as avg_price
from 
	devices_people as dp 
	join people as p 
		on dp.people_id = p.id
	join devices as d 
		on dp.device_id = d.id 
group by
	p.name
having 
	avg(d.price) > 5000
order by fio
;