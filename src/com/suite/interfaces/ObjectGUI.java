package com.suite.interfaces;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.suite.loaders.ObjectDefinition562;
import com.suite.loaders.ObjectDefinition562;

public class ObjectGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public ObjectGUI() {
		initComponents();
		setVisible(true);
	}

	private void itemListMouseReleased(MouseEvent e) {
		ObjectDefinition562 item = new ObjectDefinition562(SuiteUI.cache(),
				list1.getSelectedIndex());
		label.setText("");
		lblValue.setText("");
		lblNewLabel.setText("");
		if (item != null) {
			itemListMouseReleased1(e);
		}
	}
	
	private void itemListMouseReleased1(MouseEvent e) {
		ObjectDefinition562 item = new ObjectDefinition562(SuiteUI.cache(),
				list1.getSelectedIndex());
		label.setText("");
		lblValue.setText("");
		lblNewLabel.setText("");
		if (item != null) {
			switch (list.getSelectedIndex()) {
				case 0:
					if (item.models != null)
						lblValue.setText("object_model_ids: "+Arrays.toString(item.models));
					else
						lblValue.setText("object_model_ids: null");
					if (item.shapes != null)
						label.setText("object_model_type: "+Arrays.toString(item.shapes));
					else
						label.setText("object_model_type: null");
					break;
				case 1:
					if (item.name != "null")
						lblValue.setText("name: "+item.name);
					else
						lblValue.setText("name: null");
					break;
				/*case 2:
					if (item.name != "null")
						lblValue.setText("description: It's a "+item.name);
					else
						lblValue.setText("description: null");
					break;
				case 3:
					if (item.object_model_type != null)
						lblValue.setText("object_model_type: "+Arrays.toString(item.object_model_type));
					else
						lblValue.setText("object_model_type: null");
					break;
				case 4:
					if (item.width != 1)
						lblValue.setText("width: "+item.width);
					else
						lblValue.setText("width: 1");
					break;
				case 5:
					if (item.height != 1)
						lblValue.setText("height: "+item.height);
					else
						lblValue.setText("height: 1");
					break;
				case 6:
					if (!item.isUnwalkable)
						lblValue.setText("isUnwalkable: false");
					else
						lblValue.setText("isUnwalkable: true");
					break;
				case 7:
					if (!item.isUnwalkable)
						lblValue.setText("isUnwalkable: false");
					else
						lblValue.setText("isUnwalkable: true");
					break;
				case 8:
					if (item.hasActions != -1)  {
						if (item.hasActions == 1)
						lblValue.setText("hasActions: true");
					} else 
						lblValue.setText("hasActions: false");
					break;
				case 9:
					if (item.adjustToTerrain == 1)
						lblValue.setText("adjustToTerrain: true");
					else
						lblValue.setText("adjustToTerrain: false");
					break;
				case 10:
					if (item.nonFlatShading)
						lblValue.setText("nonFlatShading: true");
					else
						lblValue.setText("nonFlatShading: false");
					break;
				case 11:
					if (item.aBoolean764 == 1)
						lblValue.setText("aBoolean764: true");
					else
						lblValue.setText("aBoolean764: false");
					break;
				case 12:
					if (item.animation_id != -1)
						lblValue.setText("animation_id: "+item.animation_id);
					else
						lblValue.setText("animation_id: -1");
					break;
				case 13:
					if (item.anInt775 != -1)
						lblValue.setText("anInt775: "+item.anInt775);
					else
						lblValue.setText("anInt775: -1");
					break;
				case 14:
					if (item.brightness != 0)
						lblValue.setText("brightness: "+item.brightness);
					else
						lblValue.setText("brightness: 0");
					break;
				case 15:
					if (item.contrast != 0)
						lblValue.setText("contrast: "+item.contrast);
					else
						lblValue.setText("contrast: 0");
					break;
				case 16:
					if (item.actions != null)
						lblValue.setText("actions: "+Arrays.toString(item.actions));
					else
						lblValue.setText("actions: null");
					break;
				case 17:
					if (item.originalModelColors != null)
						lblValue.setText("originalModelColors: "+Arrays.toString(item.originalModelColors));
					else
						lblValue.setText("originalModelColors: null");
					if (item.modifiedModelColors != null)
						label.setText("modifiedModelColors: "+Arrays.toString(item.modifiedModelColors));
					else
						label.setText("modifiedModelColors: null");
					break;
				case 18:
					if (item.mapFunctionID != -1)
						lblValue.setText("mapFunctionID: "+item.mapFunctionID);
					else
						lblValue.setText("mapFunctionID: -1");
					break;
				case 19:
					lblValue.setText("aBoolean751: "+item.aBoolean751);
					break;
				case 20:
					if (!item.aBoolean779)
						lblValue.setText("aBoolean779: false");
					else
						lblValue.setText("aBoolean779: true");
					break;
				case 21:
					if (item.modelSizeX != 128)
						lblValue.setText("modelSizeX: "+item.modelSizeX);
					else
						lblValue.setText("modelSizeX: 128");
					break;
				case 22:
					if (item.modelSizeH != 128)
						lblValue.setText("modelSizeH: "+item.modelSizeH);
					else
						lblValue.setText("modelSizeH: 128");
					break;
				case 23:
					if (item.modelSizeY != 128)
						lblValue.setText("modelSizeY: "+item.modelSizeY);
					else
						lblValue.setText("modelSizeY: 128");
					break;
				case 24:
					if (item.mapSceneID != -1)
						lblValue.setText("mapSceneID: "+item.mapSceneID);
					else
						lblValue.setText("mapSceneID: -1");
					break;
				case 25:
					if (item.anInt768 != 0)
						lblValue.setText("anInt768: "+item.anInt768);
					else
						lblValue.setText("anInt768: 0");
					break;
				case 26:
					if (item.offsetX != 0)
						lblValue.setText("offsetX: "+item.offsetX);
					else
						lblValue.setText("offsetX: 0");
					break;
				case 27:
					if (item.offsetH != 0)
						lblValue.setText("offsetH: "+item.offsetH);
					else
						lblValue.setText("offsetH: 0");
					break;
				case 28:
					if (item.offsetY != 0)
						lblValue.setText("offsetY: "+item.offsetY);
					else
						lblValue.setText("offsetY: 0");
					break;
				case 29:
					lblValue.setText("aBoolean736: "+item.aBoolean736);
					break;
				case 30:
					lblValue.setText("isSolidObject: "+item.isSolidObject);
					break;
				case 31:
					if (item.anInt2703 != -1)
						lblValue.setText("anInt2703: "+item.anInt2703);
					else
						lblValue.setText("anInt2703: -1");
					break;
				case 32:
					lblValue.setText("variable_id_bitfield: "+item.variable_id_bitfield);
					label.setText("variable_id: "+item.variable_id);
					if (item.alternative_ids != null)
						lblNewLabel.setText("alternative_ids: "+Arrays.toString(item.alternative_ids));
					else
						lblNewLabel.setText("alternative_ids: null");
					break;*/
			}
		}
	}

	private void objectPackMouseReleased(MouseEvent e) {
		ObjectDefinition562.objectPack(SuiteUI.cache());
		progressBar1.setValue(100);
	}

	private void initComponents() {
		scrollPane2 = new JScrollPane();
		list1 = new JList();
		itemViewBox = new JPanel();
		label1 = new JLabel();
		preview = new JLabel();
		button1 = new JButton();
		progressBar1 = new JProgressBar();
		setTitle("Object Definitions");
		setResizable(false);
		Container contentPane = getContentPane();
		{
			list1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					itemListMouseReleased(e);
				}
			});
			scrollPane2.setViewportView(list1);
		}
		
		lblValue = new JLabel();
		
		label = new JLabel();
		
		lblNewLabel = new JLabel();
		{

			GroupLayout itemViewBoxLayout = new GroupLayout(itemViewBox);
			itemViewBoxLayout.setHorizontalGroup(
				itemViewBoxLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(itemViewBoxLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(itemViewBoxLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblValue, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
						.addContainerGap())
			);
			itemViewBoxLayout.setVerticalGroup(
				itemViewBoxLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(itemViewBoxLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblValue)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel)
						.addContainerGap(171, Short.MAX_VALUE))
			);
			itemViewBox.setLayout(itemViewBoxLayout);
		}
		label1.setText("Object List:");
		label1.setFont(label1.getFont().deriveFont(
				label1.getFont().getStyle() | Font.BOLD));

		preview.setText("Opcodes:");
		preview.setFont(preview.getFont().deriveFont(
				preview.getFont().getStyle() | Font.BOLD));
		preview.setBackground(Color.black);

		button1.setText("Pack Object");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				objectPackMouseReleased(e);
			}
		});
		
		scrollPane = new JScrollPane();

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
					.addGap(29)
					.addGroup(contentPaneLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(button1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(progressBar1, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(preview, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(itemViewBox, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(contentPaneLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label1)
						.addComponent(preview))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane)
								.addComponent(itemViewBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(progressBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		list = new JList();
		scrollPane.setViewportView(list);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					itemListMouseReleased1(e);
				}
			});
			scrollPane.setViewportView(list);
		contentPane.setLayout(contentPaneLayout);
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
	private JLabel preview;
	private JButton button1;
	private JLabel lblValue;
	private JProgressBar progressBar1;
	private JScrollPane scrollPane;
	public JList list;
	private JLabel label;
	private JLabel lblNewLabel;
}
