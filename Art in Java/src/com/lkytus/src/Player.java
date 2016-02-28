package com.lkytus.src;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.lkytus.game.GameLoop;
import com.lkytus.game.graphics.Art;
import com.lkytus.game.graphics.Bitmap;

public class Player {
	public int x, y;
	private int dx, dy;
	
	public Bitmap sprite;
	
	public static final int HPMAX = 100;
	public double HP;
	
	private final int up, down, left, right, space, knife, pistol, shotgun, granade;
	
	private byte dir; 
	private long mov;
	
	public boolean attacking;
	public int attackingX, attackingY;
	
	public byte weapon = 0;
	public Rectangle bounds;
	
	private List<Shot> shot = new ArrayList<Shot>();
	public boolean invencible;
	
	public int ammo, ammoshotgun;
	
	public static final int MAXAMMO = 120;
	public static final int MAXAMMOSHOTGUN = 40;
	
	
	private int invencibleTime;
	
	public int amor;
	
	
	public Player(){
		x = 100;
		y = 100;
		
		up = KeyEvent.VK_UP;
		down = KeyEvent.VK_DOWN;
		left = KeyEvent.VK_LEFT;
		right = KeyEvent.VK_RIGHT;
		space = KeyEvent.VK_SPACE;
		knife = KeyEvent.VK_1;
		pistol = KeyEvent.VK_2;
		shotgun = KeyEvent.VK_3;
		granade = KeyEvent.VK_4;
		
		HP = HPMAX;
		
		ammo = 120;
		
		sprite = Art.getSprite(Art.TERRAIN, 0, 3, 1, 2);
		bounds = new Rectangle(x, y, 8, 16);
	}
	
	public void update(KeyboardInput keys, GameLoop game){
		sprite = Art.getSprite(Art.TERRAIN, (dir % 2) +(int) (((mov % 10) / 5) * 2), ((dir / 2) * 2) + 3, 1, 2);
		bounds = new Rectangle(x, y, 8, 16);
		x += dx;
		y += dy;
		attacking = false;
		
		dx = 0; 
		dy = 0;
		
		if(x > 309) x = 309;
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(y > 160) y = 160;
		//Para colocar a vida infinita, remova essas barras
		//HP = 100;
		if(invencible) invencibleTime++; else invencibleTime = 0;
		if(invencibleTime > 60) invencible = false;
		
		if(HP > HPMAX) HP = HPMAX;
		if(HP < 0) HP = 0;
		
		if(ammo > MAXAMMO) ammo = MAXAMMO;
		if(ammoshotgun > MAXAMMOSHOTGUN) ammoshotgun = MAXAMMOSHOTGUN;
		
		if(keys.keys[up]){
			dir = 1;
			dy = -1;
			mov++;
		}
		if(keys.keys[down]){
			dir = 0;
			dy = 1;
			mov++;
		}
		if(keys.keys[left]){
			dir = 3;
			dx = -1;
			mov++;
		}
		if(keys.keys[right]){
			dir = 2;
			dx = 1;
			mov++;
		}
		if(keys.keys[space]){
			if(weapon == 0) attack();
			else if(weapon >= 1) shot();
			keys.keysblocked[space] = true;
			keys.keys[space] = false;
		}
		
		if(keys.keys[knife] && weapon != 0)weapon = 0;
		if(keys.keys[pistol] && weapon != 1)weapon = 1;
		if(keys.keys[shotgun] && weapon != 2)weapon = 2;
		if(keys.keys[granade] && weapon != 3)weapon = 3;
	}
	
	public List<Shot> getShot() {
		return shot;
	}
	
	public void getDamage(int damage, GameLoop game){
		if(invencible) return;
		invencible = true;
		invencibleTime = 0;
		if(amor > 0){
			HP -= damage / 3;
			amor -= damage;
		}else{
			HP -= damage;
		}
		if(damage > 0)game.damageSpot.add(new DamageSpot(x, y, -damage, 0xff0000)); else game.damageSpot.add(new DamageSpot(x, y, "miss", 0xff0000));
	}
	public void getHeal(int heal, GameLoop game){
		HP += heal;
		game.damageSpot.add(new DamageSpot(x,y, heal, 0x00ff00));
	}
	private void shot(){
		if(weapon == 1 && ammo > 0){
			ammo--;
			switch(dir){
			case 0 : getShot().add(new Shot(x + 4, y, dir, weapon)); break;
			case 1 : getShot().add(new Shot(x + 4, y, dir, weapon)); break;
			case 2 : getShot().add(new Shot(x, y + 4, dir, weapon)); break;
			case 3 : getShot().add(new Shot(x, y + 4, dir, weapon)); break;
			}
		} else if(weapon == 2 && ammoshotgun > 0){
			ammoshotgun--;
			switch(dir){
			case 0 : getShot().add(new Shot(x + 4, y, dir, weapon)); break;
			case 1 : getShot().add(new Shot(x + 4, y, dir, weapon)); break;
			case 2 : getShot().add(new Shot(x, y + 4, dir, weapon)); break;
			case 3 : getShot().add(new Shot(x, y + 4, dir, weapon)); break;
			}
		}
	}
	private void attack(){
		attacking = true;
		switch(dir){
		case 0 : attackingX = x; attackingY = y + 17; break;
		case 1 : attackingX = x; attackingY = y - 1; break;
		case 2 : attackingX = x + 12; attackingY = y; break;
		case 3 : attackingX = x - 2; attackingY = y; break;
		}
	}
	public int getDir() {
		return dir;
	}
}
