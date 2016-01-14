package com.lkytus.src;

import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class LifeBar {
	
	public final Bitmap lifeBarFrame, lifeBarPreloaded;
	public final Bitmap lifeBar = new Bitmap(48, 8);
	
	public LifeBar(){
		lifeBarFrame = Art.getSprite(Art.TERRAIN, 0, 9, 6, 1);
		lifeBarPreloaded = Art.getSprite(Art.TERRAIN, 0, 8, 6, 1);
	}
	
	
	public void update(double SIZE){
		double REALSIZE = (SIZE * 48.0 / Player.HPMAX);
		
		
	for(int i = 0; i < lifeBar.pixels.length; i++) lifeBar.pixels[i] = 0;
		
		if(REALSIZE >= Player.HPMAX) return;
		for(int y = 0; y < lifeBarFrame.height; y++){
			for(int x = 0; x < REALSIZE; x++){
				lifeBar.pixels[x+y*48] = lifeBarPreloaded.pixels[x+y*48];
			}
		}
	}
}
