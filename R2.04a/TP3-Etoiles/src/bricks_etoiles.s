| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

.set X_MIN,88
.set Y_MIN,30

| === Variables ===   | en mémoire à partir de 0x00FF0000

| NE PAS MODIFIER LES VARIABLES 

nb_row: ds.w  1       | nombre de lignes de briques
nb_col: ds.w  1       | nombre de colonnes de briques

x_bricks:  ds.w  100  | tableau de coordonnees des briques (abscisse)
y_bricks:  ds.w  100  | tableau de coordonnees des briques (ordonnee)

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer
	
| === DEBUT
.debut:
	move.l 0x00AABBCC,%d0 | <=== point arret
lea     x_bricks, %a1
lea     y_bricks,%a2
move.w	#1, %d3

.lesEtoiles:
move.w	#88, %d1
move.w 	#30, %d2
CMPI	#1, %d3
BEQ		.first
CMPI	#2, %d3
BEQ		.second
CMPI	#3, %d3
BEQ		.third
CMPI	#4, %d3
BEQ		.quattre


.uneEtoile:
addi.w	#1, %d3
addi.w	#16,%d1
move.w  %d1, (%a1)+
move.w  %d2, (%a2)+

move.w	#0, nb_row

.body:
CMPI.w   #2, nb_row
BHI      .footer

.uneLigne:
CMPI.w  #2,nb_col
BHI.w   .down
move.w  %d1, (%a1)+
move.w  %d2, (%a2)+
addi.w  #16, %d1
addi.w  #1, nb_col
BRA .uneLigne

.down:
addi    #8,%d2
addi    #1, nb_row
move.w  #88, %d1
move.w  #0, nb_col
BRA     .body

.footer:
move.w	#88, %d1
addi.w	#16,%d1
move.w  %d1, (%a1)+
move.w  %d2, (%a2)+
BRA 	.lesEtoiles

.first:
addi.w	#24, %d2
BRA		.uneEtoile
.second:
addi.w	#48, %d1
addi.w	#24, %d2
BRA		.uneEtoile
.third:
addi.w	#24, %d1
BRA		.uneEtoile
.quattre:
addi.w	#24, %d1
addi.w	#48, %d2
BRA		.uneEtoile

.fin: NOP
	
| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
