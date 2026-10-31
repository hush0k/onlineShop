create sequence order_histories_seq start with 1 increment by 50;

create table order_histories (
    id bigint primary key,
    order_id bigint not null,
    status varchar(30) not null,
    timestamp timestamptz not null default now(),

    constraint fk_order_histories_order foreign key (order_id) references orders(id) on delete cascade,
    constraint chk_order_histories_status check ( status in (
        'CREATED', 'PENDING_PAYMENT', 'PAID', 'PROCESSING', 'SHIPPED',
        'IN_TRANSIT', 'READY_FOR_PICKUP', 'DELIVERED', 'CANCELLED', 'RETURNED'
    ) )
);

create index idx_order_histories_order_id on order_histories(order_id);
