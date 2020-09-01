package librarian;

import javax.swing.*;

import db.Db;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
/*This class add books with serial no ,name ,author ,publisher ,quantity
 * */

public class AddBooks extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JFrame f;
	JLabel s, n, a, p, q;
	JTextField s1, n1, a1, p1, q1;
	JButton add, back;

	AddBooks() {
		f = new JFrame("Add Books");
		//Create label serial no
		s = new JLabel("Serial No");
		s.setBounds(100, 100, 100, 20);
		f.add(s);
		
		//Create label name
		n = new JLabel("Name");
		n.setBounds(100, 150, 100, 20);
		f.add(n);
		
		//Create label author
		a = new JLabel("Author");
		a.setBounds(100, 200, 100, 20);
		f.add(a);

		//Create label publisher
		p = new JLabel("Publisher");
		p.setBounds(100, 250, 100, 20);
		f.add(p);

		//Create label quantity
		q = new JLabel("Quantity");
		q.setBounds(100, 300, 100, 20);
		f.add(q);

        //Create button add books and action listener
		add = new JButton("Add books");
		add.setBounds(180, 350, 150, 30);
		f.add(add);
		add.addActionListener(this);
		
		//Create button for moving back....that is moving to Librarian section..
		back = new JButton("Back");
		back.setBounds(300, 400, 100, 30);
		f.add(back);
		back.addActionListener(this);

        //Creates textfields
		s1 = new JTextField();
		s1.setBounds(250, 100, 100, 20);
		f.add(s1);

		n1 = new JTextField();
		n1.setBounds(250, 150, 100, 20);
		f.add(n1);

		a1 = new JTextField();
		a1.setBounds(250, 200, 100, 20);
		f.add(a1);

		p1 = new JTextField();
		p1.setBounds(250, 250, 100, 20);
		f.add(p1);

		q1 = new JTextField();
		q1.setBounds(250, 300, 100, 20);
		f.add(q1);

		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);

	}
    //Function for action performed
	public void actionPerformed(ActionEvent e) {
		
		//Checking whether add button is clicked....
		if (e.getSource() == add) {
			
			//Checking whether if any of the textfield is empty if so a message dialogue box will appear...
			if(s1.getText().equals("")||n1.getText().equals("")||a1.getText().equals("")|| p1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Something went wrong..Fill all fileds");
				AddBooks.main(new String[] {});
				f.dispose();

			}
			//Store values to varibles
			String sl=String.valueOf(s1.getText());
			int sl1=Integer.parseInt(sl);
			String nl=n1.getText();
			String al=a1.getText();
			String pl=p1.getText();
			String ql=String.valueOf(q1.getText());
			int ql1=Integer.parseInt(ql);
			
			
			try {
				
				//Create the connection object and call the Db connection 
				Connection con=Db.getConnection();
				
				//Create prepared statement for inserting data to the database
				PreparedStatement ps=con.prepareStatement("insert into books(slno,name,author,publisher,quantity) values(?,?,?,?,?)"); 
				
				ps.setInt(1,sl1);
				ps.setString(2,nl);;
				ps.setString(3,al);;
				ps.setString(4,pl);;
				ps.setInt(5,ql1);
										
				
				ps.executeUpdate(); 

				con.close();  
				  
				}catch(Exception c){ System.out.println(c);} 
			
			JOptionPane.showMessageDialog(this, "Books added successfully");

			s1.setText("");
			n1.setText("");
			a1.setText("");
			p1.setText("");
			q1.setText("");


		}
		
		//Check whether back button is clicked ...if so move back to Librarian section
		if (e.getSource() == back) {
			LibSection.main(new String[] {});
			f.dispose();
		}
	}

	public static void main(String[] args) {
		new AddBooks();

	}

}
