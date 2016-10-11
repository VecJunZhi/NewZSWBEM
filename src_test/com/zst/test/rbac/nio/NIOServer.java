package com.zst.test.rbac.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
	
	private int blockSize=4096;
	private ByteBuffer sendBuffer=ByteBuffer.allocate(blockSize);
	private ByteBuffer receiveBuffer=ByteBuffer.allocate(blockSize);
	private Selector selector;
	private int flag=0;
	public NIOServer(int port){
		
		try {
			ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
			//�����Ƿ�����(������)
			serverSocketChannel.configureBlocking(false);
			ServerSocket serverSocket=serverSocketChannel.socket();
			//��IP�Ͷ˿�
			serverSocket.bind(new InetSocketAddress(port));
			//��ѡ����
			selector=Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("server start -->"+port);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//����
	public void listen(){
		System.out.println("abc");
		while(true){
			//��ѯѡ����
			try {
				selector.select();//��ѵ��ѯ����һ�������ķ���
				Set<SelectionKey> selectionKeys=selector.selectedKeys();
				Iterator<SelectionKey>iterator=selectionKeys.iterator();
				System.out.println("SERVER "+iterator.hasNext());
				while(iterator.hasNext()){
					SelectionKey selectionKey=iterator.next();
					iterator.remove();
					handleKey(selectionKey);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleKey(SelectionKey selectionKey){
		ServerSocketChannel server=null;
		SocketChannel client=null;
		String reciveText;
		String sendText;
		int count=0;
		if(selectionKey.isAcceptable()){
			try {
				server=(ServerSocketChannel) selectionKey.channel();
				client=server.accept();
				client.configureBlocking(false);
				client.write(ByteBuffer.wrap(new String("hello client").getBytes()));
				client.register(selector, SelectionKey.OP_READ);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(selectionKey.isReadable()){
			try {
				client=(SocketChannel) selectionKey.channel();
				count=client.read(receiveBuffer);
				if(count>0){
					reciveText=new String(receiveBuffer.array(), 0, count);
					System.out.println("����˽��յ��ͻ��˵����ݣ�"+reciveText);
					 ByteBuffer outBuffer = ByteBuffer.wrap(reciveText.getBytes());  
					 client.write(outBuffer);
					client.register(selector, SelectionKey.OP_WRITE);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(selectionKey.isWritable()){
			try {
				sendBuffer.clear();
				client=(SocketChannel) selectionKey.channel();
				sendText="msg send to client"+flag++;
				sendBuffer.put(sendText.getBytes());
				sendBuffer.flip();//���ͻ�����������
				client.write(sendBuffer);
				System.out.println("���������͸��ͻ��˵�����Ϊ��"+sendText);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		int port=7080;
		NIOServer nioServer=new NIOServer(port);
		nioServer.listen();
	}

}
