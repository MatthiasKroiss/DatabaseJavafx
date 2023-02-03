create table "Adresse"
(
    ID      INTEGER not null
        constraint "Adresse_pk"
            primary key,
    WOHNORT VARCHAR(25)
);

create table "Person"
(
    NAME        VARCHAR(25),
    ID          INTEGER not null
        constraint "Person_pk"
            primary key,
    "AdresseId" INTEGER
        constraint "Person_Adresse_ID_fk"
            references "Adresse"
);


insert into "Adresse" values (1,'Wels');
insert into "Adresse" values (2,'Linz');
insert into "Adresse" values (3,'Wien');
insert into "Adresse" values (4,'Sattledt');

insert into "Person" values ('Meier', 1, 2);
insert into "Person" values ('Müller', 2, 4);
insert into "Person" values ('Mustermann', 3, 1);
insert into "Person" values ('TestPerson', 4, 3);