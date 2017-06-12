package com.morris.pattern.lsp;

public class Rectangle {
	
	protected int width;
	
	protected int height;
	
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int calcArea() {
		return width * height;
	}

}
