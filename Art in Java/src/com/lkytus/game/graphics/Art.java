package com.lkytus.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Art {
	
	public static final Bitmap TERRAIN = getSpriteSheet("terrain");
	private static final Bitmap FONT = getSpriteSheet("font");
	
	public static final Bitmap getSprite(Bitmap sheet, int xOffs, int yOffs, int width, int height){
		width *= 8;
		height *= 8;
		xOffs *= 8;
		yOffs *= 8;
		
		Bitmap map = new Bitmap(width, height);
		
		for(int y = 0; y < height; y++){
			int yPix = y+yOffs;
			for(int x = 0; x < width; x++){
				int xPix = x+xOffs;
				map.pixels[x+y*width] = sheet.pixels[xPix+yPix*sheet.width] & 0xffffff;
			}
		}
		
		return map;
	}
	
	private final static Bitmap getSpriteSheet(String fileName){
		String filePath = "/textures/"+fileName+".png";
		
		URL url = Art.class.getResource(filePath);
		int width = new ImageIcon(url).getIconWidth();
		int height = new ImageIcon(url).getIconHeight();
		Bitmap map = new Bitmap(width, height);
		
		try{
			BufferedImage img = ImageIO.read(url);
			img.getRGB(0, 0, width, height, map.pixels, 0, width);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return map;
	}
	
	public static final Bitmap drawString(String text){
		Bitmap src = new Bitmap(text.length() * 8 + 8, 8);
		String src1 = text.toUpperCase();
		
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
				"0123456789!@%-+(.):$?     ";
		
		for(int i = 0; i < src1.length(); i++){
			int ch = chars.indexOf(src1.charAt(i));
			int xx = ch % 26;
			int yy = ch / 26;
			
			//System.out.println("X:" + xx  + " Y:" + yy);
			
			src.draw(Art.getSprite(FONT, xx, yy, 1, 1), i * 8 + 1, 0);
		}
		
		return src;
	}
	public static final Bitmap drawString(String text, int color){
		Bitmap src = new Bitmap(text.length() * 8, 8);
		String src1 = text.toUpperCase();
		
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
				"0123456789!@%-+(.):$?    ";
		
		for(int i = 0; i < src1.length(); i++){
			int ch = chars.indexOf(src1.charAt(i));
			
			int xx = ch % 26;
			int yy = ch / 26;
			
			src.draw(Art.getSprite(FONT, xx, yy, 1, 1), i * 8, 0);
		}
		
		for(int i = 0; i < src.pixels.length; i++) src.pixels[i] &= color;
		
		return src;
	}
	
}
