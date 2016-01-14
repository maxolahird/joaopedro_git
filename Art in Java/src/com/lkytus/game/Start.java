package com.lkytus.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.lkytus.game.graphics.Screen;
import com.lkytus.src.KeyboardInput;
import com.lkytus.src.MouseInput;

public class Start extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 320;
	private static final int HEIGHT = 240;
	private static final int SCALE = 2;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage img;
	private Screen screen;
	private int[] pixels;
	
	private GameLoop game;
	
	private KeyboardInput input = new KeyboardInput();
	private MouseInput minput = new MouseInput();
	
	
	private synchronized void start(){
		if(running) return;
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	public Start(){
		addKeyListener(input);
		addMouseListener(minput);
		addMouseMotionListener(minput);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		screen = new Screen(WIDTH, HEIGHT);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		game = new GameLoop();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
	
	}
	
	
	public static void main(String[] args){
		JFrame f = new JFrame("8-BITS Zombie Killer");
		Start game = new Start();
		
		f.add(game);
		f.setIconImage(new ImageIcon(Start.class.getResource("/textures/icon.png")).getImage());
		f.setSize(game.getPreferredSize());
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		game.start();
	}
	
	@Override
	public void run(){
		long pTime = System.nanoTime();
		final double secondsPerTick = 1/60.0;
		double unprocessedTime = 0;
		int tickCount = 0;
		int frame = 0;
		
		while(running){
			long cTime = System.nanoTime();
			long paTime = cTime - pTime;
			unprocessedTime += paTime / 1000000000.0;
			pTime = cTime;
			while(unprocessedTime > secondsPerTick){
				tick();
				tickCount++;
				unprocessedTime -= secondsPerTick;
				
				if(tickCount%60==0){
					System.out.println("ticks 60, frames " + frame);
					frame = 0;
					paTime += 1000;
				}
			}
			
			render();
			frame++;
		}
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.render(game, minput);
		
		for(int i = 0; i<WIDTH * HEIGHT; i++) pixels[i] = screen.pixels[i];
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}
	private void tick(){
		game.tick(input, minput);
	}
}
