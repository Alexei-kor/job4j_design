
CREATE TABLE models3 (
	id serial PRIMARY KEY,
	name TEXT,
	generation INT,
	dataBegin text
);
INSERT INTO models3(name, generation, databegin) VALUES('жигули', 1, '1976');
INSERT INTO models3(name, generation, databegin) VALUES('самара', 2, '2004');
UPDATE models3 SET generation=3;
SELECT * FROM models3;
DELETE FROM models3;
