package com.lkytus.src;

import com.lkytus.game.graphics.Bitmap;

public class Shot {
	public int x, y;
	private int hitCount, hitMax;
	private final int SPEED = 3;
	
	public final Bitmap graph = new Bitmap(1, 1);
	
	public final byte pos, type;
	
	public boolean visible;
	
	public Shot(int x, int y, byte pos, byte type){
		this.x = x;
		this.y = y;
		
		this.pos = pos;
		this.type = type;
		
		graph.pixels[0] = 0xffff00;
		if(type==1)
			hitMax = 1;
		else if(type == 2)
			hitMax = 5;
		visible = true;
	}


	public void update(){
		switch(pos){
		case 0 : y += SPEED; break;
		case 1 : y -= SPEED; break;
		case 2 : x += SPEED; break;
		case 3 : x -= SPEED; break;
		}
		
		if(x < 0 || x > 320 || y < 0 || y > 240) visible = false;
		if(hitCount >= hitMax) visible = false;
	}
	
	public void hit() {
		hitCount++;
	}
}
