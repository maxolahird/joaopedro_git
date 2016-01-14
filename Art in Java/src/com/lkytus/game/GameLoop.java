package com.lkytus.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lkytus.src.AmmoBar;
import com.lkytus.src.AmorBar;
import com.lkytus.src.DamageSpot;
import com.lkytus.src.Granade;
import com.lkytus.src.KeyboardInput;
import com.lkytus.src.LifeBar;
import com.lkytus.src.Menu;
import com.lkytus.src.MouseInput;
import com.lkytus.src.Player;
import com.lkytus.src.Shop;
import com.lkytus.src.Shot;
import com.lkytus.src.Zombie;

public class GameLoop 
{
	
	public int tick;
	public int zombiekilled;
	public int money, pts;

	
	public Player player;
	
	public List<Shot> playerShot;
	public List<Zombie> zombie;
	public List<DamageSpot> damageSpot;
	public List<Granade> granade;
	
	public boolean paused;
	public boolean died;
	
	public LifeBar lifeBar;
	public AmmoBar ammoBar;
	public AmorBar amorBar;
	public Shop shop;
		
	public Menu menu; 
	
	
	private Random random = new Random();
	
	public GameLoop()
	{
		player = new Player();
		playerShot = player.getShot();
		lifeBar = new LifeBar();
		ammoBar = new AmmoBar();
		amorBar = new AmorBar();
		shop = new Shop();
		zombie = new ArrayList<Zombie>();
		damageSpot = new ArrayList<DamageSpot>();
		granade = new ArrayList<Granade>();
		menu = new Menu();
	}
	public void tick(final KeyboardInput keys, final MouseInput mouse)
	{
		if(!menu.isInMenu())
		{
			if(died)
			{
				if(keys.keys[KeyEvent.VK_ENTER])
				{
					player = new Player();
					playerShot = player.getShot();
					zombie = new ArrayList<Zombie>();
					zombiekilled = 0;
					money = 0;
					damageSpot = new ArrayList<DamageSpot>();
					died = false;
				}
				return;
			}
			
			if(paused)
			{
				if(keys.keys[KeyEvent.VK_P])
				{
					paused = false;
					keys.keys[KeyEvent.VK_P] = false;			
					keys.keysblocked[KeyEvent.VK_P] = true;
				}
				return;
			}
			
			if(shop.inShop)
			{
				shop.update(mouse, this);
				return;
			}
			tick++;
			int rand = random.nextInt(2);	
			player.update(keys, this);
			for(int i = 0; i < zombie.size(); i++)
			{
				Zombie zom = zombie.get(i);
				zom.update(player);
				if(zom.HP <= 0){
					money += 100;
					pts += 1000;
					zombiekilled++;
					zombie.remove(i);
				}
			}
			for(int i = 0; i < playerShot.size(); i++)
			{
				Shot s = playerShot.get(i);
				s.update();
				if(!s.visible) playerShot.remove(i);
			}
			for(int i = 0; i < damageSpot.size(); i++)
			{
				DamageSpot src = damageSpot.get(i);
				src.update();
				if(!src.visible) damageSpot.remove(i);
			}
			if(keys.keys[KeyEvent.VK_D])
			{
				player.getDamage(10, this);
				keys.keysblocked[KeyEvent.VK_D] = true;
				keys.keys[KeyEvent.VK_D] = false;
			}
			
			if(keys.keys[KeyEvent.VK_B])
			{
				shop.inShop = true;
			}
			if(keys.keys[KeyEvent.VK_P])
			{
				paused = true;
				keys.keys[KeyEvent.VK_P] = false;			
				keys.keysblocked[KeyEvent.VK_P] = true;	
			}
			if(zombie.size() < 40)
			{
				switch(rand)
				{
				case 0 : zombie.add(new Zombie(random.nextInt(200 - 100) + 100,-random.nextInt(800 - 400) + 400)); break;
				case 1 : zombie.add(new Zombie(-random.nextInt(800-400)+400, random.nextInt(200 - 100) + 100)); break;
				}
			}
			if(player.HP <= 0) died = true; 
			lifeBar.update(player.HP);
			ammoBar.update(player);
			amorBar.update(this);
			checkCollisions();
		} else 
		{
			menu.update(mouse);
		}
	}
	
	private void checkCollisions()
	{
		for(Zombie zom : zombie)
		{
			if(player.attackingX >= zom.getX() && player.attackingY >= zom.getY() && player.attackingX <= zom.getX() + 8 && player.attackingY <= zom.getY() + 16 && player.attacking)
			{
				switch(player.getDir())
				{
				case 0 : zom.getDamage(random.nextInt(20 - 15) + 15, this); zom.setY(zom.getY() + 10); break;
				case 1 : zom.getDamage(random.nextInt(20 - 15) + 15, this); zom.setY(zom.getY() - 10); break;
				case 2 : zom.getDamage(random.nextInt(20 - 15) + 15, this); zom.setX(zom.getX() + 10); break;
				case 3 : zom.getDamage(random.nextInt(20 - 15) + 15, this); zom.setX(zom.getX() - 10); break;
				}
				pts += 50;
			}
			for(Shot shot : playerShot) if(shot.x > zom.getX() && shot.y > zom.getY() && shot.x < zom.getX() + 8 && shot.y < zom.getY() + 16)
			{
				if(player.weapon == 1) switch(shot.pos)
				{
				case 0 : zom.getDamage(random.nextInt(25 - 15) + 15, this); zom.setY(zom.getY() + 10); break;
				case 1 : zom.getDamage(random.nextInt(25 - 15) + 15, this); zom.setY(zom.getY() - 10); break;
				case 2 : zom.getDamage(random.nextInt(25 - 15) + 15, this); zom.setX(zom.getX() + 10); break;
				case 3 : zom.getDamage(random.nextInt(25 - 15) + 15, this); zom.setX(zom.getX() - 10); break;
				} else switch(shot.pos)
				{
				case 0 : zom.getDamage(random.nextInt(50 - 30) + 30, this); zom.setY(zom.getY() + 10); break;
				case 1 : zom.getDamage(random.nextInt(50 - 30) + 30, this); zom.setY(zom.getY() - 10); break;
				case 2 : zom.getDamage(random.nextInt(50 - 30) + 30, this); zom.setX(zom.getX() + 10); break;
				case 3 : zom.getDamage(random.nextInt(50 - 30) + 30, this); zom.setX(zom.getX() - 10); break;
				}
				shot.hit();
				pts += 20;
			}
			
			
			if(zom.getX() >= player.x && zom.getY() >= player.y && zom.getX() <= player.x + 4 && zom.getY() <= player.y + 16 && !player.invencible) 
				player.getDamage(random.nextInt(zom.Attack),this);
		}
	}
}
