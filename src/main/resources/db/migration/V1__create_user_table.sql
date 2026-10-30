create sequence user_seq start with 1 increment by 50;

create table users(
    id bigint primary key,
    nickname varchar(100) not null,
    first_name varchar(100),
    last_name varchar(100),
    date_of_birth date,
    role varchar(20) not null,
    phone_number varchar(20),
    hashed_password varchar(255) not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),

    constraint uq_users_nickname unique (nickname),
    constraint uq_users_phone unique (phone_number),
    constraint chk_user_nickname_lower check ( nickname = lower(nickname) )
);

create unique index uq_users_nickname_lower on users (lower(nickname));

