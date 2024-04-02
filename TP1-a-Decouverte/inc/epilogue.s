| ====================================================================
| === NE PAS MODIFIER
| ====================================================================
    nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	bra .epilogue

	.globl	exemple
    .type   exemple, @function
exemple:
    movem.l %d0-%d7/%a0-%a6,-(%sp) | sauvegarde complete du contexte
	bra .begin
	
.epilogue:
	movem.l (%sp)+,%d0-%d7/%a0-%a6 | restauration complete du contexte
	move.l  resultat,%d0           | valeur de retour
    rts
