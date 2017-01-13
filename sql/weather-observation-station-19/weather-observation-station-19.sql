-- https://www.hackerrank.com/challenges/weather-observation-station-19

DROP TABLE IF EXISTS station;

CREATE TABLE station(
  id SERIAL NOT NULL PRIMARY KEY,
  city TEXT NOT NULL,
  lat_n REAL NOT NULL,
  long_w REAL NOT NULL
);

INSERT INTO station (city, lat_n, long_w) VALUES
  ('Moscow', 10.323, 19.3209),
  ('Moscow', 4.345234, 14.4345),
  ('Moscow', 11.5438, 17.9834),
  ('Moscow', 8.324232, 10.9403);


SELECT sqrt((d - b) * (d - b) + (c - a) * (c - a))
FROM
  (SELECT min(lat_n) a, max(lat_n) b, min(long_w) c, max(long_w) d
  FROM station) sq;