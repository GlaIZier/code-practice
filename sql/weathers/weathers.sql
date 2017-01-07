-- - Напишите SQL запрос для выбора id всех записей,
-- температура в которых была выше, чем в предыдущий день (чем вчера).

CREATE TABLE weather(
  id SERIAL NOT NULL PRIMARY KEY,
  date DATE NOT NULL,
  temperature INT NOT NULL
);

INSERT INTO weather (date, temperature) VALUES
  ('2017-01-01', 10),
  ('2017-01-02', 25),
  ('2017-01-03', 20),
  ('2017-01-04', 30);

SELECT w1.*
FROM weather w1
JOIN weather w2 ON w1.id = w2.id + 1
WHERE w1.temperature > w2.temperature;