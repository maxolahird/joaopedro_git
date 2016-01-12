package com.lkytusworld.game;

public class Color {
	private float red, green, blue;

	public Color() {
	}

	public Color(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
		if(this.red > 1)
			this.red = 1;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
		if(this.green > 1)
			this.green = 1;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
		if(this.blue > 1)
			this.blue = 1;
	}
	
	public static int pixel(Color color) {
		int red = (int)(color.getRed() * 0xFF);
		int green = (int)(color.getGreen() * 0xFF);
		int blue = (int)(color.getBlue() * 0xFF);
		
		return red << 16 | green << 8 | blue;
	}
	
	public static Color clamp(int color) {
		float red   = ((color >> 16)&0xFF)/255F;
		float green = ((color >>  8)&0xFF)/255F;
		float blue  = ((color >>  0)&0xFF)/255F;
		
		return new Color(red, green, blue);
	}
	
	public static Color multiply(Color c1, Color c2) {
		return new Color(c1.red * c2.red, c1.green * c2.green, c1.blue * c2.blue);
	}
	
	public static Color blend(Color c1, Color c2, float ratio) {
		Color cNew = new Color();
		cNew.red = (c1.red * ratio + c2.red * (1 - ratio));
		cNew.green = (c1.green * ratio + c2.green * (1 - ratio));
		cNew.blue = (c1.blue * ratio + c2.blue * (1 - ratio));
		return cNew;
	}
	
	public String toString() {
		return "Red: " + red + ", green: " + green + ", blue: " + blue;
	}
}
