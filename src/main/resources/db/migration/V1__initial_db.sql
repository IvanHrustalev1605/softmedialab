create table student (
        id bigserial primary key,
        name varchar(255),
        last_Name varchar(255),
        birth_Date varchar(255),
        grade JSON
    );
create table grade (
    id integer primary key ,
    text varchar(255)
);
insert into grade (id, text)
VALUES
    (2, 'неуд'),
    (3, 'уд'),
    (4, 'хор'),
    (5, 'отл');

