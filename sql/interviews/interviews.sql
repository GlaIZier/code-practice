-- https://www.hackerrank.com/challenges/interviews

CREATE TABLE contest (
  contest_id SERIAL NOT NULL PRIMARY KEY,
  hacker_id  INT    NOT NULL,
  name       TEXT   NOT NULL
);

CREATE TABLE college (
  college_id SERIAL NOT NULL PRIMARY KEY,
  contest_id INT    NOT NULL,
  CONSTRAINT fk_college_contest
  FOREIGN KEY (contest_id)
  REFERENCES contest (contest_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

CREATE TABLE challenge (
  challenge_id SERIAL NOT NULL PRIMARY KEY,
  college_id   INT    NOT NULL,
  CONSTRAINT fk_challenge_college
  FOREIGN KEY (college_id)
  REFERENCES college (college_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

DROP TABLE view;

CREATE TABLE view (
  challenge_id INT NOT NULL,
  views        INT NOT NULL,
  unique_views INT NOT NULL
  --   CONSTRAINT fk_view_challenge
  --   FOREIGN KEY (challenge_id)
  --   REFERENCES challenge(challenge_id)
  --   ON UPDATE NO ACTION
  --   ON DELETE NO ACTION
);

DROP TABLE submission;

CREATE TABLE submission (
  challenge_id         INT NOT NULL,
  submissions          INT NOT NULL,
  accepted_submissions INT NOT NULL
  --   CONSTRAINT fk_submission_challenge
  --   FOREIGN KEY (challenge_id)
  --   REFERENCES challenge(challenge_id)
  --   ON UPDATE NO ACTION
  --   ON DELETE NO ACTION
);

INSERT INTO contest (contest_id, hacker_id, name) VALUES
  (66406, 17973, 'Rose'),
  (66556, 79153, 'Angela'),
  (94828, 80275, 'Frank');

INSERT INTO college (college_id, contest_id) VALUES
  (11219, 66406),
  (32473, 66556),
  (56685, 94828);

INSERT INTO challenge (challenge_id, college_id) VALUES
  (18765, 11219),
  (47127, 11219),
  (60292, 32473),
  (72974, 56685);

INSERT INTO view (challenge_id, views, unique_views) VALUES
  (47127, 26, 19),
  (47127, 15, 14),
  (18765, 43, 10),
  (18765, 72, 13),
  (75516, 35, 17),
  (60292, 11, 10),
  (72974, 41, 15),
  (75516, 75, 11);

INSERT INTO submission (challenge_id, submissions, accepted_submissions) VALUES
  (75516, 34, 12),
  (47127, 27, 10),
  (47127, 56, 18),
  (75516, 74, 12),
  (75516, 83, 8),
  (72974, 68, 24),
  (72974, 82, 14),
  (47127, 28, 11);



SELECT
  contest.contest_id,
  hacker_id,
  name,
  college.college_id,
  --   challenge.challenge_id,
  CASE
    WHEN (sum(sum_s) IS NULL)
      THEN 0
    ELSE sum(sum_s)
  END         sum_s,
  CASE
    WHEN (sum(sum_as) IS NULL)
      THEN 0
    ELSE sum(sum_as)
  END         sum_as,
  sum(sum_v)  sum_v,
  sum(sum_uv) sum_uv
FROM contest
  LEFT JOIN college ON contest.contest_id = college.contest_id
  LEFT JOIN challenge ON college.college_id = challenge.college_id
  LEFT JOIN
  (SELECT
     challenge_id,
     sum(views) sum_v
   FROM view
   GROUP BY challenge_id) vsq ON challenge.challenge_id = vsq.challenge_id
  LEFT JOIN
  (SELECT
     challenge_id,
     sum(unique_views) sum_uv
   FROM view
   GROUP BY challenge_id) uvsq ON challenge.challenge_id = uvsq.challenge_id
  LEFT JOIN
  (SELECT
     challenge_id,
     sum(submissions) sum_s
   FROM submission
   GROUP BY challenge_id) ssq ON (challenge.challenge_id = ssq.challenge_id)
  LEFT JOIN
  (SELECT
     challenge_id,
     sum(accepted_submissions) sum_as
   FROM submission
   GROUP BY challenge_id) assq ON (challenge.challenge_id = assq.challenge_id)
GROUP BY contest.contest_id, hacker_id, name, college.college_id
ORDER BY contest.contest_id;