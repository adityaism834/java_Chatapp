package com.ast.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.ast.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>();//contains all the client sockets
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
    	serverSocket = new ServerSocket(PORT);
    	System.out.println("Server Started waiting for client to join...");
    	handleClientRequest();
	}
	public void handleClientRequest() throws IOException {
		while(true) {
		Socket clientSocket = serverSocket.accept();///handshaking
		//per client per thread
    	ServerWorker serverWorker = new ServerWorker(clientSocket, this);//creating a new thread/worker
    	workers.add(serverWorker);
    	serverWorker.start();
	}
	}
	
	/*
    public Server() throws IOException {
    	int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
    	serverSocket = new ServerSocket(PORT);
    	System.out.println("Server Started Waiting For The Client Connection....");
    	Socket socket = serverSocket.accept();///handshaking
    	System.out.println("Client joins ther server");
    	InputStream in = socket.getInputStream();//read bytes from network
    	byte arr[] = in.readAllBytes();
    	String str = new String(arr);//bytes into string
    	System.out.println("msg received from client:"+ str);
    	in.close();
    	socket.close();
}*/
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        Server server = new Server();
	}

}
