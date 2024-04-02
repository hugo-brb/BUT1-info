--A1
create table Artiste(
    Surnom varchar(20) primary key,
    Specialite varchar
);

create table Numero(
    Titre varchar,
    Responsable varchar(20) references Artiste(Surnom)
);

create table Interprete(
    Surnom varchar(20) references Artiste(Surnom),
    Titre varchar references Numero(Titre),
    primary key (Surnom, Titre)
);

create table Accessoire(
    CodeAcc numeric(3) primary key,
    Type varchar(20),
    Couleur varchar,
    ImmatCamion char(9)
);

create table Utilisation(
    CodeAcc numeric(3) references Accessoire(CodeAcc),
    Surnom varchar(20),
    Titre varchar,
    foreign key (Surnom, Titre) references Interprete(Surnom, Titre),
    primary key (CodeAcc, Surnom, Titre)
);