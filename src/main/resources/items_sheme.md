CREATE TYPE typeOfItem AS ENUM (
    'BOOK',
    'MOVIE',
    'AUDIOBOOK'
    );



CREATE TABLE item(
    id SERIAL PRIMARY KEY,
    author VARCHAR(255),
    titleOfItem VARCHAR(255),
    typeOfItem typeOfItem,
    dateOfBorrowing DATE,
    isAvailable BOOLEAN,
    imageUrl VARCHAR(255)
);

