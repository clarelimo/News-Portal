SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 position VARCHAR,
 role VARCHAR,
 departmentid INTEGER,
);

CREATE TABLE IF NOT EXISTS news (
 id int PRIMARY KEY auto_increment,
 post VARCHAR
);

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 description VARCHAR,
 numberofemployees INTEGER,
);

CREATE TABLE IF NOT EXISTS departments_news (
 id int PRIMARY KEY auto_increment,
 departmentid INTEGER,
 newsid INTEGER
);