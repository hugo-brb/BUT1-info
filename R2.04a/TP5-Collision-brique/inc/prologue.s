| FCT_is_bouncing_on_brick
| contexte : %d1 %a1
| arg No 1 : Brick * bricks  12(%sp)
| arg No 2 : u32 index       16(%sp)
| arg No 3 : s16 ball_x      20(%sp)
| arg No 4 : s16 ball_y      22(%sp)
| arg No 5 : s16 * ptr_dir_x 24(%sp)
| arg No 6 : s16 * ptr_dir_y 28(%sp)
|	
FCT_is_bouncing_on_brick:

	movea.l %sp,%a0
	clr %d0
	move.l 20(%a0),-(%sp)  | ptr_dir_y
	move.l 16(%a0),-(%sp)  | ptr_dir_x
	move.w 14(%a0),%d0     | ball_y
	move.l     %d0,-(%sp)  |
	move.w 12(%a0),%d0     | ball_x
	move.l     %d0,-(%sp)  | 
	move.l  8(%a0),-(%sp)  | index
	move.l  4(%a0),-(%sp)  | bricks
	jsr is_bouncing_on_brick
	adda.l #24,%sp
	rts
	
    nop
	nop
    nop
    nop
    nop
    nop
