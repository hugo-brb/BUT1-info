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

	.globl	get_paddle_direction
    .type   get_paddle_direction, @function
get_paddle_direction:
    movem.l %d1-%d7/%a0-%a6,-(%sp)
    move.w  62(%sp),-(%sp)
	jsr     FCT_get_paddle_direction
	adda.l  #2,%sp
	movem.l (%sp)+,%d1-%d7/%a0-%a6
    rts
