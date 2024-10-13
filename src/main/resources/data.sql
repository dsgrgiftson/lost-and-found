set
mode Oracle;

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
 IS_ADMIN)
values (USER_ID_SEQUENCE.nextval,
        'Giftson',
        'David',
        'giftson.david@rabobank.nl',
        'admin123',
        true);

insert into USERS
(USER_ID,
 FIRSTNAME,
 LASTNAME,
 EMAIL,
 PASSWORD,
 IS_ADMIN)
values (USER_ID_SEQUENCE.nextval,
        'Jon',
        'Doe',
        'jon.doe@testemail.com',
        'user123',
        false);

commit;

insert into LOST_ITEM_CLAIM
(CLAIM_ID,
 LOST_ITEM_ID,
 CLAIMED_QUANTITY,
 USER_ID)
values (CLAIM_ID_SEQUENCE.nextval,
        1,
        1,
        1001);

commit;