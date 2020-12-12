BEGIN
INSERT INTO LECTOR(name, surname, degree, salary) VALUES ('Oleg', 'Filipenko', 'Professor', 8000),('Ivan', 'Petrov', 'Professor', 10000),('Petro', 'Ivanenko', 'Assistant', 4000),('Vasyl', 'Skachko', 'Associate professor', 6000);
INSERT INTO DEPARTMENT(department_name) VALUES ('Philology'),('Mathematical');
COMMIT
