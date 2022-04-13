create table type(
	id serial primary key,
	name varchar(50)
);

create table product(
	id serial primary key, 
	name varchar(150), 
	type_id int REFERENCES type(id), 
	expired_date date, 
	price float
);

insert into type(name) values('сыр'), ('мороженое'), ('молоко');

insert into product(name, type_id, expired_date, price) 
values
	('Сыр плавленный', 1, '2022-4-25', 170.62),
	('Сыр моцарелла', 1, '2022-4-20', 473),
	('Сыр литовский', 1, '2022-3-19', 110),
	('Мечта сластены', 2, '2022-4-19', 75),
	('Из кореновки', 2, '2022-4-21', 80),
	('Ленинградское мороженое', 2, '2022-4-1', 55),
	('Простоквашино', 3, '2022-4-22', 98.77),
	('Домик в деревне', 3, '2022-4-10', 54)
;
insert into product(name, type_id, expired_date, price) values ('Сыр пошехонский', 1, '2022-4-29', 473);


/*Только сыры*/
select 
	p.name
from 
	product as p 
	join type as t
		on p.type_id = t.id
where
	t.name like 'сыр'
;

/*В названии встречается 'мороженое'*/
select * from product where name like '%морож%';

/*с истекшим сроком*/
select * from product where expired_date < now();

/*самый дорогой*/
select 
	p.name, 
	p.price 
from 
	product as p 
where 
	p.price = (select max(pr.price) from product as pr);

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
select 
	p.name
from 
	product as p 
	join type as t
		on p.type_id = t.id
where
	t.name like 'сыр' or t.name like 'мороженое'
;

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


