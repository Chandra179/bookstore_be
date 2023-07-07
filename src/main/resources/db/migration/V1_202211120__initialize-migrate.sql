CREATE TABLE genre
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE book
(
    id          UUID PRIMARY KEY,
    title       VARCHAR(255) NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_book_title ON book (title);

CREATE TABLE author
(
    id          UUID PRIMARY KEY,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255)
);
CREATE INDEX IF NOT EXISTS idx_author_id ON author (id);
CREATE INDEX IF NOT EXISTS idx_author_firstname_lastname ON author (first_name, last_name);

CREATE TABLE book_author
(
    fk_book   UUID REFERENCES book(id),
    fk_author UUID REFERENCES author(id),
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE book_genre
(
    fk_book   UUID REFERENCES book(id),
    fk_genre  SERIAL REFERENCES genre(id),
    PRIMARY KEY (book_id, genre_id)
);

CREATE TABLE inventory
(
    fk_book     UUID NOT NULL REFERENCES book (id),
    qty         BIGINT NOT NULL
);

CREATE TABLE pricing
(
    fk_book     UUID NOT NULL REFERENCES book (id),
    price       NUMERIC(15, 2) NOT NULL
);