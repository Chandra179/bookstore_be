CREATE TABLE genre
(
    id         SERIAL NOT NULL,
    name       VARCHAR(255),
    CONSTRAINT pk_genre PRIMARY KEY (id)
);
INSERT INTO genre (name) VALUES ('BIOGRAPHY');
INSERT INTO genre (name) VALUES ('FICTION');
INSERT INTO genre (name) VALUES ('SELF_HELP');

CREATE TABLE book
(
    id          UUID NOT NULL,
    title       VARCHAR(255),
    CONSTRAINT pk_book PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_book_title ON book (title);

CREATE TABLE author
(
    id          UUID NOT NULL,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    CONSTRAINT pk_author PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_author_id ON author (id);
CREATE INDEX IF NOT EXISTS idx_author_firstname_lastname ON author (first_name, last_name);

CREATE TABLE book_author
(
    book_id   UUID REFERENCES book(id),
    author_id UUID REFERENCES author(id),
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE book_genre
(
    book_id   UUID REFERENCES book(id),
    genre_id  SERIAL REFERENCES genre(id),
    PRIMARY KEY (book_id, genre_id)
);

