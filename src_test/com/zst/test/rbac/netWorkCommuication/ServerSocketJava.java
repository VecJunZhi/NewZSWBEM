package com.zst.test.rbac.netWorkCommuication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ServerSocket:�����socket���յ��ͻ��˵�����󣬻ᴴ��һ������˵�Socket��ͻ��˽���ͨ��
 * ����ServerSocket�Ϳ����ڳ��˿ڼ��������Ƿ��������Ŀͻ��˵�����
 * @author zsjr
 *
 */
public class ServerSocketJava {
	
	public static void main(String[] args) {
		final ServerSocketJava serverSocketJava=new ServerSocketJava();
		//serverSocketJava.read();
		//serverSocketJava.write();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				serverSocketJava.write();
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				serverSocketJava.read();
			}
		}).start();
	}
	private static int num=0;
	private void write() {
		ServerSocket serverSocket = null;
		try {
			//����ServerSocket����ȷ����˵Ķ˿�(IP�������ڼ������Ip)��
			serverSocket=new ServerSocket(5050);
			//serverSocket=getServerSocketInstence();
			while(true){
				Socket socket=serverSocket.accept();
				//����˷������ݡ�
					//BufferedWriter writer=new BufferedWriter(
				PrintWriter writer=new PrintWriter(socket.getOutputStream());
				writer.write("i acceptde:"+num++);
				writer.flush();
				writer.close();
				System.out.println("server is send ok");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				serverSocket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	private void read() {
		ServerSocket serverSocket = null;
		try {
			serverSocket=new ServerSocket(5050);
			//serverSocket=getServerSocketInstence();
			while(true){
				Socket socket=serverSocket.accept();
				InputStreamReader reader=new InputStreamReader(socket.getInputStream());
				BufferedReader bufferReader=new BufferedReader(reader);
				String line=null;
				while((line=bufferReader.readLine())!=null){
					System.out.println(line);
				}
				socket.close();
			}
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			try {
				serverSocket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	private static  ServerSocket serverSocket;
	
	private ServerSocket getServerSocketInstence (){
		System.out.println("serv "+serverSocket);
		if(serverSocket==null){
			try {
				serverSocket=new ServerSocket(5050);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return serverSocket;
		
	}

}
