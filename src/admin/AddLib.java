package admin;

import javax.swing.*;

import db.Db;

import java.awt.event.*;
import java.sql.*;

/*This class add librarian with id ,name password ,email ,phone no.......*/
public class AddLib extends JFrame implements ActionListener {
	private static final long serialVersionUID=1L;

	JFrame f;
	JLabel id, name, email, pass, phno;
	JPasswordField p;
	JTextField l1, l2, l3, l4;
	JButton add, back;

	AddLib() {

		f = new JFrame("ADD LIBRARIAN");
		//Create label id
		id = new JLabel("ID");
		id.setBounds(100, 100, 100, 30);
		f.add(id);
		
		//Create label name
		name = new JLabel("Name");
		name.setBounds(100, 150, 100, 30);
		f.add(name);

		//Create label password
		pass = new JLabel("Password");
		pass.setBounds(100, 200, 100, 30);
		f.add(pass);

		//Create label Email
		email = new JLabel("Email");
		email.setBounds(100, 250, 100, 30);
		f.add(email);

		//Create label phoneno
		phno = new JLabel("PhoneNo");
		phno.setBounds(100, 300, 100, 30);
		f.add(phno);

		//Create textfield for id
		l1 = new JTextField();
		l1.setBounds(250, 100, 100, 30);
		f.add(l1);

		//Create textfield for name
		l2 = new JTextField();
		l2.setBounds(250, 150, 100, 30);
		f.add(l2);

		//Create textfield for email
		l3 = new JTextField();
		l3.setBounds(250, 250, 100, 30);
		f.add(l3);

		//Create textfield for phone no
		l4 = new JTextField();
		l4.setBounds(250, 300, 100, 30);
		f.add(l4);

		//Create password field
		p = new JPasswordField();
		p.setBounds(250, 200, 100, 30);
		f.add(p);

		//Create button for adding librarian and action listener
		add = new JButton("ADD LIBRARIAN");
		add.setBounds(180, 350, 150, 30);
		f.add(add);
		add.addActionListener(this);

		//Create button for moving back....that is moving to admin section..
		back = new JButton("Back");
		back.setBounds(300, 400, 100, 30);
		f.add(back);
		back.addActionListener(this);

		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);

	}
    
	// Function for action performed
	public void actionPerformed(ActionEvent e) {
		//Checking whether add librarian button is clicked....
		if (e.getSource() == add) {
			//Checking whether if any of the textfield is empty if so a message dialogue box will appear...
			if(l1.getText().equals("")||l2.getText().equals("")||l3.getText().equals("")|| l4.getText().equals("")||p.getPassword().equals(null)) {
				JOptionPane.showMessageDialog(this, "Something went wrong..Fill all fileds");
				

			}
			//if all textfields are filled then moves to adding librarian to database
			else {
				
			//storing id to a variable
			String lid = String.valueOf(l1.getText());
			int lidd = Integer.parseInt(lid);//converting string to integer
			
			//storing name ,password ,email ,phone no to variables
			String l22 = l2.getText();
			String lp = String.valueOf(p.getPassword());
			String l23 = l3.getText();
			String l24 = l4.getText();
			try {
				
				//Create the connection object and call the Db connection 
				Connection con = Db.getConnection();
				
				//Create prepared statement for inserting data to the database
				PreparedStatement ps = con.prepareStatement("insert into librarian values(?,?,?,?,?)");

				ps.setInt(1, lidd);//1 specifies first parmeter in query
				ps.setString(2, l22);
				ps.setString(3, lp);
				ps.setString(4, l23);
				ps.setString(5, l24);
				ps.executeUpdate();

				con.close();

			} catch (Exception c) {
				System.out.println(c);
			}

			JOptionPane.showMessageDialog(this, "Librarian added successfully!");//After insertion shows dialogue box

			l1.setText("");
			l2.setText("");
			p.setText("");
			l3.setText("");
			l4.setText("");

		}}
		//Check whether back button is clicked ...if so move back to admin section
		if (e.getSource() == back) {
			AdminSection.main(new String[] {});
			f.dispose();
		}

	}

	public static void main(String ags[]) {
		new AddLib();
		
	}
}
