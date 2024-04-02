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
	rts

	.globl	is_touching
    .type   is_touching, @function
is_touching:
    movem.l %d1-%d7/%a0-%a6,-(%sp)
	move.w  62(%sp),%d3   | s16 x1
	move.w  66(%sp),%d4   | s16 x2
	move.w  70(%sp),%d5   | s16 seuil
	
    move.w  %d5,-(%sp)    | seuil
    move.w  %d4,-(%sp)    | x2
    move.w  %d3,-(%sp)    | x1
	move.l  #0,%d0
	jsr     FCT_is_touching
	adda.l  #6,%sp
	movem.l (%sp)+,%d1-%d7/%a0-%a6
    rts

