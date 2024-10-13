set
mode Oracle;

insert into ROLES
(ID,
 NAME,
 DESCRIPTION,
 CREATED_AT,
 UPDATED_AT)
values (1, 'ADMIN', 'Admin role', current_timestamp,
        current_timestamp);

insert into ROLES
(ID,
 NAME,
 DESCRIPTION,
 CREATED_AT,
 UPDATED_AT)
values (2, 'USER', 'User role', current_timestamp,
        current_timestamp);

commit;

insert into LOST_ITEM
(LOST_ITEM_ID,
 ITEM_NAME,
 QUANTITY,
 PLACE)
values (LOST_ITEM_ID_SEQUENCE.nextval,
        'Wallet',
        3,
        'Den Haag Central Station');

commit;

insert into USERS
(USER_ID,
 FIRSTNAME,
 LASTNAME,
 EMAIL,
 PASSWORD,
 ROLE_ID)
values (USER_ID_SEQUENCE.nextval,
        'Giftson',
        'David',
        'giftson.david@rabobank.nl',
        'admin123',
        1);

insert into USERS
(USER_ID,
 FIRSTNAME,
 LASTNAME,
 EMAIL,
 PASSWORD,
 ROLE_ID)
values (USER_ID_SEQUENCE.nextval,
        'Jon',
        'Doe',
        'jon.doe@testemail.com',
        'user123',
        2);

commit;

insert into LOST_ITEM_CLAIM
(CLAIM_ID,
 LOST_ITEM_ID,
 CLAIMED_QUANTITY,
 USER_ID,
 CLAIM_TIMESTAMP)
values (CLAIM_ID_SEQUENCE.nextval,
        1,
        1,
        1001,
        current_timestamp);

commit;
