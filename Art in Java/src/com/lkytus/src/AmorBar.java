package com.lkytus.src;

import com.lkytus.game.GameLoop;
import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class AmorBar {
	public final Bitmap frame, preloaded;
	public Bitmap bar;
	
	public AmorBar(){
		frame = Art.getSprite(Art.TERRAIN, 6, 11, 6, 1);
		preloaded = Art.getSprite(Art.TERRAIN, 6, 10, 6, 1);		
		bar = new Bitmap(48, 8);
		
	}
	
	
	public void update(GameLoop game){
		Player player = game.player;
		int SIZE = (player.amor * 48) / Player.HPMAX;
		
		for(int i = 0; i < bar.pixels.length; i++) bar.pixels[i] = 0;
		
		if(SIZE >= Player.HPMAX) return;
		for(int y = 0; y < frame.height; y++){
			for(int x = 0; x < SIZE; x++){
				bar.pixels[x+y*48] = preloaded.pixels[x+y*48];
			}
		}
		
	}
	
}
