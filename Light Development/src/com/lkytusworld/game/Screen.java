package com.lkytusworld.game;

import java.lang.reflect.Array;
import java.util.Random;

public class Screen {
	private final int[] pixels;
	private final Color[] lightBuffer;
	private final int width, height;
	
	public Screen(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
		this.lightBuffer = new Color[width * height];
	}
	
	public void render() {
		clear();
		
		float lightForce = Math.abs((float)(Math.sin((System.currentTimeMillis()*2) % 1000 / 1000.0 * 2 * Math.PI))/2);
		
		render_light(new Color(0.7F, 0.3F, 0.2F), 10 + lightForce, width / 2 + 10, height / 2);
		render_light(new Color(0.2F, 0.3F, 0.7F), 10 + lightForce, width / 2 - 10, height / 2);
		render_shadows();
	}
	
	private void render_light(Color color, float force, float x_pos, float y_pos) {
		for(int y = 0; y < height; y++) for(int x = 0; x < width; x++) {
			float x_delta = Math.abs(x - x_pos);
			float y_delta = Math.abs(y - y_pos);
			
			float distance = (float)Math.sqrt(Math.pow(x_delta, 2) + Math.pow(y_delta, 2));
			float ratio = force / distance;
			Color c = new Color();
			c.setRed(color.getRed() * ratio + lightBuffer[x+y*width].getRed());
			c.setGreen(color.getGreen() * ratio + lightBuffer[x+y*width].getGreen());
			c.setBlue(color.getBlue() * ratio + lightBuffer[x+y*width].getBlue());
			lightBuffer[x+y*width] = c;
		}
	}
	
	private void clear() {
		for(int i = 0; i < width * height; i++)
		{
			pixels[i] = 0xFFFFFF;
			lightBuffer[i] = new Color();
		}
	}
	
	private void render_shadows() {
		for(int i = 0; i < width * height; i++) {
			Color color = Color.clamp(pixels[i]);
			color.setRed(color.getRed() * lightBuffer[i].getRed());
			color.setGreen(color.getGreen() * lightBuffer[i].getGreen());
			color.setBlue(color.getBlue() * lightBuffer[i].getBlue());
			pixels[i] = Color.pixel(color);
		}
	}
}
