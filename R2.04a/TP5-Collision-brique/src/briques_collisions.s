| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

.set NB_BRICKS,54

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

| FCT_check_collisions
| Variables
| u32 brick_index			   (%sp) <- (%a1) avec %a1 = %sp
| u32 i						  4(%sp) 
| contexte : a1               8(%sp)
| @retour                    12(%sp)
| arg No 1 : Brick * bricks  16(%sp)   
| arg No 2 : s16 ball_x      20(%sp)
| arg No 3 : s16 ball_y      22(%sp)
| arg No 4 : s16 * ptr_dir_x 24(%sp)
| arg No 5 : s16 * ptr_dir_y 28(%sp)
|
FCT_check_collisions:
    | move.l 0x00AABBCC,%d7 | <=== point d'arret
	
    | A COMPLETER

| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
