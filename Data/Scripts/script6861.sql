
create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('fish', 45, '2020-1-1')
insert into fauna(name, avg_age, discovery_date) values('fish1', 45, '2020-1-1');
insert into fauna(name, avg_age, discovery_date) values('fish2', 45, '1980-7-1');
insert into fauna(name, avg_age, discovery_date) values('cat', 8, '2000-8-1');
insert into fauna(name, avg_age, discovery_date) values('fish3', 11000, '1920-4-1');
insert into fauna(name, avg_age, discovery_date) values('dog', 12, '2004-6-1');
insert into fauna(name, avg_age, discovery_date) values('fish4', 45, '2008-2-1');
insert into fauna(name, avg_age) values('fish5', 18000);

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is NULL;
select * from fauna where discovery_date < '1950-1-1';
