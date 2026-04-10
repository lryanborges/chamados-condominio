CREATE TABLE block (
    id SERIAL PRIMARY KEY,
    identity VARCHAR(50) NOT NULL,
    qtd_floors INT NOT NULL,
    n_units INT NOT NULL
);

CREATE TABLE unit (
    id SERIAL PRIMARY KEY,
    block_id INT NOT NULL,
    floor INT NOT NULL,
    FOREIGN KEY (block_id) REFERENCES block(id)
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    scope VARCHAR(50)
);

CREATE TABLE user_unit (
    user_id INT NOT NULL,
    unit_id INT NOT NULL,
    PRIMARY KEY (user_id, unit_id),
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (unit_id) REFERENCES unit(id)
);

CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE call (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    deadline TIMESTAMP,
    user_id INT NOT NULL,
    unit_id INT NOT NULL,
    status_id INT NOT NULL,
    type_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (unit_id) REFERENCES unit(id),
    FOREIGN KEY (status_id) REFERENCES status(id),
    FOREIGN KEY (type_id) REFERENCES type(id)
);

CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    call_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (call_id) REFERENCES call(id),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE annex (
    id SERIAL PRIMARY KEY,
    content TEXT,
    call_id INT NOT NULL,
    FOREIGN KEY (call_id) REFERENCES call(id)
);