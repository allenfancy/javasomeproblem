package com.allenfancy.desgin.facade.demo;
/**
 * 外观类
 * @author allen
 *
 */
public class ShapMaker {

	private Shape circle;
	private Shape rectangle;
	private Shape square;
	
	public ShapMaker(){
		circle = new Circle();
		rectangle = new Rectangle();
		square = new Square();
	}
	
	public void drawCircle(){
		circle.draw();
	}
	
	public void drawRectangle(){
		rectangle.draw();
	}
	public void drawSquare(){
		square.draw();
	}
}
