create sequence orders_seq start with 1 increment by 50;

create table orders (
    id bigint primary key,
    user_id bigint not null,
    created_at timestamptz not null default now(),
    predicted_date_of_delivery timestamptz,
    status varchar(30) not null,
    sub_price numeric(12, 2) not null,
    delivery_price numeric(12, 2) not null default 0,
    discount numeric(12, 2) not null default 0,
    total_price numeric(12, 2) not null,
    currency varchar(3) not null,
    delivery_city varchar(100),
    delivery_address varchar(255),

    constraint fk_orders_user foreign key (user_id) references users(id) on delete cascade,
    constraint chk_orders_sub_price check ( sub_price >= 0 ),
    constraint chk_orders_delivery_price check ( delivery_price >= 0 ),
    constraint chk_orders_discount check ( discount >= 0 ),
    constraint chk_orders_total_price check ( total_price >= 0 ),
    constraint chk_orders_currency check ( currency in ('KZT', 'USD', 'RUB', 'CNY') ),
    constraint chk_orders_status check ( status in (
        'CREATED', 'PENDING_PAYMENT', 'PAID', 'PROCESSING', 'SHIPPED',
        'IN_TRANSIT', 'READY_FOR_PICKUP', 'DELIVERED', 'CANCELLED', 'RETURNED'
    ) )
);

create index idx_orders_user_id on orders(user_id);
create index idx_orders_status on orders(status);
