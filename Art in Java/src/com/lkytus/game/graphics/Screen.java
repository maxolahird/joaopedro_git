package com.lkytus.game.graphics;

import java.util.Random;

import com.lkytus.game.GameLoop;
import com.lkytus.src.MouseInput;

public class Screen extends Bitmap{
	private GameScreen screen;
	private Bitmap shopbg;
	private Bitmap paused = Art.drawString("paused", 0xFFA514);
	Random random = new Random();
	public Screen(int width, int height) {
		super(width, height);
		screen = new GameScreen(width, height - 50);
		shopbg = new Bitmap(120, 50);
		for(int i = 0; i < shopbg.pixels.length; i++) shopbg.pixels[i] = 0xacacac;
	}
	public void render(GameLoop game, MouseInput input){
		for(int i = 0; i < width * height; i++) pixels[i] = 0;
		if(game.menu.isInMenu()){
			draw(Art.drawString("8-bits zombie killer", 0xff0000), 90, 20);
			draw(game.menu.getStartBitmap(), game.menu.pos[0][0], game.menu.pos[0][1]);
			draw(game.menu.getExitBitmap(), game.menu.pos[1][0], game.menu.pos[1][1]);
			return;
		}
		screen.render(game);
		draw(screen, 0, 50);
		
		draw(Art.drawString("LIFE:" +(int) game.player.HP + "%"), 20, 10);
		
		draw(game.lifeBar.lifeBar, 20, 20);
		draw(game.lifeBar.lifeBarFrame, 20, 20);
		
		if(game.player.amor > 0){
			draw(game.amorBar.frame, 20, 35);
			draw(game.amorBar.bar, 20, 35);
		}
		
		draw(game.ammoBar.bar, 260, 20);
		draw(game.ammoBar.frame, 260, 20);
		
		draw(Art.drawString("CASH:" + game.money + " $"), 10, 215);
		draw(Art.drawString("by: Lkytus"), 235, 215);
		
		draw(Art.drawString("weapon: "), 140, 12);
		switch(game.player.weapon){
		case 0 : draw(Art.getSprite(Art.TERRAIN, 3, 13, 3, 2), 140, 22); break;
		case 1 : draw(Art.getSprite(Art.TERRAIN, 0, 13, 3, 2), 140, 22); break;
		case 2 : draw(Art.getSprite(Art.TERRAIN, 6, 13, 4, 2), 140, 22); break;
		}
		
		if(game.player.ammo <= 0 && game.player.weapon == 1) draw(Art.drawString("BUY MORE AMMO!", 0xff0000), 120, 60);
		if(game.player.ammoshotgun <= 0 && game.player.weapon == 2) draw(Art.drawString("BUY MORE AMMO!", 0xff0000), 120, 60);
		
		if(game.shop.inShop){
			draw(shopbg, (width - shopbg.width) / 2, (height - shopbg.height) / 2);
			draw(Art.getSprite(Art.TERRAIN, 0, 15, 6, 2), 120, 100);
			draw(game.shop.EXIT, game.shop.pos[0][0],game.shop.pos[0][1]);
			draw(game.shop.BULLETSBOX, game.shop.pos[1][0], game.shop.pos[1][1]);
			draw(game.shop.HEALTHPACK, game.shop.pos[2][0], game.shop.pos[2][1]);
			draw(game.shop.SHOTGUNBULLETBOX, game.shop.pos[3][0], game.shop.pos[3][1]);
			draw(game.shop.AMOR, game.shop.pos[4][0], game.shop.pos[4][1]);
			if(game.shop.bool1) draw(game.shop.BUY, input.getX() + 8, input.getY() + 8);
			if(game.shop.bool2) draw(game.shop.BUY2, input.getX() + 8, input.getY() + 8);
			if(game.shop.bool3) draw(Art.drawString("buy? 1000$"), input.getX() + 8, input.getY() + 8);
			if(game.shop.bool4) draw(Art.drawString("buy? 3000$"), input.getX() + 8, input.getY() + 8);
		}
		
		if(game.paused){
			for(int i = 0; i < width * height; i++){
				pixels[i] &= 0xacacac;
			}
			draw(paused, (width - paused.width) / 2, (height - paused.height) / 2);

		}
		if(game.died){
			for(int i = 0; i < width * height; i++) pixels[i] = 0;
			draw(Art.drawString("you died!", 0xff0000), 120, 80);
			draw(Art.drawString("points: " + game.pts, 0xff0000), 120, 90);
			draw(Art.drawString("kills : " + game.zombiekilled, 0xff0000), 120, 100);
			draw(Art.drawString("press enter to continue", 0xff0000), 70, 110);
		}
	}
}
