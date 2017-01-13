-- https://www.hackerrank.com/challenges/weather-observation-station-20

CREATE TABLE station(
  id SERIAL NOT NULL PRIMARY KEY,
  city TEXT NOT NULL,
  lat_n REAL NOT NULL,
  long_w REAL NOT NULL
);

INSERT INTO station (city, lat_n, long_w) VALUES
  ('Moscow', 10.323, 100),
  ('Moscow', 4.345234, 100),
  ('Moscow', 11.5438, 100),
  ('Moscow', 8.324232, 100);

-- median is a value in the table that separates lower half from upper one
SELECT id, city, round(lat_n::NUMERIC, 4) as median
FROM station s
WHERE
  (SELECT count(id)
   FROM station
   WHERE station.lat_n < s.lat_n) =
  (SELECT count(id)
   FROM station
   WHERE station.lat_n > s.lat_n) - 1;


SELECT id, city, round(lat_n::NUMERIC, 4) as median
FROM
  (SELECT row_number() OVER (ORDER BY lat_n) AS row_number, id, city, lat_n
   FROM station) sq
WHERE row_number =
      (SELECT count(id) / 2 FROM station);