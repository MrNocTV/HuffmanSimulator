import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
//just import stuff, don't bother with this
import javax.swing.table.DefaultTableModel;


//figure it out for yourself, why do we have to extend JFrame? :) 
public class HuffmanProgram extends JFrame {
	//declare necessary things
	private JTable sourceTable;
	private JTable resultTable;
	private double averageLength;
	private double entropy;
	private double efficiency;
	private static final String source1 = "x";
	private static final String source2 = "x.x";
	private static final String source3 = "x.x.x";
	private JTextField inputKeyTextField;
	private JTextField inputProbabilityTextField;
	private JButton addRecordButton;
	private JButton removeRecordButton;
	private JLabel entropyLabel;
	private JLabel averageLengthLabel;
	private JLabel efficiencyLabel;
	private JButton encodeButton;
	private JButton clearButton;
	private JRadioButton source1RadioButton;
	private JRadioButton source2RadioButton;
	private JRadioButton source3RadioButton;
	private JRadioButton standardRadioButton;
	private JRadioButton extendRadioButton;
	private JPopupMenu popupMenu;
	private DefaultTableModel sourceTableModel;
	private DefaultTableModel resultTableModel;
	private JLabel nLabel;
	private JTextField searchTextField;
	private JButton searchButton;
	private JTextField searchResultTextField;
	
	//of course, this is a constructor
	public HuffmanProgram() {
		super("Hu77m4n   &C0D|nG");
		try {
			//set look-and-feel of window
			LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
			for(LookAndFeelInfo laf : info) {
				System.out.println(laf.getClassName());
			}
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(HuffmanProgram.this, "Cannot load look-and-feel.");
		}
		setLayout(null); //set layout to null means you have to put every single UI component programmatically 
		getContentPane().setBackground(Color.BLACK); //set background color to black ... because I like darkness , hahahaha
		
		
		/******inputPanel*******/
		//create a font .... for what? Of course, to set font for some components in this panel 
		//this font is from Serif family, its style is bold, and its size is 15
		Font font = new Font("Serif", Font.BOLD, 15);
		//create panel for input purpose
		//contains inputKeyTextField & inputProbabilityTextField
		JPanel inputPanel = new JPanel();
		inputPanel.setSize(278, 125);
		inputPanel.setBackground(Color.BLACK);
		inputPanel.setLayout(null);
		inputPanel.setLocation(10, 10);
		inputPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GREEN, 2), "Input", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font, Color.GREEN));
		add(inputPanel); //add this panel to window
		
		//create label for inputKeyTextField
		//set font like above
		//set font color to green and background color to black, I like darkness =]]
		
		JLabel inputKeyLabel = new JLabel("Key");
		inputKeyLabel.setFont(font);
		inputKeyLabel.setForeground(Color.GREEN);
		inputKeyLabel.setBackground(Color.BLACK);
		//don't forget to set its size and location 
		//note: this label location coordinates is base on its parent's location, in this case, is the inputPanel
		inputKeyLabel.setSize(90, 20);
		inputKeyLabel.setLocation(10, 30);
		inputPanel.add(inputKeyLabel); //and last but not least, put it in inputPanel
		
		//create text field for input key, set size, location
		//from now on, no more explanation for this kind of stuff
		inputKeyTextField = new JTextField();
		inputKeyTextField.setSize(160, 20);
		inputKeyTextField.setLocation(100, 27);
		inputKeyTextField.setHorizontalAlignment(JTextField.RIGHT);
		inputPanel.add(inputKeyTextField);
		
		JLabel inputProbabilityLabel = new JLabel("Probability");
		inputProbabilityLabel.setFont(font);
		inputProbabilityLabel.setForeground(Color.GREEN);
		inputProbabilityLabel.setBackground(Color.BLACK);
		inputProbabilityLabel.setSize(90, 20); //width, height, respectively
		inputProbabilityLabel.setLocation(10, 60);
		inputPanel.add(inputProbabilityLabel);
		
		//create text field for input probability
		inputProbabilityTextField = new JTextField();
		inputProbabilityTextField.setSize(160, 20);
		inputProbabilityTextField.setLocation(100, 60);
		inputProbabilityTextField.setHorizontalAlignment(JTextField.RIGHT);
		inputPanel.add(inputProbabilityTextField);
		
		//create add button
		addRecordButton = new JButton("Add");
		addRecordButton.setBorder(new LineBorder(Color.GREEN, 2));
		addRecordButton.setBackground(Color.BLACK);
		addRecordButton.setForeground(Color.GREEN);
		addRecordButton.setSize(70, 20);
		addRecordButton.setLocation(100,90);
		addRecordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//set another color when user hover the mouse over this button
		addRecordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				addRecordButton.setBackground(new Color(100, 100, 100));
			}
			
			@Override
			public void mouseExited(MouseEvent event) {
				addRecordButton.setBackground(Color.BLACK);
			}
		});
		
		inputPanel.add(addRecordButton);
		
		//create remove button
		removeRecordButton = new JButton("Remove");
		removeRecordButton.setBorder(new LineBorder(Color.GREEN, 2));
		removeRecordButton.setBackground(Color.BLACK);
		removeRecordButton.setForeground(Color.GREEN);
		removeRecordButton.setSize(70, 20);
		removeRecordButton.setLocation(190,90);
		removeRecordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//set another color when user hover the mouse over this button
		removeRecordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				removeRecordButton.setBackground(new Color(100, 100, 100));
			}
			
			@Override
			public void mouseExited(MouseEvent event) {
				removeRecordButton.setBackground(Color.BLACK);
			}
		});
		inputPanel.add(removeRecordButton);
		/*************/
		
		
		/******belowInputPanel*****/
		JPanel belowInputPanel = new JPanel();
		belowInputPanel.setLayout(null);
		belowInputPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GREEN, 2), "Other Info", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font, Color.GREEN));
		belowInputPanel.setSize(278, 160);
		belowInputPanel.setLocation(10, 140);
		belowInputPanel.setBackground(Color.BLACK);
		add(belowInputPanel);
		
		JLabel kindOfSourceLabel = new JLabel("Kind of source:");
		kindOfSourceLabel.setSize(120, 20);
		kindOfSourceLabel.setForeground(Color.GREEN);
		kindOfSourceLabel.setFont(font);
		kindOfSourceLabel.setBackground(Color.BLACK);
		kindOfSourceLabel.setLocation(13,25);
		belowInputPanel.add(kindOfSourceLabel);
	
		ButtonGroup sourceGroup = new ButtonGroup();
		source1RadioButton = new JRadioButton("x");
		source1RadioButton.setSize(60, 20);
		source1RadioButton.setLocation(10, 40);
		source1RadioButton.setFont(font);
		source1RadioButton.setForeground(Color.GREEN);
		source1RadioButton.setBackground(Color.BLACK);
		source1RadioButton.setBorderPainted(false);
		source2RadioButton = new JRadioButton("x.x");
		source2RadioButton.setSize(60,20);
		source2RadioButton.setLocation(115, 40);
		source2RadioButton.setFont(font);
		source2RadioButton.setForeground(Color.GREEN);
		source2RadioButton.setBackground(Color.BLACK);
		source3RadioButton = new JRadioButton("x.x.x");
		source3RadioButton.setSize(60,20);
		source3RadioButton.setLocation(205, 40);
		source3RadioButton.setFont(font);
		source3RadioButton.setForeground(Color.GREEN);
		source3RadioButton.setBackground(Color.BLACK);
		//add radio button to group 
		//this group manage all radio buttons so that only 1 button can be clicked at a time
		sourceGroup.add(source1RadioButton);
		sourceGroup.add(source2RadioButton);
		sourceGroup.add(source3RadioButton);
		source1RadioButton.setSelected(true); //set x to be the default kind of source
		//add these radio buttons to jframe
		belowInputPanel.add(source1RadioButton);
		belowInputPanel.add(source2RadioButton);
		belowInputPanel.add(source3RadioButton);
		
		JLabel standardExtendLabel = new JLabel("Standard/Extend");
		standardExtendLabel.setSize(120, 20);
		standardExtendLabel.setLocation(13, 65);
		standardExtendLabel.setFont(font);
		standardExtendLabel.setForeground(Color.GREEN);
		standardExtendLabel.setBackground(Color.BLACK);
		belowInputPanel.add(standardExtendLabel);
		
		//standard/extend group
		ButtonGroup seGroup = new ButtonGroup();
		standardRadioButton = new JRadioButton("m=2");
		standardRadioButton.setSize(60, 20);
		standardRadioButton.setLocation(10, 80);
		standardRadioButton.setFont(font);
		standardRadioButton.setForeground(Color.GREEN);
		standardRadioButton.setBackground(Color.BLACK);
		belowInputPanel.add(standardRadioButton);
		extendRadioButton = new JRadioButton("m=3");
		extendRadioButton.setSize(60, 20);
		extendRadioButton.setLocation(205, 80);
		extendRadioButton.setFont(font);
		extendRadioButton.setForeground(Color.GREEN);
		extendRadioButton.setBackground(Color.BLACK);
		belowInputPanel.add(extendRadioButton);
		seGroup.add(standardRadioButton);
		seGroup.add(extendRadioButton);
		standardRadioButton.setSelected(true); //default would be standard
		
		JLabel encodeClearLabel = new JLabel("Encode/Clear");
		encodeClearLabel.setSize(120, 20);
		encodeClearLabel.setLocation(13, 102);
		encodeClearLabel.setFont(font);
		encodeClearLabel.setForeground(Color.GREEN);
		encodeClearLabel.setBackground(Color.BLACK);
		belowInputPanel.add(encodeClearLabel);
		encodeButton = new JButton("Encode");
		encodeButton.setBorder(new LineBorder(Color.GREEN, 2));
		encodeButton.setBackground(Color.BLACK);
		encodeButton.setForeground(Color.GREEN);
		encodeButton.setSize(70, 20);
		encodeButton.setLocation(60,125);
		encodeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//set another color when user hover the mouse over this button
		encodeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				encodeButton.setBackground(new Color(100, 100, 100));
			}
			
			@Override
			public void mouseExited(MouseEvent event) {
				encodeButton.setBackground(Color.BLACK);
			}
		});
		belowInputPanel.add(encodeButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBorder(new LineBorder(Color.GREEN, 2));
		clearButton.setBackground(Color.BLACK);
		clearButton.setForeground(Color.GREEN);
		clearButton.setSize(70, 20);
		clearButton.setLocation(150,125);
		clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//set another color when user hover the mouse over this button
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				clearButton.setBackground(new Color(100, 100, 100));
			}
			
			@Override
			public void mouseExited(MouseEvent event) {
				clearButton.setBackground(Color.BLACK);
			}
		});
		belowInputPanel.add(clearButton);
		/***************************/
		
		
		/*******sourcePanel********/
		JPanel sourcePanel = new JPanel();
		sourcePanel.setLayout(null);
		sourcePanel.setSize(278, 290);
		sourcePanel.setLocation(295, 10);
		sourcePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GREEN, 2), "Source", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font, Color.GREEN));
		sourcePanel.setBackground(Color.BLACK);
		add(sourcePanel);
		
		//here we go, resourceTable, a JTable
		//In order to build a table, we must have an Object array represent its headers
		//We create a DefaultTableModel object with the above array we've created
		//and past model object into table constructor
		sourceTable = new JTable(new DefaultTableModel(new Object[]{"Key", "Probability"}, 0)){
			//prevent table from being edited
			@Override
			public boolean isCellEditable(int row, int col) { return false; }
		};
		sourceTable.setFillsViewportHeight(true);
		sourceTable.setGridColor(Color.BLACK);
		sourceTableModel = (DefaultTableModel) sourceTable.getModel();
		//by default, JTable does not have a scroll
		//so we must attach to it a scroll pane
		JScrollPane sourceScrollPane = new JScrollPane(sourceTable);
		sourceScrollPane.setSize(248,250);
		sourceScrollPane.setLocation(15,25);
		//set the vertical bar always exist 
		sourceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sourcePanel.add(sourceScrollPane);
		/***************************/
		
		//because we've already talked about table above
		//so I don't comment anything here
		/********resultTable - resultPanel*********/
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		resultPanel.setSize(563, 200);
		resultPanel.setLocation(10, 305);
		resultPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GREEN, 2), "Result", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font, Color.GREEN));
		resultPanel.setBackground(Color.BLACK);
		add(resultPanel);
		
		
		resultTable = new JTable(new DefaultTableModel(new Object[]{"Key", "Probability","Code","Length"}, 0)) {
			@Override
			public boolean isCellEditable(int row, int col) { return false; }
		};
		resultTable.setGridColor(Color.BLACK);
		resultTable.setFillsViewportHeight(true);
		//this is called (down casting in Java - assigned super class variable to its subclass )
		resultTableModel = (DefaultTableModel) resultTable.getModel();
		JScrollPane resultScrollPane = new JScrollPane(resultTable);
		resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		resultScrollPane.setSize(535, 160);
		resultScrollPane.setLocation(15,25);
		resultPanel.add(resultScrollPane);
		/***************************/
		
		//entropy
		//average length
		//efficiency
		//n label - number of times we got new probability value while processing
		entropyLabel = new JLabel("H = ");
		entropyLabel.setForeground(Color.WHITE);
		entropyLabel.setFont(font);
		entropyLabel.setBackground(Color.BLACK);
		entropyLabel.setSize(100, 20);
		entropyLabel.setLocation(10,510);
		add(entropyLabel);
		averageLengthLabel = new JLabel("Avg length = ");
		averageLengthLabel.setForeground(Color.WHITE);
		averageLengthLabel.setFont(font);
		averageLengthLabel.setBackground(Color.BLACK);
		averageLengthLabel.setSize(140,20);
		averageLengthLabel.setLocation(150, 510);
		add(averageLengthLabel);
		efficiencyLabel = new JLabel("h = ");
		efficiencyLabel.setSize(100,20);
		efficiencyLabel.setFont(font);
		efficiencyLabel.setForeground(Color.WHITE);
		efficiencyLabel.setBackground(Color.BLACK);
		efficiencyLabel.setLocation(340, 510);
		add(efficiencyLabel);
		nLabel = new JLabel("n = ");
		nLabel.setSize(100, 20);
		nLabel.setFont(font);
		nLabel.setBackground(Color.BLACK);
		nLabel.setForeground(Color.WHITE);
		nLabel.setLocation(460, 510);
		add(nLabel);
		
		//search text field
		searchTextField = new JTextField();
		searchTextField.setSize(327, 20);
		searchTextField.setLocation(12, 540);
		searchTextField.setText("Search");
		add(searchTextField);
		
		searchButton = new JButton("Search");
		searchButton.setBorder(new LineBorder(Color.GREEN, 2));
		searchButton.setBackground(Color.BLACK);
		searchButton.setForeground(Color.GREEN);
		searchButton.setSize(70, 20);
		searchButton.setLocation(350,540);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//set another color when user hover the mouse over this button
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				searchButton.setBackground(new Color(100, 100, 100));
			}
			
			@Override
			public void mouseExited(MouseEvent event) {
				searchButton.setBackground(Color.BLACK);
			}
		});
		add(searchButton);
		
		searchResultTextField = new JTextField();
		searchResultTextField.setEditable(false);
		searchResultTextField.setSize(140, 20);
		searchResultTextField.setLocation(430, 540);
		add(searchResultTextField);
		
		//huffman label 
		JLabel huffmanLabel = new JLabel("Hu77m4n   &C0D|nG");
		huffmanLabel.setSize(400, 150);
		huffmanLabel.setForeground(Color.GREEN);
		huffmanLabel.setBackground(Color.BLACK);
		huffmanLabel.setFont(new Font("Serif", Font.BOLD, 40));
		huffmanLabel.setLocation(100, 520);
		add(huffmanLabel);
		
		JLabel copyrightLabel = new JLabel("© 03/24/2016 mrN0b0dy");
		copyrightLabel.setSize(170, 20);
		copyrightLabel.setLocation(200, 610);
		copyrightLabel.setFont(font);
		copyrightLabel.setForeground(Color.GREEN);
		copyrightLabel.setBackground(Color.BLACK);
		add(copyrightLabel);
	
		
		/****Add handlers******/
		InputHandler inputHandler = new InputHandler();
		inputProbabilityTextField.addActionListener(inputHandler);
		addRecordButton.addActionListener(inputHandler);
		removeRecordButton.addActionListener(inputHandler);
		ClearHandler clearHandler = new ClearHandler();
		clearButton.addActionListener(clearHandler);
		EncodeHandler encodeHandler = new EncodeHandler();
		encodeButton.addActionListener(encodeHandler);
		SearchHandler searchHandler = new SearchHandler();
		searchTextField.addActionListener(searchHandler);
		searchButton.addActionListener(searchHandler);
		/**********/
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close window when click on "x" icon
		setSize(580,660); //set size of window
		setResizable(false); //prevent user from resizing this window
		setVisible(true); //make it appear =]]]]
		setLocationRelativeTo(null); //locate window at center of screen
	}
	
	/*************These are class for handling events****************/
	//class for handling input
	//if you don't know, this is called inner class
	//this class has power to access its outer class private instances
	//in this case, HuffmanProgram's instances
	private class InputHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			//detect if either user hit enter from inputProbabilityTextField
			//or they click on add button
			if(event.getSource() == inputProbabilityTextField || event.getSource() == addRecordButton) {
				String key = inputKeyTextField.getText();
				if(key == null || key.equals("")) {
					JOptionPane.showMessageDialog(HuffmanProgram.this, "You must enter a key/name");
					return;
				}
				//check duplicate keys
				for(int i = 0; i < sourceTable.getRowCount(); ++i) {
					if(sourceTable.getValueAt(i, 0).equals(key)) {
						JOptionPane.showMessageDialog(HuffmanProgram.this, "Duplicate key");
						return;
					}
				}
				
				String probString = inputProbabilityTextField.getText();
				Double prob = .0;
				try {
					prob = Double.parseDouble(probString);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(HuffmanProgram.this, "Exception from probability input.");
				}
				//insert a new row in source table
				sourceTableModel.addRow(new Object[]{key,prob.toString()});
			}
			//handle when user click on remove button 
			//because of there silly mistake
			//to not to use this button, make sure your input is clear and right before you hit enter or add button
			else if(event.getSource() == removeRecordButton) {
				//if the key field is empty -> we do nothing
				String key = inputKeyTextField.getText();
				if( key == null || key.equals("")) {
					return;
				}
				
				//check in source table if there is a row contains the key value which equals with the value user want to remove
				for(int i = 0; i < sourceTable.getRowCount(); ++i ) {
					//we found it -> remove and then return
					if(sourceTable.getValueAt(i, 0).equals(key)) {
						sourceTableModel.removeRow(i);
						return;
					}
				}
			} //end else if
		}
		
	}
	
	//handler for clear button click
	private class ClearHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			//clear text in input fields
			inputKeyTextField.setText("");
			inputProbabilityTextField.setText("");
			//clear data in source table
			while(sourceTable.getRowCount() > 0) {
				sourceTableModel.removeRow(0);
			}
			//reset value for entropyLabel, averageLength label, efficiencyLabel, nLabel
			entropyLabel.setText("H = ");
			averageLengthLabel.setText("Avg length = ");
			efficiencyLabel.setText("h = ");
			nLabel.setText("n = ");
			
			//clear all data in result table
			//we do this last because it might take long time to erase all data in a big table
			while(resultTable.getRowCount() > 0) {
				resultTableModel.removeRow(0);
			}	
			
			searchResultTextField.setText("");
			searchTextField.setText("Search");
		}
	}
	
	//handler for encode button click
	private class EncodeHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			//prevent user from click on encode button after they've just encoded 
			//to do that we just simply check result table size, if it's bigger than 0, 
			//means users have just encoded something
			if(resultTable.getRowCount() > 0) return;
			// I don't have time to program this so that it can support multithreading
			//so if data is big, it might take long to encode
			
			//m=2
			if(standardRadioButton.isSelected()) {
				LinkedList<Node> list = new LinkedList<Node>();
				//get data from source table
				for(int i = 0; i < sourceTable.getRowCount(); ++i) {
					String name = (String)sourceTable.getValueAt(i, 0);
					BigDecimal val = new BigDecimal((String) sourceTable.getValueAt(i, 1));
					list.add(new Node(name, val));
				}
				Collections.sort(list, new NodeComparator());
				//source = "x"
				if(source1RadioButton.isSelected()) {
					printList(list);
					huffman(list, list.size());
				}
				//source ="x.x"
				else if(source2RadioButton.isSelected()) {
					//this is x.x list 
					LinkedList<Node> tempList = new LinkedList<Node>();
					for(Node node : list) {
						for(Node node1 : list) {
							Node tempNode = new Node(node.name + node1.name, node.val.multiply(node1.val));
							tempList.add(tempNode);
						}
					}
					printList(tempList);
					huffman(tempList, tempList.size());
				}
				//source ="x.x.x"
				else if(source3RadioButton.isSelected()) {
					//this is x.x list 
					LinkedList<Node> tempList = new LinkedList<Node>();
					for(Node node : list) {
						for(Node node1 : list) {
							Node tempNode = new Node(node.name + node1.name, node.val.multiply(node1.val));
							tempList.add(tempNode);
						}
					}
					//this is x.x.x list
					LinkedList<Node> lastList = new LinkedList<Node>(tempList);
					for(Node node : tempList) {
						for(Node node1 : list) {
							Node tempNode = new Node(node.name + node1.name, node.val.multiply(node1.val));
							lastList.add(tempNode);
						}
					}
					printList(lastList);
					huffman(lastList, lastList.size());
				}
			} 
			//m=3
			else if(extendRadioButton.isSelected()) {
				LinkedList<ExtendedNode> list = new LinkedList<ExtendedNode>();
				for(int i = 0; i < sourceTable.getRowCount(); ++i) {
					String name = (String) sourceTable.getValueAt(i, 0);
					BigDecimal val = new BigDecimal((String) sourceTable.getValueAt(i, 1));
					ExtendedNode tempNode = new ExtendedNode(name, val);
					list.add(tempNode);
				}
				//add last record with probability = .0f
				list.add(new ExtendedNode("last", BigDecimal.valueOf(0)));
				Collections.sort(list, new ExtendedNodeComparator());
				//source = "x"
				if(source1RadioButton.isSelected()) {
					printExtendedList(list);
					extendedHuffman(list, list.size());
				}
				//source = "x.x"
				else if(source2RadioButton.isSelected()) {
					LinkedList<ExtendedNode> tempList = new LinkedList<>();
					for(ExtendedNode node : list) {
						for(ExtendedNode node1 : list) {
							ExtendedNode tempNode = new ExtendedNode(node.name+node1.name, node.val.multiply(node1.val));
							tempList.add(tempNode);
						}
					}
					printExtendedList(tempList);
					extendedHuffman(tempList, tempList.size());
				}
				//source = "x.x.x"
				else if(source3RadioButton.isSelected()) {
					//this is x.x
					LinkedList<ExtendedNode> tempList = new LinkedList<>();
					for(ExtendedNode node : list) {
						for(ExtendedNode node1 : list) {
							ExtendedNode tempNode = new ExtendedNode(node.name+node1.name, node.val.multiply(node1.val));
							tempList.add(tempNode);
						}
					}
					
					//this is x.x.x
					LinkedList<ExtendedNode> lastList = new LinkedList<ExtendedNode>(tempList);
					for(ExtendedNode node : tempList) {
						for(ExtendedNode node1 : list) {
							ExtendedNode tempNode = new ExtendedNode(node.name+node1.name, node.val.multiply(node1.val));
							lastList.add(tempNode);
						}
					}
					printExtendedList(lastList);
					extendedHuffman(lastList, lastList.size());
				}
			}
			
			//calculate entropy, average length, efficiency, n 
			if(source1RadioButton.isSelected()) nLabel.setText("n = " + (sourceTable.getRowCount() - 2));
			else if(source2RadioButton.isSelected()) nLabel.setText("n = " + ((int)Math.pow(sourceTable.getRowCount(), 2) -2));
			else if(source3RadioButton.isSelected()) nLabel.setText("n = " + ((int)Math.pow(sourceTable.getRowCount(), 3) -2));
			double sum = .0;
			double base = standardRadioButton.isSelected() ? 2 : 3;
			for(int i = 0; i < resultTable.getRowCount(); ++i) {
				double val = Double.parseDouble((String) resultTable.getValueAt(i, 1).toString());
				double log = -logOfBase(base, val);
				val*= log;
				sum += val;
			}
			entropyLabel.setText("H = " + String.format("%.2f",sum) );
			double avgLength = .0;
			for(int i = 0; i < resultTable.getRowCount(); ++i) {
				double val = Double.parseDouble((String) resultTable.getValueAt(i, 1).toString());
				double l =  Double.parseDouble(resultTable.getValueAt(i, 3).toString());
				avgLength += (val*l);
				System.out.println(avgLength);
			}
			averageLengthLabel.setText("Avg length = " + String.format("%.2f", avgLength));
			
			efficiencyLabel.setText(String.format("h = %.2f ", sum/avgLength*100) + "%");
		}
	}
	
	private double logOfBase(double base, double val) {
		return (Math.log(val) / Math.log(base));
	}
	
	//handler for search
	private class SearchHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == searchTextField || event.getSource() == searchButton) {
				//search in result table
				for(int i = 0; i < resultTable.getRowCount(); ++i) {
					if(resultTable.getValueAt(i, 0).toString().equals(searchTextField.getText())) {
						searchResultTextField.setText(resultTable.getValueAt(i, 2).toString());
						break;
					}
				}
			}
		}
	}
	
	/*****************************/
	
	public static void main(String[] args) {
		new HuffmanProgram(); //tadahhhh .... we have a new window!
	}
	
	//these are method for encoding using Huffman algorithm
	//these are separated with the above methods by main 
	//for standard huffman (m=2)
		/*****************************/
		private void huffman(LinkedList<Node> list, int size) {
			Stack<Node> resultStack = new Stack<Node>();
			LinkedList<Node> tempList = new LinkedList<Node>(list);
			int count = 1;
		
			while(list.size() >= 2) {
				// two last item in list
				String name = "b" + count++;
				BigDecimal val = list.get(list.size()-1).val.add(list.get(list.size()-2).val);
				Node tmpNode = new Node(name, val);
				tmpNode.right = list.getLast();
				tmpNode.left = list.get(list.size()-2);
				resultStack.push(tmpNode);
				list.removeLast();
				list.removeLast();
				if(list.isEmpty()) tmpNode.name = "root";
				list.add(tmpNode);
				Collections.sort(list, new NodeComparator());
				for(Node n : list)
					System.out.print(n.name + " ");
				System.out.println();
			}
			//we got a result stack
			//this is like building the tree from bottom to root
			for(Node n : resultStack) {
				System.out.println(n.name + " : " + n.val + ", left: " +((n.left != null) ? n.left.name : "null") +", right: " + (n.right != null ? n.right.name : "null") );
			}
			for(Node node : tempList) {
				encryptedValueOfKey(node.name, resultStack, node.val);
			}
			
		}
		
		private void encryptedValueOfKey(String k, Stack<Node> resultStack, BigDecimal val) {
			String encryptedValue = "";
			String original =  new String(k);
			for(Node n : resultStack) {
				if(n.left.name.equals(k)) {
					k = n.name;
					encryptedValue = "0" + encryptedValue;
				}
				else if(n.right.name.equals(k)) {
					k = n.name;
					encryptedValue = "1" + encryptedValue;
				}
			}
			System.out.println(original + " : " + encryptedValue);
			resultTableModel.addRow(new Object[]{original, val, encryptedValue, encryptedValue.length()});
		}
		
		private class NodeComparator implements Comparator<Node> {
			@Override
			public int compare(Node n1, Node n2) {
				return n2.val.compareTo(n1.val);
			}
		}
		
		private class Node {
			BigDecimal val;
			String name;
			Node left;
			Node right;
			public Node(String name, BigDecimal val) {
				left = right = null;
				this.name = name;
				this.val = val;
			}
		}
		
		private void printList(Collection<Node> list) {
			for(Node n : list) {
				System.out.println(n.name + " : " + n.val + ", left: " +((n.left != null) ? n.left.name : "null") +", right: " + (n.right != null ? n.right.name : "null") );
			}
		}
		/********************/
		
		//for extended huffman (m=3)
		private void extendedHuffman(LinkedList<ExtendedNode> list, int size) {
			Stack<ExtendedNode> resultStack = new Stack<ExtendedNode>();
			LinkedList<ExtendedNode> tempList = new LinkedList<ExtendedNode>(list);
			int count = 1;
			while(list.size() >= 3) {
				String name = "b" + count++;
				BigDecimal val = list.get(list.size()-1).val.add(list.get(list.size()-2).val).add(list.get(list.size()-3).val);
				ExtendedNode tmpNode = new ExtendedNode(name, val);
				tmpNode.right = list.getLast();
				tmpNode.mid = list.get(list.size()-2);
				tmpNode.left = list.get(list.size()-3);
				resultStack.push(tmpNode);
				list.removeLast();
				list.removeLast();
				list.removeLast();
				if(list.isEmpty()) tmpNode.name = "root";
				list.add(tmpNode);
				Collections.sort(list, new ExtendedNodeComparator());
				for(ExtendedNode n : list)
					System.out.print(n.name + " ");
				System.out.println();
			}
			//we got a result stack
			//this is like building the tree from bottom to root
			for(ExtendedNode n : resultStack) {
				System.out.println(n.name + " : " + n.val + ", left: " +((n.left != null) ? n.left.name : "null") +", mid: " + (n.mid != null ? n.mid.name : "null") + ", right: " + (n.right != null ? n.right.name : "null") );
			}
			for(ExtendedNode node : tempList) {
				if(!node.name.equals("last"))
					encryptedValueOfKeyExtend(node.name, resultStack, node.val);
			}
		}
		
		private void encryptedValueOfKeyExtend(String k, Stack<ExtendedNode> resultStack, BigDecimal val) {
			if(!k.contains("last")) {
				String encryptedValue = "";
				String original =  new String(k);
				for(ExtendedNode n : resultStack) {
					if(n.left.name.equals(k)) {
						k = n.name;
						encryptedValue = "0" + encryptedValue;
					} else if(n.mid.name.equals(k)) {
						k = n.name;
						encryptedValue = "1" + encryptedValue;
					} else if(n.right.name.equals(k)) {
						k = n.name;
						encryptedValue = "2" + encryptedValue;
					}
				}
				System.out.println(original + " : " + encryptedValue);
				resultTableModel.addRow(new Object[]{original, val, encryptedValue, encryptedValue.length()});
			}
		}
		
		private class ExtendedNode {
			BigDecimal val;
			String name;
			ExtendedNode left;
			ExtendedNode right;
			ExtendedNode mid;
			public ExtendedNode(String name, BigDecimal val) {
				left = right = mid = null;
				this.val = val;
				this.name = name;
			}
		}
		
		private class ExtendedNodeComparator implements Comparator<ExtendedNode> {
			@Override
			public int compare(ExtendedNode n1, ExtendedNode n2) {
				return n2.val.compareTo(n1.val);
			}
		}
		
		private void printExtendedList(Collection<ExtendedNode> list) {
			for(ExtendedNode n : list) {
				System.out.println(n.name + " : " + n.val + ", left: " +((n.left != null) ? n.left.name : "null") + ", mid: " + (n.mid != null ? n.mid.name : "null") +", right: " + (n.right != null ? n.right.name : "null") );
			}
		}
}
