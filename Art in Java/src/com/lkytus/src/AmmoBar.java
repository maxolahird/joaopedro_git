package com.lkytus.src;

import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class AmmoBar {
	public Bitmap frame, preloaded, bar = new Bitmap(48, 8);
	public AmmoBar(){
		frame = Art.getSprite(Art.TERRAIN, 0, 11, 6, 1);
		preloaded = Art.getSprite(Art.TERRAIN, 0, 10, 6, 1);
	}
	
	public void update(Player player){
		
		double REALSIZE = 0;
		if(player.weapon == 1) REALSIZE = (player.ammo * 48.0 / Player.MAXAMMO);
		if(player.weapon == 2) REALSIZE = (player.ammoshotgun * 48.0 / Player.MAXAMMOSHOTGUN);
		
		
		for(int i = 0; i < bar.pixels.length; i++) bar.pixels[i] = 0;
		
		if(REALSIZE >= Player.HPMAX) return;
		for(int y = 0; y < frame.height; y++){
			for(int x = 0; x < REALSIZE; x++){
				bar.pixels[x+y*48] = preloaded.pixels[x+y*48];
			}
		}
	}
}
