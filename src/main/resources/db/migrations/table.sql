create TABLE note (
    id serial primary key not null,
    note varchar(1000)
);

create TABLE user (
    id serial primary key not null,
    username varchar(50),
    password varchar(500)
);
