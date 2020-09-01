package admin;

import javax.swing.*;
import java.awt.event.*;
/*This class is admin section which deals with adding librarian ,viewing librarian,deleting librarian etc....
*/
public class AdminSection extends JFrame implements ActionListener {
	JButton add, view, delete, logout;
	private static final long serialVersionUID=1L;

	AdminSection() {
		//Create button for adding Librarian and action listener
		add = new JButton("Add Librarian");
		add.setBounds(180, 150, 110, 30);
		add(add);
		add.addActionListener(this);
		
		//Create button for Viewing Librarian and action listener
		view = new JButton("View");
		view.setBounds(180, 200, 110, 30);
		add(view);
		view.addActionListener(this);
		
		//Create button for deleting Librarian and action listener
		delete = new JButton("Delete");
		delete.setBounds(180, 250, 110, 30);
		add(delete);
		delete.addActionListener(this);
		
		//Create button for logout
		logout = new JButton("Logout");
		logout.setBounds(180, 300, 110, 30);
		add(logout);
		logout.addActionListener(this);

		setSize(500, 500);
		setLayout(null);
		setVisible(true);

	}
	
	// Function for action performed
	public void actionPerformed(ActionEvent e) {
		
		//cheking whether add button is clicked ...if it is clicked then add librarian page will be loaded
		if (e.getSource() == add) {
			AddLib.main(new String[] {});
			dispose();

		}
		
		//cheking whether view button is clicked ...if it is clicked then view librarian page will be loaded
		if (e.getSource() == view) {
			ViewLib.main(new String[] {});
			
		}
		
		//cheking whether delete button is clicked ...if it is clicked then delete librarian page will be loaded
		if (e.getSource() == delete) {
			DeleteLib.main(new String[] {});
			dispose();
		}
		
		//cheking whether logout button is clicked ...if it is clicked then app wilbe closed
		if (e.getSource() == logout) {
			dispose();

		}
	}

	public static void main(String args[]) {
		new AdminSection();
	}

}
