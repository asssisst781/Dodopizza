create table permissions(
    id serial primary key,
    role varchar(50)
);

INSERT INTO permissions(role) values('ROLE_ADMIN');
INSERT INTO permissions(role) values('ROLE_USER');