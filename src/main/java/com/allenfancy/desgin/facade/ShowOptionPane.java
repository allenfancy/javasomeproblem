package com.allenfancy.desgin.facade;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ShowOptionPane {

	public static void main(String[] args){
		
		Font font = new Font("Dialog",Font.PLAIN,18);
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		
		int option;
		do{
			option = JOptionPane.showConfirmDialog(null, "Had enough?","A Shubborn Dialog",JOptionPane.YES_NO_OPTION);
		}while(option == JOptionPane.NO_OPTION);
	}
}
