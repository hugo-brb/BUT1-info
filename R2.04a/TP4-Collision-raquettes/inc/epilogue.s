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

	
	.globl	collision_or_lost
    .type   collision_or_lost, @function
collision_or_lost:
    movem.l %d1-%d7/%a0-%a6,-(%sp)  | contexte + @retour = 60 octets

	movea.l %sp,%a0
	move.l  76(%a0),-(%sp)   | ptr_dir_y
	move.w  74(%a0),-(%sp)   | pad_y
	move.w  70(%a0),-(%sp)   | pad_x
	move.w  66(%a0),-(%sp)   | ball_y
	move.w  62(%a0),-(%sp)   | ball_x
	move.l  #0,%d0
	jsr     FCT_collision_or_lost
	adda.l  #12,%sp
	movem.l (%sp)+,%d1-%d7/%a0-%a6
    rts
