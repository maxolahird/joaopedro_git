package com.lkytus.src;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{
	public boolean[] buttons = new boolean[10];
	
	private int x, y;
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int c = e.getButton();
		buttons[c] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int c = e.getButton();
		buttons[c] = false;
	}
	
	public int getX(){
		return x / 2;
	}
	public int getY(){
		return y / 2;
	}
}
