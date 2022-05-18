
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Database.Connect;
import Database.user;

public class Register extends JFrame implements ActionListener{
	
	Connect con = Connect.getInstance();
	JTextField rUsername, rEmail, dob1,dob2,dob3, phone1;
	JRadioButton rMale, rFemale;
	JTextArea address1;
	JPasswordField rPass, cPass;
	JCheckBox agree;
	JButton register, loginForm;
	Vector<user> vUser = new Vector<user>();
	ResultSet rss;
	
	void init() {
		this.setTitle("Register");
		this.setBackground(Color.pink);
//		this.setSize(700,800);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public Register() {
		JPanel atas, tengah, bawah;
		// TODO Auto-generated constructor stub
		atas = new JPanel();
		JLabel login = new JLabel("CakeLAnd");
		login.setFont(new Font("Serif", Font.BOLD, 50));
		login.setForeground(Color.BLUE);
		atas.setBackground(Color.pink);
		atas.add(login);
		this.add(atas, BorderLayout.NORTH);
		
		//TENGAH
		tengah = new JPanel();
//		tengah.setLayout(new GridLayout(9,8));
		tengah.setLayout(new GridBagLayout());
		GridBagConstraints g1 = new GridBagConstraints();
		g1.insets = new Insets(0, 15, 0, 25);
		g1.anchor = GridBagConstraints.WEST;
		//
		tengah.setBackground(Color.pink);
		JLabel username = new JLabel("Username");
		rUsername = new JTextField(20);
		rUsername.setPreferredSize(new Dimension(0, 28));
		JPanel fUsername = new JPanel();
		fUsername.add(rUsername);
		fUsername.setBackground(Color.pink);
	
		
		JLabel email = new JLabel("Email");
		JPanel fEmail = new JPanel();
		rEmail = new JTextField(20);
		rEmail.setPreferredSize(new Dimension(0, 28));
		fEmail.add(rEmail);
		fEmail.setBackground(Color.pink);
		g1.gridx = 0;
		g1.gridy = 0;
		tengah.add(username, g1);
		g1.gridx = 10;
		tengah.add(fUsername,g1);
		g1.gridx = 0;
		g1.gridy = 1;
		tengah.add(email,g1);
		g1.gridx = 10;
		g1.gridy = 1;
		tengah.add(fEmail,g1);

		
		JLabel gender = new JLabel("Gender");
		rMale = new JRadioButton("Male");
		rFemale = new JRadioButton("Female");
		rMale.setBackground(Color.pink);
		rFemale.setBackground(Color.pink);
		ButtonGroup genderButton = new ButtonGroup();
		JPanel gender2 = new JPanel();
		genderButton.add(rMale);
		genderButton.add(rFemale);
		gender2.setLayout(new GridLayout(1,1));
		gender2.add(rMale);
		gender2.add(rFemale);

		g1.gridx = 0;
		g1.gridy = 2;
		tengah.add(gender,g1);
		g1.gridx = 10;
		g1.gridy = 2;
		tengah.add(gender2,g1);

		JLabel dash = new JLabel("-");
		JLabel dash2 = new JLabel("-");
		
		JLabel dob = new JLabel("Date of Birth");
		JPanel ffDOB = new JPanel();
		JPanel fDob = new JPanel(new GridLayout(0,3));
		dob1 = new JTextField(5);
		dob2 = new JTextField(5);
		dob3 = new JTextField(5);
		dob1.setPreferredSize(new Dimension(0, 28));
		dob2.setPreferredSize(new Dimension(0, 28));
		dob3.setPreferredSize(new Dimension(0, 28));
		dob1.setText("Year");
		dob2.setText("Month");
		dob3.setText("Day");
		fDob.add(dob1);

		fDob.add(dob2);
		fDob.add(dob3);
		ffDOB.setBackground(Color.pink);
		ffDOB.add(fDob);
	
		g1.gridx = 0;
		g1.gridy = 3;
		tengah.add(dob, g1);
		g1.gridx = 10;
		g1.gridy = 3;
		tengah.add(ffDOB,g1);
		
		JLabel phone = new JLabel("Phone Number");
		JPanel fPhone = new JPanel();
		phone1 = new JTextField(20);
		phone1.setPreferredSize(new Dimension(0,28));
		fPhone.add(phone1);
		fPhone.setBackground(Color.pink);
		g1.gridx = 0;
		g1.gridy = 4;
		tengah.add(phone, g1);
		g1.gridx = 10;
		g1.gridy = 4;
		tengah.add(fPhone, g1);
		
		JLabel address = new JLabel("Address");
		JPanel fAddress = new JPanel();
		address1 = new JTextArea(9,20);
		address1.setPreferredSize(new Dimension(150,150));
		address1.setLineWrap(true);
		address1.setWrapStyleWord(true);
		
//		address1.setMaximumSize(new Dimension(1,1));
		fAddress.add(address1);
		fAddress.setBackground(Color.pink);
		g1.gridx = 0;
		g1.gridy = 5;
		tengah.add(address,g1);
		g1.gridx = 10;
		g1.gridy = 5;
		tengah.add(fAddress,g1);
		
		JLabel pass = new JLabel("Password");
		JPanel fPass = new JPanel();
		rPass = new JPasswordField(20);
		rPass.setPreferredSize(new Dimension(0,28));
		fPass.setBackground(Color.pink);
		fPass.add(rPass);
		g1.gridx = 0;
		g1.gridy = 6;
		tengah.add(pass,g1);
		g1.gridx = 10;
		g1.gridy = 6;
		tengah.add(fPass,g1);
		
		JLabel confirmPass = new JLabel("Confirm Password");
		JPanel fConfirm = new JPanel();
		cPass = new JPasswordField(20);
		cPass.setPreferredSize(new Dimension(0,28));
		fConfirm.setBackground(Color.pink);
		fConfirm.add(cPass);
		g1.gridx = 0;
		g1.gridy = 7;
		tengah.add(confirmPass,g1);
		g1.gridx = 10;
		g1.gridy = 7;
		tengah.add(fConfirm,g1);
		
		agree = new JCheckBox("Agree to terms & conditions");
		agree.setBackground(Color.pink);
		g1.insets = new Insets(0, 270,25,0);
		g1.gridx =0;
		g1.gridy = 8;
		tengah.add(agree,g1);
		
		g1.insets = new Insets(0, 290,25,0);
		g1.gridx = 0;
		g1.gridy = 9;
		register = new JButton("Register");
		register.addActionListener(this);
		register.setPreferredSize(new Dimension(140,50));
		register.setBorderPainted(false);
		tengah.add(register, g1);
		
	
		this.add(tengah, BorderLayout.CENTER);

		
		bawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bawah.setBackground(Color.pink);




		
		loginForm = new JButton("Already have an account? Login");
		loginForm.setLocation(100, 10);
		loginForm.addActionListener(this);
		loginForm.setForeground(Color.blue);
		loginForm.setFont(new Font("Serif", Font.ITALIC, 15));
		loginForm.setContentAreaFilled(false);
		loginForm.setBorderPainted(false);
		bawah.add(loginForm);
		this.add(bawah, BorderLayout.SOUTH);
		//Initialize
		init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	if(e.getSource() == register) {
		
			cek();
		} else if(e.getSource() == loginForm) {
			this.dispose();
			Login ll = new Login();
		}
	}
	

	void cek() {
		//ascii @ = 64
		if(rUsername.getText().trim().isEmpty()) {//1
			JOptionPane.showMessageDialog(this, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(rEmail.getText().trim().isEmpty()) {//2
			JOptionPane.showMessageDialog(this, "Email cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!rFemale.isSelected() && !rMale.isSelected()) {//3
			JOptionPane.showMessageDialog(this, "Gender cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(dob1.getText().trim().isEmpty() || dob2.getText().trim().isEmpty() || dob3.getText().trim().isEmpty()) {//4
			JOptionPane.showMessageDialog(this, "Date of birth cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(phone1.getText().trim().isEmpty()) {//5
			JOptionPane.showMessageDialog(this, "Phone Number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(address1.getText().trim().isEmpty()) {//6
			JOptionPane.showMessageDialog(this, "Address cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(rPass.getText().trim().isEmpty()) {//7
			JOptionPane.showMessageDialog(this, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cPass.getText().trim().isEmpty()) {//8
			JOptionPane.showMessageDialog(this, "Confirm Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!agree.isSelected()) {//9
			JOptionPane.showMessageDialog(this, "Please agree to our Terms & Conditions", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!(rPass.getText().equals(cPass.getText()))) {
			JOptionPane.showMessageDialog(this, "Your Password and Confirm Password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
		} // -----------------empty----------------
		else if(rUsername.getText().length() < 5 || rUsername.getText().length() > 15) {
			JOptionPane.showMessageDialog(this, "Username must be 5-15 characters", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekNewEmail() == false) {
			JOptionPane.showMessageDialog(this, "Character '@' must not be next to '.'", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekStartEnd() == false) {
			JOptionPane.showMessageDialog(this, "Must not starts and ends with '@' nor '.'", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekEmailAdd() == false) {
			JOptionPane.showMessageDialog(this, "Must be only 1 '@'", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekEmailDot() == false) {
			JOptionPane.showMessageDialog(this, "Must contain only 1 '.' after '@' for separating [provider] and [domain]", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekPNumber() == false) {
			JOptionPane.showMessageDialog(this, "This phone number already taken","Error",JOptionPane.ERROR_MESSAGE);
		} else if(cekIsNumber() == false) {
			JOptionPane.showMessageDialog(this, "Phone Number must be number", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(phone1.getText().length() < 10 || phone1.getText().length() > 12) {
			JOptionPane.showMessageDialog(this, "Phone Numberconsist only 10 - 12 digits only", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!address1.getText().endsWith(" Street")) {
			JOptionPane.showMessageDialog(this, "Address must ends with ' Street'", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekPass() == false) {
			JOptionPane.showMessageDialog(this, "Password must be alphanumeric", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(availUser() == false) {
			JOptionPane.showMessageDialog(this, "Username is exist, try again", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekDOB() == false) {
			JOptionPane.showMessageDialog(this, "DOB must be number and valid input, try again", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			
			insert();
			JOptionPane.showMessageDialog(this, "Success register", "Succes", JOptionPane.INFORMATION_MESSAGE);
//			this.dispose();
//			Login ll = new Login();
		}
	}
	//UserID

	//RANDOM
	Random r = new Random();
	Vector<String> allID = new Vector<String>();
	
	boolean check() {
   	       	boolean cek2 = true;
   	     for(int j = 0; j < allID.size(); j++) {
        		if((randID()).equals(allID.get(j))) {
            		cek2 = false;
            		break;
            	} else {
            		cek2 = true;
            	}
        	}
   	     
   	     if(cek2 == false) {
   	    	 cek2 = true;
   	    	 return false;
   	     } else {
   	    	 return true;
   	     }
	}
	
	String randID() {
        String id1 = "U";
        String id2 = ""; 	
          	for (int i = 0; i < 4; i++) {
          		id2 += (char) (r.nextInt(10) + 48);
          	}
        return id1+id2;
        
    }
	String tempID = randID();
	boolean okeID2() {
	
		int count = 0;
		String cekID = String.format("SELECT * FROM user WHERE UserID = '%s'", tempID);
		ResultSet rssID = con.execQuery(cekID);
		try {
			while(rssID.next()) {		
				if(tempID.equals(rssID.getString("UserID"))) {
					count++;
					break;
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count != 0) {
			count = 0;
			return false;
		} else {
			return true;
		}
	}
	
	String okeID() {

		int count = 0;
		String cekID = String.format("SELECT * FROM user WHERE UserID = '%s'", tempID);
		ResultSet rssID = con.execQuery(cekID);
		
		okeID2();
		while(okeID2() == false) {
			tempID = randID();
			System.out.println(tempID);
			okeID2();
		}
		allID.add(tempID);
		
		return tempID;
	}
	
	
	//USERNAME
	boolean availUser() {

		String query = "Select * FROM user";
		rss = con.execQuery(query);
		
		int temp=0;
		String cekUsername = String.format("SELECT * FROM user WHERE Username = '%s'",rUsername.getText());
		ResultSet rss1 = con.execQuery(cekUsername);
			try {
				while(rss1.next()) {
					if(rUsername.getText().equals(rss1.getString("Username"))) {
						temp++;
						break;
					} else {
						continue;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(temp == 0) {
				return true;
			} else {
				temp = 0;
				return false;
			}
	}
	
	
	
		//EMAIL
	boolean cekStartEnd() {
		String cekEmail = rEmail.getText();
		if(cekEmail.startsWith("@")) {
			return false;
		} else if (cekEmail.startsWith(".")) {
			return false;
		} else if (cekEmail.endsWith("@")) {
			return false;
		} else if (cekEmail.endsWith(".")) {
			return false;
		} else {
			return true;
		}
	}
	
	boolean cekNewEmail() {
		for(int i = 0 ; i < rEmail.getText().length(); i++) {
			char temp = rEmail.getText().charAt(i);
			if(temp == 64 && rEmail.getText().charAt(i)+1 == 46) {
				return false;
			} else if(temp == 64 && rEmail.getText().charAt(i)-1 == 46){
				return false;
			} else {
				continue;
			}
		}
		return true;
	}
	
	boolean cekEmailAdd() {
		int countAdd = 0;
		for(int i = 0 ; i < rEmail.getText().length(); i++) {
			char temp = rEmail.getText().charAt(i);
			if(temp == 64) {
				countAdd++;
			} else {
				countAdd += 0;
			}
		}
		
		if(countAdd > 1 || countAdd == 0) {
			countAdd = 0;
			return false;
		} else {
			countAdd = 0;
			return true;
		}
	}
	
	boolean cekEmailDot() {
		int countDot=0;
		String temp2 = rEmail.getText();
		for(int i = 0 ; i < rEmail.getText().length(); i++) {
			char temp = rEmail.getText().charAt(i);
			if(temp == 64) {
			temp2 = temp2.substring(i, rEmail.getText().length());
			break;
			} else if (temp != 64){
				continue;
			}
		}
		
		for(int j = 0; j < temp2.length(); j++) {
			char temp = temp2.charAt(j);
			if(temp == 46) {
				countDot++;
			} else {
				continue;
			}
		}
		
		if(rEmail.getText().endsWith("com") && countDot == 1) {
			countDot = 0;
			return true;
		} else if(rEmail.getText().endsWith("co.id") && countDot == 2) {
			countDot = 0;
			return true;
		} else {
			countDot = 0;
			return false;
		}
	}
	
	//PHONE NUMBER

	boolean cekPNumber() {
		int temp=0;
		String query = "Select * FROM user";
		rss = con.execQuery(query);
		try {
			while(rss.next()) {
				if(phone1.getText().equals(rss.getString("UserPhoneNumber"))) {
					temp = 1;
					break;
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(temp != 0) {
			temp = 0;
			return false;
		} else {
			return true;
		}
	}
	
	char[] word = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
			'p','q','r','s','t','u','v','w','x','y','z'};
	
	char[] number = {'1','2','3','4','5','6','7','8','9','0'};
	
	boolean cekIsNumber() {
		int count = 0;
		
		for(int i = 0; i < number.length; i++) {
			for(int j = 0; j < phone1.getText().length(); j++) {
				char temp = phone1.getText().charAt(j);
				if(temp == number[i]) {
					count++;
				} else {
					continue;
				}
			}
		}
		
		if(count == phone1.getText().length()) {
			count =0;
			return true;
		} else {
			count = 0;
			return false;
		}
		
	}
	
	
	//PASSWORD

	boolean cekPass() {
		int tempWord = 0;
		int tempNum = 0;
		
		for(int i = 0; i < word.length; i++) {
			for(int j = 0; j < rPass.getText().length(); j++) {
				char tempString = rPass.getText().charAt(j);
				if(tempString == word[i]) {
					tempWord++;
				} else {
					continue;
				}
			}
		}
		
		for(int i = 0; i < number.length; i++) {
			for(int j = 0; j < rPass.getText().length(); j++) {
				char tempString = rPass.getText().charAt(j);
				if(tempString == number[i]) {
					tempNum++;
				} else {
					continue;
				}
			}
		}
		
		if(tempWord+tempNum != rPass.getText().length()) {
			tempWord = 0;
			tempNum = 0;
			return false;
		} else if (tempWord+tempNum != rPass.getText().length()){
			tempWord = 0;
			tempNum = 0;
			return true;
		} else if(tempWord == 0) {
			tempNum = 0;
			return false;
		} else if(tempNum == 0) {
			tempWord = 0;
			return false;
		} else {
			tempWord = 0;
			tempNum = 0;
			return true;
		}			
	}
	

	
	//DOB
	
	boolean DOBisNumber() {
		int count = 0;
		for(int i = 0; i < number.length; i++) {
			for(int j = 0; j < dob1.getText().length(); j++) {
				char temp = dob1.getText().charAt(j);
				if(temp == number[i]) {
					count++;
				} else {
					continue;
				}
			}
		}
		
		if(count != dob1.getText().length()) {
			return false;
		} else {
			return true;
		}
	}
	
	String[] month1 = {"01", "03", "05", "07", "08", "10", "12"};
	String[] month2 = {"04", "06", "09", "11"};
	String[] month3 = {"02"};
	Date nDate = new Date();
	int currYear = nDate.getYear()+1900;
	boolean cekDOB() {
		if(DOBisNumber() == true) {
			int count = 0;
			if(dob1.getText().length() > 4) {
				return false;
			} else if(dob2.getText().length() != 2) {
				return false;
			} else if(dob3.getText().length() != 2) {
				return false;
			} else if(dob1.getText().length() <= 4){
				int temp = Integer.parseInt(dob1.getText());
				if(temp > currYear) {
					return false;
				} else {
					return true;
				}
			} else if(dob2.getText().length() == 2) {
				int temp = Integer.parseInt(dob2.getText());
				if(temp > 12) {
					return false;
				} else {
					return true;
				}
			} else if(dob3.getText().length() == 2) {
				int temp = Integer.parseInt(dob3.getText());
				for(int i = 0; i < month1.length; i++) {
						if(dob3.getText().contains(month1[i])) {
							count = 1;
							break;
						} else {
							continue;
						}
				}
				
				for(int j = 0 ; j < month2.length; j++) {
					if(dob3.getText().contains(month2[j])) {
						count = 2;
						break;
					} else{
						continue;
					}
				}
				
				for(int k = 0 ; k < month3.length; k++) {
					if(dob3.getText().contains(month3[k])) {
						count = 3;
						break;
					} else {
						continue;
					}
				}
				
				if(count == 1 && temp > 31) {
					count = 0;
					return false;
				} else if(count == 2 && temp > 30) {
					count = 0;
					return false;
				} else if(count == 3 && currYear%4 == 0 && temp > 29) {
					count = 0;
					return false;
				} else if(count == 3 && currYear%4 != 0 && temp > 28) {
					count = 0;
					return false;
				} else {
					count = 0;
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	void insert() {
		String tempUser = "User";

		if(rMale.isSelected()) {
				String query = "INSERT INTO user "+
						"VALUES('"+okeID()+"','"+rUsername.getText()+"','"+rEmail.getText()+"','"+rPass.getText()+"','"+rMale.getText()+"','"+
						dob1.getText()+dob2.getText()+dob3.getText()+"','"+
								phone1.getText()+"','"+address1.getText()+"','"+tempUser+"')";
				con.executeUpdate(query);
		} else if(rFemale.isSelected()) {
			String query = "INSERT INTO user "+
					"VALUES('"+okeID()+"','"+rUsername.getText()+"','"+rEmail.getText()+"','"+rPass.getText()+"','"+rFemale.getText()+"','"+
					dob1.getText()+"-"+dob2.getText()+"-"+dob3.getText()+"','"+
							phone1.getText()+"','"+address1.getText()+"','"+tempUser+"')";
			con.executeUpdate(query);
		}
	}
}
