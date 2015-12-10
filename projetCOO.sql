drop table ville;
drop table categorie;
drop table chambre;
drop table hotel;
drop table client;

drop sequence sq_ville;
drop sequence sq_categorie;
drop sequence sq_client;
drop sequence sq_hotel;
drop sequence sq_chambre;

-- Creation des sequences de clé primaire
create sequence sq_ville start with 1 increment by 1;
create sequence sq_categorie start with 1 increment by 1;
create sequence sq_client start with 1 increment by 1;
create sequence sq_hotel start with 1 increment by 1;
create sequence sq_chambre start with 1 increment by 1;



create table VILLE (
  idVille number(5),
  nom varchar(50) not null,
  constraint pk_ville primary key (idVille)
);

create table CLIENT(
  idClient number(5),
  nom varchar(50),
  prenom varchar(50),
  age number (3),
  date_naissance date,
  idVille number(5),
  constraint fk_client_ville foreign key (idVille) references ville(idVille) ON DELETE CASCADE,
  constraint pk_client primary key (idClient)
);


create table HOTEL (
  idHotel number(5),
  idVille number(5),  
  nom varchar(50),
  delaiAnnulation number (3),
  constraint fk_hotel_ville FOREIGN KEY (idVille) references  ville(idVille) ON DELETE CASCADE,
  constraint pk_hotel primary key (idHotel)

);

create table CATEGORIE(
  idCategorie number(5) primary key,
  nom varchar(100)
);

create table CHAMBRE(
  idChambre number(5) primary key,
  idCategorie number(5),
  idHotel number(5),
  capacite number (2),
  constraint fk_hotel_chambre FOREIGN KEY (idHotel) references  HOTEL(idHotel) ON DELETE CASCADE,
  constraint fk_chambre_categorie FOREIGN KEY (idCategorie) references  categorie(idCategorie)ON DELETE CASCADE
);


CREATE or REPLACE TRIGGER insertCategorieTrigger
  BEFORE INSERT
  ON Categorie
  FOR EACH ROW
BEGIN
  SELECT sq_categorie.nextval
    INTO :new.idCategorie
    FROM dual;
END;
/

CREATE or REPLACE TRIGGER insertVilleTrigger
  BEFORE INSERT
  ON Ville
  FOR EACH ROW
BEGIN
  SELECT sq_ville.nextval
    INTO :new.idVille
    FROM dual;
END;
/

CREATE or REPLACE TRIGGER insertClientTrigger
  BEFORE INSERT
  ON Client
  FOR EACH ROW
BEGIN
  SELECT sq_client.nextval
    INTO :new.idClient
    FROM dual;
END;
/


CREATE or REPLACE TRIGGER insertHotelTrigger
  BEFORE INSERT
  ON Hotel
  FOR EACH ROW
BEGIN
  SELECT sq_hotel.nextval
    INTO :new.idHotel
    FROM dual;
END;
/


CREATE or REPLACE TRIGGER insertChambreTrigger
  BEFORE INSERT
  ON Chambre
  FOR EACH ROW
BEGIN
  SELECT sq_chambre.nextval
    INTO :new.idChambre
    FROM dual;
END;
/

create table promotion(
idpromotion number(3),
nompromo varchar2(50),
tauxpromo number(3),
debut date,
fin date,
constraint pk_promo primary key (idpromotion),
constraint ck_taux check (tauxpromo>0 and tauxpromo<101)
);

create table vol(
idvol number(3),
idpromo number(3),
depart number(3),
arrivee number(3),
aller date,
retour date,
capacitepermclasse number(5),
capecitesecclasse number(5),
prixpremclasse number(5),
prixsecclasse number(5),
annulation date,
constraint pk_vol primary key(idvol)
);

