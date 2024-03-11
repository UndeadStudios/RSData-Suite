package com.suite;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.suite.interfaces.SuiteUI;

public class Main {

	public static void main(String args[]) {
		System.out.println("Suite is now running!");
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceMagmaLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		new SuiteUI();
	}

}
