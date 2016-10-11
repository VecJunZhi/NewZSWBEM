package com.zst.test.rbac.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO�ͻ���
 * @author С·
 */
public class NIOClient2 {
	//ͨ��������
	private Selector selector;
	ByteBuffer sendBuffer=ByteBuffer.allocate(1024);
	/**
	 * ���һ��Socketͨ�������Ը�ͨ����һЩ��ʼ���Ĺ���
	 * @param ip ���ӵķ�������ip
	 * @param port  ���ӵķ������Ķ˿ں�         
	 * @throws IOException
	 */
	public void initClient(String ip,int port) throws IOException {
		// ���һ��Socketͨ��
		SocketChannel channel = SocketChannel.open();
		// ����ͨ��Ϊ������
		channel.configureBlocking(false);
		// ���һ��ͨ��������
		this.selector = Selector.open();
		
		// �ͻ������ӷ�����,��ʵ����ִ�в�û��ʵ�����ӣ���Ҫ��listen���������е�
		//��channel.finishConnect();�����������
		channel.connect(new InetSocketAddress(ip,port));
		//��ͨ���������͸�ͨ���󶨣���Ϊ��ͨ��ע��SelectionKey.OP_CONNECT�¼���
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	/**
	 * ������ѯ�ķ�ʽ����selector���Ƿ�����Ҫ������¼�������У�����д���
	 * @throws IOException
	 */
	public void listen() throws IOException {
		// ��ѯ����selector
		while (true) {
			selector.select();
			// ���selector��ѡ�е���ĵ�����
			Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// ɾ����ѡ��key,�Է��ظ�����
				ite.remove();
				// �����¼�����
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key
							.channel();
					// ����������ӣ����������
					if(channel.isConnectionPending()){
						channel.finishConnect();
						
					}
					// ���óɷ�����
					channel.configureBlocking(false);

					//��������Ը�����˷�����ϢŶ
					//channel.write(ByteBuffer.wrap(new String("�����˷�����һ����Ϣ").getBytes()));
					sendBuffer.clear();
					String sendText="Msg send to server-->";
					sendBuffer.put(sendText.getBytes());
					sendBuffer.flip();
					channel.write(sendBuffer);
					System.out.println();
					//�ںͷ�������ӳɹ�֮��Ϊ�˿��Խ��յ�����˵���Ϣ����Ҫ��ͨ�����ö���Ȩ�ޡ�
					channel.register(this.selector, SelectionKey.OP_READ);
					
					// ����˿ɶ����¼�
				} else if (key.isReadable()) {
						read(key);
				}

			}

		}
	}
	/**
	 * �����ȡ����˷�������Ϣ ���¼�
	 * @param key
	 * @throws IOException 
	 */
	public void read(SelectionKey key) throws IOException{
		//�ͷ���˵�read����һ��
		SocketChannel channel = (SocketChannel) key.channel();
		// ������ȡ�Ļ�����
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear(); 
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("�ͻ����յ���Ϣ��"+msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		channel.write(outBuffer);// ����Ϣ���͸��ͻ���
	}
	
	
	/**
	 * �����ͻ��˲���
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		NIOClient2 client = new NIOClient2();
		client.initClient("localhost",8000);
		client.listen();
	}

}
