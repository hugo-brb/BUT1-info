/*
	##### R1_05 Controle Machine NetSerieFlix Janvier 2024 #####

	COMMENCEZ PAR BIEN LIRE LE PDF FOURNI AVEC CE SCRIPT SQL !

	Puis renommer ce fichier en remplaçant "Groupe-NOM-Prenom" par :
		votre groupe (A1,A2,B1,B2,C1,C2,D1,D2,E1,E2), votre NOM et votre Prénom

	Vous devez compléter ce script SQL en remplaçant les parties avec des "SELECT...;"
	par vos requêtes SQL.
	Cependant, VEUILLEZ LAISSER LES COMMENTAIRES PRÉSENTS DANS CE SCRIPT !

	Pour chaque requête, les résultats attendus sont indiqués.
	VEUILLEZ RESPECTER LES ALIAS DÉFINIS DANS LES RÉSULTATS ATTENDUS (quand un alias est défini).

	Pour réaliser ce TP, vous avez le droit de vous aider de vos cours 
	et à faire des recherches sur internet mais l'utilisation de l'IA générative, par exemple ChatGPT est interdite.
	Vous devez vous engager à faire ce contrôle par vous-même en recopiant
	la phrase dans le commentaire ci-dessous (section "À FAIRE AU DÉBUT") en
	indiquant bien votre prénom et votre nom.

	Commande pour se connecter à la base NetSerieFlix en tant qu'invite (mot de passe = invite) :
		psql -h postgres-info -U invite netserieflix
*/


/*** À FAIRE AU DÉBUT : 
Recopier ci-dessous la phrase :
"Je m'appelle [Prénom NOM]."
==> Je m'appelle Hugo BARBIERI et je m'engage à faire ce TP tout seul, sans l'aide d'une personne extérieure.

*/


/*** REQUETE 1 :
	Le nom et la note des séries
	qui ont une note strictement supérieure à 95.

	Quelles sont les opérations relationnelles à réaliser ? :
	==> Il faut utiliser un select, un from et un WHERE avec l'attribut note > 95.

	### Résultats attendus : ###
	    nom_serie    | note 
	-----------------+------
	 Black Mirror    |   98
	 Stranger Things |   96
	(2 rows)
***/

SELECT nom_serie, note
FROM SERIE 
WHERE note > 95;


/*** REQUETE 2 :
	Le nom des genres associés à la série qui a l'ID 1.

	Quelles sont les opérations relationnelles à réaliser ? :
	==> Il faut utiliser 2 tables : Appartenir et genre; que l'on relie avec l'attribut
	id_genre dans le WHERE puis on séléctionne seulement les série d'id 1 dans le AND.

	### Résultats attendus : ###
	    nom_genre    
	-----------------
	 Science-Fiction
	 Fantastique
	(2 rows)
***/

SELECT nom_genre
FROM APPARTENIR a, GENRE g 
WHERE g.id_genre = a.id_genre
AND id_serie = 1;


/*** REQUETE 3 :
	Le nom des épisodes dont le nom commence par 'Retour',
	triés par ordre alphabétique.

	Quelles sont les opérations relationnelles à réaliser ? :
	==> On utilise l'opérateur LIKE dans le WHERE pour préciser que nom_ep doit contenir 'Retour'
	puis on rajoute un % qui permet de préciser qu'il peut y avoir n'importe quoi après 'Retour'
	On utilise ensuite ORDER BY pour trier les résultats en fonction de nom_ep, par défaut par ordre alphabétique. 

	### Résultats attendus : ###
	       nom_ep       
	--------------------
	 Retour aux sources
	 Retour sur image
	(2 rows)
***/

SELECT nom_ep
FROM EPISODE
WHERE nom_ep LIKE 'Retour%'
ORDER BY nom_ep;

/*** REQUETE 4 :
	Le nom de séries qui ont commencé en 2010 ou après 2010
	(C'est-à-dire qui ont une 1ère saison à partir de 2010).

	Quelles sont les opérations relationnelles à réaliser ? :
	==> On commence par lier les deux tables SERIE et SAISON par l'attribut id_serie.
	Ensuite on vérifie que le numéro de la saison est bien 1 car nous cherchons à
	vérifier l'année de début de la série.
	Enfin on vérifie que l'année de la saison est bien supérieur ou égale à 2010.

	### Résultats attendus : ###
	    nom_serie    
	-----------------
	 Black Mirror
	 Stranger Things
	(2 rows)
***/

SELECT DISTINCT nom_serie
FROM SERIE s, SAISON sa
WHERE s.id_serie = sa.id_serie
AND num_saison = 1
AND annee >= 2010;


/*** REQUETE 5 :
	Le nom de la série qui appartient à la fois au genre Fantastique
	et au genre Thriller.

	Quelles sont les opérations relationnelles à réaliser ? :
	==> Il faut utiliser l'opérateur IN pour vérifier si id_serie est bien dans la liste
	des séries qui ont pour genre Fantastique et pour vérifier si id_serie est biend dans la 
	liste des séries qui ont pour genre Thriller.
	
	### Résultats attendus : ###
	    nom_serie    
	-----------------
	 Stranger Things
	(1 row)
***/

SELECT nom_serie
FROM SERIE
WHERE id_serie IN (SELECT id_serie FROM APPARTENIR a, GENRE g 
					WHERE a.id_genre = g.id_genre AND nom_genre = 'Fantastique')
AND id_serie IN (SELECT id_serie FROM APPARTENIR a, GENRE g 
					WHERE a.id_genre = g.id_genre AND nom_genre = 'Thriller');



/*** REQUETE 6 :
	Le nom des genres associés soit à la série Black Mirror,
	soit à la série Stranger Things, soit aux deux.
	Les résultats ne doivent pas comporter de doublons,
	et doivent être triés par ordre alphabétique inversé
	(à l'inverse de l'ordre du dictionnaire).
	
	### Résultats attendus : ###
	    nom_genre    
	-----------------
	 Thriller
	 Science-Fiction
	 Fantastique
	(3 rows)
***/

SELECT DISTINCT nom_genre
FROM GENRE 
WHERE id_genre IN (SELECT id_genre FROM APPARTENIR a, SERIE s
					WHERE a.id_serie = s.id_serie
					AND nom_serie = 'Black Mirror')
OR id_genre IN (SELECT id_genre FROM APPARTENIR a, SERIE s
					WHERE a.id_serie = s.id_serie
					AND nom_serie = 'Stranger Things')
ORDER BY nom_genre DESC;


/*** REQUETE 7 :
	Les différentes durées des épisodes de la série The IT Crowd.
	Les durées doivent être triées de la plus LONGUE à la plus COURTE durée
	et il ne doit pas y avoir de doublon dans les résultats.
	
	### Résultats attendus : ###
	 duree_en_min 
	--------------
	           31
	           25
	           24
	           23
	           22
	(5 rows)         
***/

SELECT DISTINCT duree_en_min
FROM EPISODE e, SERIE s 
WHERE e.id_serie = s.id_serie
AND nom_serie = 'The IT Crowd'
ORDER BY duree_en_min DESC;


/*** REQUETE 8 :
	Le numéro, le nom et la durée des épisodes de la saison 2
	de Black Mirror, triés du plus COURT au plus LONG.
	
	### Résultats attendus : ###
	 num_ep |      nom_ep       | duree_en_min 
	--------+-------------------+--------------
	      2 | La Chasse         |           42
	      3 | Le Show de Waldo  |           43
	      1 | Bientôt de retour |           48
	      4 | Blanc comme neige |           73
	(4 rows)
***/

SELECT num_ep, nom_ep, duree_en_min
FROM EPISODE e, SERIE s
WHERE e.id_serie = s.id_serie
AND num_saison = 2
AND nom_serie = 'Black Mirror'
ORDER BY duree_en_min;


/*** REQUETE 9 :
	Les saisons qui sont sorties entre 2010 et 2011,
	avec le nom de la série et le numéro de la saison.
	Les résultats doivent être triés par nom de série dans l'ordre alphabétique.
	
	### Résultats attendus : ###
	  nom_serie   | num_saison 
	--------------+------------
	 Black Mirror |          1
	 Hero Corp    |          2
	 The IT Crowd |          4
	(3 rows)
***/

SELECT nom_serie, num_saison
FROM SAISON sa, SERIE s
WHERE s.id_serie = sa.id_serie
AND annee >= 2010
AND annee <= 2011
ORDER BY nom_serie;


/*** REQUETE 10 :
	Le nom et la durée de l'épisode de Black Mirror qui dure le plus longtemps.
	
	### Résultats attendus : ###
	     nom_ep      | duree_maxi 
	-----------------+------------
	 Haine virtuelle |         89
	(1 row)
***/

SELECT nom_ep, max(duree_en_min) as duree_maxi
FROM EPISODE
GROUP BY nom_ep;


/*** REQUETE 11 :
	Le nombre de saisons pour chaque série, 
	avec les résultats triés du plus petit au plus grand nombre de saisons.
	
	### Résultats attendus : ###
	    nom_serie    | nb_saisons 
	-----------------+------------
	 Hero Corp       |          2
	 Stranger Things |          3
	 Black Mirror    |          4
	 The IT Crowd    |          4
	(4 rows)
***/

SELECT nom_serie, count(SELECT num_saison FROM SAISON sa WHERE sa.id_serie = s.id_serie) as nb_saisons
FROM SERIE s
GROUP BY nom_serie
ORDER BY nb_saisons;


/*** REQUETE 12 :
	Le nom et le nombre d'épisodes des séries qui
	ont au moins 25 épisodes différents.
	Les résultats doivent être triés du plus GRAND
	au plus PETIT nombre d'épisodes.
	
	### Résultats attendus : ###
	    nom_serie    | nb_episodes 
	-----------------+-------------
	 Hero Corp       |          30
	 Stranger Things |          25
	(2 rows)
***/

SELECT nom_serie, count(num_ep) as nb_episodes
FROM EPISODE e, SERIE s
WHERE e.id_serie = s.id_serie 
GROUP BY nom_serie
HAVING count(num_ep) >= 25
ORDER BY nb_episodes DESC;



/*** REQUETE 13 :
	Le temps total nécessaire pour voir tous les épisodes
	de la série Black Mirror (en minutes).
	
	### Résultats attendus : ###
	 somme_durees 
	--------------
	         1090
	(1 row)
***/

SELECT sum(duree_en_min)as somme_durees
FROM EPISODE e, SERIE s 
WHERE e.id_serie = s.id_serie
AND nom_serie = 'Black Mirror';


/*** REQUETE 14 :
	Pour chaque saison de Stranger Things, la moyenne de durée des épisodes.
	Les moyennes doivent être arrondies au 1er chiffre après la virgule.
	
	### Résultats attendus : ###
	 num_saison | moy_duree 
	------------+-----------
	          1 |      49.4
	          2 |      51.6
	          3 |      56.1
	(3 rows)
***/

SELECT num_saison, round(avg(duree_en_min), 1)as moy_duree
FROM EPISODE e, SERIE s 
WHERE e.id_serie = s.id_serie
AND nom_serie = 'Stranger Things'
GROUP BY num_saison;


/*** REQUETE 15 :
	Le nom des séries qui ont le plus grand nombre de saisons.
	Si plusieurs séries ont le même nombre de saisons,
	on veut toutes les afficher.

	### Résultats attendus : ###
	  nom_serie   
	--------------
	 Black Mirror
	 The IT Crowd
	(2 rows)
***/

SELECT nom_serie
FROM SERIE s, SAISON sa
WHERE s.id_serie = sa.id_serie
GROUP BY nom_serie, sa.num_saison
HAVING num_saison IN (SELECT max(num_saison) FROM SAISON)
ORDER BY nom_serie;


/*** REQUETE 16 :
	Le nom des séries qui n'ont AUCUN épisode qui dépasse 30 minutes.

	### Résultats attendus : ###
	  nom_serie   
	--------------
	 Hero Corp
	(1 row)
***/

SELECT nom_serie
FROM SERIE
WHERE id_serie IN (SELECT id_serie 
					FROM EPISODE 
					GROUP BY id_serie, duree_en_min
					HAVING duree_en_min < 30);


/*** REQUETE 17 :
	Nom de la série qui appartient seulement au genre Comique
	(et pas à un autre genre).

	### Résultats attendus : ###
	  nom_serie   
	--------------
	 The IT Crowd
	(1 row)
***/

SELECT nom_serie
FROM SERIE s, APPARTENIR a, GENRE g
WHERE s.id_serie = a.id_serie AND a.id_genre = g.id_genre
AND nom_genre = 'Comique'
EXCEPT 
SELECT nom_serie
FROM SERIE s, APPARTENIR a, GENRE g
WHERE s.id_serie = a.id_serie AND a.id_genre = g.id_genre
AND nom_genre != 'Comique';


/*** REQUETE 18 :
	Le numéro et le nom de tous les épisodes de la saison 2 de Black Mirror,
	sauf celui qui s'appelle 'La Chasse', triés par numéros d'épisodes croissants.

	### Résultats attendus : ###
	 num_ep |      nom_ep       
	--------+-------------------
	      1 | Bientôt de retour
	      3 | Le Show de Waldo
	      4 | Blanc comme neige
	(3 rows)
***/

SELECT num_ep, nom_ep
FROM EPISODE e, SERIE s
WHERE e.id_serie = s.id_serie
AND nom_serie = 'Black Mirror'
AND num_saison = 2
EXCEPT
SELECT num_ep, nom_ep
FROM EPISODE e
WHERE nom_ep = 'La Chasse'
ORDER BY num_ep;


/*** REQUETE 19 :
	Le nom et la durée des 3 plus courts épisodes
	de la série Black Mirror, avec les résultats
	triés du plus COURT au plus LONG épisode.

	### Résultats attendus : ###
	      nom_ep      | duree_en_min 
	------------------+--------------
	 Tête de Métal    |           41
	 La Chasse        |           42
	 Le Show de Waldo |           43
	(3 rows)
***/

SELECT nom_ep, duree_en_min
FROM EPISODE e, SERIE s
WHERE e.id_serie = s.id_serie
AND nom_serie = 'Black Mirror'
ORDER BY duree_en_min
LIMIT 3;


/*** REQUETE 20 :
	Le nombre d'épisodes pour chaque saison de la série Black Mirror.

	### Résultats attendus : ###
	 num_saison | nb_episodes 
	------------+-------------
	          1 |           3
	          2 |           4
	          3 |           6
	          4 |           6
	(4 rows)
***/

SELECT num_saison, (SELECT count(*)FROM EPISODE e2 WHERE e.num_saison = e2.num_saison AND e.id_serie = e2.id_serie)as nb_episodes
FROM EPISODE e, SERIE s
WHERE e.id_serie = s.id_serie
AND nom_serie = 'Black Mirror'
GROUP BY num_saison, e.id_serie;


/*** REQUETE 21 :
	Le nom des séries et la durée moyenne des épisodes des séries ayant
	au moins 25 épisodes (25 ou plus), avec les résultats triés alphabétiquement par
	nom de série.

	### Résultats attendus : ###
	    nom_serie    |    duree_moyenne    
	-----------------+---------------------
	 Hero Corp       | 23.4333333333333333
	 Stranger Things | 52.3200000000000000
	(2 rows)
***/

SELECT nom_serie, (SELECT avg(duree_en_min) FROM EPISODE WHERE id_serie = s.id_serie)as duree_moyenne
FROM SERIE s
WHERE (SELECT avg(duree_en_min) FROM EPISODE WHERE id_serie = s.id_serie) >= 25
GROUP BY id_serie
ORDER BY nom_serie;


/*** FIN ***/
