package Bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class BalanceTransfer extends JFrame {

	private JPanel contentPane;
	Connection connection = null;
	private JTextField textFieldAcNo;
	private JTextField textFieldAmountT;
	private JTextField textFieldRcAcc;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BalanceTransfer frame = new BalanceTransfer();
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
	public BalanceTransfer() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewAccountNo = new JLabel("Account Number");
		lblNewAccountNo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewAccountNo.setForeground(Color.BLACK);
		lblNewAccountNo.setBounds(143, 59, 176, 38);
		contentPane.add(lblNewAccountNo);
		
		JLabel lblNewPassword = new JLabel("Password");
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewPassword.setBounds(143, 96, 186, 25);
		contentPane.add(lblNewPassword);
		
		textFieldAcNo = new JTextField();
		textFieldAcNo.setBounds(355, 59, 219, 33);
		contentPane.add(textFieldAcNo);
		textFieldAcNo.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(355, 102, 219, 33);
		contentPane.add(passwordField);
		
		JLabel lblNewAmount = new JLabel("Amount");
		lblNewAmount.setForeground(Color.BLACK);
		lblNewAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewAmount.setBounds(10, 260, 112, 25);
		contentPane.add(lblNewAmount);
		
		textFieldAmountT = new JTextField();
		textFieldAmountT.setBounds(77, 260, 203, 30);
		contentPane.add(textFieldAmountT);
		textFieldAmountT.setColumns(10);
		
		JLabel lblNewLabelRcAmo = new JLabel("Receaver Account No");
		lblNewLabelRcAmo.setForeground(Color.BLACK);
		lblNewLabelRcAmo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabelRcAmo.setBounds(326, 260, 186, 25);
		contentPane.add(lblNewLabelRcAmo);
		
		textFieldRcAcc = new JTextField();
		textFieldRcAcc.setBounds(522, 259, 186, 30);
		contentPane.add(textFieldRcAcc);
		textFieldRcAcc.setColumns(10);
		
		JButton btnConferm = new JButton("Transfer");
		//btnConferm.setIcon(new ImageIcon(""));
		btnConferm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Do You Really Want To Transfer","Transfer",JOptionPane.YES_NO_OPTION);
				if(action == 0){
				try{
					String query = "select Amount from CustomerInfo where CID=? and Password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldAcNo.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					String amount = rs.getString("Amount");
					int count = 0;
					while(rs.next()){
						count++;
					}
					if(count == 1){
					
                	String BalanceTra = textFieldAmountT.getText(); 
                	double totalAm = Double.parseDouble(amount);
                	double transfer = Double.parseDouble(BalanceTra);
                    double value = transfer + (transfer*0.0001)+500;
                	
                	if(value < totalAm)
                	{
                		    transfer = transfer + (transfer*0.0001);
                		    //System.out.println(transfer);
            				String Balance = null, Balance2 = null;
                        	double total = totalAm - transfer;
                            Balance = String.valueOf(total);
                            
                            
        					String query2 = "Update CustomerInfo set Amount='"+Balance+"' where CID='"+textFieldAcNo.getText()+"' ";
        					PreparedStatement pst2 = connection.prepareStatement(query2);
        					pst2.execute();
        					
        					String query3 = "select Amount from CustomerInfo where CID='"+textFieldRcAcc.getText()+"'";
        					PreparedStatement pst3 = connection.prepareStatement(query3);
        					ResultSet rs2 = pst3.executeQuery();
        					String RCamount = rs2.getString("Amount");
        					double ReceverAcm = Double.parseDouble(RCamount);
        					double totalReceverAcm = transfer + ReceverAcm;
        					Balance2 = String.valueOf(totalReceverAcm);
        					
        					//System.out.println(totalReceverAcm);
        					String query4 = "Update CustomerInfo set Amount='"+Balance2+"' where CID='"+textFieldRcAcc.getText()+"' ";
        					PreparedStatement pst4 = connection.prepareStatement(query4);
        					pst4.execute();
        					
        					JOptionPane.showMessageDialog(null, "Your Taka Transfer is Successful...");
        					pst2.close();
        					pst4.close();
                	}
                	if(transfer > totalAm){
                		JOptionPane.showMessageDialog(null, "Your Balance is Insufficient,Please Try Again...");
                	}
                	
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "UserName and Password is not Correct, Try again...");
					
				}
			}
				
			}
		});
		
		JButton btnLogout = new JButton("Exit");
		//btnLogout.setIcon(new ImageIcon("C:\\Users\\User\\workspace\\BankingManagement\\Image\\imagesert.jpg"));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  System.exit(0);
				
			}
		});
		
		JLabel lblUserMenu = new JLabel("Back For User Menu");
		lblUserMenu.setForeground(Color.BLACK);
		lblUserMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserMenu.setBounds(10, 344, 163, 14);
		contentPane.add(lblUserMenu);
		
		JButton btnEnter = new JButton("User Menu");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserMenu UM = new UserMenu();
				UM.setVisible(true);
				
			}
		});
		btnEnter.setIcon(new ImageIcon("C:\\Users\\APU BARUA\\Desktop\\BankingManagement\\BankingManagement\\Image"));
		btnEnter.setBounds(10, 382, 153, 44);
		contentPane.add(btnEnter);
		btnLogout.setBounds(294, 382, 153, 44);
		contentPane.add(btnLogout);
		btnConferm.setBounds(579, 382, 129, 44);
		contentPane.add(btnConferm);
		
		JLabel lblNewLabelPic = new JLabel("");
		lblNewLabelPic.setIcon(new ImageIcon("EXIT"));
		lblNewLabelPic.setBounds(0, 11, 739, 437);
		contentPane.add(lblNewLabelPic);
	}
}
