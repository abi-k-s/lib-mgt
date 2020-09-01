package librarian;
import javax.swing.*;
import java.awt.event.*;

/*This class is Librarian section which deals with activities of librarian including 
 adding books,viewing books ,issuing books ,viewing issued books,returning books etc......
*/
public class LibSection extends JFrame implements ActionListener {
	private static final long serialVersionUID=1L;

	JFrame f;
	JButton adb,vib,isb,viewib,rtb,lo;
	LibSection(){
		
		f=new JFrame("Librarian Section");
		
		//Create button for adding books and action listener
		adb = new JButton("Add Books");
		adb.setBounds(150, 100, 200, 30);
		f.add(adb);
		adb.addActionListener(this);
		
		//Create button for viewing books and action listener
		vib = new JButton("View Books");
		vib.setBounds(150, 150, 200, 30);
		f.add(vib);
		vib.addActionListener(this);

		//Create button for issuing books and action listener
		isb = new JButton("Issue Books");
		isb.setBounds(150, 200, 200, 30);
		f.add(isb);
		isb.addActionListener(this);

		//Create button for viewing issued books and action listener
		viewib = new JButton("View Issued Books");
		viewib.setBounds(150, 250, 200, 30);
		f.add(viewib);
		viewib.addActionListener(this);

		//Create button for returning books and action listener
		rtb = new JButton("Return Book");
		rtb.setBounds(150, 300, 200, 30);
		f.add(rtb);
		rtb.addActionListener(this);

		//Create button for logout
		lo= new JButton("Logout");
		lo.setBounds(150, 350, 200, 30);
		f.add(lo);
		lo.addActionListener(this);

		f.setSize(500,500);
		f.setLayout(null);
		f.setVisible(true);
		
	}
	
	// Function for action performed
	public void actionPerformed(ActionEvent e) {
		
		//checking whether add button is clicked ...if it is clicked then add books page will be loaded
		if(e.getSource()==adb) {
			AddBooks.main(new String[] {});
			f.dispose();
		}
		
		//checking whether view button is clicked ...if it is clicked then view books page will be loaded
		if(e.getSource()==vib) {
			ViewBooks.main(new String[] {});
			
		}
		
		//cheking whether issue books button is clicked ...if it is clicked then issue books page will be loaded
		if(e.getSource()==isb) {
			IssueBooks.main(new String[] {});
		}
		
		//checking whether view issued books button is clicked ...if it is clicked then view issued books page will be loaded
		if(e.getSource()==viewib) {
			ViewIssuedBooks.main(new String[] {});
		}
		
		//checking whether return books button is clicked ...if it is clicked then return books page will be loaded
		if(e.getSource()==rtb) {
	        ReturnBook.main(new String[] {});
	        f.dispose();
		}
		
		//checking whether logout button is clicked ...if it is clicked then app wilbe closed
		if(e.getSource()==lo) {
			f.dispose();
	
		}

		
	}
	

	public static void main(String[] args) {
		new LibSection();

	}

}
