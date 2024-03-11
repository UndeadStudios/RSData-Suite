package com.suite.interfaces;

/*
 * Created by JFormDesigner on Wed Jun 29 00:41:51 EDT 2011
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import com.suite.loaders.ItemDefinition;

/**
 * @author Brainrain
 */
public class ItemGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemGUI() {
		initComponents();
		setVisible(true);
	}

	private void itemListMouseReleased(MouseEvent e) {
		ItemDefinition item = new ItemDefinition(SuiteUI.cache(),
				list1.getSelectedIndex());
		if (item.name != "null") {
			label3.setText(item.name);
		} else {
			label3.setText("");
		}
	}

	private void itemPackMouseReleased(MouseEvent e) {
		ItemDefinition.itemPack(SuiteUI.cache());
		progressBar1.setValue(100);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		scrollPane2 = new JScrollPane();
		list1 = new JList();
		itemViewBox = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		button1 = new JButton();
		progressBar1 = new JProgressBar();
		label3 = new JLabel();

		// ======== this ========
		setTitle("Item Definitions");
		setResizable(false);
		Container contentPane = getContentPane();

		// ======== scrollPane2 ========
		{

			// ---- list1 ----
			list1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					itemListMouseReleased(e);
				}
			});
			scrollPane2.setViewportView(list1);
		}

		// ======== itemViewBox ========
		{

			GroupLayout itemViewBoxLayout = new GroupLayout(itemViewBox);
			itemViewBox.setLayout(itemViewBoxLayout);
			itemViewBoxLayout.setHorizontalGroup(itemViewBoxLayout
					.createParallelGroup().addGap(0, 408, Short.MAX_VALUE));
			itemViewBoxLayout.setVerticalGroup(itemViewBoxLayout
					.createParallelGroup().addGap(0, 236, Short.MAX_VALUE));
		}

		// ---- label1 ----
		label1.setText("Item List:");
		label1.setFont(label1.getFont().deriveFont(
				label1.getFont().getStyle() | Font.BOLD));

		// ---- label2 ----
		label2.setText("Preview:");
		label2.setFont(label2.getFont().deriveFont(
				label2.getFont().getStyle() | Font.BOLD));
		label2.setBackground(Color.black);

		// ---- button1 ----
		button1.setText("Pack Items");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				itemPackMouseReleased(e);
			}
		});

		// ---- label3 ----
		label3.setText("Item Name");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout
				.setHorizontalGroup(contentPaneLayout
						.createParallelGroup()
						.addGroup(
								contentPaneLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												contentPaneLayout
														.createParallelGroup()
														.addComponent(
																label1,
																GroupLayout.PREFERRED_SIZE,
																61,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																scrollPane2,
																GroupLayout.PREFERRED_SIZE,
																91,
																GroupLayout.PREFERRED_SIZE))
										.addGap(29, 29, 29)
										.addGroup(
												contentPaneLayout
														.createParallelGroup()
														.addComponent(
																itemViewBox,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																contentPaneLayout
																		.createSequentialGroup()
																		.addComponent(
																				button1,
																				GroupLayout.PREFERRED_SIZE,
																				98,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				progressBar1,
																				GroupLayout.DEFAULT_SIZE,
																				292,
																				Short.MAX_VALUE))
														.addGroup(
																contentPaneLayout
																		.createSequentialGroup()
																		.addComponent(
																				label2,
																				GroupLayout.PREFERRED_SIZE,
																				65,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(114,
																				114,
																				114)
																		.addComponent(
																				label3,
																				GroupLayout.PREFERRED_SIZE,
																				120,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		contentPaneLayout
				.setVerticalGroup(contentPaneLayout
						.createParallelGroup()
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								contentPaneLayout
										.createSequentialGroup()
										.addGap(22, 22, 22)
										.addGroup(
												contentPaneLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(label1)
														.addComponent(label2)
														.addComponent(label3))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												contentPaneLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																contentPaneLayout
																		.createSequentialGroup()
																		.addComponent(
																				itemViewBox,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED,
																				14,
																				Short.MAX_VALUE)
																		.addGroup(
																				contentPaneLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								progressBar1,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								button1,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addComponent(
																scrollPane2,
																GroupLayout.PREFERRED_SIZE,
																278,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		setSize(560, 370);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane scrollPane2;
	public JList list1;
	public JPanel itemViewBox;
	private JLabel label1;
	private JLabel label2;
	private JButton button1;
	private JProgressBar progressBar1;
	private JLabel label3;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
