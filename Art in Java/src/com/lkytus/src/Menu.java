package com.lkytus.src;

import java.awt.event.MouseEvent;

import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class Menu {
	private Bitmap EXIT, START;
	public final int[][] pos = {{120, 90}, {120, 150}};
	private boolean inMenu = false;
	
	public Menu(){
		START = Art.getSprite(Art.TERRAIN, 12, 0, 10, 2);
		EXIT = Art.getSprite(Art.TERRAIN, 19, 3, 7, 2);
		inMenu = true;
		
	}
	
	public void update(MouseInput input){
		
			START = Art.getSprite(Art.TERRAIN, 12, 0, 10, 2);
			EXIT = Art.getSprite(Art.TERRAIN, 19, 4, 7, 2);
			
			if(input.getX() >= pos[0][0] && input.getY() >= pos[0][1] && input.getX() <= pos[0][0] + 80 && input.getY() <= pos[0][1] + 16){
				START = Art.getSprite(Art.TERRAIN, 12, 2, 10, 2);
				if(input.buttons[MouseEvent.BUTTON1]) inMenu = false;
			}
			if(input.getX() >= pos[1][0] && input.getY() >= pos[1][1] && input.getX() <= pos[1][0] + 56 && input.getY() <= pos[1][1] + 16){
				EXIT = Art.getSprite(Art.TERRAIN, 19, 6, 7, 2);
				if(input.buttons[MouseEvent.BUTTON1]) System.exit(0);
			}
	}
	
	public boolean isInMenu(){
		return inMenu;
	}
	
	
	public Bitmap getStartBitmap(){
		return START;
	}
	
	
	public Bitmap getExitBitmap(){
		return EXIT;
	}
}
