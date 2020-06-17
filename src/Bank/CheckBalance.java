package Bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class CheckBalance extends JFrame {

	Connection connection = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldAccountNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckBalance frame = new CheckBalance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void refreshTable(){
		try {
			String query = "select Amount from CustomerInfo where CID='"+textFieldAccountNo.getText()+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public CheckBalance() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewCheckBalance = new JLabel("CHECK BALANCE");
		lblNewCheckBalance.setForeground(SystemColor.BLACK);
		lblNewCheckBalance.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblNewCheckBalance.setBounds(275, 58, 197, 32);
		contentPane.add(lblNewCheckBalance);
		
		JLabel lblNewLabelAcNo = new JLabel("Please Enter Account No:");
		lblNewLabelAcNo.setForeground(SystemColor.BLACK);
		lblNewLabelAcNo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabelAcNo.setBounds(41, 116, 247, 32);
		contentPane.add(lblNewLabelAcNo);
		
		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setBounds(310, 119, 226, 32);
		contentPane.add(textFieldAccountNo);
		textFieldAccountNo.setColumns(10);
		
		JLabel lblNewCBalance = new JLabel("Your Current Balance Is:");
		lblNewCBalance.setForeground(SystemColor.BLACK);
		lblNewCBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewCBalance.setBounds(41, 213, 230, 27);
		contentPane.add(lblNewCBalance);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 221, 226, 38);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Back For Main Menu :");
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(75, 286, 226, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("User Menu");
		//btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\images12.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserMenu UM = new UserMenu();
				UM.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(70, 372, 171, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewEnter = new JButton("Confirm");
		//btnNewEnter.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\images4.jpg"));
		btnNewEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select Amount from CustomerInfo where CID='"+textFieldAccountNo.getText()+"'";
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
			
		});
		
		btnNewEnter.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewEnter.setBounds(318, 373, 171, 38);
		contentPane.add(btnNewEnter);
		
		JButton btnNewButtonExit = new JButton("EXIT");
		btnNewButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//JButton btnNewButtonExit = new JButton("Logout");
		//btnNewButtonExit.setIcon(new ImageIcon("C:\\Users\\APU BARUA\\Desktop\\BankingManagement\\BankingManagement\\Image\\imagesert.jpeg"));
		btnNewButtonExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButtonExit.setBounds(553, 373, 171, 39);
		contentPane.add(btnNewButtonExit);
		
		JLabel lblNewLabel_1 = new JLabel("");
		//lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\APU BARUA\\Desktop\\BankingManagement\\Image\\shutterstock_239571814_full.jpeg"));
		lblNewLabel_1.setBounds(10, 11, 714, 427);
		contentPane.add(lblNewLabel_1);
		refreshTable();
	}
}
