| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

.set YMAX,205

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

| === DEBUT

| FCT_collision_or_lost
| arg No 1 : ball_x (entier  .W) 4(%sp)
| arg No 2 : ball_y (entier  .W) 4(%sp)
| arg No 3 : pad_x  (entier  .W) 4(%sp)
| arg No 4 : pad_y  (entier  .W) 4(%sp)
| arg No 5 : dir_y  (adresse .L) 4(%sp)
| valeur de retour : 1 si perdu, 0 sinon
|

FCT_collision_or_lost:
	move.l 0x00AABBCC,%d7 | <=== point d'arret
	| A COMPLETER

| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
