package com.zst.test.rbac.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatServer {
	
	public static void main(String[] args) {
		new ChatServer().go();
	}
	ArrayList<PrintWriter> clientOutputStream;
	public class ClientHandler implements Runnable{
		BufferedReader reader;
		Socket socket;
		public ClientHandler(Socket clientSocket){
			try {
				socket=clientSocket;
				InputStreamReader inputReader=new InputStreamReader(socket.getInputStream());
				reader=new BufferedReader(inputReader);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String message;
			try {
				while((message=reader.readLine())!=null){
					System.out.println("meaage a "+message);
					tellEveryOne(message);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	private void go() {
		// TODO Auto-generated method stub
		clientOutputStream=new ArrayList<PrintWriter>();
		try {
			ServerSocket serverSocket=new ServerSocket(5050);
			while(true){
				Socket socket=serverSocket.accept();
				PrintWriter writer=new PrintWriter(socket.getOutputStream());
				clientOutputStream.add(writer);
				Thread thread =new Thread(new ClientHandler(socket));
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void tellEveryOne(String message) {
		// TODO Auto-generated method stub
		Iterator<PrintWriter> iterator=clientOutputStream.iterator();
		System.out.println(clientOutputStream.size());
		while(iterator.hasNext()){
			try {
				PrintWriter writer=(PrintWriter)iterator.next();
				writer.write("fd");
				writer.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
