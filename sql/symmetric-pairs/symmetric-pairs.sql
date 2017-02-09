-- https://www.hackerrank.com/challenges/symmetric-pairs

CREATE TABLE pairs (
  id SERIAL,
  x  INT NOT NULL,
  y  INT NOT NULL
);

INSERT INTO pairs (x, y) VALUES
  (20, 20),
  (20, 20),
  (20, 21),
  (23, 22),
  (22, 23),
  (21, 20);

INSERT INTO pairs (x, y) VALUES
  (20, 21),
  (21, 20),
  (20, 20),
  (20, 20),
  (29, 29);

DELETE FROM pairs;

DROP TABLE pairs;


SELECT
  x,
  y
FROM
  (SELECT -- get all points twice so need to exclude half
     row_number()
     OVER (
       ORDER BY p1.x, p1.y) AS row_number,
     p1.x x,
     p1.y y
   FROM pairs p1
     JOIN pairs p2 ON p1.x = p2.y AND p1.y = p2.x AND p1.id != p2.id) sq -- don't need to include the same row;
WHERE row_number % 2 = 0;


-- solution from site
select f1.x, f1.y from pairs as f1
  inner join pairs as f2 on f2.y = f1.x
where f1.y >= f1.x and f2.x = f1.y
group by f1.x, f1.y
having count(*) > 1 or (count(*) = 1 and f1.y != f1.x)
order by f1.x