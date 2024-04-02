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

void check_walls(s16 * ptr_ball_x, s16 * ptr_ball_y, s16 * ptr_dir_x, s16 * ptr_dir_y);

static void create_sprites(Sprite ** sprites, s16 bx, s16 by) {
	sprites[0] = SPR_addSprite(&ball_sprite, bx, by, TILE_ATTR(PAL2, TRUE, FALSE, FALSE));
    SPR_setAnim(sprites[0], 0);
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
    s16 ball_x  = 144, ball_y  = 100;
	s16 dir_x = 1, dir_y = -1;

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
    create_sprites(sprt, ball_x, ball_y);	
	loadPalettes();
	
	// boucle d'animation
	while(1) {
		// nouvelle position du sprite pour la balle
		ball_x += dir_x;
		ball_y += dir_y;
		
		check_walls(&ball_x, &ball_y, &dir_x, &dir_y);
		
		// mise a jour
		SPR_setPosition(sprt[0], ball_x, ball_y);
	    SPR_update();
        VDP_waitVSync();
	}
}

int main() {
	breakout();
}