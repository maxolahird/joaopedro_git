package com.lkytus.src;

import java.awt.Rectangle;
import java.util.Random;

import com.lkytus.game.GameLoop;
import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class Zombie {
	private double x, y;
	private float dx, dy;
	
	private byte dir;
	public Bitmap graphics;
	public Rectangle bounds;
	public int HP;
	
	public final int Attack;
	
	private Random random = new Random();
	private final float speed;
	
	public Zombie(int x, int y){
		this.x = x;
		this.y = y;
		
		Attack = random.nextInt(10 - 5) + 5;
		
		speed = (random.nextInt(6 - 2) + 2) / 10f;
		
		HP = random.nextInt(120 - 50) + 50;
		
		graphics = Art.getSprite(Art.TERRAIN, 9, 3, 1, 2);
		bounds = new Rectangle(x, y, 8, 16);
	}
	
	public void update(Player player){
		bounds = new Rectangle((int)x,(int)y, 8, 16);
		graphics = Art.getSprite(Art.TERRAIN, 9 + (dir % 2), 3 + (dir / 2) * 2 , 1, 2);
		x += dx;
		y += dy;
		
		dx = 0;
		dy = 0;
		
		if(player.x < x){
			dir = 3;
			dx = -speed;
		}
		if(player.y < y){
			dir = 1;
			dy = -speed;
		}
		if(player.x > x){
			dir = 2;
			dx = speed;
		}
		if(player.y > y){
			dir = 0;
			dy = speed;
		}
		
	}
	
	
	public int getX(){
		return (int) x;
	}
	public int getY(){
		return (int) y;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	public void setY(int newY) {
		y = newY;
	}

	public void getDamage(int i, GameLoop gameLoop) {
		HP -= i;
		gameLoop.damageSpot.add(new DamageSpot((int) x, (int) y, i, 0xffff00));
	}
}
