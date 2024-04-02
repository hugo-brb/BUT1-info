| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

| === Variables ===   | en mémoire à partir de 0x00FF0000

| /!\ ATTENTION : ce sont des variables non initialisées
| Les valeurs seront affectées par le programme appelant
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
	
	move.l 0x00AABBCC,%d7 | <=== point arret
| === DEBUT
.debut:
lea     x_bricks, %a1
lea     y_bricks,%a2

move.w  #88, %d1
move.w  #30, %d2
move.w  #0, nb_col
move.w  #0,nb_row

.colonnes:
CMPI.w   #5, nb_row
BHI      .fin
.uneLigne:
CMPI.w  #8,nb_col
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
BRA     .colonnes

.fin:   NOP	
| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
