package Bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin extends JFrame {
	private JTextField textFieldF;
	private JTextField textFieldL;
	private JTextField textFieldCID;
	private JTextField textFieldC;
	private JTextField textFieldMID;
	private JTextField textFieldAge;
	private JTextField textFieldAmnt;
	private JTextField textFieldNID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	String fname,lname,cell,mid,nid;
	
	public void refreshTable(){
		try {
			String query = "select CID,F_name,L_name,Cell,Mail_ID,N_ID,Age,Amount from CustomerInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Admin() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 550);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabeCID = new JLabel("CID");
		lblNewLabeCID.setBounds(10, 81, 79, 14);
		getContentPane().add(lblNewLabeCID);
		
		JLabel lblNewLabelF = new JLabel("First Name");
		lblNewLabelF.setBounds(10, 106, 79, 14);
		getContentPane().add(lblNewLabelF);
		
		JLabel lblNewLabelL = new JLabel("Last Name");
		lblNewLabelL.setBounds(10, 134, 79, 14);
		getContentPane().add(lblNewLabelL);
		
		JLabel lblNewLabelC = new JLabel("Cell");
		lblNewLabelC.setBounds(10, 159, 79, 14);
		getContentPane().add(lblNewLabelC);
		
		JLabel lblNewLabelM = new JLabel("Mail ID");
		lblNewLabelM.setBounds(10, 187, 79, 14);
		getContentPane().add(lblNewLabelM);
		
		JLabel lblNewLabelNID = new JLabel("National ID");
		lblNewLabelNID.setBounds(10, 212, 79, 14);
		getContentPane().add(lblNewLabelNID);
		
		JLabel lblNewLabelAge = new JLabel("Age");
		lblNewLabelAge.setBounds(10, 237, 79, 14);
		getContentPane().add(lblNewLabelAge);
		
		JLabel lblNewLabelOP = new JLabel("Amount");
		lblNewLabelOP.setBounds(10, 262, 79, 14);
		getContentPane().add(lblNewLabelOP);
		
		textFieldCID = new JTextField();
		textFieldCID.setBounds(74, 78, 139, 20);
		getContentPane().add(textFieldCID);
		textFieldCID.setColumns(10);
		
		textFieldF = new JTextField();
		textFieldF.setBounds(74, 103, 139, 20);
		getContentPane().add(textFieldF);
		textFieldF.setColumns(10);
		
		textFieldL = new JTextField();
		textFieldL.setBounds(74, 128, 139, 20);
		getContentPane().add(textFieldL);
		textFieldL.setColumns(10);
		
		textFieldC = new JTextField();
		textFieldC.setBounds(74, 156, 139, 20);
		getContentPane().add(textFieldC);
		textFieldC.setColumns(10);
		
		textFieldMID = new JTextField();
		textFieldMID.setBounds(74, 181, 139, 20);
		getContentPane().add(textFieldMID);
		textFieldMID.setColumns(10);
		
		textFieldNID = new JTextField();
		textFieldNID.setBounds(74, 206, 139, 20);
		getContentPane().add(textFieldNID);
		textFieldNID.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String query = "Update CustomerInfo set CID='"+textFieldCID.getText()+"', F_name='"+textFieldF.getText()+"', L_name='"+textFieldL.getText()+"', Cell='"+textFieldC.getText()+"', Mail_ID='"+textFieldMID.getText()+"', N_ID='"+textFieldNID.getText()+"', Age='"+textFieldAge.getText()+"', Amount='"+textFieldAmnt.getText()+"' where CID='"+textFieldCID.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					//JOptionPane.showMessageDialog(null, "Data Successfully Saved...");
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(74, 231, 139, 20);
		getContentPane().add(textFieldAge);
		textFieldAge.setColumns(10);
		
		textFieldAmnt = new JTextField();
		textFieldAmnt.setBounds(74, 259, 139, 20);
		getContentPane().add(textFieldAmnt);
		textFieldAmnt.setColumns(10);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(10, 313, 89, 23);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Do You Really Want To Delete","Delete",JOptionPane.YES_NO_OPTION);
				if(action == 0){
				try {
					String query = "delete from CustomerInfo where CID='"+textFieldCID.getText()+"'  ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					pst.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(124, 313, 89, 23);
		getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(221, 78, 606, 379);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
				int row = table.getSelectedRow();
				String CID=(table.getModel().getValueAt(row, 0)).toString();
				String query = "select * from CustomerInfo where CID='"+CID+"' ";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
				while (rs.next())
						{
							textFieldCID.setText(rs.getString("CID"));
							textFieldF.setText(rs.getString("F_name"));
							textFieldL.setText(rs.getString("L_name"));
							textFieldC.setText(rs.getString("Cell"));
							textFieldMID.setText(rs.getString("Mail_ID"));
							textFieldNID.setText(rs.getString("N_ID"));
							textFieldAge.setText(rs.getString("Age"));
							textFieldAmnt.setText(rs.getString("Amount"));
						}
				
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			}
		});
		scrollPane.setViewportView(table);
		JButton ShowAlldata = new JButton("Show All Data");
		
		ShowAlldata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select CID,F_name,L_name,Cell,Mail_ID,N_ID,Age,Amount from CustomerInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		
		ShowAlldata.setFont(new Font("Tahoma", Font.BOLD, 15));
		ShowAlldata.setBounds(427, 52, 139, 23);
		getContentPane().add(ShowAlldata);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(10, 358, 89, 23);
		getContentPane().add(btnNewButton);
		
		refreshTable();
	}
}
