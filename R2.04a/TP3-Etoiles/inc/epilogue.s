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

	.globl	get_bricks_coords
    .type   get_bricks_coords, @function
get_bricks_coords:
    movem.l %d0-%d7/%a0-%a6,-(%sp)
    move.w 66(%sp),nb_row
    move.w 70(%sp),nb_col	
	bra .begin
	
.epilogue:	
	move.w 66(%sp),%d0
	mulu   70(%sp),%d0
    move.l 72(%sp),%a0
    move.l 76(%sp),%a1
	
    lea    x_bricks,%a2
    lea    y_bricks,%a3
	
	bra .L2
.L1:
	move.w (%a2)+,(%a0)+
	move.w (%a3)+,(%a1)+
	
	subi.w #1,%d0 
.L2:
    cmpi.w #0,%d0
	bgt    .L1	

	movem.l (%sp)+,%d0-%d7/%a0-%a6
    rts
