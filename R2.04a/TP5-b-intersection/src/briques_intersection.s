.section .data
| ====================================================================
| === Constantes et Variables globales
| ====================================================================

| === Variables ===   | en mémoire à partir de 0x00FF0000

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

| === DEBUT

FCT_is_touching:
| contexte : %d1
| arg No 1 : s16 x1      (entier.W)
| arg No 2 : s16 x2      (entier.W)
| arg No 3 : s16 seuil   (entier.W)

MOVEA       %sp, %a0
MOVE.w      4(%a0), %d1
SUB.W       6(%a0), %d1
CMP         8(%a0), %d1
BLT         .vrai 
MOVE.W      #0, %d0
RTS

.vrai:
MOVE.w      #1, %d0
RTS

| FCT_is_intersecting_brick
| arg No 1 : s16 ball_x  (entier.W)
| arg No 2 : s16 ball_y  (entier.W)
| arg No 3 : s16 brick_x (entier.W)
| arg No 4 : s16 brick_y (entier.W)
|
FCT_is_intersecting_brick:
|	move.l 0x00AABBCC,%d7 | <=== point d'arret

MOVEA       %sp, %a0

MOVE.W      #12, -(%sp)
MOVE.W      8(%a0), -(%sp)
MOVE.w      4(%a0), -(%sp)
SUBI.w      #4, (%sp)
JSR         FCT_is_touching
ADDA        #6, %sp

CMP         #1, %d0
BNE         .fin

MOVEA       %sp, %a0
MOVE.w      #8, -(%sp)
MOVE.w      10(%a0), -(%sp)
MOVE.W      6(%a0), -(%sp)
JSR         FCT_is_touching
ADDA        #6, %sp

CMP         #1, %d0
BNE         .fin

MOVE.W      #1, %d0
RTS

.fin:
MOVE.w      #0, %d0
RTS


| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
