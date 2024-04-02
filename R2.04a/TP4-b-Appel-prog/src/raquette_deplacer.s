| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

.set BUTTON_UP,0x0001
.set BUTTON_DOWN,0x0002
.set BUTTON_LEFT,0x0004
.set BUTTON_RIGHT,0x0008
.set RMIN,89
.set RMAX,200

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

| === DEBUT

| FCT_get_paddle_direction
| arg No 1 : pad_value (entier .W)
| valeur de retour : +1 si vers la droite, -1 si vers la gauche
|
FCT_get_paddle_direction:

CMPI 	#4, 4(%sp)
BEQ		.gauche
CMPI 	#8, 4(%sp)
BEQ		.droite
MOVE.W	#0, %d0
RTS

.gauche:
SUBI.W	#-1, %d0
RTS

.droite:
ADDI.W 	#1, %d0
RTS

| FCT_move_paddle
| arg No 1 : paddle_x (entier .W)
| arg No 2 : pad_value  (entier .W)
| valeur de retour : nouvelle position de la raquette (abscisse)
|
FCT_move_paddle:
|    move.l 0x00AABBCC,%d7 | <=== point d'arret

MOVE.w   6(%sp), -(%sp)
JSR      FCT_get_paddle_direction
ADD.W    4(%sp), %d0

CMPI    #89, %d0
BLS     .depassement
CMPI    #200, %d0
BCC     .depassement
RTS

.depassement:
MOVE.W  4(%sp), %d0
RTS


| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
