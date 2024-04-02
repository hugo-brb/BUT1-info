FCT_is_intersecting_paddle:
	movea.l %sp,%a0
	clr %d0
	move.w 10(%a0),%d0
	move.l %d0,-(%sp)
	move.w 8(%a0),%d0
	move.l %d0,-(%sp)
	move.w 6(%a0),%d0
	move.l %d0,-(%sp)
	move.w 4(%a0),%d0
	move.l %d0,-(%sp)
	jsr is_intersecting_paddle
	adda.l #16,%sp
	rts

.begin:
    nop
	nop
    nop
    nop
    nop
    nop
