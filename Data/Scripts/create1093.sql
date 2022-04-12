create table users(
	id serial primary key,
	name varchar(150)
);

create table roles(
	id serial primary key,
	name varchar(150)
);

create table roleusers(
	id serial primary key,
	id_u int REFERENCES users(id),
	id_r int REFERENCES roles(id)
);

create table rules(
	id serial primary key,
	name varchar(150)
);

create table rulesforroles(
	id serial primary key,
	id_rul int REFERENCES rules(id),
	id_rol int REFERENCES roles(id)
);

create table category(
	id serial primary key,
	name varchar(50)
);

create table states(
	id serial primary key,
	name varchar(50)
);

create table items(
	id serial primary key,
	dateItm date,
	id_usr int REFERENCES users(id),
	id_cat int REFERENCES category(id),
	id_sta int REFERENCES states(id)	
);


create table rolesfromitems(
	id serial primary key,
	id_itm int REFERENCES items(id),
	id_rol int REFERENCES roles(id)
);

create table commentsitems(
	id serial primary key,
	id_itm int REFERENCES items(id),
	comment_ text
);

create table attachs(
	id serial primary key,
	scan bytea
);

create table attachsitems(
	id serial primary key,
	id_itm int REFERENCES items(id),
	id_att int REFERENCES attachs(id)
);
