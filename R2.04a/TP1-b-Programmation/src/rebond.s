| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

| === Constantes ===
.set XMIN,87        | coordonnees des murs
.set XMAX,228       |
.set YMIN,5         |
.set YMAX,220       |

| === Variables === | en mémoire à partir de 0x00FF0000

| Variables non initialisées
| Les valeurs sont mises à jour par le code appelant
x:  ds.w 1          | coordonnee de la balle
y:  ds.w 1          |

vx: ds.w 1          | vecteur direction de la balle
vy: ds.w 1          |

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

|	move.l 0x00AABBCC,%d7 | <=== point d'arret
| === DEBUT
si1: 	
	CMP #XMAX,x		| x ? XMAX
	BHI faire1		| si  x > XMAX aller à faire1
	CMP #XMIN,x		| x ? XMIN
	BHI si2			| si x > XMIN aller à fin1
faire1:
	NEG vx
si2: 	
	CMP #YMAX,y		| y ? YMAX
	BHI faire2		| si y > YMAX aller à faire2
	CMP #YMIN,y		| y ? YMIN
	BHI fin2		| si y > YMIN aller à fin2
faire2:
	NEG vy
fin2: NOP

| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
	
