/*
create table type(
	id serial primary key,
	name varchar(50)
);

create table product(
	id serial primary key, 
	name varchar(150), 
	type_id int REFERENCES type(id), 
	expired_date boolean, 
	price float
);

insert into type(name) values('сыр'), ('мороженое'), ('молоко');

insert into product(name, type_id, expired_date, price) 
values
	('Сыр плавленный', 1, false, 170.62),
	('Сыр моцарелла', 1, false, 473),
	('Сыр литовский', 1, true, 110),
	('Мечта сластены', 2, false, 75),
	('Из кореновки', 2, false, 80),
	('Ленинградское мороженое', 2, true, 55),
	('Простоквашино', 3, false, 98.77),
	('Домик в деревне', 3, true, 54)
;
*/

/*Только сыры*/
select * from product where type_id=1;

/*В названии встречается 'мороженое'*/
select * from product where name like '%морож%';

/*с истекшим сроком*/
select * from product where expired_date;

/*самый дорогой*/
select * from product order by price desc LIMIT 1;

/*количество типов продуктов*/
select 
	t.name, 
	count(p.name) 
from 
	product as p 
	join type as t
		on p.type_id = t.id
group by 
	t.name
;

/*Сыры и молоко*/
select * from product where type_id=1 or type_id = 3;

/*типы продуктов, разновидностей которых < 10*/
select 
	t.name, 
	count(p.name) 
from 
	product as p 
	join type as t
		on p.type_id = t.id
group by 
	t.name
HAVING count(p.name) < 10
;

/*все продукты и их тип*/
select 
	p.name as product,
	t.name as type 
from 
	product as p 
	join type as t
		on p.type_id = t.id
order by type, product
;
	

