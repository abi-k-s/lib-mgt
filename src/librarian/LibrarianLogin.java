package librarian;

import javax.swing.*;

import db.Db;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//This class is Librarian login
public class LibrarianLogin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame f;
	JLabel l1, l2;
	JTextField t;
	JPasswordField p;
	JButton l;

	LibrarianLogin() {
		f = new JFrame("Librarian LOgin");
		
		//Create label Enter name
		l1 = new JLabel("ENTER NAME");
		l1.setBounds(100, 150, 100, 30);
		f.add(l1);
		
		//Create label Enter password
		l2 = new JLabel("ENTER PASSWORD");
		l2.setBounds(100, 200, 200, 30);
		f.add(l2);
		
		//Create textfield for entering name
		t = new JTextField();
		t.setBounds(250, 150, 100, 30);
		f.add(t);
		
		//Create password field
		p = new JPasswordField();
		p.setBounds(250, 200, 100, 30);
		f.add(p);
		//Create button for login
		l = new JButton("LOGIN");
		l.setBounds(180, 250, 100, 30);
		f.add(l);
		
		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);
		l.addActionListener(this);

	}
	//Function for action performed
	public void actionPerformed(ActionEvent e) {
		//storing name and password to variables
		String name = t.getText();
		String pass = String.valueOf(p.getPassword());
		//Checking whether the name and password matches the database
		boolean status = false;
		try {
			//Create the connection object and call the Db connection 
			Connection con = Db.getConnection();
			//Create prepared statement 
			PreparedStatement ps = con.prepareStatement("select * from librarian where name=? and password=?");
			ps.setString(1, name);
			ps.setString(2, pass);
			//Create resultset
			ResultSet rs = ps.executeQuery();
			status = rs.next();//if name and password is in the database resultset moves to next query and status becomes true
			con.close();

		} catch (Exception x) {
			System.out.println(x);
		}
		//Checking the value of status... if it is true Librarian section will be loaded 
		if (status == true) {
			LibSection.main(new String[] {});
			f.dispose();

		}
		//if the value of status is false a pop up message will be shown....
		else {
			JOptionPane.showMessageDialog(this, "Sorry,Name or Password wrong..");
			t.setText("");//set the value of textfield null
			p.setText("");//set the value of textfield null
		}

	}

	public static void main(String[] args) {

		new LibrarianLogin();

	}

}
