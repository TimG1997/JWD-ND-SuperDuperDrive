CREATE TABLE IF NOT EXISTS USERS (
     id INT PRIMARY KEY auto_increment,
     username VARCHAR(20) UNIQUE,
     salt VARCHAR,
     password VARCHAR,
     first_name VARCHAR(20),
     last_name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
     id INT PRIMARY KEY auto_increment,
     title VARCHAR(20),
     description VARCHAR (1000),
     user_id INT,
     foreign key (user_id) references USERS(id)
);

CREATE TABLE IF NOT EXISTS FILES (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    content_type VARCHAR,
    size VARCHAR,
    user_id INT,
    data BLOB,
    foreign key (user_id) references USERS(id)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
   id INT PRIMARY KEY auto_increment,
   url VARCHAR(100),
   username VARCHAR (30),
   key VARCHAR,
   password VARCHAR,
   user_id INT,
   foreign key (user_id) references USERS(id)
);