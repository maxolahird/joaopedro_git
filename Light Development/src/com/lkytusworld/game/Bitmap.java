package com.lkytusworld.game;

public class Bitmap {
	private final int[] pixels;
	private final int width, height;
	
	public Bitmap(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	public int _pixel_data(int index) { return pixels[index]; }
}
