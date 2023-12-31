create table buckets
(
    id      bigint generated by default as identity
        primary key,
    user_id bigint
);

alter table buckets
    owner to postgres;

create table categories
(
    id    bigint generated by default as identity
        primary key,
    title varchar(255)
);

alter table categories
    owner to postgres;

create table products
(
    id    bigint generated by default as identity primary key,
    price numeric(19, 2),
    title varchar(255)
);

alter table products
    owner to postgres;

create table buckets_products
(
    bucket_id  bigint not null
        constraint fkc49ah45o66gy2f2f4c3os3149
            references buckets,
    product_id bigint not null
        constraint fkloyxdc1uy11tayedf3dpu9lci
            references products
);

alter table buckets_products
    owner to postgres;

create table products_categories
(
    product_id  bigint not null
        constraint fktj1vdea8qwerbjqie4xldl1el
            references products,
    category_id bigint not null
        constraint fkqt6m2o5dly3luqcm00f5t4h2p
            references categories
);

alter table products_categories
    owner to postgres;

create table users
(
    id        bigint generated by default as identity
        primary key,
    archive   boolean not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    bucket_id bigint
        constraint fk8l2qc4c6gihjdyoch727guci
            references buckets
);

alter table users
    owner to postgres;

alter table buckets
    add constraint fknl0ltaj67xhydcrfbq8401nvj
        foreign key (user_id) references users;

create table orders
(
    id           bigint generated by default as identity
        primary key,
    address      varchar(255),
    created      timestamp,
    order_status varchar(255),
    sum          numeric(19, 2),
    updated      timestamp,
    user_id      bigint
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

alter table orders
    owner to postgres;

create table orders_details
(
    id         bigint generated by default as identity
        primary key,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    order_id   bigint
        constraint fk5o977kj2vptwo70fu7w7so9fe
            references orders,
    product_id bigint
        constraint fks0r9x49croribb4j6tah648gt
            references products
);

alter table orders_details
    owner to postgres;

