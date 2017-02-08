-- https://www.hackerrank.com/challenges/symmetric-pairs

CREATE TABLE pairs(
  id SERIAL,
  x INT NOT NULL,
  y INT NOT NULL
);

INSERT INTO pairs (x, y) VALUES
  (20, 20),
  (20, 20),
  (20, 21),
  (23, 22),
  (22, 23),
  (21, 20)
;

INSERT INTO pairs (x, y) VALUES
  (20, 20),
  (20, 20),
  (20, 21),
  (21, 20),
  (29, 29)
;

DELETE FROM pairs;

DROP TABLE pairs;


SELECT p1.x, p1.y
FROM pairs p1
JOIN pairs p2 ON p1.x = p2.y AND p1.y = p2.x AND p1.id != p2.id -- don't need to include the same row
ORDER BY p1.x, p1.y -- get all symmetric pairs twice
