package com.allenfancy.desgin.facade;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;



public class SwingFacade {

	public static TitledBorder createTitleBorder(String title){
		
		TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),title,TitledBorder.LEFT,TitledBorder.TOP);
		
		tb.setTitleColor(Color.black);
		tb.setTitleFont(getStandardFont());
		return tb;
	}
	
	public static JPanel createTitlePanel(String title,JPanel in){
		JPanel out = new JPanel();
		out.add(in);
		out.setBorder(createTitleBorder(title));
		return out;
	}
	
	public static Font getStandardFont(){
		return new Font("Dialog",Font.PLAIN,18);
	}
	
	public static JFrame launch(Component c,String title){
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(c);
		
		frame.pack();
		frame.setVisible(true);
		return frame;
	}
	
}
