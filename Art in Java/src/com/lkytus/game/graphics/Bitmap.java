package com.lkytus.game.graphics;


public class Bitmap {
	public final int width, height, pixels[];
	public Bitmap(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	public void draw(Bitmap bitmap, int xOffs, int yOffs){
		for(int y = 0; y < bitmap.height; y++){
			int yPix = y+yOffs;
			if(yPix < 0 || yPix >= height) continue;
			for(int x = 0; x < bitmap.width; x++){
				int xPix = x+xOffs;
				if(xPix < 0 || xPix >= width) continue;
				int src = bitmap.pixels[x+y*bitmap.width];
				if(src > 0)
				pixels[xPix+yPix*width] = src;
			}
		}
	}
}
