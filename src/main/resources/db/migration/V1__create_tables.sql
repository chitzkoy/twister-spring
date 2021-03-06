CREATE TABLE posts (
    id INT DEFAULT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    body  VARCHAR(1000000) NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP
);
CREATE TABLE comments (
    id INT DEFAULT NULL AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    body VARCHAR(1000000) NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(id)
);
CREATE TABLE users (
    id INT DEFAULT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    pass_hash VARCHAR(100) NOT NULL,
    avatar MEDIUMBLOB,
    registered TIMESTAMP
);