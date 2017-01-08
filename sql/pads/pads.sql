-- https://www.hackerrank.com/challenges/the-pads

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

SELECT name || '(' || substring(o1.occupation from 1 for 1) || ')'
FROM occupation o1
UNION ALL
(
SELECT 'There are ' || count(o2.occupation) || ' ' || o2.occupation || 's'
FROM occupation o2
GROUP BY o2.occupation
ORDER BY count(o2.occupation), o2.occupation
);
