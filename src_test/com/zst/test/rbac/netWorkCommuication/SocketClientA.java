package com.zst.test.rbac.netWorkCommuication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientA {
	public static void main(String[] args) {
		//new SocketClientA().send();
		new SocketClientA().read();
	}
	private void send() {
		try {
			//连接服务器
			Socket socket=new Socket("127.0.01", 5050);
			//向服务器写数据
			PrintWriter writer=new PrintWriter(socket.getOutputStream());
			writer.write("clinet a send something and ok");
			writer.flush();
			writer.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void read() {
		try {
			Socket socket=new Socket("127.0.01", 5050);
			InputStreamReader reader=new InputStreamReader(socket.getInputStream());
			BufferedReader bufferReader=new BufferedReader(reader);
			String line=null;
			while((line=bufferReader.readLine())!=null){
				System.out.println(line);
			}
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
