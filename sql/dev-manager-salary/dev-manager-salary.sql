-- Напишите SQL запрос который найдет имена всех работников,
-- которые получают больше чем их менеджеры.

CREATE TABLE employee(
  id SERIAL NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  salary money NOT NULL,
  manager_id INT
);

INSERT INTO employee (name, salary, manager_id) VALUES
  ('A', 70000, 3),
  ('B', 80000, 4),
  ('C', 60000, NULL),
  ('D', 90000, NULL);

SELECT e1.*
FROM employee e1
JOIN employee e2 ON e1.manager_id = e2.id
WHERE e1.salary > e2.salary;