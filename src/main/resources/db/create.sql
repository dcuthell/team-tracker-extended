SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS members (
   id int PRIMARY KEY auto_increment,
   first VARCHAR,
   last VARCHAR,
   teamId int
);

CREATE TABLE IF NOT EXISTS teams (
   id int PRIMARY KEY auto_increment,
   name VARCHAR,
   description VARCHAR
);