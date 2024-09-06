CREATE TABLE users (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(30) NOT NULL UNIQUE,
   password VARCHAR(26) NOT NULL,
   profile_picture BLOB,
   last_login_time DATETIME,
   is_deleted BOOLEAN DEFAULT FALSE
);


INSERT INTO users (username, password, profile_picture, last_login_time, is_deleted)
VALUES
    ('john_doe', 'password123', NULL, now(), FALSE),
    ('jane_smith', 'abc456', NULL, now(), FALSE),
    ('robert_brown', 'robert2024', NULL, now(), TRUE),
    ('emily_white', 'emilyw123', NULL, now(), FALSE),
    ('michael_black', 'blackmichael', NULL, now(), FALSE);