package com.ast.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	int counter;
	public UserView() {
		counter=0;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);
		setTitle("LOGIN");
		setLocationRelativeTo(null);
		//setLocation(500,150);
		
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("arial",Font.BOLD,40));
		Container container = this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(100,70,250,60);
		container.add(welcome);
		JButton button =new JButton("count");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				counter++;
				welcome.setText("count: "+ counter);
			}
		});
		button.setBounds(100,300,200,50);
		container.add(button);
		
		setVisible(true);
	}
   public static void main(String[]args) {
         UserView userView= new UserView();
   }
   
}