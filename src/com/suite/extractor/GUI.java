package com.suite.extractor;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public JFileChooser filechooser = null;

	private JButton btnExtract;
	private JCheckBox checkBox, checkBox_1, checkBox_2, checkBox_3, checkBox_4, checkBox_5, checkBox_6, checkBox_7, checkBox_8, checkBox_9, checkBox_10
	, checkBox_11, checkBox_12, checkBox_13, checkBox_14, checkBox_15, checkBox_16, checkBox_17, checkBox_18;
	private JLabel lblNewLabel;
	
	private class CheckBoxListener implements ItemListener{
        @Override
		public void itemStateChanged(ItemEvent e) {
            if(e.getSource() == checkBox){
                if(checkBox.isSelected()) {
                	Constants.toExtract[0] = !Constants.toExtract[0];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[0] = !Constants.toExtract[0];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_1){
                if(checkBox_1.isSelected()) {
                	Constants.toExtract[1] = !Constants.toExtract[1];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[1] = !Constants.toExtract[1];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_2){
                if(checkBox_2.isSelected()) {
                	Constants.toExtract[2] = !Constants.toExtract[2];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[2] = !Constants.toExtract[2];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_3){
                if(checkBox_3.isSelected()) {
                	Constants.toExtract[3] = !Constants.toExtract[3];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[3] = !Constants.toExtract[3];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_4){
                if(checkBox_4.isSelected()) {
                	Constants.toExtract[4] = !Constants.toExtract[4];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[4] = !Constants.toExtract[4];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_5){
                if(checkBox_5.isSelected()) {
                	Constants.toExtract[5] = !Constants.toExtract[5];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[5] = !Constants.toExtract[5];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_6){
                if(checkBox_6.isSelected()) {
                	Constants.toExtract[6] = !Constants.toExtract[6];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[6] = !Constants.toExtract[6];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_7){
                if(checkBox_7.isSelected()) {
                	Constants.toExtract[7] = !Constants.toExtract[7];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[7] = !Constants.toExtract[7];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_8){
                if(checkBox_8.isSelected()) {
                	Constants.toExtract[8] = !Constants.toExtract[8];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[8] = !Constants.toExtract[8];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_9){
                if(checkBox_9.isSelected()) {
                	Constants.toExtract[9] = !Constants.toExtract[9];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[9] = !Constants.toExtract[9];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_10){
                if(checkBox_10.isSelected()) {
                	Constants.toExtract[10] = !Constants.toExtract[10];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[10] = !Constants.toExtract[10];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_11){
                if(checkBox_11.isSelected()) {
                	Constants.toExtract[11] = !Constants.toExtract[11];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[11] = !Constants.toExtract[11];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_12){
                if(checkBox_12.isSelected()) {
                	Constants.toExtract[12] = !Constants.toExtract[12];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[12] = !Constants.toExtract[12];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_13){
                if(checkBox_13.isSelected()) {
                	Constants.toExtract[13] = !Constants.toExtract[13];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[13] = !Constants.toExtract[13];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_14){
                if(checkBox_14.isSelected()) {
                	Constants.toExtract[14] = !Constants.toExtract[14];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[14] = !Constants.toExtract[14];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_15){
                if(checkBox_15.isSelected()) {
                	Constants.toExtract[15] = !Constants.toExtract[15];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[15] = !Constants.toExtract[15];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_16){
                if(checkBox_16.isSelected()) {
                	Constants.toExtract[16] = !Constants.toExtract[16];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[16] = !Constants.toExtract[16];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_17){
                if(checkBox_17.isSelected()) {
                	Constants.toExtract[17] = !Constants.toExtract[17];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[17] = !Constants.toExtract[17];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            } else if(e.getSource() == checkBox_18){
                if(checkBox_18.isSelected()) {
                	Constants.toExtract[18] = !Constants.toExtract[18];
                    System.out.println(Arrays.toString(Constants.toExtract));
                } else {
                	Constants.toExtract[18] = !Constants.toExtract[18];
                    System.out.println(Arrays.toString(Constants.toExtract));
                }
            }
        }
    }


	
	public GUI() {
		setSize(366,328);
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void selectInput(MouseEvent e) {
		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(1);
		int response = filechooser.showOpenDialog(this);
		if (response == 0) {
			File selectedDir = filechooser.getSelectedFile();
			if (selectedDir.isDirectory()) {
				Constants.inputFolder = selectedDir.getAbsolutePath();
				System.out.println("Input directory set as: "+Constants.inputFolder);
			}
			if (Constants.inputFolder != "" && Constants.outputFolder != "" && Constants.format != "")
				btnExtract.setEnabled(true);
		}
	}
	
	private void selectOutput(MouseEvent e) {
		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(1);
		int response = filechooser.showOpenDialog(this);
		if (response == 0) {
			File selectedDir = filechooser.getSelectedFile();
			if (selectedDir.isDirectory()) {
				Constants.outputFolder = selectedDir.getAbsolutePath();
				System.out.println("Output directory set as: "+Constants.outputFolder);
			}
			if (Constants.inputFolder != "" && Constants.outputFolder != "" && Constants.format != "")
				btnExtract.setEnabled(true);
		}
	}
	
	private void extract(MouseEvent e) {
		Core.start();
		lblNewLabel.setText("Done");
	}
	

	private void initComponents() {
		btnExtract = new JButton();
		checkBox = new JCheckBox("0");
		checkBox_1 = new JCheckBox("1");
		checkBox_2 = new JCheckBox("2");
		checkBox_3 = new JCheckBox("3");
		checkBox_4 = new JCheckBox("4");
		checkBox_5 = new JCheckBox("5");
		checkBox_6 = new JCheckBox("6");
		checkBox_7 = new JCheckBox("7");
		checkBox_8 = new JCheckBox("8");
		checkBox_9 = new JCheckBox("9");
		checkBox_10 = new JCheckBox("10");
		checkBox_11 = new JCheckBox("11");
		checkBox_12 = new JCheckBox("12");
		checkBox_13 = new JCheckBox("13");
		checkBox_14 = new JCheckBox("14");
		checkBox_15 = new JCheckBox("15");
		checkBox_16 = new JCheckBox("16");
		checkBox_17 = new JCheckBox("17");
		checkBox_18 = new JCheckBox("18");

		// ======== this ========
		setTitle("Cache Exporter by Poesy700");
		setResizable(false);
		Container contentPane = getContentPane();
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Select input folder");
		btnNewButton.setBounds(29, 11, 127, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				selectInput(e);
			}
		});
		
		JButton button = new JButton("Select output folder");
		button.setBounds(185, 11, 127, 23);
		getContentPane().add(button);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				selectOutput(e);
			}
		});
		
		String[] format = { "", "Old", "New" };
		final JComboBox comboBox = new JComboBox(format);
		comboBox.setToolTipText("Choose format");
		comboBox.setBounds(164, 60, 113, 20);
		getContentPane().add(comboBox);
		ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) comboBox.getSelectedItem();//get the selected item

                switch (s) {//check for a match
                    case "Old":
                        Constants.format = "old";
                        System.out.println("Cache format set to: "+Constants.format);
                        if (Constants.inputFolder != "" && Constants.outputFolder != "" && Constants.format != "") {
                        	btnExtract.setEnabled(true);
            				checkBox.setEnabled(true);
            				checkBox_1.setEnabled(true);
            				checkBox_2.setEnabled(true);
            				checkBox_3.setEnabled(true);
            				checkBox_4.setEnabled(true);
            				checkBox_5.setEnabled(false);
            				checkBox_6.setEnabled(false);
            				checkBox_7.setEnabled(false);
            				checkBox_8.setEnabled(false);
            				checkBox_9.setEnabled(false);
            				checkBox_10.setEnabled(false);
            				checkBox_11.setEnabled(false);
            				checkBox_12.setEnabled(false);
            				checkBox_13.setEnabled(false);
            				checkBox_14.setEnabled(false);
            				checkBox_15.setEnabled(false);
            				checkBox_16.setEnabled(false);
            				checkBox_17.setEnabled(false);
            				checkBox_18.setEnabled(false);
                		}
                        break;
                    case "New":
                        Constants.format = "new";
                        System.out.println("Cache format set to: "+Constants.format);
                        if (Constants.inputFolder != "" && Constants.outputFolder != "" && Constants.format != "") {
            				btnExtract.setEnabled(true);
            				checkBox.setEnabled(true);
            				checkBox_1.setEnabled(true);
            				checkBox_2.setEnabled(true);
            				checkBox_3.setEnabled(true);
            				checkBox_4.setEnabled(true);
            				checkBox_5.setEnabled(true);
            				checkBox_6.setEnabled(true);
            				checkBox_7.setEnabled(true);
            				checkBox_8.setEnabled(true);
            				checkBox_9.setEnabled(true);
            				checkBox_10.setEnabled(true);
            				checkBox_11.setEnabled(true);
            				checkBox_12.setEnabled(true);
            				checkBox_13.setEnabled(true);
            				checkBox_14.setEnabled(true);
            				checkBox_15.setEnabled(true);
            				checkBox_16.setEnabled(true);
            				checkBox_17.setEnabled(true);
            				checkBox_18.setEnabled(true);
                        }
                        break;
                    default:
                        System.out.println("Default");
                        Constants.format = "";
                    	btnExtract.setEnabled(false);
        				checkBox.setEnabled(false);
        				checkBox_1.setEnabled(false);
        				checkBox_2.setEnabled(false);
        				checkBox_3.setEnabled(false);
        				checkBox_4.setEnabled(false);
        				checkBox_5.setEnabled(false);
        				checkBox_6.setEnabled(false);
        				checkBox_7.setEnabled(false);
        				checkBox_8.setEnabled(false);
        				checkBox_9.setEnabled(false);
        				checkBox_10.setEnabled(false);
        				checkBox_11.setEnabled(false);
        				checkBox_12.setEnabled(false);
        				checkBox_13.setEnabled(false);
        				checkBox_14.setEnabled(false);
        				checkBox_15.setEnabled(false);
        				checkBox_16.setEnabled(false);
        				checkBox_17.setEnabled(false);
                        break;
                }
            }
        };

        comboBox.addActionListener(cbActionListener);
		
		JLabel lblCacheFormat = new JLabel("Cache Format: ");
		lblCacheFormat.setBounds(46, 63, 74, 14);
		getContentPane().add(lblCacheFormat);
		
		//JButton btnExtract = new JButton("Extract");
		btnExtract.setText("Extract");
		btnExtract.setEnabled(false);
		btnExtract.setBounds(125, 265, 89, 23);
		btnExtract.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lblNewLabel.setText("Extracting...");
				extract(e);
			}
		});
		getContentPane().add(btnExtract);
		
		checkBox.setEnabled(false);
		checkBox.setBounds(57, 126, 31, 23);
		checkBox.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox);
		
		checkBox_1.setEnabled(false);
		checkBox_1.setBounds(57, 152, 31, 23);
		checkBox_1.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_1);
		
		checkBox_2.setEnabled(false);
		checkBox_2.setBounds(57, 177, 31, 23);
		checkBox_2.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_2);
		
		checkBox_3.setEnabled(false);
		checkBox_3.setBounds(57, 203, 31, 23);
		checkBox_3.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_3);
		
		checkBox_4.setEnabled(false);
		checkBox_4.setBounds(92, 126, 31, 23);
		checkBox_4.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_4);
		
		checkBox_5.setEnabled(false);
		checkBox_5.setBounds(92, 152, 31, 23);
		checkBox_5.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_5);
		
		checkBox_6.setEnabled(false);
		checkBox_6.setBounds(92, 177, 31, 23);
		checkBox_6.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_6);
		
		checkBox_7.setEnabled(false);
		checkBox_7.setBounds(92, 203, 31, 23);
		checkBox_7.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_7);
		
		checkBox_8.setEnabled(false);
		checkBox_8.setBounds(125, 126, 31, 23);
		checkBox_8.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_8);
		
		checkBox_9.setEnabled(false);
		checkBox_9.setBounds(125, 152, 31, 23);
		checkBox_9.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_9);
		
		checkBox_10.setEnabled(false);
		checkBox_10.setBounds(125, 177, 37, 23);
		checkBox_10.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_10);
		
		checkBox_11.setEnabled(false);
		checkBox_11.setBounds(125, 203, 37, 23);
		checkBox_11.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_11);
		
		checkBox_12.setEnabled(false);
		checkBox_12.setBounds(166, 126, 37, 23);
		checkBox_12.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_12);
		
		checkBox_13.setEnabled(false);
		checkBox_13.setBounds(166, 152, 37, 23);
		checkBox_13.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_13);
		
		checkBox_14.setEnabled(false);
		checkBox_14.setBounds(166, 177, 37, 23);
		checkBox_14.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_14);
		
		checkBox_15.setEnabled(false);
		checkBox_15.setBounds(166, 203, 37, 23);
		checkBox_15.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_15);
		
		checkBox_16.setEnabled(false);
		checkBox_16.setBounds(214, 126, 37, 23);
		checkBox_16.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_16);
		
		checkBox_17.setEnabled(false);
		checkBox_17.setBounds(214, 152, 43, 23);
		checkBox_17.addItemListener(new CheckBoxListener());
		getContentPane().add(checkBox_17);
		
		JLabel lblIndexes = new JLabel("Indexes:");
		lblIndexes.setBounds(57, 105, 46, 14);
		getContentPane().add(lblIndexes);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(148, 240, 46, 14);
		getContentPane().add(lblNewLabel);
		
		checkBox_18.setEnabled(false);
		checkBox_18.setBounds(214, 177, 43, 23);
		checkBox_18.addItemListener(new CheckBoxListener());

		getContentPane().add(checkBox_18);

	}
}
