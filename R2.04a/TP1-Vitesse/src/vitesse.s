| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

| === Constantes ===
.set XMIN,87        | coordonnees des murs
.set XMAX,228       |
.set YMIN,5         |
.set YMAX,220       |

| === Variables === | en mémoire à partir de 0x00FF0500
	.org 0x0500 

x:  dc.w 10         | coordonnee de la balle
y:  dc.w 20         |

vx: dc.w 1          | vecteur direction de la balle
vy: dc.w 1          |

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

	| move.l 0x00AABBCC,%d7 | <=== point d'arret
| === DEBUT	
six: 	
	CMP #XMAX,x		| x ? XMAX
	BHI fairex		| si  x > XMAX aller à fairex
	CMP #XMIN,x		| x ? XMIN
	BHI siy			| si x > XMIN aller à siy
fairex:
	NEG vx
	MOVE.W 	#3, %D0
	AND.W 	%D0, vx
	CMP 	vx, D0
	BEQ 	
siy: 	
	CMP #YMAX,y		| y ? YMAX
	BHI fairey		| si y > YMAX aller à fairey
	CMP #YMIN,y		| y ? YMIN
	BHI finy		| si y > YMIN aller à finy
fairey:
	NEG vy
finy: NOP
| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer