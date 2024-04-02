--A1

create table Artiste(
    Surnom varchar(20) primary key,
    Specialite varchar NOT NULL,
    CONSTRAINT c_specialite CHECK (Specialite IN ('jongleur', 'clown', 'acrobate', 'équilibriste'))
);

create table Numero(
    Titre varchar primary key,
    Responsable varchar(20) references Artiste(Surnom) NOT NULL
);

create table Interprete(
    Surnom varchar(20) references Artiste(Surnom),
    Titre varchar references Numero(Titre),
    primary key (Surnom, Titre)
);

create table Accessoire(
    CodeAcc numeric(3) primary key,
    Type varchar(20) check ( Type in ('ballon', 'cerceau', 'foulard', 'quilles', 'trapèze', 'corde', 'autre'))
                       DEFAULT 'autre',
    Couleur varchar check ( Couleur IN ('blanc', 'noir', 'rouge', 'bleu', 'vert', 'jaune', 'orange', 'violet') ),
    ImmatCamion char(9) CHECK ( ImmatCamion Like('__-___-__') )
);

create table Utilisation(
    CodeAcc numeric(3) references Accessoire(CodeAcc),
    Surnom varchar(20),
    Titre varchar,
    foreign key (Surnom, Titre) references Interprete(Surnom, Titre),
    primary key (CodeAcc, Surnom, Titre)
);

ALTER TABLE Numero ADD Duree time NOT NULL CHECK(Duree BETWEEN '00:15:00' AND '00:30:00');
CREATE TABLE Lieu(
	IDLieu varchar PRIMARY KEY,
	Adresse varchar NOT NULL,
	SiteWeb varchar CHECK(SiteWeb LIKE 'https://%')
);
CREATE TABLE Representation(
	CodeRep varchar PRIMARY KEY,
	Date date NOT NULL,
	HeureDebut time NOT NULL CHECK(HeureDebut BETWEEN '10:00:00' AND '22:00:00'),
	IDLieu varchar NOT NULL references Lieu(IDLieu)
);
CREATE TABLE Presente(
	CodeRep varchar REFERENCES Representation(CodeRep),
	Titre varchar REFERENCES Numero(Titre),
	OrdrePassage int NOT NULL CHECK(OrdrePassage >= 1),
	PRIMARY KEY(CodeRep, Titre),
	UNIQUE (CodeRep, OrdrePassage)
);