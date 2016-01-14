package com.lkytus.src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{
	public boolean[] keys = new boolean[650];
	public boolean[] keysblocked = new boolean[650];
	
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(keysblocked[c]) return;
		keys[c] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		keysblocked[c] = false;
		keys[c] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
