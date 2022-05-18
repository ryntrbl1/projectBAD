import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Database.Connect;

public class Manage extends JFrame implements ActionListener, MouseListener {
	
	JPanel westPanel, eastPanel, northPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scp;
	JLabel title, titleSmall, list, qty;
	JSpinner qtyField;
	JButton removeCart, updateCart, checkOut, viewMenu, backto, backMainMenu;
	Connect con = Connect.getInstance();
	
	void init() {
		this.setTitle("CakeLAnd");
		this.setBackground(Color.pink);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(700, 650);
	}

	public Manage() {
		//North
		northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		GridBagConstraints g1 = new GridBagConstraints();
		g1.insets = new Insets(15,20,15,20);
		g1.anchor = GridBagConstraints.CENTER;
		
		title = new JLabel("CakeLAnd");
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(Color.red);
		
		titleSmall = new JLabel("Your Cart");
		titleSmall.setFont(new Font("Serif", Font.BOLD, 30));
		titleSmall.setForeground(Color.blue);
		
		backto = new JButton();
		backto.addActionListener(this);
		backto.setVisible(true);
		backto.setText("Back to Main Menu");
		backto.setFont(new Font("Serif", Font.BOLD, 14));
		backto.setForeground(Color.blue);
		backto.setBackground(Color.PINK);
		backto.setBorderPainted(true);
		
		northPanel.setBackground(Color.pink);
		g1.gridx= -5;
		g1.gridy = 0;
		northPanel.add(title,g1);
		g1.gridx = -5;
		g1.gridy = 5;
		northPanel.add(titleSmall, g1);
		g1.gridx= 20;
		g1.gridy = 0;
		northPanel.add(backto, g1);
		this.add(northPanel, BorderLayout.NORTH);
		
		//west
		westPanel = new JPanel();
		
		String query = "Select CakeName, CakeSize, CakeShape, CakePrice, Quantity FROM cake c JOIN cart ca ON c.CakeID = ca.CakeID";
		ResultSet rs;
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		String[] header = {"Cake Name", "Cake Shape", "Cake Size","Cake Price" , "Quantity"};
		dtm = new DefaultTableModel(header, 0);
		table.setModel(dtm);
		table.addMouseListener(this);
		scp = new JScrollPane(table);
		scp.setPreferredSize(new Dimension(350, 400));
		
		rs = con.execQuery(query);
		
		 try {
		       while(rs.next()) {
		           String cName = rs.getString("CakeName");
		           String cPrice = rs.getString("CakePrice");
		           String cSize = rs.getString("CakeSize");
		           String cShape = rs.getString("CakeShape");
		           String cQuan = rs.getString("Quantity");
		
		           Vector<Object> listData = new Vector<Object>();
		           listData.add(cName);
		           listData.add(cShape);
		           listData.add(cSize);
		           listData.add(cPrice);
		           listData.add(cQuan);
		
		           dtm.addRow(listData);
		        }
		    } catch (SQLException e) {
		         e.printStackTrace();
        }
		
		scp.setBackground(Color.pink);
		westPanel.setBackground(Color.pink);
		
		westPanel.add(scp);
		this.add(westPanel, BorderLayout.WEST);
		
		//east
		eastPanel = new JPanel(new GridBagLayout());
		qty = new JLabel("Quantity");
		
		removeCart = new JButton("Remove From Cart");
		removeCart.setForeground(Color.blue);
		removeCart.setFont(new Font("Serif", Font.BOLD, 16));
		removeCart.setContentAreaFilled(false);
		removeCart.setBorderPainted(true);
		removeCart.addActionListener(this);
		eastPanel.add(removeCart);
		
		updateCart = new JButton("update Cake Order");
		updateCart.setForeground(Color.blue);
		updateCart.setFont(new Font("Serif", Font.BOLD, 16));
		updateCart.setContentAreaFilled(false);
		updateCart.setBorderPainted(true);
		updateCart.addActionListener(this);
		eastPanel.add(updateCart);
		
		checkOut = new JButton("Check Out");
		checkOut.setForeground(Color.blue);
		checkOut.setFont(new Font("Serif", Font.BOLD, 16));
		checkOut.setContentAreaFilled(false);
		checkOut.setBorderPainted(true);
		checkOut.addActionListener(this);
		eastPanel.add(checkOut);
		
		viewMenu = new JButton("View All Menu");
		viewMenu.setForeground(Color.blue);
		viewMenu.setFont(new Font("Serif", Font.BOLD, 16));
		viewMenu.setContentAreaFilled(false);
		viewMenu.setBorderPainted(true);
		viewMenu.addActionListener(this);
		eastPanel.add(viewMenu);

		SpinnerNumberModel model1 = new SpinnerNumberModel(1,1,100,1);
		qtyField = new JSpinner(model1);
		qtyField.setSize(15,15);
		
		JComponent editor = qtyField.getEditor();
		JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
		tf.setColumns(6);
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.insets = new Insets (5,10,10,10);
		gc.anchor = GridBagConstraints.WEST;
		
		gc.gridx = 0;
		gc.gridy =0;
		eastPanel.add(qty, gc);
		
		gc.gridx = 1;
		eastPanel.add(qtyField, gc);
		eastPanel.setBackground(Color.pink);
		
		gc.gridx = 0;
		gc.gridy = 2;
		eastPanel.add(removeCart, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		eastPanel.add(updateCart, gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		eastPanel.add(checkOut, gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		eastPanel.add(viewMenu, gc);
		
		this.add(eastPanel, BorderLayout.CENTER);
				
				init();
		}


	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==removeCart){
			int row = table.getSelectedRow();
			ResultSet rss;	
			String ID= "";
			String name = table.getValueAt(row,0).toString();
				String tempQ = String.format("SELECT c.CakeID FROM cart c JOIN cake ca ON c.CakeID = ca.CakeID WHERE CakeName = '%s'", name);
				rss = con.execQuery(tempQ);
				try {
					if(rss.next()) {
						ID = rss.getString("CakeID");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String querySelectTable = String.format("DELETE FROM cart WHERE CakeID = '%s'", ID);
					
				con.executeUpdate(querySelectTable);
				dtm.removeRow(row);
			JOptionPane.showMessageDialog(this, "Cake successfully removed from your cart!","Success", JOptionPane.INFORMATION_MESSAGE);
			
		} else if (e.getSource() == updateCart){
			int row = table.getSelectedRow();
			ResultSet rss;	
			String ID= "";
			String name = table.getValueAt(row,0).toString();
				String tempQ = String.format("SELECT c.CakeID FROM cart c JOIN cake ca ON c.CakeID = ca.CakeID WHERE CakeName = '%s'", name);
				rss = con.execQuery(tempQ);
				try {
					if(rss.next()) {
						ID = rss.getString("CakeID");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String querySelectTable = String.format("UPDATE cart SET Quantity = '%s' WHERE CakeID = '%s'", qtyField.getValue(), ID);
				con.executeUpdate(querySelectTable);
				dtm.setValueAt(qtyField.getValue(), row, 4);
				
			JOptionPane.showMessageDialog(this, "Cake successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else if(e.getSource() == backto) {
			this.dispose();
			UserMainForm a = new UserMainForm(Login.name);
		} else if(e.getSource()==checkOut) {
			Checkout a = new Checkout();
			this.dispose();
		} else if(e.getSource()==viewMenu){
			cakeMenu b = new cakeMenu();
			this.dispose();
		}
	}
}