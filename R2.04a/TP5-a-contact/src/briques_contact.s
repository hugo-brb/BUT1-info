| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

| === DEBUT

| FCT_is_touching
| contexte : %d1
| arg No 1 : s16 x1      (entier.W)
| arg No 2 : s16 x2      (entier.W)
| arg No 3 : s16 seuil   (entier.W)

FCT_is_touching:
|    move.l 0x00AABBCC,%d7 | <=== point d'arret

LEA         (%sp), %a0
MOVE.w      4(%a0), %d1
SUB.W       6(%a0), %d1
CMP         8(%a0), %d1
BLT         .vrai 
MOVE.W      #0, %d0
RTS

.vrai:
MOVE.w      #1, %d0
RTS

| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
