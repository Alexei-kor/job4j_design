/*
create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	id_bod int REFERENCES body(id),
	id_eng int REFERENCES engine(id),
	id_tra int REFERENCES transmission(id)
);


insert into body(name) values('седан'), ('универсал'), ('хетчбек'), ('купе'), ('пикап'), ('минивэн');

insert into engine(name) values('бензиновый'), ('дизельный'), ('роторный');

insert into transmission(name) values('механическая'), ('автоматическая'), ('роботизированная');

insert into car(name, id_bod, id_eng, id_tra) values('жигули', 1, 1, 1),
	('девятка', 3, 1, 1),
	('приора', 2, 1, 2),
	('мерседес', 4, 2, 2),
	('фиат', 1, 2, 1),
	('шкода', 2, 1, 2),
	('уаз патриот', 5, 1, 1)	
;
*/
select 
	c.name as car,
	b.name as body,
	e.name as engine,
	t.name as transmission	
from 
	car as c
	left join body as b
		on c.id_bod = b.id
	left join engine as e
		on c.id_eng = e.id
	left join transmission as t
		on c.id_tra = t.id	
;

/*неиспользуемые кузова*/
select 
	b.name as body
from 
	body as b
	left join car as c 
		on b.id = c.id_bod
where
	c.name is null
;

/*неиспользуемые двигателя*/
select 
	e.name as engine
from 
	engine as e
	left join car as c 
		on e.id = c.id_eng
where
	c.name is null
;

/*неиспользуемые коробки передач*/
select 
	t.name as transmission
from 
	transmission as t
	left join car as c 
		on t.id = c.id_tra
where
	c.name is null
;