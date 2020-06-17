package Bank;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JPasswordField passwordField;
	private JTextField textField;
	public JLabel lblNewClock;
	private JTextField textFieldNum2;
	private JPasswordField passwordField2;
	/**
	 * Create the application.
	 */
	public void clock(){
		
		Thread clock = new Thread(){
			public void run(){
				try {
					for(;;){
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);
					month = month+1;
					
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					
					lblNewClock.setText("Time: "+hour+":"+minute+":"+second+",  Date: "+day+"-"+month+"-"+year);
					
					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		clock.start();
		
	}
	public Menu() {
		connection = sqliteConnection.dbConnector();
		initialize();
		clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(SystemColor.window);
		frame.setBounds(100, 100, 898, 633);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewClock = new JLabel("Clock");
		lblNewClock.setForeground(Color.GREEN);
		lblNewClock.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewClock.setBounds(448, 0, 290, 62);
		frame.getContentPane().add(lblNewClock);
		
		JLabel lblNewLabel = new JLabel("Welcome To Our");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(271, 51, 303, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bank Management System");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(219, 108, 443, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Admin");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_2.setBounds(67, 366, 78, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("User Name");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(45, 431, 73, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(45, 466, 73, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(135, 429, 169, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 464, 169, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		//btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\index4.jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					int r,b;
					String User, Value;
		            Value = "Apu";
					r = Integer.parseInt(passwordField.getText());
					User = String.valueOf(textField.getText());
					b = User.compareTo(Value);
					if((r == 123456789) && (b == 0)){
						frame.dispose();
						Admin AM = new Admin();
						AM.setVisible(true);
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "User Name or Password is wrong,Please Try Again...");
					
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(160, 502, 144, 37);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("User");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(749, 369, 108, 17);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel UserName = new JLabel("User Name");
		UserName.setForeground(Color.BLACK);
		UserName.setBounds(586, 432, 73, 14);
		frame.getContentPane().add(UserName);
		
		JLabel UserPassword = new JLabel("Password");
		UserPassword.setForeground(Color.BLACK);
		UserPassword.setBounds(596, 467, 86, 14);
		frame.getContentPane().add(UserPassword);
		
		textFieldNum2 = new JTextField();
		textFieldNum2.setBounds(694, 429, 164, 20);
		frame.getContentPane().add(textFieldNum2);
		textFieldNum2.setColumns(10);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(694, 464, 164, 20);
		frame.getContentPane().add(passwordField2);
		
		JButton UserLogin = new JButton("Login");
		//UserLogin.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\index4.jpg"));
		UserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select * from CustomerInfo where U_name=? and Password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldNum2.getText());
					pst.setString(2, passwordField2.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;	
					}
						if(count == 1){
							frame.dispose();
							UserMenu UM = new UserMenu();
							UM.setVisible(true);
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
		UserLogin.setBounds(714, 502, 144, 37);
		frame.getContentPane().add(UserLogin);
		
		JLabel lblNewCreate = new JLabel("Do not have any account?");
		lblNewCreate.setForeground(SystemColor.desktop);
		lblNewCreate.setBounds(446, 564, 150, 19);
		frame.getContentPane().add(lblNewCreate);
		
		JButton btnNewButton_1 = new JButton("Create one");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{
					//JOptionPane.showMessageDialog(null, "Connection Successful");
					frame.dispose();
					LoginOrCreateAc LCC = new LoginOrCreateAc();
					LCC.setVisible(true);
					
				}catch(Exception e){
					
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(596, 562, 110, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel Admin = new JLabel("");
		//Admin.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\login_icon2.png"));
		Admin.setForeground(SystemColor.text);
		Admin.setBounds(0, 176, 180, 189);
		frame.getContentPane().add(Admin);
		
		JLabel User_1 = new JLabel("");
		//User_1.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\benefits2.png"));
		User_1.setForeground(SystemColor.text);
		User_1.setBounds(694, 176, 188, 189);
		frame.getContentPane().add(User_1);
	}
	}
