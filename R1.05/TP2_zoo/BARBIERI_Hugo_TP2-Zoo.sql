/*
_________________________________________________________
--
--          Requêtes TP2 (fichier à compléter)
--
_________________________________________________________
*/

/*------------------------------------------------------------------------------
(a) Nom et numéro des 3 premiers gardiens selon l'ordre alphabétique
------------------------------------------------------------------------------*/
SELECT nomgard, numgard
FROM gardien
ORDER BY nomgard
LIMIT 3;

/*------------------------------------------------------------------------------
(b)	Nom, espèce et numéro de cage des animaux dont s'occupent Tintin ou Milou
    (résultat ordonné sur le nom d'animal)
------------------------------------------------------------------------------*/
SELECT nomani, espece, numcage
FROM animal
WHERE numcage IN (SELECT numcage FROM soccupe WHERE numgard = (SELECT numgard FROM gardien WHERE nomgard = 'Tintin') OR numgard = (SELECT numgard FROM gardien WHERE nomgard = 'Milou')) 
ORDER BY nomani;
------------------------------------
SELECT nomani, espece, a.numcage
FROM animal a, soccupe s, gardien g
WHERE a.numcage = s.numcage AND g.numgard = s.numgard
AND (g.nomgard = 'Tintin' OR g.nomgard = 'Milou')
GROUP BY a.nomani
ORDER BY nomani;



/*------------------------------------------------------------------------------
(c)	Nom, espèce et numéro de cage des animaux dont s'occupent Tintin et Milou
------------------------------------------------------------------------------*/
SELECT nomani, espece, numcage
FROM animal
WHERE numcage IN (SELECT numcage FROM soccupe WHERE numgard = (SELECT numgard FROM gardien WHERE nomgard = 'Tintin') AND numgard = (SELECT numgard FROM gardien WHERE nomgard = 'Milou')) 
ORDER BY nomani;
---------------------------------------
SELECT nomani, espece, a.numcage
FROM animal a, soccupe s, gardien g
WHERE a.numcage = s.numcage AND g.numgard = s.numgard
AND (g.nomgard = 'Tintin' AND g.nomgard = 'Milou')
GROUP BY a.nomani
ORDER BY nomani;

/*------------------------------------------------------------------------------
(d)	Nom des gardiens qui ne s'occupent d'aucune cage
Une version avec opérateur ensembliste + une version avec sous-requête(s)
------------------------------------------------------------------------------*/
SELECT nomgard
FROM gardien
EXCEPT
SELECT nomgard
FROM gardien g, soccupe s
WHERE g.numgard = s.numgard; 

-----------------------------

SELECT nomgard 
FROM gardien
WHERE numgard NOT IN (SELECT numgard FROM soccupe);

/*------------------------------------------------------------------------------
(e)	Nom des gardiens qui s'occupent d'éléphants
------------------------------------------------------------------------------*/
SELECT nomgard
FROM gardien
WHERE numgard IN (SELECT numgard FROM soccupe WHERE numcage IN (SELECT numcage FROM animal WHERE espece = 'éléphant'))
ORDER BY nomgard;

/*------------------------------------------------------------------------------
(f)	Nom des gardiens qui ne s'occupent pas d'éléphants
		(mais qui s'occupent d'au moins une cage)
------------------------------------------------------------------------------*/
SELECT nomgard
FROM gardien
WHERE numgard IN (SELECT numgard FROM soccupe) AND numgard NOT IN (SELECT numgard FROM soccupe WHERE numcage IN (SELECT numcage FROM animal WHERE espece = 'éléphant'))
ORDER BY nomgard;

/*------------------------------------------------------------------------------
(g)	Nombre d'animaux qui cohabitent avec l'éléphant dumbo
------------------------------------------------------------------------------*/

SELECT (count(*)-1) AS colloc_dumbo
FROM animal
WHERE numcage = (SELECT numcage FROM animal WHERE nomani = 'dumbo');

/*------------------------------------------------------------------------------
(h)	Nom et espèce des animaux qui cohabitent avec l'éléphant dumbo
------------------------------------------------------------------------------*/
SELECT nomani, espece
FROM animal
WHERE numcage = (SELECT numcage FROM animal WHERE nomani = 'dumbo')
EXCEPT
SELECT nomani, espece
FROM animal
WHERE nomani = 'dumbo';

/*------------------------------------------------------------------------------
(i) Nom et adresse des gardiens nés en 1980
------------------------------------------------------------------------------*/
SELECT nomgard, adresse
FROM gardien g
WHERE EXTRACT(YEAR FROM datenaissgard) = 1980;

/*------------------------------------------------------------------------------
(j) Âge qu'a atteint aujourd'hui le gardien Gaston Lagaffe
------------------------------------------------------------------------------*/
SELECT DATE(NOW()) AS datecalcul, (AGE(DATE(NOW ()), datenaissgard)) 
FROM gardien
WHERE nomgard = 'Gaston Lagaffe';

/*------------------------------------------------------------------------------
(k)	Par ordre chronologique, date du diagnostic, nom de la maladie
    et nom de l'animal malade, pour chaque cas de maladie enregistré en 2022
------------------------------------------------------------------------------*/
SELECT datedebutmal, maladie, nomani
FROM suivi_mal
WHERE EXTRACT(YEAR FROM datedebutmal) = 2022
ORDER BY datedebutmal;


/*------------------------------------------------------------------------------
(l)	Nombre d'éléphants qui ont eu au moins une fois le typhus
------------------------------------------------------------------------------*/
SELECT count(DISTINCT nomani) AS atteint_typhus
FROM suivi_mal
WHERE nomani IN (SELECT nomani FROM animal WHERE espece = 'éléphant') AND maladie = 'typhus';


/*------------------------------------------------------------------------------
(m)	Nom et espèce des animaux qui sont encore malades aujourd'hui
------------------------------------------------------------------------------*/
SELECT DISTINCT a.nomani, espece 
FROM animal a, suivi_mal s
WHERE a.nomani = s.nomani
AND s.datefinmal IS NULL;

/*------------------------------------------------------------------------------
(n)	Nom des animaux qui ont contracté au moins deux maladies différentes
------------------------------------------------------------------------------*/
SELECT DISTINCT nomani
FROM suivi_mal s
WHERE (SELECT count(DISTINCT (nomani, maladie)) FROM suivi_mal WHERE s.nomani = nomani GROUP BY nomani) >= 2;

/*------------------------------------------------------------------------------
(o)	Numéro des cages où sont gardés des animaux qui ont déjà contracté
    le typhus et la gale
------------------------------------------------------------------------------*/
SELECT numcage
FROM animal
WHERE nomani IN (SELECT nomani FROM suivi_mal WHERE maladie = 'typhus')
AND nomani IN (SELECT nomani FROM suivi_mal WHERE maladie = 'gale');

/*------------------------------------------------------------------------------
(p)	Nom et numéro de cage des animaux qui ont été ou sont malades et dont
    le gardient Gaston Lagaffe a la charge
		(résultat ordonné sur le numéro de cage puis sur le nom d'animal)
------------------------------------------------------------------------------*/
SELECT nomani, numcage
FROM animal
WHERE nomani IN (SELECT nomani FROM suivi_mal)
AND numcage IN (SELECT numcage FROM soccupe WHERE numgard = (SELECT numgard FROM gardien WHERE nomgard = 'Gaston Lagaffe'))
ORDER BY numcage, nomani;


/*------------------------------------------------------------------------------
(q)	Nombre de jours minimum qu'un animal a mis pour guérir d'une maladie
		qu'il avait contractée
------------------------------------------------------------------------------*/
SELECT min(AGE(datefinmal, datedebutmal)) AS min_nbjoursmal
FROM suivi_mal;

/*------------------------------------------------------------------------------
(r)	Nombre de jours minimum qu'un animal a mis pour guérir de la gale et
		Nombre de jours minimum qu'un animal a mis pour guérir du typhus
INDICATION : utliser des sous-requêtes pour déterminer chacun des nombres de
jours à afficher
------------------------------------------------------------------------------*/
SELECT (SELECT min(AGE(datefinmal, datedebutmal)) FROM suivi_mal WHERE maladie = 'gale') AS min_nbj_gale, (SELECT min(AGE(datefinmal, datedebutmal)) FROM suivi_mal WHERE maladie = 'typhus') AS min_nbj_typhus
FROM suivi_mal
LIMIT 1;

/*------------------------------------------------------------------------------
(s)	Pour chaque animal du zoo, nombre de maladies différentes
    qu'il a contractées (résultat ordonné par nombre de maladies décroissant)
ATTENTION, certains animaux n'ont encore jamais été malades !
------------------------------------------------------------------------------*/
SELECT nomani, (SELECT count(DISTINCT maladie) FROM suivi_mal WHERE a.nomani = nomani) AS nb_maladies_diff
FROM animal a
ORDER BY nb_maladies_diff DESC; 


/*------------------------------------------------------------------------------
(t)	Nombre d'animaux par cage du zoo – le numéro et le type de chaque cage
    sera affiché (résultat ordonné par nombre d'animaux décroissant)
ATTENTION - il peut y avoir des cages vides
------------------------------------------------------------------------------*/
SELECT numcage, typecage, (SELECT count(DISTINCT nomani) FROM animal WHERE c.numcage = numcage) AS nb_ani
FROM cage c
ORDER BY nb_ani DESC;

/*------------------------------------------------------------------------------
(u)	Nombre de cages affectées à chaque gardien – le nom du gardien doit être
    affiché (pas son numéro)
    (résultat ordonné par nombre de cages décroissant)
------------------------------------------------------------------------------*/
SELECT nomgard, (SELECT count(numcage) FROM soccupe WHERE g.numgard = numgard) AS nb_cages
FROM gardien g
ORDER BY nb_cages DESC;

/*------------------------------------------------------------------------------
(v)	Nombre d'animaux dont s'occupe chaque gardien – le nom du gardien doit être
    affiché (pas son numéro)
    (résultat ordonné par nombre d'animaux décroissant)
------------------------------------------------------------------------------*/
SELECT nomgard, (SELECT count(*) FROM animal a, soccupe s WHERE a.numcage = s.numcage AND s.numgard = g.numgard) AS nb_ani_en_charge
FROM gardien g
ORDER BY nb_ani_en_charge DESC;

/*------------------------------------------------------------------------------
(w)	Nom, âge (en années) et adresse des gardiens qui s'occupent
    d'au moins 7 animaux
------------------------------------------------------------------------------*/
SELECT nomgard, AGE(DATE(NOW()), datenaissgard), adresse
FROM gardien g
WHERE (SELECT count(*) FROM animal a, soccupe s WHERE a.numcage = s.numcage AND s.numgard = g.numgard) >= 7;


/*------------------------------------------------------------------------------
(x)	Titeuf s'occupe de beaucoup d'animaux ! On voudrait en savoir plus…
    1.	Combien de cages dont il s'occupe abritent des éléphants ?
    2.	Quel est l'âge en années du plus vieil animal (ou des plus vieux animaux)
        dont il s'occupe ?
    3.	Pour chaque espèce d'animal, combien a-t-il d'animaux en charge ?
------------------------------------------------------------------------------*/
-- 1.

SELECT count(DISTINCT numcage) AS nb_cages_éléphant
FROM animal
WHERE numcage IN (SELECT numcage FROM soccupe s, gardien g WHERE s.numgard = g.numgard AND nomgard='Titeuf')
AND espece='éléphant';

-- 2.
SELECT max(EXTRACT(YEAR FROM AGE(DATE(NOW()), datenaissani))) || ' ans'  AS age_plus_vieux_ani
FROM animal
WHERE numcage IN (SELECT numcage FROM soccupe s, gardien g WHERE s.numgard = g.numgard AND nomgard='Titeuf');
-- Correction fausse, l'âge d'un animal n'est pas l'année de sa naissance...

--3.
SELECT espece, (SELECT count(*)
                         FROM animal
                         WHERE espece = a.espece
                         AND numcage IN (SELECT numcage FROM soccupe s, gardien g WHERE s.numgard = g.numgard AND nomgard='Titeuf'))
    AS nb_animaux
FROM animal a
GROUP BY espece;

--------------------------------------------------------------------------------
-- FIN !!!
--------------------------------------------------------------------------------
---------------------
