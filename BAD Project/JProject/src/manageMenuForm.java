import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Database.Connect;

public class manageMenuForm extends JFrame implements ActionListener, MouseListener {
	
	Random rand = new Random();
	JLabel cakeLandTitle, cakeList, cakeName, cakePrice, shape, ovalSize, rectangleSize;
	JTable cakeTable;
	JScrollPane cakeTableContainer;
	JTextField cakeNameField, cakePriceField;
	JComboBox shapeBox, ovalSizeBox, rectangleSizeBox;
	JButton removeCake, addCake, backToMainMenu;
	DefaultTableModel cakeTableModel;
	
	JPanel northPanel, north_1, north_2, centerPanel, southPanel, southWestPanel, southEastPanel;
	Connect con = Connect.getInstance();
	
    ResultSet rs;
	
	
	public manageMenuForm() {
		
		//north
		
		northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		
		backToMainMenu = new JButton("Back to Main Menu");
		backToMainMenu.addActionListener(this);
		backToMainMenu.setBackground(Color.PINK);
//		northPanel.add(backToMainMenu);
		
		north_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		north_1.setBackground(Color.pink);
		north_1.add(backToMainMenu);
		
		
		north_2 = new JPanel();
		cakeLandTitle = new JLabel("cakeLAnd");
		cakeLandTitle.setFont(new Font("Serif", Font.BOLD, 40));
		north_2.setBackground(Color.pink);
		north_2.add(cakeLandTitle);
		
		northPanel.add(north_1, BorderLayout.CENTER);
		northPanel.add(north_2, BorderLayout.SOUTH);
		
		
		//center
		GridBagConstraints c = new GridBagConstraints();
		
		cakeList = new JLabel("Cake List");
		cakeList.setFont(new Font("Serif", Font.BOLD, 40));
		cakeList.setForeground(Color.blue);
		
		centerPanel = new JPanel();
		cakeTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		centerPanel.setBackground(Color.pink);
	
		
		String [] cakeHeader = {"Cake ID","Cake Name", "Cake Price", "Cake Shape", "Cake Size"};

        cakeTableModel = new DefaultTableModel(cakeHeader, 0);
        cakeTable.setModel(cakeTableModel);
        
        String query = "SELECT * FROM cake";
        ResultSet rs;
        rs = con.execQuery(query);

        try {
            while(rs.next()) {
                String cakeID = rs.getString("CakeID");
                String cakeName = rs.getString("CakeName");
                String cakePrice = rs.getString("CakePrice");
                String cakeShape = rs.getString("CakeShape");
                String cakeSize = rs.getString("CakeSize");
                

                Vector<Object> cakeData = new Vector<Object>();
                cakeData.add(cakeID);
                cakeData.add(cakeName);
                cakeData.add(cakePrice);
                cakeData.add(cakeShape);
                cakeData.add(cakeSize);
                
                cakeTableModel.addRow(cakeData);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cakeTableContainer = new JScrollPane(cakeTable);
        cakeTable.setFillsViewportHeight(true);
        cakeTable.addMouseListener(this);
        centerPanel.add(cakeTableContainer, BorderLayout.NORTH);

        
		cakeTable.setBackground(Color.pink);
		cakeTableContainer = new JScrollPane(cakeTable);
		cakeTableContainer.setBackground(Color.pink);
		centerPanel.add(cakeList);
		centerPanel.add(cakeTableContainer);
		
		
		//south
		Vector<String> shapeV = new Vector<>();
		Vector<String> ovalSizeV = new Vector<>();
		Vector<String> rectangleSizeV = new Vector<>();
		shapeV.add("Oval");
		shapeV.add("Rectangle");
		
		ovalSizeV.add("15 cm");
		ovalSizeV.add("20 cm");
		ovalSizeV.add("25 cm");
		
		rectangleSizeV.add("10 x 10 cm");
		rectangleSizeV.add("20 x 20 cm");
		rectangleSizeV.add("30 x 30 cm");
		
		southPanel = new JPanel(new GridBagLayout());
		southPanel.setBackground(Color.pink);
		
		southWestPanel = new JPanel(new GridBagLayout());
		southWestPanel.setBackground(Color.pink);
		cakeName = new JLabel("Cake Name");
		cakePrice = new JLabel("Cake Price");
		shape = new JLabel("Shape");
		ovalSize = new JLabel("Oval");
		rectangleSize = new JLabel("Rectangle");
		
		cakeNameField = new JTextField(10);
		cakePriceField = new JTextField(10);
		shapeBox = new JComboBox(shapeV);
		shapeBox.addActionListener(this);
		shapeBox.setPreferredSize(new Dimension(113, 25));
		ovalSizeBox = new JComboBox(ovalSizeV);
		ovalSizeBox.setPreferredSize(new Dimension(113, 25));
		rectangleSizeBox = new JComboBox(rectangleSizeV);
		rectangleSizeBox.setPreferredSize(new Dimension(113, 25));
		
		c.insets = new Insets(10, 10, 10, 90);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		southWestPanel.add(cakeName, c);
		
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		southWestPanel.add(cakeNameField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		southWestPanel.add(cakePrice, c);
		
		c.gridx = 1;
		southWestPanel.add(cakePriceField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		southWestPanel.add(shape, c);
		
		c.gridx = 1;
		southWestPanel.add(shapeBox, c);
		
		c.gridx = 0;
		c.gridy = 3;
		southWestPanel.add(ovalSize, c);
		
		c.gridx = 1;
		southWestPanel.add(ovalSizeBox, c);
		
		c.gridx = 0;
		c.gridy = 4;
		southWestPanel.add(rectangleSize, c);
		
		c.gridx = 1;
		southWestPanel.add(rectangleSizeBox, c);
		
		southPanel.add(southWestPanel);
		
		southEastPanel = new JPanel();
		southEastPanel.setLayout(new BorderLayout());
		removeCake = new JButton("Remove Cake");
		removeCake.setPreferredSize(new Dimension(150,100));
		removeCake.addActionListener(this);
		addCake = new JButton("Add Cake");
		addCake.setPreferredSize(new Dimension(150,100));
		addCake.addActionListener(this);
		
		southEastPanel.add(removeCake, BorderLayout.NORTH);
		southEastPanel.add(addCake, BorderLayout.SOUTH);
		southPanel.add(southEastPanel);

		rectangleSizeBox.setEnabled(false);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		setTitle("cakeLAnd");
		setVisible(true);
		setSize(500, 700);
		setBackground(Color.pink);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	String a = "";
	Random r = new Random();
	String randID() {
      String id1 = "";
      String id2 = ""; 	
		if (shapeBox.getSelectedItem().equals("Oval")) {
			
			if (ovalSizeBox.getSelectedItem().equals("15 cm")) {
				id1 = "COF";
				
			} else if (ovalSizeBox.getSelectedItem().equals("20 cm")) {
				id1 = "CON";
				
			} else if (ovalSizeBox.getSelectedItem().equals("25 cm")) {
				id1 = "COV";
				
			}
		} else if (shapeBox.getSelectedItem().equals("Rectangle")) {
			
			if (rectangleSizeBox.getSelectedItem().equals("10 x 10 cm")) {
				id1 = "CRT";
				
			} else if (rectangleSizeBox.getSelectedItem().equals("20 x 20 cm")) {
				id1 = "CRW";
				
			} else if (rectangleSizeBox.getSelectedItem().equals("25 x 25 cm")) {
				id1 = "CRH";
				
			}
			
		}
		for (int i = 0; i < 4; i++) {
			id2 += (char) (r.nextInt(10) + 48);
		}
//		System.out.println(id1+id2);
		return id1+id2;
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeCake) {
				int row = cakeTable.getSelectedRow();
				ResultSet rss;	
//				String ID= "";
				String ID = cakeTable.getValueAt(row,0).toString();
					String tempQ = String.format("DELETE FROM cake WHERE CakeID = '%s'", ID);
					con.executeUpdate(tempQ);
					cakeTableModel.removeRow(row);
				JOptionPane.showMessageDialog(this, "Cake successfully remove from your cart!","Success", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == addCake) {
				int row = cakeTable.getSelectedRow();
				Vector<Object> newCakeData = new Vector<>();
				ResultSet rss;	
				String name = cakeNameField.getText();
				if (shapeBox.getSelectedItem().equals("Oval") == true) {
					String addCakeQ = String.format("INSERT INTO cake VALUES ('"+randID()+"', '"+cakeNameField.getText()+"', '"+cakePriceField.getText()+"', '"+ovalSizeBox.getSelectedItem()+"', 'Oval' ) ");
					con.executeUpdate(addCakeQ);
					newCakeData.add(randID());
					newCakeData.add(cakeNameField.getText());
					newCakeData.add(cakePriceField.getText());
					newCakeData.add("Oval");
					newCakeData.add(ovalSizeBox.getSelectedItem());
               		
               		cakeTableModel.addRow(newCakeData);
               		
				} else if (shapeBox.getSelectedItem().equals("Rectangle")) {
					String addCakeQ = String.format("INSERT INTO cake VALUES ('"+randID()+"', '"+cakeNameField.getText()+"', '"+cakePriceField.getText()+"', '"+rectangleSizeBox.getSelectedItem()+"', 'Rectangle') ");
					con.executeUpdate(addCakeQ);
					newCakeData.add(randID());
					newCakeData.add(cakeNameField.getText());
					newCakeData.add(cakePriceField.getText());
					newCakeData.add("Rectangle");
					newCakeData.add(rectangleSizeBox.getSelectedItem());
               		
               		cakeTableModel.addRow(newCakeData);
					
				} 

			JOptionPane.showMessageDialog(this, "Cake successfully inputted to the database!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else if(e.getSource() == shapeBox) {
			if (shapeBox.getSelectedItem().equals("Oval") == true) {
			rectangleSizeBox.setEnabled(false);		
			ovalSizeBox.setEnabled(true);
		} else if(shapeBox.getSelectedItem().equals("Rectangle")){
			ovalSizeBox.setEnabled(false);
			rectangleSizeBox.setEnabled(true);
		} 
		}
	 else if (e.getSource()==backToMainMenu) {
			AdminMainForm a = new AdminMainForm();
			this.dispose();
			}
	} 

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == cakeTable) {
			int row = cakeTable.getSelectedRow();
			cakeNameField.setText(cakeTableModel.getValueAt(row, 0).toString());
			cakeNameField.setText(cakeTableModel.getValueAt(row, 1).toString());
			cakePriceField.setText(cakeTableModel.getValueAt(row, 2).toString());
			shapeBox.setSelectedItem(cakeTableModel.getValueAt(row, 3).toString());
			if(ovalSizeBox.isEnabled()) {
				ovalSizeBox.setSelectedItem(cakeTableModel.getValueAt(row, 4));
			} else if(rectangleSizeBox.isEnabled()) {
				rectangleSizeBox.setSelectedItem(cakeTableModel.getValueAt(row, 4));
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
