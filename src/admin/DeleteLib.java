package admin;

import javax.swing.*;

import db.Db;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
/*This class delete librarian with specified Id
 * */
public class DeleteLib extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JFrame f;
	JLabel l;
	JTextField t;
	JButton d, b;

	DeleteLib() {
		f = new JFrame("DELETE LIBRARIAN");
		
		//Create label enter id
		l = new JLabel("Enter ID");
		l.setBounds(100, 100, 100, 30);
		f.add(l);

		//Create textfield for entering id
		t = new JTextField();
		t.setBounds(200, 100, 100, 25);
		f.add(t);

		//Create button for deleting librarian and action listener
		d = new JButton("Delete");
		d.setBounds(150, 150, 100, 20);
		f.add(d);
		
		//Create button for moving back....that is moving to admin section..
		b = new JButton("Back");
		b.setBounds(250, 200, 100, 15);
		f.add(b);
		
		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);
		d.addActionListener(this);
		b.addActionListener(this);
		


	}

	// Function for action performed
	public void actionPerformed(ActionEvent e) {
		
		//Checking whether delete librarian button is clicked....
		if (e.getSource() == d) {
			
			//Checking whether id textfield is empty...if so a message dialogue box will be shown
			if (t.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "ID can't be blank");
			}
			//If not empty it checks in database whether the entered data is in database or not
			else 
			{
            //storing textfield data to a variable
			String delete = t.getText();
			int del = Integer.parseInt(delete);//converting string to integer
			int i = 0;
			try {
				
				//Create the connection object and call the Db connection 
				Connection con =Db.getConnection();
			
				PreparedStatement ps = con.prepareStatement("delete from librarian where id=?");
				ps.setInt(1, del);

				i = ps.executeUpdate();
				con.close();

			
		     //if entered data is in database i value gets incremented
			 if (i > 0) {
				JOptionPane.showMessageDialog(this, "Record deleted successfully");
			} else  {
				JOptionPane.showMessageDialog(this, " Unable to delete given id!Check ID");
				t.setText("");
			}
		} catch (Exception c) {
			System.out.println(c);
		}
		}}
		
		//Check whether back button is clicked ...if so move back to admin section
		if (e.getSource() == b) {
			AdminSection.main(new String[] {});
			f.dispose();
		}
	}

	public static void main(String[] args) {
		new DeleteLib();

	}

}
