package librarian;

import javax.swing.*;

import db.Db;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*This class return book with specified serial no and student id
 * */
public class ReturnBook extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JFrame f;
	JLabel sl, stid;
	JTextField sl1, stid1;
	JButton returnbook, back;

	ReturnBook() {
		f = new JFrame("Return Book");
		//Create labels
		sl = new JLabel("Serial NO:");
		sl.setBounds(100, 150, 100, 30);
		f.add(sl);

		stid = new JLabel("Student ID:");
		stid.setBounds(100, 200, 100, 30);
		f.add(stid);

		//Create textfields
		sl1 = new JTextField();
		sl1.setBounds(250, 150, 100, 30);
		f.add(sl1);

		stid1 = new JTextField();
		stid1.setBounds(250, 200, 100, 30);
		f.add(stid1);
		//Create button return book and action listener
		returnbook = new JButton("Return Book");
		returnbook.setBounds(180, 250, 150, 30);
		f.add(returnbook);
		returnbook.addActionListener(this);

		//Create button for moving back....that is moving to Librarian section..
		back = new JButton("Back");
		back.setBounds(300, 300, 100, 30);
		f.add(back);
		back.addActionListener(this);

		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);

	}
	
    //Function for action performed
	public void actionPerformed(ActionEvent e) {
		//Checking whether return button is clicked....
		if (e.getSource() == returnbook) {
			
			//Checking whether if any of the textfield is empty if so a message dialogue box will appear...
			if (sl1.getText().isEmpty()|| stid1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Sorry Fil all fields");
			}
			//if all fields are filled then checks student id and serial no matches issuebooks database
			else {
			
			String sr = sl1.getText();
			int sr1 = Integer.parseInt(sr);
			String id = stid1.getText();
			int id1 = Integer.parseInt(id);
			boolean status = false;

			try {
				
				//Create the connection object and call the Db connection 
				Connection con = Db.getConnection();
				
				//Create prepared statement for selecting book with entered serial no and student id
				PreparedStatement ps = con.prepareStatement("select * from issuebooks where serialno=? and stid=?");
				ps.setInt(1,sr1);
				ps.setInt(2,id1);
				ResultSet rs = ps.executeQuery();
				status = rs.next();
				//if data not found shows message dialog box
				if (status == false) {
					JOptionPane.showMessageDialog(this, "Sorry,Can't Return book.Check SerialNo and ID");
					
					con.close();
				} //if data found the data deletes from issuebooks
				else {

				   ps = con.prepareStatement("delete from issuebooks where serialno =? and stid= ?");

					ps.setInt(1, sr1);
					ps.setInt(2, id1);
					ps.executeUpdate();

					ps = con.prepareStatement("update books set quantity= quantity+1 where slno=? ");//updates quantity in books
					ps.setInt(1, sr1);
					ps.executeUpdate();

					ps = con.prepareStatement("update books set issued= issued-1 where slno=? ");//updates issued in books
					ps.setInt(1, sr1);
					ps.executeUpdate();

					JOptionPane.showMessageDialog(this, "Book returned successfully!");//shows success message
					sl1.setText("");
					stid1.setText("");
				}
			} catch (Exception c) {
				System.out.println(c);
			}

		}}
		
		//Check whether back button is clicked ...if so move back to Librarian section
		if (e.getSource() == back) {
			LibSection.main(new String[] {});
			f.dispose();
		}
	}

	public static void main(String[] args) {
		new ReturnBook();

	}

}
