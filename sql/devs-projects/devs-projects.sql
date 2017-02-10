-- 4. select all users who has been involved in all projects.

DROP TABLE dev;
DROP TABLE project;
DROP TABLE dev_project;

CREATE TABLE dev(
  id SERIAL NOT NULL PRIMARY KEY,
  last_name TEXT NOT NULL
);

CREATE TABLE project (
  id SERIAL NOT NULL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE dev_project (
  id SERIAL NOT NULL PRIMARY KEY,
  dev_id INT NOT NULL,
  project_id INT NOT NULL,
  CONSTRAINT fk_dev_dev_project
  FOREIGN KEY (dev_id)
  REFERENCES dev (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT fk_dev_project_project
  FOREIGN KEY (project_id)
  REFERENCES project (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
);

INSERT INTO dev (last_name) VALUES
  ('A'),
  ('B'),
  ('C')
;

INSERT INTO project (name) VALUES
  ('X'),
  ('Y'),
  ('Z')
;

INSERT INTO dev_project (dev_id, project_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 1),
  (3, 2)
;

SELECT dev.last_name, project.name
FROM dev
JOIN dev_project ON dev.id = dev_project.dev_id
JOIN project ON dev_project.project_id = project.id;

SELECT *
FROM dev
WHERE id IN
      (SELECT id
      FROM (
        SELECT dev.id, count(project.name)
        FROM dev
        JOIN dev_project ON dev.id = dev_project.dev_id
        JOIN project ON dev_project.project_id = project.id
        GROUP BY dev.id
        HAVING count(project.name) =
               (SELECT count(name)
                FROM project)
      ) t1);

-- Gleb's solution
select * from dev d
where not exists (
    -- тут мы выбираем проект, которого нет на разработчике d.id
    select 1 from project p
    where not exists (
        select 1 from dev_project dp
        where dp.project_id = p.id
              and dp.dev_id = d.id
    )
);

-- explain queries for Gleb's solution
-- exists check solution for every row if u have link to external query in condition
SELECT * FROM dev d
WHERE exists(
  SELECT 1 FROM dev dd
  WHERE dd = d);

-- get all projects that are not existed in dev_project table
select 1 from project p
where not exists (
    select 1 from dev_project dp
    where dp.project_id = p.id);

-- get all projects that are not existed for dev with id = 1
select 1 from project p
where not exists (
    select 1 from dev_project dp
    where dp.project_id = p.id
      and dp.dev_id = 1);