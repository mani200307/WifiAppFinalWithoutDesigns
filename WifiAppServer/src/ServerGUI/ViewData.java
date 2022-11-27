package ServerGUI;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class ViewData extends JFrame {

	private JPanel contentPane;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private JTable table;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewData frame = new ViewData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ViewData() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 75, 375, 176);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		connect();
		st = con.createStatement();
		String sql = "SELECT * FROM PresentCnt;";
		rs = st.executeQuery(sql);

		table.setModel(DbUtils.resultSetToTableModel(rs));		

		
		JLabel lblNewLabel = new JLabel("Present Count");
		lblNewLabel.setBounds(174, 11, 83, 14);
		contentPane.add(lblNewLabel);

	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wifidb","root","");
			st = con.createStatement();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
