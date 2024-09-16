CREATE TABLE minions.people (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    picture BLOB,
    height DECIMAL(6, 2),
    weight DECIMAL(6, 2),
    gender ENUM('m', 'f') NOT NULL,
    birthdate DATE NOT NULL,
    biography TEXT
);

INSERT INTO minions.people(name, picture, height, weight, gender, birthdate, biography)
VALUES  ('John Doe', NULL, 1.80, 75.00, 'm', '1985-03-15', 'John is a software engineer with 10 years of experience.'),
        ('Jane Smith', NULL, 1.65, 60.50, 'f', '1990-07-25', 'Jane is a talented graphic designer.'),
        ('Robert Brown', NULL, 1.75, NULL, 'm', '1978-11-02', 'Robert is a project manager with a keen eye for detail.'),
        ('Emily White', NULL, 1.70, 55.75, 'f', '1995-06-13', 'Emily is a professional photographer.'),
        ('Michael Black', NULL, NULL, 85.00, 'm', '1988-02-28', 'Michael is a fitness trainer and athlete.');
