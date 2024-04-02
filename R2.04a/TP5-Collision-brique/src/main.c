#include "genesis.h"
#include "gfx.h"
#include "sprite.h"

#define XMIN 87
#define XMAX 228
#define YMIN 5
#define YMAX 220
#define RMIN 89
#define RMAX 200

#define NB_BRICKS_ROW 6
#define NB_BRICKS_COL 9 
#define NB_BRICKS (NB_BRICKS_ROW * NB_BRICKS_COL)

#define ABS(a) (((a) >= 0) ? (a) : -(a))

typedef struct {
	s16 x, y;
	s32 visible;
	u32 index;
	Sprite * sprite;	
} Brick;

Brick bricks[NB_BRICKS];

static void check_walls(s16 * ptr_ball_x, s16 * ptr_ball_y, s16 * ptr_dir_x, s16 * ptr_dir_y) {
	s16 x, y, vx, vy;
	
	x  = (*ptr_ball_x);  y = (*ptr_ball_y);
	vx = (*ptr_dir_x);   vy = (*ptr_dir_y);
	
	if ((x < XMIN) | (x > XMAX)) {
	   (*ptr_dir_x) = -vx;
	   (*ptr_ball_x) -= vx;
	}
	
	if ((y < YMIN) | (y > YMAX)) {
	   (*ptr_dir_y) = -vy;
	   (*ptr_ball_y) -= vy;
	}	
}

static void get_bricks_layout(u16 nb_bricks, u16 * id_bricks) {
	u32 i;
	
	for(i = 0; i < nb_bricks; i++) {
		id_bricks[i] = i & 3;
	}
}

static void get_bricks_coords(u16 nb_rows, u16 nb_cols, s16 * x_bricks, s16 * y_bricks) {
	u32 i, j;
	s16 x, y, idx;
	
	y = 30;
	idx = 0;
	for(i = 0; i < nb_rows; i++) {
		x = 88;
		for(j = 0; j < nb_cols; j++) {
			x_bricks[idx] = x;
			y_bricks[idx] = y;
			idx++;
			x += 16;			
		}		
		y += 8;
    }
}

static u32 is_intersecting_brick(s16 ball_x, s16 ball_y, s16 brick_x, s16 brick_y) {
	if (   (ABS(ball_x - brick_x - 4) < 12) 
	    && (ABS(ball_y - brick_y) < 8)) {
		return 1;
	}
	
	return 0;
}

extern u32 is_bouncing_on_brick(Brick * bricks, u32 index, s16 ball_x, s16 ball_y, s16 * ptr_dir_x, s16 * ptr_dir_y);
u32 is_bouncing_on_brick(Brick * bricks, u32 index, s16 ball_x, s16 ball_y, s16 * ptr_dir_x, s16 * ptr_dir_y) {
	Brick * brick = &(bricks[index]);
	
	if (   (brick -> visible == 1)
	    && is_intersecting_brick(ball_x, ball_y, brick -> x, brick -> y)) {
		brick -> visible = 0;
		
		if (   ((ball_x - brick -> x + 4) >= 0)
		    && ((ball_x - brick -> x + 4) <= 16)) {
			(*ptr_dir_y) = -(*ptr_dir_y);
		} else {
			(*ptr_dir_x) = -(*ptr_dir_x);
		}
		
		return 1;
	}
		
	return 0;
}

u32 check_collisions(Brick * bricks, s16 ball_x, s16 ball_y, s16 * dir_x, s16 * dir_y);

static u32 is_intersecting_paddle(s16 ball_x, s16 ball_y, s16 paddle_x, s16 paddle_y) {
    return (   (ABS(ball_x - paddle_x - 12) < 20)
	        && (ABS(ball_y - paddle_y) < 8));
}

static s16 get_paddle_direction(u16 pad_value) {
    if (pad_value & BUTTON_LEFT) return -1;
    else if (pad_value & BUTTON_RIGHT) return 1;
	
    return 0;
}

static s16 move_paddle(s16 paddle_x, u16 pad_value) {
	s16 paddle_direction;
	
    paddle_direction = get_paddle_direction(pad_value);
	if (paddle_direction !=0) {
		paddle_x += paddle_direction;
		if (paddle_x <= RMIN) { 
			paddle_x = RMIN; 
		} else if (paddle_x >= RMAX) { 
			paddle_x = RMAX; 
		}
	}
	
	return paddle_x;
}

static void create_sprites(Sprite ** sprites, s16 bx, s16 by, s16 rx, s16 ry) {
	u32 i;
	s16 x, y;
    s16 x_bricks[NB_BRICKS];
    s16 y_bricks[NB_BRICKS];
    u16 id_bricks[NB_BRICKS];
	
	sprites[0] = SPR_addSprite(&ball_sprite, bx, by, TILE_ATTR(PAL2, TRUE, FALSE, FALSE));
	sprites[1] = SPR_addSprite(&raq_sprite, rx, ry, TILE_ATTR(PAL1, TRUE, FALSE, FALSE));
    SPR_setAnim(sprites[0], 0);
	SPR_setAnim(sprites[1], 0);
	
	/* generation du layout des bricks */
	get_bricks_layout(NB_BRICKS, id_bricks);
	get_bricks_coords(NB_BRICKS_ROW, NB_BRICKS_COL, x_bricks, y_bricks);
	
	for(i = 0; i < NB_BRICKS; i++) {
		x = x_bricks[i];
		y = y_bricks[i];
		
		bricks[i].x = x;
		bricks[i].y = y;
		bricks[i].index = i;
		bricks[i].visible = 1;
		bricks[i].sprite = SPR_addSprite(&bricks_sprite, x, y, TILE_ATTR(PAL2, TRUE, FALSE, FALSE));
		SPR_setAnim(bricks[i].sprite, id_bricks[i]);
    }

	SPR_update();
}

static void loadPalettes(void) {
    u16 palette[64];
	
	// Creation des palettes de couleur a partir des sprites
	memcpy(&palette[0],  bg_image.palette->data, 16 * 2);
    memcpy(&palette[16], raq_sprite.palette->data, 16 * 2);
    memcpy(&palette[32], bricks_sprite.palette->data, 16 * 2);	
	
	// Affichage des images
    VDP_fadeIn(0, (3 * 16) - 1, palette, 20, FALSE);
}

static void breakout(void) {
    Sprite * sprt[2];
	u32 brick_index, game_over = 0;
	u16 pad_value;
    s16 ball_x  = 144, ball_y  = 100;
	s16 dir_x = 1, dir_y = -1;
	s16 paddle_x  = 156, paddle_y  = 204;

    // initialisation ecran	
	VDP_setScreenWidth320();
	
	// image de fond
	VDP_setPaletteColors(0, (u16*) palette_black, 64);
	VDP_setTextPlan(PLAN_B);
	VDP_setPalette(PAL3, palette_grey);
    VDP_setTextPalette(PAL3);
    	
	VDP_drawImageEx(PLAN_A, &bg_image, 
	                TILE_ATTR_FULL(PAL0, FALSE, FALSE, FALSE, TILE_USERINDEX), 
					0, 0, FALSE, TRUE);
					
	// positionnement image de fond (scrolling)
    VDP_setHorizontalScroll(PLAN_A, 0);
    VDP_setVerticalScroll(PLAN_A, 0);
	
	// Creation des sprites et des palettes des couleurs
	SPR_init(126, 0, 0);	
    create_sprites(sprt, ball_x, ball_y, paddle_x, paddle_y);	
	loadPalettes();
	
	// boucle d'animation
	while(1) {
		if (game_over) {
			SYS_disableInts();
			VDP_drawText("GAME OVER", 0, 2);
			SYS_enableInts();
            VDP_waitVSync();
			continue;
		}
		
		// touche du clavier / joystick
		pad_value = JOY_readJoypad(JOY_1);
		
		// position de la raquette
		paddle_x = move_paddle(paddle_x, pad_value);
		
		// nouvelle position du sprite pour la balle
		ball_x += dir_x;
		ball_y += dir_y;
		
		// intersection avec les bricks
        brick_index = check_collisions(bricks, ball_x, ball_y, &dir_x, &dir_y);
        
        if (brick_index != -1) {
			SPR_setVisibility(bricks[brick_index].sprite, 1);
		} else {
			// intersection avec un mur
			if (is_intersecting_paddle(ball_x, ball_y, paddle_x, paddle_y)) {
				dir_y = -dir_y;
			} else if (ball_y >= YMAX) {
				game_over = 1;
			} else {
				check_walls(&ball_x, &ball_y, &dir_x, &dir_y);
			}
		}
		
		// mise a jour
		SPR_setPosition(sprt[0], ball_x, ball_y);
	    SPR_setPosition(sprt[1], paddle_x, paddle_y);
	    SPR_update();
        VDP_waitVSync();
	}
}

int main() {
	breakout();
}