CREATE DATABASE soft_uni;

CREATE TABLE soft_uni.towns(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE soft_uni.addresses(
  id INT AUTO_INCREMENT PRIMARY KEY,
  address_text VARCHAR(255),
  town_id INT NOT NULL,

  CONSTRAINT fk_addresses_towns FOREIGN KEY (town_id) REFERENCES soft_uni.towns (id)
);

CREATE TABLE soft_uni.departments(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE soft_uni.employees (
   id INT AUTO_INCREMENT PRIMARY KEY ,
   first_name VARCHAR(255) NOT NULL,
   middle_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   job_title VARCHAR(255) NOT NULL,
   department_id INT NOT NULL,
   hire_date DATE NOT NULL,
   salary DOUBLE(12,2) NOT NULL,
   address_id INT,

   CONSTRAINT fk_employees_department
       FOREIGN KEY (department_id) REFERENCES soft_uni.departments (id),
   CONSTRAINT fk_employees_addresses
       FOREIGN KEY (address_id) REFERENCES soft_uni.addresses (id)
);


INSERT INTO soft_uni.towns (name)
VALUES   ('Sofia'),
         ('Plovdiv'),
         ('Varna'),
         ('Burgas');

INSERT INTO soft_uni.departments (name)
VALUES ('Engineering'),
       ('Sales'),
       ('Marketing'),
       ('Software Development'),
       ('Quality Assurance');

INSERT INTO soft_uni.employees (first_name, middle_name, last_name, job_title, department_id, hire_date, salary)
VALUES  ('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
        ('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
        ('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
        ('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
        ('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);