package com.zst.test.rbac.netWorkCommuication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketOptions;//针对socket ip  tcp 做一些设置。
import java.util.concurrent.Executors;//线程池


public class Sockets extends Socket{
	public static void main(String[] args) {
		new Sockets().abc();
		try {
			new Socket().getReceiveBufferSize();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private  void abc() {
		
		System.out.println(super.getClass().getName());
	}
	 /*
	  *  public FaceId_ErrorCode Execute(String command, FaceIdAnswer output, String charsetName)
	  {
	    try
	    {
	      NetworkStreamPlus Stream = getNetworkStreamPlus();//注入一个socket实例和一个加密类实力
	      
	      output.answer = Stream.Query(command, charsetName);
	      if (output.answer.startsWith("Wait")) {
	        output.answer = Stream.Read(charsetName);
	      }
	      if (output.answer.startsWith("Return"))
	      {
	        String status = FaceId_Item.GetKeyValue(output.answer, "result");
	        if (status.equals("success")) {
	          return FaceId_ErrorCode.Success;
	        }
	        if (status.equals("busy")) {
	          return FaceId_ErrorCode.Busy;
	        }
	        return FaceId_ErrorCode.Failed;
	      }
	      return FaceId_ErrorCode.NotSupportedException;
	    }
	    catch (Exception e)
	    {
	      return ToErrorCode(e);
	    }
	  }
	  */
	
	  /*public void Write(byte[] buffer, int offset, int size)
			    throws Exception
			  {
			    if ((this.Protector == null) || (IsNullOrEmpty(this.Protector.getSecretKey())))
			    {
			      this.Client.getOutputStream().write(buffer, offset, size);
			    }
			    else
			    {
			      byte[] Cipher = this.Protector.encrypt(buffer, offset, size, 0);
			      this.Client.getOutputStream().write(Cipher, 0, Cipher.length);
			    }
	}*/
	
	/* public byte[] Read()
			    throws Exception
			  {
			    ByteArrayOutputStream memoryStream = null;
			    try
			    {
			      int ReceiveBufferSize = this.Client.getReceiveBufferSize();
			      memoryStream = new ByteArrayOutputStream(ReceiveBufferSize);
			      
			      InputStream netStream = this.Client.getInputStream();
			      int ReceiveBytes = 0;
			      byte[] buffer = new byte[ReceiveBufferSize];
			      int numberOfBytesRead;
			      for (;;)
			      {
			        numberOfBytesRead = netStream.read(buffer);
			        if (numberOfBytesRead <= 0) {
			          throw new SocketException();
			        }
			        if ((this.Protector == null) || (IsNullOrEmpty(this.Protector.getSecretKey())))
			        {
			          int EndIndex = ArrayUtils.IndexOf(buffer, (byte)41, 0, numberOfBytesRead);
			          if (EndIndex == -1)
			          {
			            memoryStream.write(buffer, 0, numberOfBytesRead);
			          }
			          else
			          {
			            memoryStream.write(buffer, 0, EndIndex + 1);
			            break;
			          }
			        }
			        else
			        {
			          byte[] Octets = this.Protector.decrypt(buffer, 0, numberOfBytesRead, ReceiveBytes);
			          ReceiveBytes += numberOfBytesRead;
			          

			          int EndIndex = ArrayUtils.IndexOf(Octets, (byte)41, 0, Octets.length);
			          if (EndIndex == -1)
			          {
			            memoryStream.write(Octets, 0, Octets.length);
			          }
			          else
			          {
			            memoryStream.write(Octets, 0, EndIndex + 1);
			            break;
			          }
			        }
			      }
			      return memoryStream.size() > 0 ? memoryStream.toByteArray() : null;
			    }
			    finally
			    {
			      if (memoryStream != null) {
			        memoryStream.close();
			      }
			    }
			  }*/


}
