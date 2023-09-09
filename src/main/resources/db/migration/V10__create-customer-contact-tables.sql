CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE contact_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE customer
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE contact
(
    id            BIGINT PRIMARY KEY,
    contact_value VARCHAR(255) NOT NULL,
    contact_type  VARCHAR(255) NOT NULL,
    customer_id   BIGINT REFERENCES customer(id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT customer_contact_unique UNIQUE (contact_value, customer_id)
);
