DROP TABLE IF EXISTS MODEL;

CREATE TABLE MODEL
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    prog INT,
    curr INT,
    temp double,
    work BOOLEAN DEFAULT false
);