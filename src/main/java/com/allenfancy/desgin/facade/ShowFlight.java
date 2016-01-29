package com.allenfancy.desgin.facade;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class ShowFlight extends JPanel {

	public static void main(String[] args) {
		ShowFlight fp = new ShowFlight();
		fp.setPreferredSize(new Dimension(300, 200));

		JPanel fp_titled = createTitledPanel("Flight Path", fp);

		JFrame frame = new JFrame("Flight Path for Shell Duds");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(fp_titled);

		frame.pack();
		frame.setVisible(true);
	}

	public static TitledBorder createTitledBorder(String title) {
		TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), title,
				TitledBorder.LEFT, TitledBorder.TOP);
		tb.setTitleColor(Color.black);
		tb.setTitleFont(getStandardFont());
		return tb;
	}

	/**
	 * Create a new panel that wraps a titled border around a given panel.
	 */
	public static JPanel createTitledPanel(String title, JPanel in) {
		JPanel out = new JPanel();
		out.add(in);
		out.setBorder(createTitledBorder(title));
		return out;
	}

	/**
	 * @return a standard font for GUI use
	 */
	public static Font getStandardFont() {
		return new Font("Dialog", Font.PLAIN, 18);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // paint the background
		int nPoint = 101;
		double w = getWidth() - 1;
		double h = getHeight() - 1;
		int[] x = new int[nPoint];
		int[] y = new int[nPoint];
		for (int i = 0; i < nPoint; i++) {
			// t goes 0 to 1
			double t = ((double) i) / (nPoint - 1);
			// x goes 0 to w
			x[i] = (int) (t * w);
			// y is h at t = 0 and t = 1, and y is 0 at t = .5
			y[i] = (int) (4 * h * (t - .5) * (t - .5));
		}
		g.drawPolyline(x, y, nPoint);
	}

}
