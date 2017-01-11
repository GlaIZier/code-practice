-- https://www.hackerrank.com/challenges/occupations
CREATE TABLE occupation(
  name TEXT NOT NULL,
  occupation TEXT NOT NULL
);

INSERT INTO occupation (name, occupation) VALUES
  ('A', 'Do'),
  ('B', 'Ac'),
  ('C', 'Ac'),
  ('D', 'Si'),
  ('E', 'Pr'),
  ('F', 'Pr'),
  ('G', 'Pr'),
  ('H', 'Ac'),
  ('I', 'Do'),
  ('J', 'Si');

SELECT
  CASE occupation WHEN 'Do' THEN name END as d,
  CASE occupation WHEN 'Pr' THEN name END as p,
  CASE occupation WHEN 'Si' THEN name END as s,
  CASE occupation WHEN 'Ac' THEN name END as a
FROM occupation;

SELECT a.occupation, d
FROM
  (
    SELECT
      occupation,
      CASE occupation WHEN 'Do' THEN name END as d,
      CASE occupation WHEN 'Pr' THEN name END as p,
      CASE occupation WHEN 'Si' THEN name END as s,
      CASE occupation WHEN 'Ac' THEN name END as a
    FROM occupation
  ) a
GROUP BY occupation;