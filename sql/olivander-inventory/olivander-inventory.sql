-- https://www.hackerrank.com/challenges/harry-potter-and-wands
CREATE TABLE wand_property(
  code SERIAL NOT NULL PRIMARY KEY,
  age INT NOT NULL,
  is_evil INT NOT NULL
);

CREATE TABLE wand(
  id SERIAL NOT NULL PRIMARY KEY,
  code INT NOT NULL,
  cost INT NOT NULL,
  power INT NOT NULL,
  CONSTRAINT fk_wand_wand_propery
    FOREIGN KEY (code)
    REFERENCES wand_property(code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO wand_property (age, is_evil) VALUES
  (45, 0),
  (40, 0),
  (4, 1),
  (20, 0),
  (17, 0);

INSERT INTO wand (code, cost, power) VALUES
  (4, 3688, 8),
  (3, 9365, 3),
  (3, 7187, 10),
  (3, 734, 8),
  (1, 6020, 2),
  (2, 6773, 7),
  (3, 9873, 9),
  (4, 7721, 7),
  (1, 1647, 10),
  (4, 504, 5),
  (2, 7587, 5),
  (5, 9897, 10),
  (3, 4651, 8),
  (2, 5408, 1),
  (2, 6018, 7);

-- Write a query to print the id, age, coins_needed, and power of the wands that Ron's interested in,
-- sorted in order of descending power. If more than one wand has same power, sort the result in order of descending age.
SELECT id, age, cost, power
FROM wand
JOIN wand_property ON wand.code = wand_property.code
WHERE wand_property.is_evil = 0
ORDER BY power DESC, age DESC;