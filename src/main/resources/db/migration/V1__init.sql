CREATE TABLE team
(
    id         BIGINT PRIMARY KEY,
    name       TEXT NOT NULL,
    code       TEXT NOT NULL,
    city       TEXT NOT NULL,
    stadium    TEXT NOT NULL,
    conference TEXT NOT NULL,
    division   TEXT NOT NULL
);
