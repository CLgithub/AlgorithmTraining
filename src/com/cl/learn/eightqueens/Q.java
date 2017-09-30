package com.cl.learn.eightqueens;

public class Q {
	private int x;
	private int y;
	private int values;

	public Q() {
	}

	public Q(int values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Q [x=" + x + ", y=" + y + ", values=" + values + "]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValues() {
		return values;
	}

	public void setValues(int values) {
		this.values = values;
	}

}
