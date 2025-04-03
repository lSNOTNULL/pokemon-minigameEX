create table poke_user(
    poker_user_id varchar(255) primary key,
    username varchar(255) not null unique ,
    password varchar(255) not null
);

ALTER table poke_user rename column poker_user_id to poke_user_id;