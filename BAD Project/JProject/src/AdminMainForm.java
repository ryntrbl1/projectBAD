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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Connect;


public class AdminMainForm extends JFrame implements ActionListener, MouseListener{

	Connect con = Connect.getInstance();
	JMenu manage, cakeMenu;
	JMenuItem profile, logoff, manageCake;
	JMenuBar menuBar = new JMenuBar();
	JPanel atas, tengah, bawah;
	
	void init() {
		this.setTitle("CakeLAnd");
		this.setBackground(Color.pink);
		this.setSize(700,500);
//		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public AdminMainForm() {
		// TODO Auto-generated constructor stub
		profile = new JMenuItem("Profile");
		profile.addActionListener(this);
		profile.addMouseListener(this);
		logoff = new JMenuItem("Logout");
		logoff.addMouseListener(this);
		logoff.addActionListener(this);
		manageCake = new JMenuItem("Manage Menu");
		manageCake.addMouseListener(this);
		manageCake.addActionListener(this);
		manage = new JMenu("Manage Account");
		manage.addMouseListener(this);
		manage.addActionListener(this);
		cakeMenu = new JMenu("Cake Menu");
		cakeMenu.addActionListener(this);
		
		manage.add(profile);
		manage.addMouseListener(this);
		manage.add(logoff);
		cakeMenu.add(manageCake);
		
		menuBar.add(manage);
		menuBar.add(cakeMenu);
		menuBar.setBackground(Color.pink);
		atas = new JPanel(new GridLayout(1,1));
		atas.setBackground(Color.pink);
		atas.add(menuBar);
		atas.setPreferredSize(new Dimension(30,30));
		
		tengah = new JPanel(new GridBagLayout());
		GridBagConstraints g1 = new GridBagConstraints();
		g1.insets = new Insets(10,20,10,20);
		JLabel welcome = new JLabel("Welcome to CakeLAnd");
		welcome.setFont(new Font("Serif", Font.BOLD, 50));
		g1.gridx = 0;
		g1.gridy = 10;
		tengah.add(welcome, g1);
		tengah.setBackground(Color.pink);
		this.add(atas, BorderLayout.NORTH);
		this.add(tengah, BorderLayout.CENTER);
		
		init();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == profile) {
			updateProfile up = new updateProfile();
			this.dispose();
		} else if(e.getSource() == logoff) {
			Login rr = new Login();
			this.dispose();
		}else if(e.getSource() == manageCake) {
			manageMenuForm rr = new manageMenuForm();
			this.dispose();
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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
