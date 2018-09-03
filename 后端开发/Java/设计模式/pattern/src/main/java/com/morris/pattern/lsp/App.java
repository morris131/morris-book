package com.morris.pattern.lsp;

public class App {
	
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle(10, 20);
		//Rectangle rectangle = new Square(10, 20);
		System.out.println(rectangle.calcArea());
	}
}
