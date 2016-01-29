package com.allenfancy.HtmlToImage;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Demo1 {

	public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException, AWTException{
		Desktop.getDesktop().browse(new URL("file:///Users/allen/temp/untitled.html").toURI());
		
		Robot robot = new Robot();
		robot.delay(500);
		Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		int width = (int) d.getWidth();
		int height = (int) d.getHeight();
		
		robot.keyRelease(KeyEvent.VK_F10);
		robot.delay(500);
		Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		Graphics g = bi.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);//, sy1, sx2, sy2, observer)
		ImageIO.write(bi, "jpg", new File("/Users/allen/temp/baidu.jpg"));
	}
}
