insert into roles(name) values ('Администратор');
insert into roles(name) values ('Кладовщик');
insert into roles(name) values ('Менеджер');

insert into users(name, id_rol) values ('Иванов И И', 1);
insert into users(name, id_rol) values ('Петров П П', 2), ('Петров П П', 3);
insert into users(name, id_rol) values ('Сидоров С С', 3);

insert into rules(name) values ('Полные права');
insert into rules(name) values ('Чтение приходного ордера');
insert into rules(name) values ('Добавление/изменение приходного ордера');
insert into rules(name) values ('Чтение заказов клиентов');
insert into rules(name) values ('Добавление/изменение заказов клиентов');

insert into rulesforroles(id_rul, id_rol) values (1, 1);
insert into rulesforroles(id_rul, id_rol) values (2, 2);
insert into rulesforroles(id_rul, id_rol) values (3, 2);
insert into rulesforroles(id_rul, id_rol) values (4, 3);
insert into rulesforroles(id_rul, id_rol) values (5, 3);

insert into category(name) values ('Срочная');
insert into category(name) values ('Обычная');

insert into states(name) values ('Черновик');
insert into states(name) values ('На согласовании');
insert into states(name) values ('Согласована');
insert into states(name) values ('Отклонена');

insert into items(dateItm, id_usr, id_cat, id_sta) values ('2022-4-1', 1, 1, 1);
insert into items(dateItm, id_usr, id_cat, id_sta) values ('2022-4-2', 2, 2, 2);
insert into items(dateItm, id_usr, id_cat, id_sta) values ('2022-3-2', 2, 1, 3);
insert into items(dateItm, id_usr, id_cat, id_sta) values ('2022-4-3', 3, 1, 2);

insert into commentsitems(id_itm, comment_) values (3, 'Замечаний нет');
insert into commentsitems(id_itm, comment_) values (4, 'Состав запрашиваемых ролей не соответствует должностным обязанностям');

insert into attachs(scan, id_itm) values(pg_read_file('C:/projects/job4j_design/Data/text.txt')::bytea, 1);
