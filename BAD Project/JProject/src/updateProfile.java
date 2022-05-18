import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Database.Connect;

public class updateProfile extends JFrame implements ActionListener{
	JPanel northPanel, northNorthPanel, northSouthPanel, centerPanel, southPanel, genderPanel, dobPanel;
	JLabel cakeLAndTitle, changeProfile, username, email, gender, DOB, phoneNumber, address, oldPassword, newPassword, confirmNewPassword, dash, dash2;
	JTextField usernameField, emailField, DOBDateField, DOBMonthField, DOBYearField, phoneNumberField, oldPasswordField, newPasswordField, confirmNewPasswordField;
	JTextArea addressArea;
	JRadioButton genderMaleRB, genderFemaleRB;
	JPasswordField oldPasswordPField, newPasswordPField, confirmNewPasswordPField;
	JButton saveChange;
	ButtonGroup genderGroup;
	Connect con = Connect.getInstance();
	Login l;
	public updateProfile() {
		//north
		northPanel = new JPanel(new BorderLayout());
		
		northNorthPanel = new JPanel();
		northNorthPanel.setBackground(Color.pink);
		northSouthPanel = new JPanel();
		northSouthPanel.setBackground(Color.pink);
		
		cakeLAndTitle = new JLabel("cakeLAnd");
		cakeLAndTitle.setFont(new Font("Serif", Font.BOLD, 40));
		
		changeProfile = new JLabel("Change Profile");
		changeProfile.setFont(new Font("Serif", Font.BOLD, 40));
		changeProfile.setForeground(Color.blue);
		
		northNorthPanel.add(cakeLAndTitle);
		northSouthPanel.add(changeProfile);
		
		northPanel.add(northNorthPanel, BorderLayout.NORTH);
		northPanel.add(northSouthPanel, BorderLayout.SOUTH);
		
		//center
		username = new JLabel("Username");
		email = new JLabel("Email");
		gender = new JLabel("Gender");
		DOB = new JLabel("Date of Birth");
		phoneNumber = new JLabel("Phone Number");
		address = new JLabel("Address");
		oldPassword = new JLabel("Old Password");
		newPassword = new JLabel("New Password");
		confirmNewPassword = new JLabel("Confirm New Password");
		
		usernameField = new JTextField(17);
		emailField = new JTextField(17);
		genderMaleRB = new JRadioButton("Male");
		genderFemaleRB = new JRadioButton("Female");
		DOBDateField = new JTextField(4);
		DOBMonthField = new JTextField(4);
		DOBYearField = new JTextField(4);
		dash = new JLabel("-");
		dash2 = new JLabel("-");
		phoneNumberField = new JTextField(17);
		addressArea = new JTextArea(6, 17);
		oldPasswordPField = new JPasswordField(17);
		newPasswordPField = new JPasswordField(17);
		confirmNewPasswordPField = new JPasswordField(17);
		
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.pink);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 10, 5, 100);
		c.anchor = GridBagConstraints.WEST;
		
		c.gridx = 0;
		c.gridy = 0;
		centerPanel.add(username, c);
		
		c.insets = new Insets(5, 10, 5, 10);
		c.gridx = 1;
		centerPanel.add(usernameField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		centerPanel.add(email, c);
		
		c.gridx = 1;
		centerPanel.add(emailField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		centerPanel.add(gender, c);
		
		genderPanel = new JPanel();
		genderGroup = new ButtonGroup();
		genderGroup.add(genderMaleRB);
		genderMaleRB.setBackground(Color.pink);
		genderFemaleRB.setBackground(Color.pink);
		
		genderGroup.add(genderFemaleRB);
		genderPanel.add(genderMaleRB);
		genderPanel.add(genderFemaleRB);
		genderPanel.setBackground(Color.pink);
		
		System.out.println(l.fName.toString());
		String query5 = String.format("SELECT UserGender FROM user WHERE Username = '%s'", l.fName);
		ResultSet rs5;
		rs5 = con.execQuery(query5);
		
		try {
			if(rs5.next()) {
					if(rs5.getString("UserGender").equals("Male")) {
						genderMaleRB.setSelected(true);
						genderMaleRB.setEnabled(false);
						genderFemaleRB.setEnabled(false);
					} else if(rs5.getString("UserGender").equals("Female")) {
						genderFemaleRB.setSelected(true);
						genderFemaleRB.setEnabled(false);
						genderMaleRB.setEnabled(false);
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.gridx = 1;
		centerPanel.add(genderPanel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		centerPanel.add(DOB, c);
		
		dobPanel = new JPanel();
		dobPanel.add(DOBDateField);
		dobPanel.add(dash);
		dobPanel.add(DOBMonthField);
		dobPanel.add(dash2);
		dobPanel.add(DOBYearField);
		dobPanel.setBackground(Color.pink);
		
		c.gridx = 1;
		centerPanel.add(dobPanel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		centerPanel.add(phoneNumber, c);
		
		c.gridx = 1;
		centerPanel.add(phoneNumberField, c);
		
		c.gridx = 0;
		c.gridy = 5;
		centerPanel.add(address, c);
		
		c.gridx = 1;
		centerPanel.add(addressArea, c);
		
		c.gridx = 0;
		c.gridy = 6;
		centerPanel.add(oldPassword, c);
		
		c.gridx = 1;
		centerPanel.add(oldPasswordPField, c);
		
		c.gridx = 0;
		c.gridy = 7;
		centerPanel.add(newPassword, c);
		
		c.gridx = 1;
		centerPanel.add(newPasswordPField, c);
		
		c.gridx = 0;
		c.gridy = 8;
		centerPanel.add(confirmNewPassword, c);
		
		c.gridx = 1;
		centerPanel.add(confirmNewPasswordPField, c);
		
		
		//south
		southPanel = new JPanel();
		southPanel.setBackground(Color.pink);
		
		saveChange = new JButton("Save Change");
		saveChange.setPreferredSize(new Dimension(110, 50));
		saveChange.addActionListener(this);
		southPanel.add(saveChange);
		
		//jframesection
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		setTitle("cakeLAnd");
		setVisible(true);
		setSize(500, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(usernameField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(emailField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Email cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!genderFemaleRB.isSelected() && !genderMaleRB.isSelected()) {
			JOptionPane.showMessageDialog(this, "Gender cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(DOBYearField.getText().trim().isEmpty() || DOBMonthField.getText().trim().isEmpty() || DOBDateField.getText().trim().isEmpty()) {//4
			JOptionPane.showMessageDialog(this, "Date of birth cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(phoneNumberField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Phone Number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(addressArea.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Address cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(!(oldPasswordPField.getText().equals(l.fPass1))) {
			JOptionPane.showMessageDialog(this, "Old Password wrong", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(newPasswordPField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(confirmNewPasswordPField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Confirm Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!(newPasswordPField.getText().equals(confirmNewPasswordPField.getText()))) {
			JOptionPane.showMessageDialog(this, "Your Password and Confirm Password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
		} // -----------------empty----------------
		else if(usernameField.getText().length() < 5 || username.getText().length() > 15) {
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
		} else if(phoneNumberField.getText().length() < 10 || phoneNumber.getText().length() > 12) {
			JOptionPane.showMessageDialog(this, "Phone Numberconsist only 10 - 12 digits only", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!addressArea.getText().endsWith(" Street")) {
			JOptionPane.showMessageDialog(this, "Address must ends with ' Street'", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekPass() == false) {
			JOptionPane.showMessageDialog(this, "Password must be alphanumeric", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(availUser() == false) {
			JOptionPane.showMessageDialog(this, "Username is exist, try again", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(cekDOB() == false) {
			JOptionPane.showMessageDialog(this, "DOB must be number and valid input, try again", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			//UPDATE
			update();
			JOptionPane.showMessageDialog(this, "Account Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			new Login();
		}
	}
	
	//USERNAME
	boolean availUser() {

		String query = "Select * FROM user";
		
		int temp=0;
		String cekUsername = String.format("SELECT * FROM user WHERE Username = '%s'", username.getText());
		ResultSet rss1 = con.execQuery(cekUsername);
			try {
				while(rss1.next()) {
					if(usernameField.getText().equals(rss1.getString("Username"))) {
						temp++;
						break;
					} else {
						continue;
					}
				}
			} catch (SQLException e) {
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
		String cekEmail = emailField.getText();
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
		for(int i = 0 ; i < emailField.getText().length(); i++) {
			char temp = emailField.getText().charAt(i);
			if(temp == 64 && emailField.getText().charAt(i)+1 == 46) {
				return false;
			} else if(temp == 64 && emailField.getText().charAt(i)-1 == 46){
				return false;
			} else {
				continue;
			}
		}
		return true;
	}
	
	boolean cekEmailAdd() {
		int countAdd = 0;
		for(int i = 0 ; i < emailField.getText().length(); i++) {
			char temp = emailField.getText().charAt(i);
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
		String temp2 = emailField.getText();
		for(int i = 0 ; i < email.getText().length(); i++) {
			char temp = emailField.getText().charAt(i);
			if(temp == 64) {
			temp2 = temp2.substring(i, email.getText().length());
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
		
		if(emailField.getText().endsWith("com") && countDot == 1) {
			countDot = 0;
			return true;
		} else if(emailField.getText().endsWith("co.id") && countDot == 2) {
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
		ResultSet rss;
		rss = con.execQuery(query);
		try {
			while(rss.next()) {
				if(phoneNumberField.getText().equals(rss.getString("UserPhoneNumber"))) {
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
			for(int j = 0; j < phoneNumberField.getText().length(); j++) {
				char temp = phoneNumberField.getText().charAt(j);
				if(temp == number[i]) {
					count++;
				} else {
					continue;
				}
			}
		}
		
		if(count == phoneNumberField.getText().length()) {
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
			for(int j = 0; j < newPasswordPField.getText().length(); j++) {
				char tempString = newPasswordPField.getText().charAt(j);
				if(tempString == word[i]) {
					tempWord++;
				} else {
					continue;
				}
			}
		}
		
		for(int i = 0; i < number.length; i++) {
			for(int j = 0; j < newPasswordPField.getText().length(); j++) {
				char tempString = confirmNewPasswordPField.getText().charAt(j);
				if(tempString == number[i]) {
					tempNum++;
				} else {
					continue;
				}
			}
		}
		
		if(tempWord+tempNum != newPasswordPField.getText().length()) {
			tempWord = 0;
			tempNum = 0;
			return false;
		} else if (tempWord+tempNum != newPasswordPField.getText().length()){
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
			for(int j = 0; j < DOBYearField.getText().length(); j++) {
				char temp = DOBYearField.getText().charAt(j);
				if(temp == number[i]) {
					count++;
				} else {
					continue;
				}
			}
		}
		
		if(count != DOBYearField.getText().length()) {
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
			if(DOBYearField.getText().length() > 4) {//1
				return false;
			} else if(DOBMonthField.getText().length() != 2) {
				return false;
			} else if(DOBDateField.getText().length() != 2) {//3
				return false;
			} else if(DOBYearField.getText().length() <= 4){//1
				int temp = Integer.parseInt(DOBYearField.getText());
				if(temp > currYear) {
					return false;
				} else {
					return true;
				}
			} else if(DOBMonthField.getText().length() == 2) {
				int temp = Integer.parseInt(DOBMonthField.getText());
				if(temp > 12) {
					return false;
				} else {
					return true;
				}
			} else if(DOBDateField.getText().length() == 2) {//3
				int temp = Integer.parseInt(DOBDateField.getText());//3
				for(int i = 0; i < month1.length; i++) {
						if(DOBDateField.getText().contains(month1[i])) {//3
							count = 1;
							break;
						} else {
							continue;
						}
				}
				
				for(int j = 0 ; j < month2.length; j++) {
					if(DOBDateField.getText().contains(month2[j])) {
						count = 2;
						break;
					} else{
						continue;
					}
				}
				
				for(int k = 0 ; k < month3.length; k++) {
					if(DOBDateField.getText().contains(month3[k])) {
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
	
	void update() {
		
		String queryID = String.format("SELECT UserID from user WHERE UserID = '%s'", Login.fID);
		ResultSet rst;
		String ID = "";
		rst = con.execQuery(queryID);
		try {
			if(rst.next()) {
				ID = rst.getString("UserID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query = String.format("UPDATE user SET Username = '%s', UserEmail = '%s', UserDOB = '%s/%s/%s', UserPhoneNumber = '%s', UserAddress = '%s', UserPassword = '%s' WHERE UserID = '%s'", usernameField.getText(), emailField.getText(), DOBYearField.getText(), DOBMonthField.getText(), DOBDateField.getText(), phoneNumberField.getText(), addressArea.getText(), newPasswordPField.getText(), ID);
		con.executeUpdate(query);

	}
}