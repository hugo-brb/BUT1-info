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

	.globl	move_paddle
    .type   move_paddle, @function
move_paddle:
    movem.l %d1-%d7/%a0-%a6,-(%sp)
	move.w  66(%sp),%d5   | pad_value
	move.w  62(%sp),%d6   | paddle_x
	
    move.w  %d5,-(%sp)    | pad_value
    move.w  %d6,-(%sp)    | paddle_x	
	jsr     FCT_move_paddle
	adda.l  #4,%sp
	movem.l (%sp)+,%d1-%d7/%a0-%a6
    rts
