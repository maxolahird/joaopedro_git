package com.lkytus.src;

import java.awt.event.MouseEvent;

import com.lkytus.game.GameLoop;
import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class Shop {
	public Bitmap BUY, BUY2, BULLETSBOX, HEALTHPACK, EXIT, SHOTGUNBULLETBOX, BUY3, AMOR;
	public boolean inShop;
	public boolean bool1, bool2, bool3, bool4;
	
	
	public final int[][] pos = {{100, 94}, {120, 120}, {135, 120}, {150, 120}, {165, 120}};
	
	public Shop(){
		 BUY = Art.drawString("BUY? 100$");
		 BUY2 = Art.drawString("BUY? 10000$");
		 BULLETSBOX = Art.getSprite(Art.TERRAIN, 1, 12, 1, 1);
		 HEALTHPACK = Art.getSprite(Art.TERRAIN, 0, 12, 1, 1);
		 SHOTGUNBULLETBOX = Art.getSprite(Art.TERRAIN, 2, 12, 1, 1);
		 AMOR = Art.getSprite(Art.TERRAIN, 3, 12, 1, 1);
		 EXIT = Art.getSprite(Art.TERRAIN, 0, 2, 1, 1);
	}
	
	public void update(MouseInput input, GameLoop game){
		 BULLETSBOX = Art.getSprite(Art.TERRAIN, 1, 12, 1, 1);
		 HEALTHPACK = Art.getSprite(Art.TERRAIN, 0, 12, 1, 1);
		 EXIT = Art.getSprite(Art.TERRAIN, 0, 2, 1, 1);
		 
		 bool1 = false;
		 bool2 = false;
		 bool3 = false;
		 bool4 = false;
		 
		int x = input.getX();
		int y = input.getY();
		
		if(x >= pos[0][0] && y >= pos[0][1] && x <= pos[0][0] + 8 && y <= pos[0][1] + 8){
			EXIT = Art.getSprite(Art.TERRAIN, 1, 2, 1, 1);
			if(input.buttons[MouseEvent.BUTTON1]) inShop = false;
		}
		if(x >= pos[1][0] && y >= pos[1][1] && x <= pos[1][0] + 8 && y <= pos[1][1] + 8){
			bool1 = true;
			if(input.buttons[MouseEvent.BUTTON1] && game.money >= 100){
				game.money -= 100;
				game.player.ammo = Player.MAXAMMO;
				inShop = false;
			}
		}
		if(x >= pos[2][0] && y >= pos[2][1] && x <= pos[2][0] + 8 && y <= pos[2][1] + 8){
			bool2 = true;
			if(input.buttons[MouseEvent.BUTTON1] && game.money >= 10000){
				game.money -= 10000;
				game.player.HP = Player.HPMAX;
				inShop = false;
			}
		}
		if(x >= pos[3][0] && y >= pos[3][1] && x <= pos[3][0] + 8 && y <= pos[3][1] + 8){
			bool3 = true;
			if(input.buttons[MouseEvent.BUTTON1] && game.money >= 1000){
				game.money -= 1000;
				game.player.ammoshotgun = Player.MAXAMMO;
				inShop = false;
			}
		}
		if(x >= pos[4][0] && y >= pos[4][1] && x <= pos[4][0] + 8 && y <= pos[4][1] + 8){
			bool4 = true;
			if(input.buttons[MouseEvent.BUTTON1] && game.money >= 3000){
				game.money -= 3000;
				game.player.amor = Player.HPMAX;
				inShop = false;
			}
		}
		
	}
}
