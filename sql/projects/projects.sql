-- https://www.hackerrank.com/challenges/projects
DROP TABLE IF EXISTS task;

CREATE TABLE task(
  id SERIAL NOT NULL PRIMARY KEY,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL CHECK(end_date - start_date = 1)
);

INSERT INTO task (start_date, end_date) VALUES
  ('2015-10-01', '2015-10-02'),
  ('2015-10-02', '2015-10-03'),
  ('2015-10-03', '2015-10-04'),
  ('2015-10-13', '2015-10-14'),
  ('2015-10-14', '2015-10-15'),
  ('2015-10-28', '2015-10-29'),
  ('2015-10-30', '2015-10-31');
