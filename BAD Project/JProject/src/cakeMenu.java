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
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import Database.Connect;
import javax.swing.JOptionPane;

public class cakeMenu extends JFrame implements ActionListener, MouseListener {
	
	JPanel westPanel, eastPanel, northPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scp;
	JLabel title, titleSmall, list, qty;
	JSpinner qtyField;
	JButton addition, viewButton, backto;
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

	public cakeMenu() {
		//North
				northPanel = new JPanel();
				northPanel.setLayout(new GridBagLayout());
				GridBagConstraints g1 = new GridBagConstraints();
				g1.insets = new Insets(15,20,15,20);
				g1.anchor = GridBagConstraints.CENTER;
				
				title = new JLabel("CakeLAnd");
				title.setFont(new Font("Serif", Font.BOLD, 50));
				title.setForeground(Color.red);
				
				titleSmall = new JLabel("CakeLAnd");
				titleSmall.setFont(new Font("Serif", Font.BOLD, 30));
				titleSmall.setForeground(Color.blue);
				
				backto = new JButton();
				backto.setVisible(true);
				backto.setText("Back to Main Menu");
				backto.setFont(new Font("Serif", Font.BOLD, 16));
				backto.setForeground(Color.blue);
				backto.setBackground(Color.PINK);
				backto.setBorderPainted(true);
				backto.addActionListener(this);
				northPanel.add(backto);	
				
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
		
		String query = "Select CakeName, CakePrice, CakeShape, CakeSize FROM cake";
		ResultSet rs;
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		String[] header = {"Cake Name", "Cake Price", "Cake Shape" , "Cake Size"};
		dtm = new DefaultTableModel(header, 0);
		table.setModel(dtm);
		rs = con.execQuery(query);
		
		try {
			while(rs.next()){
				String cName = rs.getString("CakeName");
				String cPrice = rs.getString("CakePrice");
                String cShape = rs.getString("CakeShape");
                String cSize = rs.getString("CakeSize");
                
                Vector<Object> listData = new Vector<Object>();
                listData.add(cName);
                listData.add(cPrice);
                listData.add(cShape);
                listData.add(cSize);
                
                dtm.addRow(listData);               
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scp = new JScrollPane(table);
		scp.setPreferredSize(new Dimension(350, 400));
		scp.setBackground(Color.pink);
		table.setFillsViewportHeight(true);
		table.addMouseListener(this);
		westPanel.setBackground(Color.pink);
		
		
		westPanel.add(scp);
		this.add(westPanel, BorderLayout.WEST);
		
		//east
		eastPanel = new JPanel(new GridBagLayout());
		qty = new JLabel("Quantity");
		addition = new JButton("add to cart");
		addition.setForeground(Color.blue);
		addition.setFont(new Font("Serif", Font.BOLD, 16));
		addition.setContentAreaFilled(false);
		addition.setBorderPainted(true);
		addition.addActionListener(this);
		eastPanel.add(addition);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(1,1,100,1);
		qtyField = new JSpinner(model1);
		
		JComponent editor = qtyField.getEditor();
		JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
		tf.setColumns(6);
		
		viewButton = new JButton("view cart");
		viewButton.setForeground(Color.blue);
		viewButton.setFont(new Font("Serif", Font.BOLD, 16));
		viewButton.setContentAreaFilled(false);
		viewButton.setBorderPainted(true);
		viewButton.addActionListener(this);
		eastPanel.add(viewButton);
		
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
		eastPanel.add(addition, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		eastPanel.add(viewButton, gc);
		
		
		this.add(eastPanel, BorderLayout.CENTER);
		
		
		init();
	}
	
	boolean availName(){
		String query = "Select * From cart";
		ResultSet rss;
		rss = con.execQuery(query);
		
		int temp=0;
		int column=0;
		int row = table.getSelectedRow();
		//String namaCake = table.getModel().getValueAt(row, column).toString();
		String cekCakename = String.format("Select c.CakeID FROM cart c JOIN cake ca ON c.CakeID = ca.CakeID WHERE CakeName = '%s'",table.getModel().getValueAt(row, column).toString());
		ResultSet rss1 = con.execQuery(cekCakename);
		try{
			while(rss1.next()){
				if(table.getModel().getValueAt(row, column).toString().equals(rss1.getString("CakeID"))){
					temp++;
					break;
				}else{
					continue;
				}
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (temp == 0) {
			return true;			
		}else{
			temp = 0;
			return false;
		}
		
	}
	
	String ckid = "";
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == addition) {////////////////////////////IDK
				
//				JOptionPane.showMessageDialog(this, "Your cake is in the cart, please go to update cart", "Error", JOptionPane.ERROR_MESSAGE);
			
				int row = table.getSelectedRow();
				ResultSet rss;	
				String ID= "";
				String name = table.getValueAt(row,0).toString();
					String tempQ = String.format("SELECT CakeID FROM cake WHERE CakeName = '%s'", name);
					rss = con.execQuery(tempQ);
					try {
						if(rss.next()) {
							ID = rss.getString("CakeID");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					}
					
					String query12 = String.format("SELECT CakeID FROM cart WHERE CakeID = '%s'", ID);
					ResultSet rs2;
					rs2 = con.execQuery(query12);
					try {
						if(rs2.next()) {
							JOptionPane.showMessageDialog(this, "Your cake is in the cart, please go to update cart", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String querySelectTable = "INSERT INTO cart VALUES('"+Login.fID+"','"+ID+"','"+qtyField.getValue()+"')";

						
					con.executeUpdate(querySelectTable);
//					dtm.removeRow(row);

			
		} else if(e.getSource() == viewButton) {
			this.dispose();
			Manage a = new Manage();
		} else if (e.getSource()==backto) {
			this.dispose();
			UserMainForm a = new UserMainForm(Login.name);
		}
	
	
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
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
