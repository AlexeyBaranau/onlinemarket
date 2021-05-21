-- customers

create table m_customers
(
    id         bigserial                                              not null
        constraint m_customers_pk
            primary key,
    name       varchar(100)                                           not null,
    surname    varchar(100)                                           not null,
    gender     varchar(20)  default 'NOT_SELECTED'::character varying not null,
    created    timestamp(6) default CURRENT_TIMESTAMP                 not null,
    changed    timestamp(6) default CURRENT_TIMESTAMP                 not null,
    phone      varchar(50),
    address    varchar(100),
    is_deleted boolean      default false                             not null,
    login      varchar(100) default 'login'::character varying        not null,
    password   varchar(100) default 'password'::character varying     not null
);

alter table m_customers
    owner to postgres;

create unique index m_customers_id_uindex
    on m_customers (id);

create unique index m_customers_login_uindex
    on m_customers (login);

-- roles

create table m_roles
(
    id          bigserial                                          not null
        constraint m_roles_pk
            primary key,
    role_name   varchar(50) default 'ROLE_USER'::character varying not null,
    customer_id bigint                                             not null
        constraint m_roles_m_customers_id_fk
            references m_customers
            on delete cascade
);

alter table m_roles
    owner to postgres;

create unique index m_roles_id_uindex
    on m_roles (id);

-- categories

create table m_categories
(
    id   bigserial not null
        constraint m_categories_pk
            primary key,
    name varchar(60)
);

alter table m_categories
    owner to postgres;

create unique index m_categories_id_uindex
    on m_categories (id);

-- manufacturers

create table m_manufacturers
(
    id   bigserial not null
        constraint m_manufacturers_pk
            primary key,
    name varchar(60)
);

alter table m_manufacturers
    owner to postgres;

create unique index m_manufacturers_id_uindex
    on m_manufacturers (id);

-- products

create table m_products
(
    id              bigserial                              not null
        constraint m_products_pk
            primary key,
    name            varchar(60)                            not null,
    description     varchar(500)                           not null,
    price           numeric(10, 2)                         not null,
    created         timestamp(6) default CURRENT_TIMESTAMP not null,
    changed         timestamp(6) default CURRENT_TIMESTAMP not null,
    is_deleted      boolean      default false             not null,
    manufacturer_id bigint
        constraint m_products_m_manufacturers_id_fk
            references m_manufacturers
            on update cascade on delete cascade,
    category_id     bigint
        constraint m_products_m_categories_id_fk
            references m_categories
            on update cascade on delete cascade
);

alter table m_products
    owner to postgres;

create unique index m_products_id_uindex
    on m_products (id);

-- baskets

create table m_baskets
(
    id          bigserial not null
        constraint m_baskets_pk
            primary key,
    total_price numeric(10, 2),
    customer_id bigint
        constraint m_baskets_m_customers_id_fk
            references m_customers
            on update cascade on delete cascade
);

alter table m_baskets
    owner to postgres;

create unique index m_baskets_id_uindex
    on m_baskets (id);

-- orders

create table m_orders
(
    id          bigserial                              not null
        constraint m_orders_pk
            primary key,
    customer_id bigint                                 not null
        constraint m_orders_m_customers_id_fk
            references m_customers
            on delete cascade,
    total_price numeric(10, 2)                         not null,
    created     timestamp(6) default CURRENT_TIMESTAMP not null,
    status      varchar(30)                            not null
);

alter table m_orders
    owner to postgres;

create unique index m_orders_id_uindex
    on m_orders (id);

create unique index m_orders_status_uindex
    on m_orders (status);

-- basketitems

create table m_baskets_items
(
    id         bigserial not null
        constraint m_baskets_items_pk
            primary key,
    product_id bigint
        constraint m_baskets_items_m_products_id_fk
            references m_products
            on update cascade on delete cascade,
    basket_id  bigint
        constraint m_baskets_items_m_baskets_id_fk
            references m_baskets
            on update cascade on delete cascade,
    amount     integer
);

alter table m_baskets_items
    owner to postgres;

create unique index m_baskets_items_id_uindex
    on m_baskets_items (id);

-- ordered_products

create table m_ordered_products
(
    id         bigserial not null
        constraint m_ordered_products_pk
            primary key,
    order_id   bigint
        constraint m_ordered_products_m_orders_id_fk
            references m_orders
            on update cascade on delete cascade,
    product_id bigint
        constraint m_ordered_products_m_products_id_fk
            references m_products
            on update cascade on delete cascade,
    amount     integer
);

alter table m_ordered_products
    owner to postgres;

create unique index m_ordered_products_id_uindex
    on m_ordered_products (id);



