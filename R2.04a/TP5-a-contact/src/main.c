#include "genesis.h"
#include "gfx.h"

u32 is_touching(s16 x1, s16 x2, s16 seuil);

static void loadPalettes(void) {
    u16 palette[16];
	
	// Creation des palettes de couleur a partir des sprites
	memcpy(&palette[0],  bg_image.palette->data, 16 * 2);
	
	// Affichage des images
    VDP_fadeIn(0, 15, palette, 20, FALSE);
}

static void breakout(void) {
	s32 res;
	char str1[32];
	char str2[32];
	char str3[32];
	
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
	loadPalettes();
	
	// boucle d'animation
	while(1) {
		res = is_touching(37, 12, 10);
		sprintf(str1, "|37 - 12|  < 10 ? => %d", (int) res);
		res = is_touching(56, 65, 10);
		sprintf(str2, "|56 - 65|  < 10 ? => %d", (int) res);
		res = is_touching(99, 95, 10);
		sprintf(str3, "|99 - 95|  < 10 ? => %d", (int) res);		
		SYS_disableInts();
        VDP_drawText(str1, 0, 2);
        VDP_drawText(str2, 0, 3);
        VDP_drawText(str3, 0, 4);;
		SYS_enableInts();
		
		// mise a jour
        SPR_update();
        VDP_waitVSync();
	}
}

int main() {
	breakout();
}