package com.ast.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.ast.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea)throws UnknownHostException, IOException{
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
		out = socket.getOutputStream();
		in =socket.getInputStream();
		this.textArea=textArea;
		readmessages();
		
        /*
         * System.out.println("Client Comes....");
		//System.out.println("Enter msg send to the Server:");
		//Scanner sc = new Scanner(System.in);
		//String message = sc.nextLine();
		OutputStream out = socket.getOutputStream();//write bytes on network
		out.write(message.getBytes());
		System.out.println("msg send to the server");
		sc.close();
		out.close();
		socket.close();
		*/
	}
	
	public void sendmessage(String message) throws IOException {
		message = message +"\n";
		out.write(message.getBytes());
	}
	public void readmessages() {
		worker = new ClientWorker(in,textArea);//calling a read thread
		worker.start();
	}
/*
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
    Client client = new Client();
	}
*/
}
