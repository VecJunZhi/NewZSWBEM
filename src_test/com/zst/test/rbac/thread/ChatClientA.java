package com.zst.test.rbac.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientA {
	public static void main(String[] args) {
		ChatClientA clientA=new ChatClientA();
		clientA.setNetWoring();
		clientA.read();
	}
	Socket socket=null;
	InputStreamReader reader=null;
	BufferedReader bufferReader=null;
	PrintWriter writer=null;
	private void setNetWoring() {
		 try {
			socket=new Socket("127.0.01", 5050);
			reader=new InputStreamReader(socket.getInputStream());
			bufferReader=new BufferedReader(reader);
			writer=new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	}
	private void read() {
		// 读取服务器的socket串流
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String message;
				try {
					while((message=bufferReader.readLine())!=null){
						System.out.println("meaage 6 "+message);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
		//write();

	}
	private void write() {
		writer.write("da");
		writer.flush();

	}
}
