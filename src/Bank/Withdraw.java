package Bank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class Withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraw frame = new Withdraw();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField textFieldCID;
	private JTable table;
	private JPasswordField passwordField;
	/**
	 * 
	 * Create the frame.
	 */
	
	public void refreshTable(){
		try {
			String query = "select Amount from CustomerInfo where CID='"+textFieldCID.getText()+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Withdraw() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewWithdraw = new JLabel("WITHDRAW");
		lblNewWithdraw.setForeground(SystemColor.BLACK);
		lblNewWithdraw.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewWithdraw.setBounds(295, 54, 163, 31);
		contentPane.add(lblNewWithdraw);
		
		JLabel lblNewLabel = new JLabel("Please, Enter Your ");
		lblNewLabel.setForeground(SystemColor.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(79, 99, 156, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabelAcNo = new JLabel("Account No :");
		lblNewLabelAcNo.setForeground(SystemColor.BLACK);
		lblNewLabelAcNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabelAcNo.setBounds(146, 141, 89, 14);
		contentPane.add(lblNewLabelAcNo);
		
		JLabel Password = new JLabel("Password :");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Password.setForeground(Color.BLACK);
		Password.setBounds(156, 166, 79, 14);
		contentPane.add(Password);
		
		JLabel lblNewLabelAmnt = new JLabel("Amount  :");
		lblNewLabelAmnt.setForeground(SystemColor.BLACK);
		lblNewLabelAmnt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabelAmnt.setBounds(162, 195, 73, 14);
		contentPane.add(lblNewLabelAmnt);
		
		textFieldCID = new JTextField();
		textFieldCID.setBounds(295, 140, 190, 20);
		contentPane.add(textFieldCID);
		textFieldCID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(295, 165, 190, 20);
		contentPane.add(passwordField);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(295, 194, 190, 20);
		contentPane.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JLabel CurrentAc = new JLabel("Your Current Balance  :");
		CurrentAc.setForeground(SystemColor.BLACK);
		CurrentAc.setFont(new Font("Tahoma", Font.BOLD, 15));
		CurrentAc.setBounds(56, 255, 199, 20);
		contentPane.add(CurrentAc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(295, 259, 190, 37);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewBackU = new JLabel("Back For User Menu :");
		lblNewBackU.setForeground(SystemColor.BLACK);
		lblNewBackU.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewBackU.setBounds(68, 326, 180, 31);
		contentPane.add(lblNewBackU);
		
		JButton btnNewButton = new JButton("ENTER");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String query = "select Amount from CustomerInfo where CID=? and Password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldCID.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					String amount = rs.getString("Amount");
					int count = 0;
					while(rs.next()){
						count++;
					}
					
					
					if(count == 1){
						
                	String Balancepost = textFieldAmount.getText(); 
                	double totalAm = Double.parseDouble(amount);
                	double withdraw = Double.parseDouble(Balancepost);
                	double value = withdraw + (withdraw*0.0001)+500;
                	
                	if(value < totalAm)
                	{
            				withdraw = withdraw + (withdraw*0.0001);
            				
            				String Balance = null;
                        	double total = totalAm - withdraw;
                            Balance = String.valueOf(total);
                            
        					String query2 = "Update CustomerInfo set Amount='"+Balance+"' where CID='"+textFieldCID.getText()+"' ";
        					PreparedStatement pst2 = connection.prepareStatement(query2);
        					pst2.execute();
        					JOptionPane.showMessageDialog(null, "Your Taka Withdraw is Successful...");
        					pst2.close();
                	}
                	if(withdraw > totalAm){
                		JOptionPane.showMessageDialog(null, "Your Balance is Insufficient,Please Try Again...");
                	}
                	
					}
                	
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "UserName and Password is not Correct, Try again...");
					
				}
				refreshTable();
			}
		});
		btnNewButton.setFont(new Font("Times New Romen", Font.BOLD, 25));
		btnNewButton.setBounds(295, 374, 141, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewLogout = new JButton("EXIT");
		btnNewLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	
		btnNewLogout.setFont(new Font("Times New Romen", Font.BOLD, 25));
		btnNewLogout.setBounds(526, 374, 136, 37);
		contentPane.add(btnNewLogout);
		
		JButton btnNewEnter = new JButton("USER MENU");
		
		btnNewEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserMenu UM = new UserMenu();
				UM.setVisible(true);
				
			}
		});
		btnNewEnter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewEnter.setBounds(68, 373, 141, 39);
		contentPane.add(btnNewEnter);
		
		JLabel lblNewLabel_1 = new JLabel("");
	
		lblNewLabel_1.setBounds(10, 11, 714, 416);
		contentPane.add(lblNewLabel_1);
		refreshTable();
	}
}
