package com.lkytus.src;

import com.lkytus.game.GameLoop;

public class Granade {
	private int x, y;
	private final int startX, startY;
	private int time;
	
	private boolean exploded;
	

	public Granade(int x, int y){
		this.x = x;
		this.y = y;
		startX = x;
		startY = y;
	}
	
	public void update(GameLoop game){
		time++;
		y = startY + time / 5;
		x = startX + time / 5;
		
		if(time > 120){
			explode(game);
		}
	}
	
	private void explode(GameLoop level){
		
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	public boolean isExploded() {
		return exploded;
	}
}
