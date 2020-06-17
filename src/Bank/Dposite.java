package Bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Dposite extends JFrame {

	protected static final String Menu = null;
	private JPanel contentPane;
	private JTextField textFieldAcountNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dposite frame = new Dposite();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JTable table;
	private JTextField textFieldAmnt;
	private JPasswordField passwordField;
	/**
	 * Create the frame.
	 */
	
	public void refreshTable(){
		try {
			String query = "select Amount from CustomerInfo where CID='"+textFieldAcountNo.getText()+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public Dposite() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewDeposite = new JLabel("DEPOSITE");
		lblNewDeposite.setForeground(SystemColor.BLACK);
		lblNewDeposite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewDeposite.setBounds(306, 59, 174, 29);
		contentPane.add(lblNewDeposite);
		
		JLabel lblNewLabel = new JLabel("Please, Enter Your ");
		lblNewLabel.setForeground(SystemColor.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(72, 77, 187, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabelAc = new JLabel("Account No :");
		lblNewLabelAc.setForeground(SystemColor.BLACK);
		lblNewLabelAc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabelAc.setBounds(141, 129, 101, 20);
		contentPane.add(lblNewLabelAc);
		
		JLabel Password = new JLabel("Password :");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Password.setForeground(Color.BLACK);
		Password.setBounds(151, 160, 91, 14);
		contentPane.add(Password);
		
		JLabel lblNewLabelAmount = new JLabel("Amount :");
		lblNewLabelAmount.setForeground(SystemColor.BLACK);
		lblNewLabelAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabelAmount.setBounds(163, 185, 79, 20);
		contentPane.add(lblNewLabelAmount);
		
		textFieldAcountNo = new JTextField();
		textFieldAcountNo.setBounds(271, 131, 192, 20);
		contentPane.add(textFieldAcountNo);
		textFieldAcountNo.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(271, 159, 192, 20);
		contentPane.add(passwordField);
		
		textFieldAmnt = new JTextField();
		textFieldAmnt.setBounds(271, 184, 192, 20);
		contentPane.add(textFieldAmnt);
		textFieldAmnt.setColumns(10);
		
		JLabel CheckBl = new JLabel("Your Current Balance :");
		CheckBl.setForeground(SystemColor.BLACK);
		CheckBl.setFont(new Font("Tahoma", Font.BOLD, 15));
		CheckBl.setBounds(55, 264, 187, 29);
		contentPane.add(CheckBl);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 272, 192, 37);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Back For User Menu :");
		lblNewLabel_1.setForeground(SystemColor.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(53, 330, 176, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewEnter = new JButton("User Menu");
		//btnNewEnter.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\images12.png"));
		btnNewEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserMenu UM = new UserMenu();
				UM.setVisible(true);
				
			}
		});
		btnNewEnter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewEnter.setBounds(53, 381, 145, 37);
		contentPane.add(btnNewEnter);
		
		JButton btnNewButton = new JButton("Confirm");
		//btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\images4.jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                try{
                	
                	String query = "select Amount from CustomerInfo where CID=? and Password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldAcountNo.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					String amount = rs.getString("Amount");
					int count = 0;
					while(rs.next()){
						count++;
					}
					
					if(count == 1){
					
                	String Balancepre = textFieldAmnt.getText(); 
                	String Balance = null;
                    double pre = Double.parseDouble(amount);
                    double post = Double.parseDouble(Balancepre);
                    double resultTotal = pre+post;
                    Balance = String.valueOf(resultTotal);
                    //System.out.println(Balance);
					String query2 = "Update CustomerInfo set Amount='"+Balance+"' where CID='"+textFieldAcountNo.getText()+"' ";
					PreparedStatement pst2 = connection.prepareStatement(query2);
					pst2.execute();
					JOptionPane.showMessageDialog(null, "Your Deposite is Successful...");
					pst2.close();
					pst.close();
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "UserName and Password is not Correct, Try again...");
					//e1.printStackTrace();
				}
            	refreshTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(306, 381, 145, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewLogout = new JButton("EXIT");
		btnNewLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//btnNewLogout.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\images6.jpg"));
		btnNewLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewLogout.setBounds(517, 381, 145, 37);
		contentPane.add(btnNewLogout);
		
		JLabel lblNewLabelpic = new JLabel("");
		//lblNewLabelpic.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\images123.jpg"));
		lblNewLabelpic.setForeground(Color.WHITE);
		lblNewLabelpic.setBounds(10, 11, 714, 416);
		contentPane.add(lblNewLabelpic);
		refreshTable();
	}
}
