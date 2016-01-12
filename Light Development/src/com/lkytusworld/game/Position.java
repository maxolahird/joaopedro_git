package com.lkytusworld.game;

public class Position {
	private float x, y;
	
	public Position() {}
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public static float distance(Position p1, Position p2) {
		float xLength = Math.abs(p1.getX() - p2.getX());
		float yLength = Math.abs(p1.getY() - p2.getY());
		
		return (float)Math.sqrt(xLength * xLength + yLength * yLength);
	}
}
