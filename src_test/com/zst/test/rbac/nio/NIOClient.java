package com.zst.test.rbac.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class NIOClient {

	private static int blockSize=4096;
	private static ByteBuffer sendBuffer=ByteBuffer.allocate(blockSize);
	private static ByteBuffer receiveBuffer=ByteBuffer.allocate(blockSize);
	//private Selector selector;
	private static int flag=0;
	private final static InetSocketAddress serverAddress=new InetSocketAddress("127.0.0.1", 7080);
	private static int count=0;
	
	public static void main(String[] args) {
		try {
			SocketChannel socketChannel=SocketChannel.open();
			socketChannel.configureBlocking(false);
			//打开选择器
			Selector selector=Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(serverAddress);
			Set<SelectionKey>selectionKeys;
			Iterator<SelectionKey> iterator;
			SelectionKey selectionKey ;
			SocketChannel client;
			String receiveText;
			String sendText;
			
			while(true){
				selectionKeys=selector.selectedKeys();
				iterator=selectionKeys.iterator();
				//System.out.println(iterator.hasNext());
				while(iterator.hasNext()){
					System.out.println("gd");
					selectionKey=iterator.next();
					if(selectionKey.isConnectable()){
						System.out.println("clinet connet...");
						client=(SocketChannel) selectionKey.channel();
						if(client.isConnectionPending()){
							client.finishConnect();
							System.out.println("客户端完成连接");
						}
						client.configureBlocking(false);
						sendBuffer.clear();
						sendBuffer.put("Hello ,server".getBytes());
						sendBuffer.flip();
						client.write(ByteBuffer.wrap(new String("向服务端发送了一条信息").getBytes()));
						client.register(selector, SelectionKey.OP_READ);
					}if(selectionKey.isReadable()){
						client=(SocketChannel) selectionKey.channel();
						receiveBuffer.clear();
						count=client.read(receiveBuffer);
						if(count>0){
							receiveText=new String(receiveBuffer.array(), 0, count);
							System.out.println("客户端收到服务端的数据："+receiveText);
							client.register(selector, SelectionKey.OP_WRITE);
						}
					}if(selectionKey.isWritable()){
						sendBuffer.clear();
						client=(SocketChannel) selectionKey.channel();
						sendText="Msg send to server-->"+flag++;
						sendBuffer.put(sendText.getBytes());
						sendBuffer.flip();
						client.write(sendBuffer);
						System.out.println("客户端发送数据到服务端："+sendText);
						client.register(selector, SelectionKey.OP_READ);
					}
				}
				//selectionKeys.clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
