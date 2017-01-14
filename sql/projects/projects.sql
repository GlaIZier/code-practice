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

SELECT project_start_date, min(project_end_date) project_end_date
-- got the same several start date for different project_end_dates but we need only min end_date
FROM (
  SELECT
    t1.start_date AS project_start_date,
    t2.start_date AS null_date,
    project_end_date,
    null_date_2
  FROM task t1
    -- where null then it is a start date of project
    LEFT JOIN task t2 ON t1.start_date = t2.start_date + 1
    JOIN
    (SELECT
       t3.end_date project_end_date,
       t4.end_date null_date_2
     FROM task t3
       -- where null it is end_date of project
       LEFT JOIN task t4 ON t3.end_date + 1 = t4.end_date) end_dates_nulls_sq
      -- join these tables on got null and save results where start_date < end_date
      ON (t2.start_date IS NULL AND null_date_2 IS NULL AND t1.start_date < project_end_date)
) project_dates_null_sq
GROUP BY project_start_date
ORDER BY (min(project_end_date) - project_start_date) DESC, project_start_date ASC;


-- got right result but in one column not two
SELECT project_date
FROM
  (
    SELECT
      -- where null then it is a start date of project
      t1.start_date as project_date,
      t2.start_date as null_date
    FROM task t1
      LEFT JOIN task t2 ON t1.start_date = t2.start_date + 1
    UNION ALL
    -- where null it is end_date of project
    SELECT
      t1.end_date,
      t2.end_date
    FROM task t1
      LEFT JOIN task t2 ON t1.end_date + 1 = t2.end_date
  ) sq
WHERE null_date IS NULL
ORDER BY project_date;