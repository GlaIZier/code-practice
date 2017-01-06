CREATE TABLE employer(
  id SERIAL NOT NULL PRIMARY KEY,
  last_name TEXT NOT NULL
);

CREATE TABLE child (
  id SERIAL NOT NULL PRIMARY KEY,
  last_name TEXT NOT NULL
);

CREATE TABLE employer_child (
  id SERIAL NOT NULL PRIMARY KEY,
  employer_id INT NOT NULL,
  child_id INT NOT NULL,
  CONSTRAINT fk_employer_child_employer
    FOREIGN KEY (employer_id)
    REFERENCES employer (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_employer_child_child
    FOREIGN KEY (child_id)
    REFERENCES child (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO employer (last_name) VALUES
  ('A'),
  ('B'),
  ('C')
;

INSERT INTO child (last_name) VALUES
  ('B'),
  ('Z'),
  ('Y')
;

INSERT INTO employer_child (employer_id, child_id) VALUES
  (2, 1)
;

SELECT employer.id, employer.last_name, child.id, child.last_name
FROM employer
LEFT JOIN employer_child ON employer.id = employer_child.employer_id
LEFT JOIN child ON employer_child.child_id = child.id;

SELECT employer.id, employer.last_name, child.id, child.last_name
FROM employer
  LEFT JOIN employer_child
    JOIN child ON employer_child.child_id = child.id
  ON employer.id = employer_child.employer_id