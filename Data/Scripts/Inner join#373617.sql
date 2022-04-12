/* МОДЕЛИ */
CREATE TABLE models (
	id serial PRIMARY KEY,
	name TEXT,
	generation INT,
	dataBegin text
);
INSERT INTO models(name, generation, databegin) VALUES('жигули', 1, '1976');
INSERT INTO models(name, generation, databegin) VALUES('самара', 2, '2004');

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

select distinct
	mod_.name as Модель 	 
from 
	autos
	join models as mod_ 
		on autos.id_m = mod_.id
;

select distinct
	ow.name as Владелец 
from 
	autos
	join owners as ow
		on autos.id_o = ow.id
order by Владелец desc
;