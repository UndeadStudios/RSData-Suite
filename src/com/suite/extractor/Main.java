package com.suite.extractor;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {
	
	public static void main(String args[]) {
		System.out.println("Now running!");
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new GUI();
	}

}
