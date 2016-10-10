package com.morris.pattern.lsp;

public class Square extends Rectangle {

	public Square(int width, int height) {
		super(width, height);
	}
	
	@Override
	public int calcArea() {
		return width * width;
	}

}
