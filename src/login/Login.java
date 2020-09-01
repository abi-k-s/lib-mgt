package login;

import javax.swing.*;

import admin.AdminLogin;
import librarian.LibrarianLogin;

import java.awt.event.*;
/*This class is the main function for this application and should be called first
*/
public class Login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JButton adm, lib;

	Login() {
		// Create  button for admin and action Listener
		adm = new JButton("ADMIN");
		adm.setBounds(180, 150, 100, 30);
		add(adm);
		adm.addActionListener(this);
		//Create button for Librarian and action Listener
		lib = new JButton("LIBRARIAN");
		lib.setBounds(180, 200, 100, 30);
		add(lib);
		lib.addActionListener(this);

		setSize(500, 500);
		setLayout(null);
		setVisible(true);

	}
     //Function for action performed
	public void actionPerformed(ActionEvent e) {
		//checking whether admin button is clicked ...if it is clicked then admin login page will be loaded
		if (e.getSource() == adm) {
			AdminLogin.main(new String[] {});
			dispose();
		}
		//Checking whether Librarian button is clicked ....if it is clicked then librarian login page will be loaded
		if (e.getSource() == lib) {
			LibrarianLogin.main(new String[] {});
			dispose();
		}

	}

	public static void main(String args[]) {

		new Login();
	}

}
