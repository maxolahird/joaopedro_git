package com.lkytus.game.graphics;

import com.lkytus.game.GameLoop;
import com.lkytus.src.DamageSpot;
import com.lkytus.src.Shot;
import com.lkytus.src.Zombie;

public class GameScreen extends Bitmap{
	private Bitmap ground = Art.getSprite(Art.TERRAIN, 0, 0, 1, 1);
	public GameScreen(int width, int height) {
		super(width, height);
	}
	
	public void render(GameLoop game){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int xPix =(int) (x) & 7;
				int yPix =(int) (y) & 7;
				pixels[x+y*width] = ground.pixels[xPix+yPix*8];
			}
		}
		
		
		draw(game.player.sprite, game.player.x, game.player.y);
		for(Zombie zombie : game.zombie){
			draw(zombie.graphics, zombie.getX(), zombie.getY());
		}
		postRenderer(game);
		for(DamageSpot damage : game.damageSpot) draw(damage.graphics, damage.x-20, damage.y-10);
		
		if(game.player.attacking)
		switch(game.player.getDir()){
		case 3 : draw(Art.getSprite(Art.TERRAIN, 6 + game.player.getDir(), 8, 1, 1), game.player.attackingX - 10, game.player.attackingY);break;
		default:draw(Art.getSprite(Art.TERRAIN, 6 + game.player.getDir(), 8, 1, 1), game.player.attackingX, game.player.attackingY);break;
		}
		
		
		
		
		for(Shot shot : game.playerShot){
			for(int i = 0; i < 5; i++){
				switch(shot.pos){
				case 0 : draw(shot.graph, shot.x, shot.y + i); break;
				case 1 : draw(shot.graph, shot.x, shot.y + i); break;
				case 2 : draw(shot.graph, shot.x + i, shot.y); break;
				case 3 : draw(shot.graph, shot.x + i, shot.y); break;
				}
			}
		}
	}
	
	
	private void postRenderer(GameLoop game) {
	
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int xLength = Math.abs(x - game.player.x - 3);
				int yLength = Math.abs(y - game.player.y - 8);
				float distance = (float)Math.sqrt(xLength * xLength + yLength * yLength);
				 
				float diff = 30 / distance;
				if(diff > 1)
					diff = 1F;
				
				int red_src   = (pixels[x+y*width] >> 16) & 0xFF;
				int green_src = (pixels[x+y*width] >>  8) & 0xFF;
				int blue_src  = (pixels[x+y*width] >>  0) & 0xFF;
				
				int red_out   = (int) (red_src * diff);
				int green_out = (int) (green_src * diff);
				int blue_out  = (int) (blue_src * diff);			
				
				pixels[x+y*width] = red_out << 16 | green_out << 8 | blue_out;
			}
		}
	}
}
