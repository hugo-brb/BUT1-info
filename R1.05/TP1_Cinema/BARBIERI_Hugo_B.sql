
/*______________________________________________________________________________

            Requêtes TP1 - PArtie B (FICHIER À COMPLÉTER)

     Étapes à suivre pour chaque question :
     (1)  Écrire le code de la requête
     (2)  Copier la requête (ctrl C), puis le coller (ctrl + Shift + V)
          dans le terminal où vous êtes connectés à PostGreSQL
     (3)  Vérifier le résultat obtenu par comparaison à celui attendu
          (cf. TP1B_Résultats sur Chamilo)
     (4)  Si le résultat attendu n'est pas le bon, reprendre à l'étape 1

______________________________________________________________________________*/


/*------------------------------------------------------------------------------
(a) Réécrivez en utilisant une ou plusieurs sous-requêtes, les requêtes
    répondant aux questions (h) et (i) de la partie A
------------------------------------------------------------------------------*/

-- 1. Réécriture requête (h)
--    Nom des acteurs qui jouent dans Eternal Sunshine of the Spotless Mind
--    et aussi dans The Virgin Suicides

SELECT DISTINCT nomact
FROM acteur
WHERE nomact IN (SELECT nomact FROM acteur WHERE titre = 'Eternal Sunshine of the Spotless Mind') 
AND nomact IN (SELECT nomact FROM acteur WHERE titre = 'The Virgin Suicides');

-- 2. Réécriture requête (i)
--    Nom des acteurs qui jouent dans Eternal Sunshine of the Spotless Mind
--    mais pas dans The Virgin Suicides

SELECT DISTINCT nomact
FROM acteur
WHERE nomact IN (SELECT nomact FROM acteur WHERE titre = 'Eternal Sunshine of the Spotless Mind') 
AND nomact NOT IN (SELECT nomact FROM acteur WHERE titre = 'The Virgin Suicides');

/*------------------------------------------------------------------------------
(b)	Nombre d'acteurs par films
    (1 version avec GROUP BY + 1 version avec sous-requêtes)
    résultat ordonné par nombre d'acteurs décroissant, puis par titre
------------------------------------------------------------------------------*/

-- 1. Version avec GROUP BY

SELECT titre, COUNT(nomact) as Nombre_d_acteurs
FROM acteur
GROUP BY titre
ORDER BY Nombre_d_acteurs DESC, titre;

-- 2. Version avec sous-requête

SELECT DISTINCT titre, (SELECT COUNT(nomact) FROM acteur WHERE a.titre = titre) as Nombre_d_acteurs
FROM acteur a
ORDER BY Nombre_d_acteurs DESC, titre;

/*------------------------------------------------------------------------------
(c) Programmation en cours : pour chaque cinéma, sachant que chaque cinéma
    projetette au moins un film
    résultat ordonné par par nombre de films décroissant, puis par nom de cinéma
------------------------------------------------------------------------------*/

SELECT DISTINCT nomcine, (SELECT COUNT(DISTINCT titre) FROM salle WHERE s.nomcine = nomcine) as nb_film
FROM salle s
ORDER BY nb_film DESC, nomcine;


/*------------------------------------------------------------------------------
(d) Nom des cinémas projetant au moins 2 films Star Wars différents
    mis en scène par George Lucas (leur titre commence par Star Wars)
------------------------------------------------------------------------------*/

SELECT nomcine, count(*)
FROM salle
WHERE titre IN (SELECT titre FROM film WHERE titre LIKE 'Star Wars - %' AND metteurscene = 'George Lucas')
GROUP BY nomcine;

// Pour moi, il a une erreur dans la correction, La Nef projette bien le 'film Star Wars - A New Hope' mis en scene par George Lucas d'après la relation Film.

/*------------------------------------------------------------------------------
(e) Prix maximum, prix minimum et prix moyen arrondi à 2 décimales,
    des projections programmées par chaque cinéma
    résultat ordonné par nom de cinéma
------------------------------------------------------------------------------*/

SELECT nomcine, max(prix) as Prix_Max, min(prix) as Prix_Min, round(avg(prix),2) as Prix_Moyen 
FROM salle 
GROUP BY nomcine
ORDER BY nomcine;


/*------------------------------------------------------------------------------
(f) Titre et nom du metteur en scène des films qui ne sont pas programmés
    pour la journée en cours
    une version sans sous-requête et une version avec sous-requête
------------------------------------------------------------------------------*/
-- 1. version sans sous-requête

SELECT *
FROM film
EXCEPT
SELECT DISTINCT f.*
FROM film f, salle s
WHERE f.titre = s.titre;

-- 2. version avec sous-requête

SELECT *
FROM film
WHERE titre NOT IN (SELECT titre FROM salle);


/*------------------------------------------------------------------------------
(g) Pour afficher le nombre de séances débutant après 20h dans chaque cinéma,
    la requête suivante a été proposée...
------------------------------------------------------------------------------*/
SELECT NomCine, count(*) AS Nb_Seances_Soir
FROM SEANCE
WHERE Heure > 20
GROUP BY NomCine
ORDER BY NomCine;

-- 1. Exécutez-la

  nomcine  | nb_seances_soir
-----------+-----------------
 La Nef    |               7
 Le Meliès |               2
 Pathé     |               6
(3 rows)


-- 2. Justifiez pourquoi son résultat n'est pas satisfaisant

Le cinéma Le Club n'apparaît pas dans les résultats car il n'a pas de séances après 20h.


--3. Proposez une solution recevable

SELECT s.nomcine, (SELECT count(*) FROM seance WHERE s.nomcine = nomcine AND heure > 20) AS Nb_Seances_Soir
FROM seance s
GROUP BY NomCine
ORDER BY NomCine;


/*------------------------------------------------------------------------------
(h) Pour chaque cinéma, Nombre de salles qui projettent un film interprété par
    Gérard Depardieu ou par Catherine Deneuve
------------------------------------------------------------------------------*/

SELECT s.nomcine, (SELECT count(*) FROM salle, acteur WHERE s.nomcine = salle.nomcine AND salle.titre = acteur.titre AND (nomact='Gérard Depardieu' OR nomact='Catherine Deneuve')) AS Nb_Salles
FROM salle s
GROUP BY nomcine
ORDER BY nomcine;


/*------------------------------------------------------------------------------
(i)	Des usagers ont fait part de leur besoin de connaître les détails
    des séances de projection proposées après 18h à un prix inférieur
    à la moyenne des prix pratiqués : heure de séance, prix à payer, titre
    du film projeté, nom du cinéma et numéro de la salle
------------------------------------------------------------------------------*/

-- 1. Écrire une requête affichant la moyenne des prix pratiqués
--   (moyenne arrondie à 2 décimales)

SELECT round(avg(prix),2) as Prix_Moyen 
FROM salle;


-- 2. En utilisant la requête précédente, affichez les informations désirées
--    résultat ordonné par heure, prix, puis par nom de cinéma

SELECT heure, prix, s.titre, s.nomcine, s.numsalle
FROM salle s, seance se
WHERE (s.nomcine, s.numsalle) = (se.nomcine, se.numsalle) 
AND heure > 18
AND prix < (SELECT round(avg(prix),2) FROM salle)
ORDER BY heure, prix, s.nomcine;



/*------------------------------------------------------------------------------
(j)	On s'intéresse aux films inteprétés par Fabrice Luchini.
	  Écrivez les requêtes affichant les informations suivantes :
------------------------------------------------------------------------------*/

-- 1. Nombre de séances où est projeté un film inteprété par Fabrice Luchini

SELECT count(*) AS nb_seance
FROM salle
WHERE titre IN (SELECT titre FROM acteur WHERE nomact = 'Fabrice Luchini');

// Pour moi le résultat est 5 pas 15, les seuls films de Luchini sont : Molière, Les femme du 6ième étage et Potiche, qui ne sont projeté que 5fois tout cinéma confondu.


-- 2. Répartition de ces séances par cinéma
--    Résultat ordonné sur le nom des cinémas

SELECT nomcine, (SELECT count(*) FROM salle WHERE s.nomcine = nomcine AND titre IN (SELECT titre FROM acteur WHERE nomact = 'Fabrice Luchini')) AS nb_seance
FROM salle s
GROUP BY nomcine
ORDER BY nomcine;

// Pareil que pour la 1)

-- 3. Pour chaque cinéma où est projeté un film inteprété par Fabrice Luchini,
--    son nom,le nombre de salles qui proposent ce film et la fourchette de
--    prix pratiquée par ces salles (prix minimum, prix maximum)

SELECT nomcine, count(*) AS nb_seance, min(prix), max(prix)
FROM salle
WHERE titre IN (SELECT titre FROM acteur WHERE nomact = 'Fabrice Luchini')
GROUP BY nomcine
ORDER BY nomcine;


--4.  Nombre de projections pour chaque film inteprété par Fabrice Luchini

SELECT titre, count(*) AS nb_projections
FROM salle
WHERE titre IN (SELECT titre FROM acteur WHERE nomact = 'Fabrice Luchini')
GROUP BY titre
ORDER BY titre;


--5.  Pour chaque film inteprété par Fabrice Luchini, nombre de projections
--    par cinéma passant ce film
--    Résultat ordonné par nombre de projections décroissant puis par titre


SELECT (SELECT DISTINCT titre FROM acteur WHERE nomact = 'Fabrice Luchini') AS titre, nomcine, (SELECT count(*) FROM salle WHERE cine.nomcine = nomcine AND titre IN (SELECT titre FROM acteur WHERE nomact = 'Fabrice Luchini')) AS nb_projections
FROM salle
ORDER BY nb_projections DESC, titre;

//Cette requete est fausse

/*------------------------------------------------------------------------------
(k) Nom et adresse des cinémas qui projettent plus de 6 films différents
------------------------------------------------------------------------------*/

SELECT nomcine, adresse
FROM cine
WHERE nomcine IN (SELECT nomcine FROM salle s WHERE (SELECT count(*) FROM salle WHERE s.nomcine = nomcine) > 6);


/*------------------------------------------------------------------------------
(l) Nom et adresse des cinémas qui projettent le plus de films différents
------------------------------------------------------------------------------*/

SELECT nomcine, adresse
FROM cine
WHERE nomcine IN (SELECT nomcine FROM salle s WHERE (SELECT count(*) FROM salle) = (SELECT count(*) FROM salle WHERE s.nomcine = nomcine));

// Cette requete est fausse aussi

--------------------------------------------------------------------------------
-- FIN PARTIE B
--------------------------------------------------------------------------------
