
CREATE TABLE models1 (
	id serial PRIMARY KEY,
	name TEXT,
	generation INT,
	dataBegin text
);
INSERT INTO models1(name, generation, databegin) VALUES('жигули', 1, '1976');
INSERT INTO models1(name, generation, databegin) VALUES('самара', 2, '2004');
UPDATE models1 SET generation=3;
SELECT * FROM models1
