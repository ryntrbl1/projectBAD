import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Database.Connect;

public class transactionHistory extends JFrame implements ActionListener, MouseListener {
	JPanel northPanel, centerPanel, southPanel;
	JTable table1, table2;
	DefaultTableModel dtm, dtm1;
	JScrollPane scp1, scp2;
	JLabel selectedID, Total;
	JTextField selectedIDField, totalField;
	JButton mainMenu;
	Component transactionHistory;
	Connect con = Connect.getInstance();
	
	public transactionHistory() {
		northPanel = new JPanel();
		northPanel.setBackground(Color.pink);
		JLabel cl = new JLabel("CakeLAnd");
		cl.setFont(new Font("Serif", Font.BOLD, 20));
		cl.setForeground(Color.magenta);
		northPanel.add(cl);
		
		centerPanel = new JPanel();
		JLabel th = new JLabel("Transaction History");
		th.setFont(new Font("Serif", Font.BOLD, 40));
		th.setForeground(Color.BLUE);
		centerPanel.add(th);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1,1,10,10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		centerPanel.setBackground(Color.PINK);
		
		table1 = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] header = {"Transaction ID", "Transaction Date", "Pick Up Date"};
		dtm = new DefaultTableModel(header, 0);
		table1.setModel(dtm);
		
		String query1 = "SELECT * FROM transactionheader";
        ResultSet rs1;
      
        rs1 = con.execQuery(query1);

        try {
            while(rs1.next()) {
                String tID = rs1.getString("TransactionID");
                String tDate = rs1.getString("TransactionDate");
                String pud = rs1.getString("PickUpDate");

                Vector<Object> listData = new Vector<Object>();
                listData.add(tID);
                listData.add(tDate);
                listData.add(pud);

                dtm.addRow(listData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scp1 = new JScrollPane(table1);
        table1.setFillsViewportHeight(true);
        table1.addMouseListener(this);
        centerPanel.add(scp1, BorderLayout.CENTER);

		scp1 = new JScrollPane(table1);
		scp1.setPreferredSize(new Dimension(400, 200));
		
		selectedID = new JLabel("Selected ID");
		selectedIDField = new JTextField(13);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(scp1);
		selectedID = new JLabel("Selected ID: ");
		selectedIDField = new JTextField(13);
		selectedIDField.setEditable(false);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		centerPanel.add(selectedID, gbc);
		centerPanel.add(selectedIDField, gbc);
		
		table2 = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] header2 = {"Cake Name", "Cake Size","Cake Shape", "Cake Price", "Quantity", "SubTotal"};
		dtm1 = new DefaultTableModel(header2,0);
		table2.setModel(dtm1);
		
        scp2 = new JScrollPane(table2);
        table2.setFillsViewportHeight(true);
        table2.addMouseListener(this);
        centerPanel.add(scp2, BorderLayout.NORTH);
        
		scp2 = new JScrollPane(table2);
		scp2.setPreferredSize(new Dimension(400,300));
		Total = new JLabel("Total: ");
		totalField = new JTextField(13);
		totalField.setEditable(false);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 0;
		centerPanel.add(scp2);
		centerPanel.add(Total,gbc);
		centerPanel.add(totalField, gbc);
		
		southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainMenu = new JButton("Back to Main Menu");
		mainMenu.addActionListener(this);
		southPanel.setBackground(Color.pink);
		southPanel.add(mainMenu);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		setTitle("CakeLAnd");
		setBackground(Color.pink);
		setSize(450,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == mainMenu) {
			this.dispose();
			UserMainForm a = new UserMainForm(Login.name);
		}
	}

	Vector<Object> allCake = new Vector<>();
	Vector<Integer> subTotal = new Vector<Integer>();
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int totalData = dtm1.getRowCount();
		
		if(allCake.isEmpty()) {
			System.out.println("");
		} else {
			for (int i = totalData - 1; i >= 0; i--) {
				dtm1.removeRow(i);
				allCake.removeAllElements();
			}
		}
		int row = table1.getSelectedRow();
		
		
		if(arg0.getSource() == table1) {
			int LastPrice = 0;
			selectedIDField.setText(table1.getValueAt(row, 0).toString());
			String getTableID = table1.getValueAt(row,0).toString();
			
			String queryTableBawah = String.format("SELECT CakeName, CakeShape, CakeSize, CakePrice, Quantity, SUM(CakePrice*Quantity) AS SubTotal "
					+ "FROM cake c JOIN transactiondetail td ON c.CakeID = td.CakeID WHERE td.TransactionID = '%s' "
					+ "GROUP BY CakeName, CakeShape, CakeSize, CakePrice, Quantity", getTableID);
	        ResultSet rs2;
	      
	        rs2 = con.execQuery(queryTableBawah);

	        try {
	            while(rs2.next()) {
	                String cName = rs2.getString("CakeName");
	                String cSize = rs2.getString("CakeSize");
	                String cShape = rs2.getString("CakeShape");
	                int cPrice = rs2.getInt("CakePrice");
	                int cQuan = rs2.getInt("Quantity");
	                int sub = rs2.getInt("SubTotal");
	        
	                allCake = new Vector<Object>();
	  	            allCake.add(cName);
	  	            allCake.add(cShape);
	  	            allCake.add(cSize);
	  	            allCake.add(cPrice);
	  	            allCake.add(cQuan);
	  	            allCake.add(sub);

	                dtm1.addRow(allCake);
	                subTotal.add(sub);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        for(int i = 0; i < subTotal.size(); i++) {
				LastPrice += subTotal.get(i);
			}
			
			totalField.setText(String.valueOf(LastPrice));
			subTotal.clear();	
			LastPrice = 0;
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
