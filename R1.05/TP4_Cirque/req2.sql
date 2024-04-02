--------------------------------------------------------------------------------
        -- INFORMATIONS SUR LES DONNÉES DE L'INSTANCE DE LA BD --
--------------------------------------------------------------------------------
SET DATESTYLE TO EUROPEAN;

/*______________________________________________________________________________
BESOIN N°1.
Affichage de toutes les représentations futures
______________________________________________________________________________*/
SELECT * 
FROM Representation
WHERE date>current_date;

/*______________________________________________________________________________
BESOIN N°2.
Affichage du jour, de l'heure de début, du lieu et du nombre de numéros
présentés, pour chacune des représentations futures.
Résultat ordonné sur la date, puis l'heure des représentations
______________________________________________________________________________*/
SELECT r.CodeRep, date, heuredebut, idlieu, count(titre)
FROM Representation r, presente p
WHERE  r.CodeRep = p.CodeRep
AND date>current_date
GROUP BY r.CodeRep
ORDER BY date, heuredebut;

/*______________________________________________________________________________
BESOIN N°3.
Nombre de représentations  passées ou à venir où est présenté un numéro sous la
responsabilité de Etoile, mais aucun numéro sous la responsabilité de Bozzo
CONTRAINTE : utiliser EXISTS / NOT EXISTS pour coder ce BESOIN
______________________________________________________________________________*/
SELECT count(Titre)AS nb_rep_etoile_pas_bozzo
FROM Representation r, presente p
WHERE r.CodeRep = p.CodeRep
AND EXISTS (SELECT Titre FROM numero WHERE Responsable = 'Etoile')
AND NOT EXISTS(SELECT Titre FROM numero WHERE Responsable = 'Bozzo');

/*______________________________________________________________________________
BESOIN N°4.
Date, heure de début et adresse des représentations dans lesquelles
au moins 5 numéros sont présentés - Résultat ordonné par date, puis heure début
______________________________________________________________________________*/
SELECT date, heuredebut, adresse
FROM Representation r, presente p, lieu l
WHERE l.IDLieu = r.IDLieu
AND r.CodeRep = p.CodeRep
GROUP BY (date, heuredebut, adresse)
HAVING count(*) >= 5
ORDER BY date, heuredebut;

/*______________________________________________________________________________
BESOIN N°5.
Date, heure de début et adresse des représentations dans lesquelles
tous les numéros sont présentés
______________________________________________________________________________*/
SELECT date, heuredebut, adresse
FROM representation r, presente p, lieu l
WHERE r.CodeRep = p.CodeRep AND l.IDLieu = r.IDLieu
GROUP BY date, heuredebut, adresse
HAVING count(*) = (SELECT count(*) FROM numero);

/*______________________________________________________________________________
BESOIN N°6.
Un entracte de 30 à 45 minutes doit être respecté lors de chaque représentation
(valeur par défaut 30 mn).
______________________________________________________________________________*/
-- (1) Faites le nécessaire sans modifier le fichier create.sql
ALTER TABLE Representation ADD entracte time DEFAULT('00:30:00') CHECK(entracte BETWEEN '00:30:00' AND '00:45:00');

-- (2) Afichez les n-uplets de REPRESENTATION
SELECT * FROM Representation;
-- Que remarquez-vous ?
-- Toutes les valeurs sont à 30min par defaut

/*______________________________________________________________________________
BESOIN N°7.
Durée de la représenation de code 'R1ANN', entracte non comprise
______________________________________________________________________________*/
SELECT sum(duree) AS duree_rep_hors_entract
FROM Representation r, presente p, numero n
WHERE r.CodeRep = p.CodeRep AND p.titre = n.titre
AND r.CodeRep = 'R1ANN';

/*______________________________________________________________________________
BESOIN N°8.
Faites en sorte que l'entracte des représentations qui présentent
plus de 5 numéros soit de 40 mn
______________________________________________________________________________*/
UPDATE Representation SET entracte = '00:40:00'
WHERE (SELECT count(*)
FROM Representation r, presente p, lieu l
WHERE l.IDLieu = r.IDLieu
AND r.CodeRep = p.CodeRep
GROUP BY (date, heuredebut, adresse)) > 5;



/*______________________________________________________________________________
BESOIN N°9.
Code, Date, Heure début, Durée (entracte comprise) de chaque représentation
passée ou à venir
Résultat ordonné par date, puis par heure début
______________________________________________________________________________*/




/*______________________________________________________________________________
BESOIN N°10.
Selon la RG8. énoncée dans le TD6, il doit y avoir au moins 5h d'écart entre
l'heure de début d'une représentation et l'heure de fin de la précédente.

------------------------------
(1) À quelle heure maximale devrait commencer une représentation qui présenterait
    tous les numéros, avec une entracte de 40 mn et qui serait programmée
    avant la représentation de code R1ANN ?

    INDICATIONS
    Procédez par étape pour constuire le résultat...
    (a) Requête donnant la durée nécessaire à la présentation de tous les
        numéros à laquelle s'ajouterait un entracte de 40 mn
    (b) Requête donnant l'heure de fin maximale d'une représentation qui serait
        donnée avant la représentation de code R1ANN (cf. RG8)
    (c) Production du résultat attendu...*/
-------------------------------

-- (a) Durée totale de présentation de tous les numéros + 40mn d'entracte




-- (b) Heure de fin maximale d'une représentation commençant avant celle
--     de code R1ANN



-- (c) Heure de début maximale d'une représentation de tous les numéros....



/*-----------------------------
(2) Si l'heure trouvée est supérieure à 10h, créez une représentation
    de code R2ANN dans le même lieu et le même jour que la réservation R1ANN
    et débutant à cette heure.
	NOTE : l'insertion dans PRESENCE de tous les numéros pour cette représentation
         n'est pas demandée
-----------------------------*/





--------------------------------------------------------------------------------
-- FIN !!!
--------------------------------------------------------------------------------
