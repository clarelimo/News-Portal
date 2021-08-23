SET MODE PostgreSQL;

CREATE DATABASE news_portal;
\c news_portal;

CREATE TABLE users (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 position VARCHAR,
 role VARCHAR,
 departmentid INTEGER
);

CREATE TABLE news (
 id SERIAL PRIMARY KEY,
 post VARCHAR
);

CREATE TABLE departments (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 description VARCHAR,
 numberofemployees INTEGER
);

CREATE TABLE departments_news (
 id SERIAL PRIMARY KEY,
 departmentid INTEGER,
 newsid INTEGER
);

CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;
