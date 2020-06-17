package Bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
public class LoginOrCreateAc extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCID;
	private JTextField textFieldFName;
	private JTextField textFieldLName;
	private JTextField textFieldUName;
	private JPasswordField passwordField;
	private JTextField textFieldCell;
	private JTextField textFieldMail;
	private JTextField textFieldNID;
	private JTextField textFieldAge;
	private JTextField textFieldAmnt;
	private JTextField textFieldUName2;
	private JPasswordField passwordField2;

	/**
	 * Launch the application.
	 */
	private LoginOrCreateAc frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginOrCreateAc frame = new LoginOrCreateAc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the frame.
	 */
	
	int cid,age;
	double amount;
	String fname,lname,uname,password,cell,mid,nid;
	
	public LoginOrCreateAc() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(43, 55, 187, 31);
		contentPane.add(lblNewLabel);
		
		JLabel CID = new JLabel("CID");
		CID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		CID.setForeground(Color.BLACK);
		CID.setBounds(43, 100, 79, 14);
		contentPane.add(CID);
		
		JLabel FirstName = new JLabel("First Name");
		FirstName.setForeground(Color.BLACK);
		FirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		FirstName.setBounds(43, 125, 79, 14);
		contentPane.add(FirstName);
		
		JLabel LastName = new JLabel("Last Name");
		LastName.setForeground(Color.BLACK);
		LastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LastName.setBounds(43, 150, 79, 14);
		contentPane.add(LastName);
		
		JLabel UserName = new JLabel("User Name");
		UserName.setForeground(Color.BLACK);
		UserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		UserName.setBounds(43, 175, 79, 14);
		contentPane.add(UserName);
		
		JLabel Password = new JLabel("Password");
		Password.setForeground(Color.BLACK);
		Password.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Password.setBounds(43, 200, 79, 14);
		contentPane.add(Password);
		
		JLabel Cell = new JLabel("Cell");
		Cell.setForeground(Color.BLACK);
		Cell.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Cell.setBounds(43, 225, 79, 14);
		contentPane.add(Cell);
		
		JLabel Mail_ID = new JLabel("Mail ID");
		Mail_ID.setForeground(Color.BLACK);
		Mail_ID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Mail_ID.setBounds(43, 250, 79, 14);
		contentPane.add(Mail_ID);
		
		JLabel National_ID = new JLabel("National ID");
		National_ID.setForeground(Color.BLACK);
		National_ID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		National_ID.setBounds(43, 275, 79, 14);
		contentPane.add(National_ID);
		
		JLabel Age = new JLabel("Age");
		Age.setForeground(Color.BLACK);
		Age.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Age.setBounds(43, 300, 79, 14);
		contentPane.add(Age);
		
		JLabel Amount = new JLabel("Amount");
		Amount.setForeground(Color.BLACK);
		Amount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Amount.setBounds(43, 325, 79, 14);
		contentPane.add(Amount);
		
		textFieldCID = new JTextField();
		textFieldCID.setBounds(144, 97, 156, 20);
		contentPane.add(textFieldCID);
		textFieldCID.setColumns(10);
		
		textFieldFName = new JTextField();
		textFieldFName.setBounds(144, 122, 156, 20);
		contentPane.add(textFieldFName);
		textFieldFName.setColumns(10);
		
		textFieldLName = new JTextField();
		textFieldLName.setBounds(144, 147, 156, 20);
		contentPane.add(textFieldLName);
		textFieldLName.setColumns(10);
		
		textFieldUName = new JTextField();
		textFieldUName.setBounds(144, 172, 156, 20);
		contentPane.add(textFieldUName);
		textFieldUName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 197, 156, 20);
		contentPane.add(passwordField);
		
		textFieldCell = new JTextField();
		textFieldCell.setBounds(144, 222, 156, 20);
		contentPane.add(textFieldCell);
		textFieldCell.setColumns(10);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(144, 247, 156, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		textFieldNID = new JTextField();
		textFieldNID.setBounds(144, 272, 156, 20);
		contentPane.add(textFieldNID);
		textFieldNID.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(144, 297, 156, 20);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		textFieldAmnt = new JTextField();
		textFieldAmnt.setBounds(144, 322, 156, 20);
		contentPane.add(textFieldAmnt);
		textFieldAmnt.setColumns(10);
		
		JButton ConformButton = new JButton("Create");
		
		ConformButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					cid = Integer.parseInt(textFieldCID.getText());
					fname = textFieldFName.getText();
					lname = textFieldLName.getText();
					uname = textFieldUName.getText();
					password = passwordField.getText();
					cell = textFieldCell.getText();
					mid = textFieldMail.getText();
					nid = textFieldNID.getText();
				    age = Integer.parseInt(textFieldAge.getText());
					amount = Double.parseDouble(textFieldAmnt.getText());
					
					String query = "insert into CustomerInfo (CID,F_name,L_name,U_name,Password,Cell,Mail_ID,N_ID,Age,Amount) Values ('"+textFieldCID.getText()+"', '"+textFieldFName.getText()+"', '"+textFieldLName.getText()+"','"+textFieldUName.getText()+"','"+passwordField.getText()+"', '"+textFieldCell.getText()+"', '"+textFieldMail.getText()+"', '"+textFieldNID.getText()+"', '"+textFieldAge.getText()+"', '"+textFieldAmnt.getText()+"')";
					Statement pst = connection.createStatement();
					

					pst.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Data Successfully Saved...");
					pst.close();
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
			}
		});
		ConformButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		ConformButton.setBounds(174, 360, 126, 31);
		contentPane.add(ConformButton);
		
		JLabel lblNewLabel_11 = new JLabel("Sign In");
		lblNewLabel_11.setForeground(Color.BLACK);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_11.setBounds(434, 100, 89, 24);
		contentPane.add(lblNewLabel_11);
		
		JLabel UserName2 = new JLabel("User Name");
		UserName2.setForeground(Color.BLACK);
		UserName2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		UserName2.setBounds(434, 150, 64, 14);
		contentPane.add(UserName2);
		
		JLabel Password2 = new JLabel("Password");
		Password2.setForeground(Color.BLACK);
		Password2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Password2.setBounds(434, 175, 64, 14);
		contentPane.add(Password2);
		
		textFieldUName2 = new JTextField();
		textFieldUName2.setBounds(531, 147, 156, 20);
		contentPane.add(textFieldUName2);
		textFieldUName2.setColumns(10);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(531, 172, 156, 20);
		contentPane.add(passwordField2);
		
		JButton Login = new JButton("Login");
		
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query = "select * from CustomerInfo where U_name=? and Password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUName2.getText());
					pst.setString(2, passwordField2.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;	
					}
						if(count == 1){
							//JOptionPane.showMessageDialog(null, "UserName and Password is Correct");
							//LoginOrCreateAc frame = new LoginOrCreateAc();

							UserMenu UM = new UserMenu();
							UM.setVisible(true);
							//LoginOrCreateAc.dispose();	
							//frame.dispose();
						}
						
						else{
							JOptionPane.showMessageDialog(null, "UserName and Password is not Correct, Try again...");
						}
						
					rs.close();
					pst.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					
				}		
			}
		});
		Login.setFont(new Font("Tahoma", Font.BOLD, 15));
		Login.setBounds(568, 222, 119, 42);
		contentPane.add(Login);
		
		
	}
}
