import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Connect;

public class Main{

	Connect con = Connect.getInstance();

	public Main() {
		Login ll = new Login();
	}

	public static void main(String[] args) {
		new Main();
	}



}
