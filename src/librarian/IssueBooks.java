package librarian;

import javax.swing.*;

import db.Db;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*This class issue books with serial no ,student name ,student id ,and phone number
 * */
public class IssueBooks extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JFrame f;
	JLabel slno, stid, stname, stphno;
	JTextField slno1, stid1, stname1, stphno1;
	JButton issueb, back;

	IssueBooks() {
		f = new JFrame("ISSUE BOOK");
		//Create labels
		slno = new JLabel("Serial NO:");
		slno.setBounds(100, 100, 100, 30);
		f.add(slno);

		stid = new JLabel("Student ID");
		stid.setBounds(100, 150, 100, 30);
		f.add(stid);

		stname = new JLabel("Student Name");
		stname.setBounds(100, 200, 100, 30);
		f.add(stname);

		stphno = new JLabel("Phone no");
		stphno.setBounds(100, 250, 100, 30);
		f.add(stphno);
		
        //Create Textfields
		slno1 = new JTextField();
		slno1.setBounds(250, 100, 100, 30);
		f.add(slno1);

		stid1 = new JTextField();
		stid1.setBounds(250, 150, 100, 30);
		f.add(stid1);

		stname1 = new JTextField();
		stname1.setBounds(250, 200, 100, 30);
		f.add(stname1);

		stphno1 = new JTextField();
		stphno1.setBounds(250, 250, 100, 30);
		f.add(stphno1);
		//Create button for issue books and action listener
		issueb = new JButton("Issue Book");
		issueb.setBounds(180, 300, 100, 30);
		f.add(issueb);
		issueb.addActionListener(this);
		
		//Create button for moving back....that is moving to Librarian section..
		back = new JButton("Back");
		back.setBounds(300, 350, 100, 30);
		f.add(back);
		back.addActionListener(this);

		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);

	}
	
    //Function for action performed
	public void actionPerformed(ActionEvent e) {
		
		//Checking whether issue button is clicked....
		if (e.getSource() == issueb) {
			if (slno1.getText().isEmpty()|| stid1.getText().isEmpty()||stname1.getText().isEmpty()||stphno1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Sorry Fil all fields");
			}
			boolean status = false;
			
			//Store values to variables
			String slnol = slno1.getText();
			int slnol1 = Integer.parseInt(slnol);
			String stidl = String.valueOf(stid1.getText());
			int stidl1 = Integer.parseInt(stidl);
			String stnamel = stname1.getText();
			String stphnol = stphno1.getText();

			try {
				
				//Create the connection object and call the Db connection 
				Connection con = Db.getConnection();
				
				//Create prepared statement for selecting book with entered serial no..
				PreparedStatement ps = con.prepareStatement("select * from books where slno=?");
				ps.setInt(1, slnol1);
				ResultSet rs = ps.executeQuery();
				status = rs.next();
				//Checking whether entered serial no is there in database...if no shows pop up message
				if (status == false) {
					JOptionPane.showMessageDialog(this, "Sorry, SerialNo doesn't exist!");
					IssueBooks.main(new String[] {});
					f.dispose();
					con.close();
				} 
				//Checking whether entered book is available or not in the database
				else {
					boolean check2 = false;
					ps = con.prepareStatement("select * from books where slno=? and quantity=0");
					ps.setInt(1, slnol1);

					rs = ps.executeQuery();
					check2 = rs.next();
					//if book is not available it shows pop up message showing book is not available
					if (check2 == true) {
						JOptionPane.showMessageDialog(this, " This Book is not available right now!");
						f.dispose();
						IssueBooks.main(new String[] {});

						con.close();

					}/*If book is available insert the details of book in issue book database 
					*/
					else {

						ps = con.prepareStatement(
								"insert into issuebooks(serialno,stid,stname,stphno) values(?,?,?,?)");

						ps.setInt(1, slnol1);
						ps.setInt(2, stidl1);
						ps.setString(3, stnamel);
						ps.setString(4, stphnol);

						ps.executeUpdate();
						ps = con.prepareStatement("update books set quantity= quantity-1 where slno=? ");//updating quantity of books in books database
						ps.setInt(1, slnol1);
						ps.executeUpdate();
						ps = con.prepareStatement("update books set issued= issued+1 where slno=? ");//updating quantity of books in issued book database
						ps.setInt(1, slnol1);

						ps.executeUpdate();

						JOptionPane.showMessageDialog(this, "Book issued successfully!");
						slno1.setText("");
						stid1.setText("");
						stname1.setText("");
						stphno1.setText("");

					}
				}
			} catch (Exception c) {
				System.out.println(c);

			}
		}
		
		//Check whether back button is clicked ...if so move back to Librarian section
		if (e.getSource() == back) {
			LibSection.main(new String[] {});
			f.dispose();
		}
	}

	public static void main(String[] args) {
		new IssueBooks();

	}

}
