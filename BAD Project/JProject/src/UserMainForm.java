import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Database.Connect;

public class UserMainForm extends JFrame implements ActionListener, MouseListener{

	Login log;
	Connect con = Connect.getInstance();
	JMenuBar mBar = new JMenuBar();
	JMenu manageAcc, trans;
	JMenuItem profile, logOff,viewMenu, manageCart, viewTransHistory;
	JPanel atas, tengah;
	public String name1="";
	
	void init() {
		this.setTitle("CakeLAnd");
		this.setBackground(Color.pink);
		this.setSize(700,500);
//		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public UserMainForm(String name) {
		
		atas = new JPanel(new GridLayout(1,1));
		
		profile = new JMenuItem("Profile");
		profile.addActionListener(this);
		logOff = new JMenuItem("Logout");
		logOff.addActionListener(this);
		manageAcc = new JMenu("Manage Account");
		manageAcc.add(profile);
		manageAcc.add(logOff);
		
		viewMenu = new JMenuItem("View All Menu");
		viewMenu.addActionListener(this);
		manageCart = new JMenuItem("Manage Cart");
		manageCart.addActionListener(this);
		viewTransHistory = new JMenuItem("View Transaction History");
		viewTransHistory.addActionListener(this);
		
		trans = new JMenu("Transaction");
		trans.add(viewMenu);
		trans.add(manageCart);
		trans.add(viewTransHistory);
		
		mBar.add(manageAcc);
		mBar.add(trans);
		mBar.setBackground(Color.pink);
		atas.add(mBar);
		atas.setBackground(Color.pink);
		atas.setPreferredSize(new Dimension(30,30));
		this.add(atas, BorderLayout.NORTH);
		
		tengah = new JPanel(new GridBagLayout());
		GridBagConstraints g1 = new GridBagConstraints();
		g1.insets = new Insets(10,20,10,20);
		JLabel welcome = new JLabel("Welcome to CakeLAnd");
		welcome.setFont(new Font("Serif", Font.BOLD, 50));
		g1.gridx = 0;
		g1.gridy = 10;
		tengah.add(welcome, g1);
		
		g1.gridx = 0;
		g1.gridy = 9;
		g1.anchor = GridBagConstraints.WEST;
		JLabel userName = new JLabel("Hello, "+name);
		userName.setFont(new Font("Serif", Font.BOLD, 35));
		tengah.add(userName, g1);
		tengah.setBackground(Color.pink);
		this.add(tengah);
		init();
	}
	
	public UserMainForm() {
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == manageCart) {
			this.dispose();
			Checkout a = new Checkout();
		}else if(e.getSource() == viewMenu){
			this.dispose();
			cakeMenu b = new cakeMenu();
			b.setVisible(true);
		}else if (e.getSource()==viewTransHistory){
			this.dispose();
			transactionHistory c = new transactionHistory();
		}else if(e.getSource()==profile){
			this.dispose();
			updateProfile abc = new updateProfile();
		}else if(e.getSource()==logOff){
			this.dispose();
			Login d = new Login();
		}
	}
}