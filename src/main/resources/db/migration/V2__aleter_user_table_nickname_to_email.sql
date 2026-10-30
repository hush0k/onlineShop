alter table users rename column nickname to email;

alter table users alter column email type varchar(255);

alter table users rename constraint uq_users_nickname to uq_users_email;
