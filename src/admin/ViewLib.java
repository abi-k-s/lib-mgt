package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Db;
/*This class shows Librarian details in tabular form
 * */
public class ViewLib extends JFrame {
	private static final long serialVersionUID=1L;

	public static void main(String[] args) {
		JTable table;
		JFrame f = new JFrame("View Librarian");
		String data[][] = null;
		String column[] = null;
		try {
			//Create the connection object and call the Db connection 
			Connection con = Db.getConnection();
			
			//Create prepared statement for selecting data from database with table named librarian
			PreparedStatement ps = con.prepareStatement("select * from librarian", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();//Gets column count
			column = new String[cols];
			for (int i = 1; i <= cols; i++) {
				column[i - 1] = rsmd.getColumnName(i);
			}

			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();

			data = new String[rows][cols];
			int count = 0;
			while (rs.next()) {
				for (int i = 1; i <= cols; i++) {
					data[count][i - 1] = rs.getString(i);
				}
				count++;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		table = new JTable(data, column);
		JScrollPane sp = new JScrollPane(table);
		f.add(sp);
		f.setSize(600, 600);
		f.setVisible(true);

	}

}
