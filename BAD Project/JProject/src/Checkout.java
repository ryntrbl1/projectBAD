import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Database.Connect;

public class Checkout extends JFrame implements ActionListener, MouseListener{
	JPanel southPanel, northPanel, tablePanel, detailPanel, mainPanel, tengahPanel, atas, atas2;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scp;
	JLabel totalPrice, pickup;
	JTextField totalPriceField, pickupField;
	JButton checkout, mainmenu;
	Connect con = Connect.getInstance();
	
	String CakeID, CakeName, CakeShape;
	Component checkOut;
	int CakePrice, CakeSize;
	Vector<Integer> totalPriceF = new Vector<Integer>();
	
	public Checkout() {
		setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.pink);
		
		atas = new JPanel(new FlowLayout());
		atas.setPreferredSize(new Dimension(600,50));
		atas.setBackground(Color.pink);
		JLabel cakeland = new JLabel("CakeLAnd");
		cakeland.setFont(new Font("Serif", Font.BOLD, 40));
		cakeland.setForeground(Color.magenta);
		
		atas2 = new JPanel(new FlowLayout());
		atas2.setPreferredSize(new Dimension(600,50));
		atas2.setBackground(Color.pink);
		atas2.setForeground(Color.blue);
		JLabel Yo = new JLabel("Your Order");
		Yo.setFont(new Font("Serif", Font.BOLD, 30));
		Yo.setForeground(Color.blue);
		
		atas.add(cakeland);
		atas2.add(Yo);
		
		mainPanel.add(atas);
		mainPanel.add(atas2);

		//NEW PANEL
		southPanel = new JPanel(new BorderLayout());
		
		JPanel tabelKiri = new JPanel();
		tabelKiri.setPreferredSize(new Dimension(400,500));
		tabelKiri.setBackground(Color.pink);
		
		String query = "SELECT CakeName, CakeShape, CakeSize, CakePrice, Quantity FROM cake c JOIN cart ca ON c.CakeID = ca.CakeID";
        ResultSet rs;
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
			String[] header = {"Cake Name", "Cake Shape", "Cake Size", "Cake Price", "Quantity"};
			dtm = new DefaultTableModel(header,0);
			table.setModel(dtm);
			scp = new JScrollPane(table);
			scp.setPreferredSize(new Dimension(200,200));
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

        scp = new JScrollPane(table);
        scp.setPreferredSize(new Dimension(400,400));
        table.setFillsViewportHeight(true);
        table.addMouseListener(this);
		
		tabelKiri.add(scp, BorderLayout.NORTH);
		
		JPanel tengahKanan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tengahKanan.setPreferredSize(new Dimension(230,320));
		tengahKanan.setBackground(Color.pink);

		JPanel tengahKanan1 = new JPanel(new GridLayout(2,2));
		tengahKanan1.setPreferredSize(new Dimension(200,100));
		tengahKanan1.setBackground(Color.pink);
		
		totalPrice = new JLabel("Total Price");
		totalPriceField = new JTextField(10);
		JPanel totalPriceField1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0, 15));
		totalPriceField1.setBackground(Color.pink);
		totalPriceField1.add(totalPriceField);
		totalPriceField.setEditable(false);
		
		pickup = new JLabel("Pick Up Date");
		pickupField = new JTextField(10);
		JPanel pickupField1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0, 15));
		pickupField1.setBackground(Color.pink);
		pickupField1.add(pickupField);
		pickupField.setEditable(false);
		
		String queryTotal = String.format("SELECT SUM(CakePrice*ca.Quantity) AS Total, PickUpDate FROM cake c JOIN cart ca ON c.CakeID = ca.CakeID JOIN transactiondetail td ON c.CakeID = td.CakeID JOIN transactionheader th ON th.TransactionID = td.TransactionID GROUP BY PickUpDate");
		ResultSet rssq;
		
		rssq = con.execQuery(queryTotal);
		int sum = 0;
		String date1 = "";
		int allTotal = 0;
		try {
			if(rssq.next()) {
				sum = rssq.getInt("Total");
				date1 = rssq.getString("PickUpDate");
				totalPriceF.add(sum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pickupField.setText(date1);
		for(int i = 0 ; i < totalPriceF.size(); i++) {
			allTotal += totalPriceF.get(i);
		}
		totalPriceField.setText(String.valueOf(allTotal));
		allTotal = 0;
		totalPriceF.clear();

		tengahKanan1.add(totalPrice);
		tengahKanan1.add(totalPriceField1);
		tengahKanan1.add(pickup);
		tengahKanan1.add(pickupField1);
		
		JPanel tengahKanan2 = new JPanel();
		tengahKanan2.setPreferredSize(new Dimension(150,50));
		tengahKanan2.setBackground(Color.pink);
		
		checkout = new JButton("Check Out");
		checkout.addActionListener(this);
		tengahKanan2.add(checkout);
		
		JPanel tengahKanan3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tengahKanan3.setPreferredSize(new Dimension(200,50));
		tengahKanan3.setBackground(Color.pink);
		
		mainmenu = new JButton("Back to Main Menu");
		mainmenu.addActionListener(this);
		tengahKanan3.add(mainmenu);
		
		tengahKanan.add(tengahKanan1);
		tengahKanan.add(tengahKanan2);
		tengahKanan.add(tengahKanan3);

		southPanel.setPreferredSize(new Dimension(630,320));
		southPanel.setBackground(Color.pink);
		
		southPanel.add(tabelKiri, BorderLayout.WEST);
		southPanel.add(tengahKanan, BorderLayout.EAST);
		
		mainPanel.add(southPanel);
		this.add(mainPanel);
		
		setTitle("CakeLAnd");
		setBackground(Color.pink);
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	void deleteCheckOut(String CakeID) {
		PreparedStatement preparedStatement;
		
		String delete = "DELETE FROM user";

	String name = "";
	}
	
	Random r = new Random();
	String randID() {
        String id1 = "T";
        String id2 = ""; 	
          	for (int i = 0; i < 4; i++) {
          		id2 += (char) (r.nextInt(10) + 48);
          	}
        return id1+id2;
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==checkout) {
			String query = "DELETE FROM cart";
			
			String query2 = String.format("INSERT INTO transactionheader VALUES('%s', '%s', DATE_SUB('%s', INTERVAL 3 DAY), '%s')", randID(), Login.fID, pickupField.getText(), pickupField.getText());

			table.removeAll();
			dtm.setRowCount(0);
			con.executeUpdate(query2);
			con.executeUpdate(query);
			
			
			JOptionPane.showMessageDialog(this, "Transaction Successful! Remember to pick up", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else if(e.getSource() == mainmenu) {
			UserMainForm a = new UserMainForm(Login.name);
			this.dispose();
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
