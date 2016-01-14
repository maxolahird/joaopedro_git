package com.lkytus.src;

import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class DamageSpot {
	public int tick;
	public Bitmap graphics;
	
	public boolean visible;
	
	public int x, y, xReal, yReal;
	public double dx, dy;
	
	public DamageSpot(int x, int y, int damage, int col){
		this.x = x;
		this.y = y;
		
		xReal = x;
		yReal = y;
		
		graphics = Art.drawString(damage + "", col);
		tick = 0;
		visible = true;
	}

	public DamageSpot(int x, int y, String string, int col) {
		this.x = x;
		this.y = y;
		
		xReal = x;
		yReal = y;
		
		graphics = Art.drawString(string + "", col);
		tick = 0;
		visible = true;
	}

	public void update(){
		tick++;
		y += 1;
		
		
		if(tick > 50) visible = false;
	}
}
