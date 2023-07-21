package com.ast.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ast.chatapp.dao.UserDAO;
import com.ast.chatapp.dto.UserDTO;
import com.ast.chatapp.utils.UserInfo;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
	}
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid = useridtxt.getText();
		char []password = passwordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid,password);	
		try {
			String message ="";
			if(userDAO.isLogin(userDTO)) {
				message = "welcome"+userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this,message);
				setVisible(false);
				dispose();
				DashBoard dashboard = new DashBoard(message);
				dashboard.setVisible(true);
			}
			else {
				message = "invalid userid or password";
				JOptionPane.showMessageDialog(this,message);
			}
			//JOptionPane.showMessageDialog(this,message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void register() {
		String userid = useridtxt.getText();
		char []password = passwordField.getPassword();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid,password);
		try {
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this,"Register Successfully");
			//System.out.println("record added..");
		}
		else {
			JOptionPane.showMessageDialog(this,"Register FAILED");
			//System.out.println("record not added..");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB issue...");
			ex.printStackTrace();}
		catch(Exception ex) {
			System.out.println("Some Generic exception raised..");
			ex.printStackTrace();//where the exception is
		}
		System.out.println("userid "+ userid +"passowrd "+ password);
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(340, 34, 203, 83);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(486, 146, 278, 48);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("USER ID");
		useridlbl.setFont(new Font("Arial Black", Font.BOLD, 18));
		useridlbl.setBounds(244, 156, 150, 38);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("PASSWORD");
		pwdlbl.setFont(new Font("Arial Black", Font.BOLD, 18));
		pwdlbl.setBounds(244, 219, 150, 38);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(486, 218, 278, 48);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("LOGIN");
		loginbt.setForeground(Color.ORANGE);
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Arial Black", Font.PLAIN, 20));
		loginbt.setBounds(263, 319, 131, 48);
		getContentPane().add(loginbt);
		
		JButton Registerbt = new JButton("REGISTER");
		Registerbt.setForeground(Color.ORANGE);
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		Registerbt.setFont(new Font("Arial Black", Font.PLAIN, 20));
		Registerbt.setBounds(486, 319, 167, 48);
		getContentPane().add(Registerbt);
		setSize (840, 482);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
