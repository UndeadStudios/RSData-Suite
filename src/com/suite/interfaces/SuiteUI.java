package com.suite.interfaces;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.suite.Constants;
import com.suite.cachelib.cache.Cache;
import com.suite.cachelib.util.Utils;
import com.suite.extractor.GUI;
import com.suite.loaders.VarBitDefinition;
import com.suite.openrs.cache.tools.MapIndexGenerator;
import com.suite.openrs.cache.tools.XTeaChecker;
import java.awt.Window.Type;

/**
 * @author Brainrain
 */
public class SuiteUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Cache cache = null;
	public JFileChooser filechooser = null;

	public static Cache cache() {
		return cache;
	}

	public SuiteUI() {
		setSize(439, 257);
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void button1MouseReleased(MouseEvent e) {
		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(1);
		int response = filechooser.showOpenDialog(this);
		if (response == 0) {
			File selectedDir = filechooser.getSelectedFile();
			if (selectedDir.isDirectory()) {
				loadCache(selectedDir.getAbsolutePath());
				Constants.cacheDir = selectedDir.getAbsolutePath();
				System.out.println("Cache directory set as: "+Constants.cacheDir);
			}
		}
	}
	
	private void button2MouseReleased(MouseEvent e) {
		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(1);
		int response = filechooser.showOpenDialog(this);
		if (response == 0) {
			File selectedDir = filechooser.getSelectedFile();
			if (selectedDir.isDirectory()) {
				if (cacheLoaded) {
				decryptButton.setEnabled(true);
				}
				Constants.xteaDir = selectedDir.getAbsolutePath();
				System.out.println("XTEA directory set as: "+Constants.xteaDir);
			}
		}
	}
	
	public static boolean cacheLoaded;

	private void loadCache(String Dir) {
		cacheLoaded = true;
		cache = new Cache(Dir);
		if (cache != null) {
			itemDefButton.setEnabled(true);
			npcButton.setEnabled(true);
			objectButton.setEnabled(true);
			varbitButton.setEnabled(true);
		}
		if (Constants.xteaDir != "") {
			decryptButton.setEnabled(true);
		}
	}
	
	private static int[] opcodes = {1,2,3,5,14,15,17,18,19,21,22,23,24,28,29,39,30,40,60,62,64,65,66,67,68,69,70,71,72,73,74,75,77};

	private void objectDefButtonActionPerformed(ActionEvent e) {
		ObjectGUI item = new ObjectGUI();
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i <= com.suite.loaders.VarBitDefinition
				.getAmountOfObjects(cache); i++)
			model.addElement(i);
		item.list1.setModel(model);
		
		DefaultListModel lemp = new DefaultListModel();
		for (int i = 0; i < opcodes.length; i++)
			lemp.addElement(opcodes[i]);
		item.list.setModel(lemp);
	}

	private void npcDefButtonActionPerformed(ActionEvent e) {
		NpcGUI item = new NpcGUI();
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i <= Utils.getAmountOfNpcs(cache); i++)
			model.addElement(i);
		item.list1.setModel(model);
	}

	private void itemDefButtonActionPerformed(ActionEvent e) {
		ItemGUI item = new ItemGUI();
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i <= Utils.getAmountOfItems(cache); i++)
			model.addElement(i);
		item.list1.setModel(model);
	}
	private void VarBitDefButtonActionPerformed(ActionEvent e) {
		VarBitGUI item = new VarBitGUI();
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i <= VarBitDefinition.getAmountOfObjects(cache); i++)
			model.addElement(i);
		item.list1.setModel(model);
	}
	private void mapindexButtonActionPerformed(ActionEvent e) {
		MapIndexGenerator.dumpMapIndex();
	}
	
	private void decryptButtonActionPerformed(ActionEvent e) {
		new GUI();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		cacheButton = new JButton();
		xteaButton = new JButton();
		Title = new JLabel();
		itemDefButton = new JButton();
		decryptButton = new JButton();
		objectButton = new JButton();
		npcButton = new JButton();
		varbitButton = new JButton();
		extractButton = new JButton();

		// ======== this ========
		setTitle("RS Data Suite");
		setResizable(false);
		Container contentPane = getContentPane();

		// ---- cacheButton ----
		cacheButton.setText("Load Cache");
		cacheButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				button1MouseReleased(e);
			}
		});
		
		// ---- cacheButton ----
		xteaButton.setText("Load XTEA");
		xteaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				button2MouseReleased(e);
			}
		});

		// ---- Title ----
		Title.setText("RS Data Suite");
		Title.setFont(new Font("Papyrus", Font.BOLD, 16));

		// ---- itemDefButton ----
		itemDefButton.setText("Item Definition");
		itemDefButton.setEnabled(false);
		itemDefButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemDefButtonActionPerformed(e);
			}
		});

		// ---- idkButton ----
		decryptButton.setText("Decrypt Maps");
		decryptButton.setEnabled(false);
		decryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapindexButtonActionPerformed(e);
			}
		});

		// ---- objectButton ----
		objectButton.setText("Object Definition");
		objectButton.setEnabled(false);
		objectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				objectDefButtonActionPerformed(e);
			}
		});

		// ---- npcButton ----
		npcButton.setText("NPC Definition");
		npcButton.setEnabled(false);
		npcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				npcDefButtonActionPerformed(e);
			}
		});
		// ---- varbitButton ----
		varbitButton.setText("Varbit Definition");
		varbitButton.setEnabled(false);
		varbitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VarBitDefButtonActionPerformed(e);
			}
		});
		// ---- animButton ----
		extractButton.setText("Open Cache Extractor");
		extractButton.setEnabled(true);
		extractButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				decryptButtonActionPerformed(e);
			}
		});

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addComponent(cacheButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(xteaButton)
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addComponent(Title, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addComponent(itemDefButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(extractButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(decryptButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(objectButton, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(npcButton, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(varbitButton, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cacheButton)
						.addComponent(Title)
						.addComponent(xteaButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(itemDefButton)
					.addGap(5)
					.addComponent(objectButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(npcButton)
					.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(varbitButton)
						.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(extractButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(decryptButton)
					.addContainerGap())
		);
		contentPane.setLayout(contentPaneLayout);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JButton cacheButton;
	private JButton xteaButton;
	private JLabel Title;
	private JButton itemDefButton;
	private JButton decryptButton;
	private JButton objectButton;
	private JButton npcButton;
	private JButton varbitButton;
	private JButton extractButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
