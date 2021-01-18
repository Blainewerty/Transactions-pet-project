create table users
(
    user_id       serial primary key,
    email         varchar(255),
    password      varchar(255),
    first_name    varchar(255),
    last_name     varchar(255),
    total_balance int check ( total_balance > 0 )
);

create table bills
(
    bill_id serial primary key check (bill_id <= 5),
    user_id int,
    name    varchar(255),
    balance int check ( balance >= 0 ),
    FOREIGN KEY (user_id)
        REFERENCES users (user_id)
);

create table transaction
(
    transaction_id    serial primary key,
    user_id           int,
    bill_id           int,
    transaction_name  varchar(255),
    transaction_date  timestamp,
    transaction_value int check ( transaction_value >= 0 ),
    FOREIGN KEY (user_id)
        REFERENCES users (user_id),
    FOREIGN KEY (bill_id)
        REFERENCES bills (bill_id),
    FOREIGN KEY (transaction_id)
        REFERENCES transaction (transaction_id)
);
