package com.allenfancy.desgin.facade;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ShowCircle extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args){
		
		ShowCircle sc = new ShowCircle();
		
		sc.setPreferredSize(new Dimension(300,300));
		SwingFacade.launch(sc, "Circle");
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int nPoint = 101;
		double w = getWidth() - 1;
		double h = getHeight() - 1;
		double r = Math.min(w, h) / 2.0;
		int[] x = new int[nPoint];
		int[] y = new int[nPoint];
		
		for(int i = 0; i < nPoint;i++){
			double t = ((double) i) / (nPoint - 1);
            double theta = Math.PI * 2.0 * t;
            x[i] = (int) (w / 2 + r * Math.cos(theta));
            y[i] = (int) (h / 2 - r * Math.sin(theta));
		}
		g.drawPolyline(x, y, nPoint);
	}

}
