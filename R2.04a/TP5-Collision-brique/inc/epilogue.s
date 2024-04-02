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

	.globl	check_collisions
    .type   check_collisions, @function
check_collisions:
    movem.l %d1-%d7/%a0-%a6,-(%sp)
	move.l  60(%sp),%a4   | Brick * bricks
	move.w  66(%sp),%d3   | s16 ball_x
	move.w  70(%sp),%d4   | s16 ball_y
	move.l  72(%sp),%a5   | s16 * ptr_dir_x
	move.l  76(%sp),%a6   | s16 * ptr_dir_y
	
    move.l  %a6,-(%sp)    | ptr_dir_y
    move.l  %a5,-(%sp)    | ptr_dir_x
	move.w  %d4,-(%sp)    | ball_y
	move.w  %d3,-(%sp)    | ball_x
    move.l  %a4,-(%sp)    | bricks	
	jsr     FCT_check_collisions
	adda.l  #16,%sp
	movem.l (%sp)+,%d1-%d7/%a0-%a6
    rts
