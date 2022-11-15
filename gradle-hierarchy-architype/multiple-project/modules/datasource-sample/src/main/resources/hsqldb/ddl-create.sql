DROP TABLE SAMPLE IF EXISTS;

CREATE TABLE SAMPLE (
    SAMPLE_ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    NAME VARCHAR_IGNORECASE(50) NOT NULL,
    ALTERNAME VARCHAR_IGNORECASE(50) NOT NULL,
    ENABLED BOOLEAN NOT NULL
);