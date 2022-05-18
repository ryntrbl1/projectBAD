import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Database.Connect;
import Database.user;

public class Login extends JFrame implements ActionListener{

	JButton loginB, regis;
	JTextField fUsername;
	JPasswordField fPass;
	private Vector<Object> tempData;
	Vector<user> listUser = new Vector<>();
	Vector<user> currUser = new Vector<>();
	Connect con = Connect.getInstance();

	
	public Login() {
		
		JPanel Login = new JPanel();
		JPanel atas = new JPanel();
		
		JLabel login = new JLabel("CakeLAnd");
		login.setFont(new Font("Serif", Font.BOLD, 50));
		login.setForeground(Color.BLUE);
		atas.setBackground(Color.pink);
		atas.add(login);
		this.add(atas, BorderLayout.NORTH);
		
		JPanel tengah = new JPanel(new GridBagLayout());

		JLabel username = new JLabel("Username");
		username.setFont(new Font("Serif", Font.BOLD, 20));
		fUsername = new JTextField(25);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Serif", Font.BOLD, 20));
		
		fPass = new JPasswordField(25);
		GridBagConstraints g1 = new GridBagConstraints();
		g1.insets = new Insets(20,10,20,10);
		
		JPanel emailField = new JPanel();
		emailField.add(fUsername);
		emailField.setBackground(Color.pink);
		JPanel passField = new JPanel();
		passField.add(fPass);
		passField.setBackground(Color.pink);
		passField.setBorder(null);
		
		g1.gridx=0;
		g1.gridy=0;
		tengah.add(username, g1);
		
		g1.gridy=1;
		tengah.add(emailField,g1);
		
		g1.gridx=0;
		g1.gridy=2;
		tengah.add(password,g1);
		
		g1.gridx=0;
		g1.gridy=3;
		tengah.add(passField,g1);
		
		g1.gridy=4;
		loginB = new JButton("Login");
		loginB.addActionListener(this);
		tengah.add(loginB, g1);
		
		tengah.setBackground(Color.pink);
		add(tengah, BorderLayout.CENTER);

		
		JPanel bawah = new JPanel();
		bawah.setLayout(new FlowLayout(FlowLayout.RIGHT));
		regis = new JButton("Don't have account? Register");
		regis.addActionListener(this);
		regis.setForeground(Color.blue);
		regis.setFont(new Font("Serif", Font.ITALIC, 13));
		regis.setContentAreaFilled(false);
		regis.setBorderPainted(false);

		bawah.add(regis);
		bawah.setBackground(Color.pink);
		this.add(bawah, BorderLayout.SOUTH);
		
		this.setTitle("Login");
		this.setBackground(Color.pink);
		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	
	static String fName = "";
	static String name = "";
	static String fPass1 = "";
	static String fID = "";
	static String fRole = "";
	@Override
	public void actionPerformed(ActionEvent e) {
		fName = fUsername.getText();
		fPass1 = fPass.getText();
		if(e.getSource() == loginB) {
			String query = "SELECT * FROM user";
			ResultSet rss = con.execQuery(query);
			try {
			while(rss.next()) {
				listUser.add(new user(rss.getString("Username"), rss.getString("UserPassword"), rss.getString("UserID"), rss.getString("UserRole")));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
			int temp = 0;
			
			
			for(int i = 0; i < listUser.size(); i++) {
				String login = String.format("SELECT * FROM user WHERE Username = '%s' AND UserPassword = '%s'",
				listUser.get(i).getUsername(), listUser.get(i).getUserPassword());
				con.execQuery(login);
				if(listUser.get(i).getUsername().equals(fUsername.getText()) &&
						listUser.get(i).getUserPassword().equals(fPass.getText())) {
					temp++;
					break;
				} else {
					temp+=0;
					continue;
				}
			}	
			if(fUsername.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
			} else if(fPass.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
			} else if(temp == 0) {
				JOptionPane.showMessageDialog(this, "Invalid Username / Password", "Error", JOptionPane.ERROR_MESSAGE);
				temp = 0;
			} else if(temp == 1) {
				temp = 0;
				
				for(int i = 0; i < listUser.size(); i++) {
					if(listUser.get(i).getUsername().equals(fUsername.getText()) && listUser.get(i).getUserRole().equals("User")) {
						name = listUser.get(i).getUsername();
						fID = listUser.get(i).getUserID();
						fRole = listUser.get(i).getUserRole();
						JOptionPane.showMessageDialog(this, "Login Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						UserMainForm um = new UserMainForm(name);
						um.name1 = name;
						um.setVisible(true);
						break;
					} else if(listUser.get(i).getUsername().equals(fUsername.getText()) && listUser.get(i).getUserRole().equals("Admin")){
						fRole = listUser.get(i).getUserRole();
						name = listUser.get(i).getUsername();
						fID = listUser.get(i).getUserID();
						JOptionPane.showMessageDialog(this, "Login Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						AdminMainForm am = new AdminMainForm();
						am.setVisible(true);
				}
	
				}	
			} 
			
		} else if(e.getSource() == regis) {
			Register rr = new Register();
			rr.setVisible(true);
			this.dispose();
		}
	}
}
