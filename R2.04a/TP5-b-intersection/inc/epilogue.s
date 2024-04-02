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

	.globl	is_intersecting_brick
    .type   is_intersecting_brick, @function
is_intersecting_brick:
    movem.l %d1-%d7/%a0-%a6,-(%sp)
	move.w  62(%sp),%d3   | s16 ball_x
	move.w  66(%sp),%d4   | s16 ball_y
	move.w  70(%sp),%d5   | s16 brick_x
	move.w  74(%sp),%d6   | s16 brick_y
	
    move.w  %d6,-(%sp)    | brick_y
    move.w  %d5,-(%sp)    | brick_x
    move.w  %d4,-(%sp)    | ball_y
	move.w  %d3,-(%sp)    | ball_x
	move.l  #0,%d0
	jsr     FCT_is_intersecting_brick
	adda.l  #8,%sp
	movem.l (%sp)+,%d1-%d7/%a0-%a6
    rts
