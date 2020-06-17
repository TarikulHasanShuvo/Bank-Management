package Bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class UserMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMenu frame = new UserMenu();
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
	public UserMenu() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 477);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Menu");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(317, 69, 167, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please Enter...");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBackground(SystemColor.inactiveCaption);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1.setBounds(240, 115, 141, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("1. For Deposite");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(299, 150, 149, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("2. For Withdraw");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(299, 185, 149, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("3. For Check Balance");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(299, 220, 167, 24);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewTransfer = new JLabel("4. For BalanceTransfer");
		lblNewTransfer.setForeground(Color.BLACK);
		lblNewTransfer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewTransfer.setBounds(299, 255, 185, 24);
		contentPane.add(lblNewTransfer);
		
		textField = new JTextField();
		textField.setBounds(349, 293, 190, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ENTER");
		//btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\images12.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					int r;
					r = Integer.parseInt(textField.getText());
					if(r==1)
					{
						Dposite DE = new Dposite();
						DE.setVisible(true);
					}
					else if(r == 2)
					{
						Withdraw WD = new Withdraw();
						WD.setVisible(true);
						
					}
					else if(r == 3)
					{
						CheckBalance CB = new CheckBalance();
						CB.setVisible(true);
					}
					else if(r == 4)
					{
						BalanceTransfer BT = new BalanceTransfer();
						BT.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Your Entry is Out of Range...,Please Try Again.");
					}
					
				}catch(Exception e1){
				
					JOptionPane.showMessageDialog(null, "Your Entry is Out of Range...,Please Try Again.");
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(149, 393, 141, 33);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		//btnExit.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\imagesert.jpg"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				    System.exit(0);
				
				}	
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setBounds(513, 392, 141, 35);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\information-technology-optical-fiber-x-world-collection-with-578961.jpg"));
		lblNewLabel_5.setBounds(0, 0, 735, 438);
		contentPane.add(lblNewLabel_5);
	}
}
