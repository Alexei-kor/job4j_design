create table departments(
	id serial primary key,
	name varchar(150)
);

create table employees(
	id serial primary key,
	name varchar(150),
	id_dep int REFERENCES departments(id)
);

insert into departments(name) VALUES('главное'), ('вспомогательное'), ('не нужное');

insert into employees(name, id_dep) VALUES('Иванов', 1), ('Петров', 1), ('Сидоров', 2);
insert into employees(name) VALUES('Николаев');

select
	e.name as fio,
	d.name as dep
from
	employees as e
	left join departments as d
		on e.id_dep = d.id
;

select
	e.name as fio,
	d.name as dep
from
	employees as e
	right join departments as d
		on e.id_dep = d.id
;

select
	e.name as fio,
	d.name as dep
from
	employees as e
	full join departments as d
		on e.id_dep = d.id
;

select
	e.name as fio,
	d.name as dep
from
	employees as e
	cross join departments as d		
;

/*В департаментах нет работников*/
select distinct
	d.name as dep	
from
	departments as d
	left join employees as e
		on d.id = e.id_dep
where
	e.name is null
;

/*Одинаковый результат*/
select
	'левое' as join,
	e.name as fio,
	d.name as dep
from
	employees as e
	left join departments as d
		on e.id_dep = d.id
union all
select
	'правое',
	e.name,
	d.name
from
	departments as d
	right join employees as e
		on d.id = e.id_dep
;

/*teens*/
create table teens(
	id serial primary key,
	name varchar(150),
	gender varchar(3)
);

insert into teens(name, gender) VALUES('Иванов', 'муж'), ('Петров', 'муж'), ('Сидоров', 'муж'), ('Николаев', 'муж'), ('Иванова', 'жен'), ('Петрова', 'жен'), ('Сидорова', 'жен'), ('Николаева', 'жен');

select
	t1.name as name1,
	t2.name as name2
from
	teens as t1
	cross join teens as t2
where
	t1.gender != t2.gender
	and t1.gender = 'муж'
;