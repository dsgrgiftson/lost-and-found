set
mode Oracle;

-- Roles table
create table ROLES
(
    ID          int primary key,
    NAME        varchar(255) unique not null,
    DESCRIPTION varchar(255)        not null,
    CREATED_AT  timestamp           not null,
    UPDATED_AT  timestamp
);

-- Lost items table
create sequence LOST_ITEM_ID_SEQUENCE start with 1 increment by 1;

-- The table could be also designed with a composite primary key (LOST_ITEM_ID, PLACE) if the same item can be lost in different places
-- But for the sake of simplicity, we will use a single primary key
create table LOST_ITEM
(
    LOST_ITEM_ID int primary key,
    ITEM_NAME    varchar(255),
    QUANTITY     int,
    PLACE        varchar(255)
);

-- Users table
create sequence USER_ID_SEQUENCE start with 1000 increment by 1;

create table USERS
(
    USER_ID   int primary key,
    FIRSTNAME varchar(255),
    LASTNAME  varchar(255),
    EMAIL     varchar(255),
    PASSWORD  varchar(255),
    ROLE_ID   int references ROLES (ID)
);

-- Lost item claims table
create sequence CLAIM_ID_SEQUENCE start with 1 increment by 1;

create table LOST_ITEM_CLAIM
(
    CLAIM_ID         int primary key,
    LOST_ITEM_ID     int references LOST_ITEM (LOST_ITEM_ID),
    CLAIMED_QUANTITY int,
    USER_ID          int references USERS (USER_ID),
    CLAIM_TIMESTAMP  timestamp
);