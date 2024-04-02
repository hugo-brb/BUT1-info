| ====================================================================
| === Constantes et Variables globales
| ====================================================================
.section .data

| === Constantes ===
.set CONSTANTE_1,66             | decimal
.set CONSTANTE_2,0xDEADBEEF     | hexadecimal
.set CONSTANTE_3,0b10011011     | binaire

| === Variables === 
	.org 0x0500                 | en mémoire à partir de 0x00FF0500
	                            | (0x00FF0000 + 0x0500)
| Exemples de declarations

resultat: dc.l 55               | variable de 4 octets initialisee
var_1: ds.l 1                   | variable de 4 octets non initialisee
var_2: dc.w 0x9999              | variable de 2 octets initialisee
var_3: ds.l 10                  | tableau de 10 elements sur 4 octets
var_4: dc.b 0b10011011          | variable de 1 octet initialisee
var_5: .ascii "Hello, World\0"  | chaine de caracteres

| ====================================================================
| === Instructions du programme
| ====================================================================
.section .text

.include "inc/prologue.s" | <=== Ne pas supprimer

	move.l 0x00AABBCC,%d7 | <=== point d'arret
| === DEBUT
.debut:	
	move.l resultat,%d1		 
	add.l  resultat, %d1
	add.l  resultat, %d1	 | resultat := resultat * 3
	add.l  #CONSTANTE_1,%d1  | resultat := resultat + CONSTANTE_1
	move.l %d1,resultat      |

| === FIN
.include "inc/epilogue.s" | <=== Ne pas supprimer
