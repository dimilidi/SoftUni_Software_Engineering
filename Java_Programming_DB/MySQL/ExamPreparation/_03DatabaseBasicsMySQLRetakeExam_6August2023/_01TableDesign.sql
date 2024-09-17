CREATE TABLE cities
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE property_types
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    type        VARCHAR(40) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE properties
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    address          VARCHAR(80)    NOT NULL UNIQUE,
    price            DECIMAL(19, 2) NOT NULL,
    area             DECIMAL(19, 2),
    property_type_id INT,
    city_id          INT,

    FOREIGN KEY (property_type_id) REFERENCES property_types (id),
    FOREIGN KEY (city_id) REFERENCES cities (id)
);

CREATE TABLE agents
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    phone      VARCHAR(20) NOT NULL UNIQUE,
    email      VARCHAR(50) NOT NULL UNIQUE,
    city_id    INT,

    FOREIGN KEY (city_id) REFERENCES cities (id)
);

CREATE TABLE buyers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    phone      VARCHAR(20) NOT NULL UNIQUE,
    email      VARCHAR(50) NOT NULL UNIQUE,
    city_id    INT,

    FOREIGN KEY (city_id) REFERENCES cities (id)
);



CREATE TABLE property_offers
(
    property_id    INT            NOT NULL,
    agent_id       INT            NOT NULL,
    price          DECIMAL(19, 2) NOT NULL,
    offer_datetime DATETIME,

    FOREIGN KEY (property_id) REFERENCES properties (id),
    FOREIGN KEY (agent_id) REFERENCES agents (id)
);

CREATE TABLE property_transactions
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    property_id      INT NOT NULL,
    buyer_id         INT NOT NULL,
    transaction_date DATE,
    bank_name        VARCHAR(30),
    iban             VARCHAR(40) UNIQUE,
    is_successful    BOOLEAN,

    FOREIGN KEY (property_id) REFERENCES properties (id),
    FOREIGN KEY (buyer_id) REFERENCES buyers (id)
);
