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

	.globl	check_walls
    .type   check_walls, @function
check_walls:
    movem.l %d0-%d7/%a0-%a6,-(%sp)
    move.l 64(%sp),%a0
	move.w (%a0),x
	move.l 68(%sp),%a0
	move.w (%a0),y
	move.l 72(%sp),%a0
	move.w (%a0),vx
	move.l 76(%sp),%a0
	move.w (%a0),vy
	bra .begin
	
.epilogue:	
    move.l 64(%sp),%a0
	move.w x,(%a0)
	move.l 68(%sp),%a0
	move.w y,(%a0)
	move.l 72(%sp),%a0
	move.w vx,(%a0)
	move.l 76(%sp),%a0
	move.w vy,(%a0)
	movem.l (%sp)+,%d0-%d7/%a0-%a6
    rts
