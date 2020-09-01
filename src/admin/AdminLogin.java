package admin;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class is admin login 
public class AdminLogin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JFrame ad;
	JLabel l1, l2;
	JPasswordField pass;
	JTextField name;
	JButton login;

	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ad = new JFrame("ADMINLOGIN");
		// Create a textfield for username
		name = new JTextField();
		name.setBounds(250, 150, 100, 30);
		ad.add(name);

		// create label username
		l1 = new JLabel("USERNAME");
		l1.setBounds(100, 150, 100, 30);
		ad.add(l1);
		// Create label password
		l2 = new JLabel("PASSWORD");
		l2.setBounds(100, 200, 100, 30);
		ad.add(l2);
		// Create Password field
		pass = new JPasswordField();
		pass.setBounds(250, 200, 100, 30);
		ad.add(pass);
		// Create a button for Login and action Listener
		login = new JButton("LOGIN");
		login.setBounds(180, 250, 100, 30);
		ad.add(login);
		login.addActionListener(this);

		ad.setSize(500, 500);
		ad.setLayout(null);
		ad.setVisible(true);
	}

	// Function for action performed
	public void actionPerformed(ActionEvent e) {
		// Storing username and password to variables
		String user = name.getText();
		String password = String.valueOf(pass.getPassword());
		// Checking username and password..if it is true admin section will run
		if (user.equals("admin") && password.equals("admin")) {
			AdminSection.main(new String[] {});
			ad.dispose();
		}
		// if it is wrong a pop up message will be shown
		else {
			JOptionPane.showMessageDialog(this, "Sorry, Username or Password Error", "Login Error!",
					JOptionPane.ERROR_MESSAGE);
			name.setText("");
			pass.setText("");
		}

	}

	public static void main(String args[]) {

		new AdminLogin();
	}
}