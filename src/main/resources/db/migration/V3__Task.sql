CREATE TABLE author
(
    id         SERIAL PRIMARY KEY,
    full_name  TEXT,
    created_at TIMESTAMP DEFAULT current_timestamp
);


ALTER TABLE budget
    ADD COLUMN author_id INT,
    ADD CONSTRAINT budget_fk_author
    FOREIGN KEY (author_id) REFERENCES author(id);

