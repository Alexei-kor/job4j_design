drop table autos;
drop table models;
drop table owners;

/* МОДЕЛИ */
CREATE TABLE models (
	id serial PRIMARY KEY,
	name TEXT,
	generation INT,
	dataBegin date
);
INSERT INTO models(name, generation, databegin) VALUES('жигули', 1, '1976-1-1');
INSERT INTO models(name, generation, databegin) VALUES('самара', 2, '2004-1-1');
INSERT INTO models(name, generation, databegin) VALUES('мерседес', 5, '2020-1-1');
INSERT INTO models(name, generation, databegin) VALUES('ауди', 6, '2022-1-1');
INSERT INTO models(name, generation, databegin) VALUES('хонда', 4, '2008-1-1');

/* ВЛАДЕЛЬЦЫ */
create TABLE owners(
	id serial primary key,
	name varchar(255)
);
insert into owners("name") values ('Иванов И И');
insert into owners("name") values ('Петров П П');
insert into owners("name") values ('Сидоров С С');

/* ВЛАДЕЛЬЦЫ МАШИН */
create table autos(
	id serial primary key,
	id_m int REFERENCES public.models(id),
	id_o int references public.owners(id)
);

insert into autos(id_m, id_o) values (1, 1);
insert into autos(id_m, id_o) values (1, 2);
insert into autos(id_m, id_o) values (1, 3);
insert into autos(id_m, id_o) values (2, 1);
insert into autos(id_m, id_o) values (2, 2);
insert into autos(id_m, id_o) values (2, 3);
insert into autos(id_m, id_o) values (3, 3);
insert into autos(id_m, id_o) values (4, 2);
insert into autos(id_m, id_o) values (5, 1);

select 
	mod_.name as Модель, 
	ow.name as Владелец 
from 
	autos
	join models as mod_ 
		on autos.id_m = mod_.id
	join owners as ow
		on autos.id_o = ow.id
;

select 
	mod_.name as Модель,
	sum(autos.id) as id
from 
	autos
	join models as mod_ 
		on autos.id_m = mod_.id
where 
	mod_.databegin > '2015-1-1'
group by 	
	mod_.name
;

select
	ow.name as Владелец,
	avg(autos.id) as id
from 
	autos
	join owners as ow
		on autos.id_o = ow.id
where
	ow.name like '%р%'
group by
	ow.name
order by Владелец desc
;
