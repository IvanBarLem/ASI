-- Indexes for primary keys have been explicitly created.

DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;