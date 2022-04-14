
/* VINs */
create table vins(
	id serial primary key,
	vin varchar(20)
);

/* transmission */
create table transmission(
	id serial primary key,
	name varchar(50)
);

/* МОДЕЛИ */
CREATE TABLE models (
	id serial PRIMARY KEY,
	name TEXT,
	generation INT,
	dataBegin text,
	id_tr int REFERENCES transmission(id),
	vin_id int REFERENCES vins(id) unique
);

/* ВЛАДЕЛЬЦЫ */
create TABLE owners(
	id serial primary key,
	name varchar(255)
);

/* ВЛАДЕЛЬЦЫ МАШИН */
create table autos(
	id serial primary key,
	id_m int REFERENCES public.models(id),
	id_o int references public.owners(id)
);

INSERT INTO vins(vin) VALUES('54s5dfsfd565dfsdf'), ('548werwerds35df');
INSERT INTO transmission(name) VALUES('механическая'), ('автоматическая');

INSERT INTO models(name, generation, databegin, id_tr, vin_id) VALUES('жигули', 1, '1976', 1, 1);
INSERT INTO models(name, generation, databegin, id_tr, vin_id) VALUES('самара', 2, '2004', 2, 2);

insert into owners("name") values ('Иванов И И');
insert into owners("name") values ('Петров П П');
insert into owners("name") values ('Сидоров С С');

insert into autos(id_m, id_o) values (1, 1);
insert into autos(id_m, id_o) values (1, 2);
insert into autos(id_m, id_o) values (1, 3);
insert into autos(id_m, id_o) values (2, 1);
insert into autos(id_m, id_o) values (2, 2);
insert into autos(id_m, id_o) values (2, 3);

