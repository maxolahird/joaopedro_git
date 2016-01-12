package com.lkytusworld.game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class LDGame extends Canvas implements Runnable
{
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final int SCALE = 4;
	
	private final JFrame        _frame;
	
	private final BufferedImage _image;
	private final int[]         _pixels;
	private final Screen        _screen;
	
	private Graphics _graphics;
	private boolean _running;
	
	private Thread _thread;
	private BufferStrategy _bs;
	
	public LDGame() {
		_frame = new JFrame();
		_frame.add(this);
		_frame.setResizable(false);
		_frame.setSize(WIDTH, HEIGHT);
		_frame.setLocationRelativeTo(null);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_image = new BufferedImage(WIDTH / SCALE, HEIGHT / SCALE, BufferedImage.TYPE_INT_RGB);
		_pixels = ((DataBufferInt)_image.getRaster().getDataBuffer()).getData();
		_screen = new Screen(_pixels, WIDTH / SCALE, HEIGHT / SCALE);
	}

	@Override
	public void run() {
		int frames = 0;
		int ticks = 0;
		
		double unTime = 0;
		final double FIX_TICK = 1/60.0;
		
		double preTime = System.nanoTime();
		while(_running) {
			double cTime = System.nanoTime();
			double delta = (cTime - preTime) / 1000000000.0;
			unTime += delta;
			preTime = cTime;
			
			while(unTime > FIX_TICK) {
				//UPDATE METHOD
				ticks++;
				unTime -= FIX_TICK;
				if(ticks%60==0)
				{
					System.out.println("Ticks 60, fps " + frames);
					frames = 0;
				}
			}
			
			_render();
			frames++;
		}
	}
	
	private void _render() {
		_screen.render();
		
		_graphics = _bs.getDrawGraphics();
		_graphics.drawImage(_image, 0, 0, WIDTH, HEIGHT, null);
		_graphics.dispose();
		_bs.show();
	}

	public synchronized void start() {
		if(_running)
			return;
		_frame.setVisible(true);
		
		createBufferStrategy(2);
		_bs = getBufferStrategy();
		
		_running = true;
		_thread = new Thread(this);
		_thread.start();
		
	}
	
	public static void main(String[] args) {
		LDGame boring = new LDGame();
		boring.start();
	}
}
