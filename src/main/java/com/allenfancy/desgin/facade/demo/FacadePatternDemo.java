package com.allenfancy.desgin.facade.demo;

public class FacadePatternDemo {

	public static void main(String[] args){
		ShapMaker s = new ShapMaker();
		
		s.drawCircle();
		s.drawRectangle();
		s.drawSquare();
	}
}
