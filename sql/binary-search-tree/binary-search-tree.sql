-- https://www.hackerrank.com/challenges/binary-search-tree-1
CREATE TABLE bst(
  n INT NOT NULL,
  p INT
);

INSERT INTO bst (n, p) VALUES
  (1, 2),
  (3, 2),
  (6, 8),
  (9, 8),
  (2, 5),
  (8, 5),
  (5, NULL);

SELECT
  n,
  CASE
    WHEN (p IS NULL) THEN 'root'
    WHEN (n NOT IN (SELECT p FROM bst WHERE p IS NOT NULL)) THEN 'leaf'
    ELSE 'inner'
  END
FROM bst
ORDER BY n;

SELECT
  n,
  CASE
    WHEN (p IS NULL) THEN 'root'
    WHEN (exists(SELECT p FROM bst WHERE p = b.n)) THEN 'inner'
    ELSE 'leaf'
  END
FROM bst as b
ORDER BY n;