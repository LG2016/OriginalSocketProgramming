package OriginalSocketProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class SeverThread extends Thread {
             Socket socket;
	
             public SeverThread(Socket socket) {
				this.socket=socket;
			}
             @Override
            public void run() {
     
                InputStream is = null;
         		InputStream in=null;
				InputStreamReader isr=null;
				BufferedReader br=null;
				OutputStream os=null;
				PrintWriter pw=null;
				InetAddress Saddress=null;
				InetAddress Daddress=null;
				try {
					is = socket.getInputStream();
					isr = new InputStreamReader(is);
					br = new BufferedReader(isr);
					String inf=null;
				    inf=br.readLine();
				    System.out.println("客户端："+inf);
				    if(inf.equals("Applications"))
				    {
						socket.shutdownInput();
						os = socket.getOutputStream();
						pw = new PrintWriter(os);
						pw.write("欢迎你访问服务端\n");
						pw.write("Date：\n");
						pw.write(inf);
						Date dateTime=new Date();
						String str=dateTime.toString();
						char[] strc=str.toCharArray();
						pw.write("\n");
						pw.write(strc);
						pw.flush();
					}else
					if(inf.equals("Transportation"))
					{
						socket.shutdownInput();
						os = socket.getOutputStream();
						pw = new PrintWriter(os);
						pw.write("欢迎你访问服务端\n");
						int portD=socket.getLocalPort();
						int portS=socket.getPort();
						String pontds=String.valueOf(portD);
						String pontss=String.valueOf(portS);
						pw.write("Src Port：");
						pw.write(pontss);
						pw.write("\nDest Port：");
						pw.write(pontds);
						pw.flush();
					}else
						if(inf.equals("Network"))
						{
							socket.shutdownInput();
							os = socket.getOutputStream();
							pw = new PrintWriter(os);
							pw.write("服务器：欢迎你访问服务端\n");
							 Saddress=socket.getInetAddress();
							String ipS=Saddress.getHostAddress();
							char[] ipSC=ipS.toCharArray();
							 Daddress=InetAddress.getLocalHost();
							String ipD=Daddress.getHostAddress();
							char[] ipDC=ipD.toCharArray();
 							pw.write("Src IP：");
							pw.write(ipSC);
							pw.write("\nDest IP：");
							pw.write(ipDC);
							pw.flush();
						}else
						{
							socket.shutdownInput();
							os = socket.getOutputStream();
							pw = new PrintWriter(os);
							pw.write("服务器：欢迎你访问服务端\n");
							String str="Please input:Applications | Transportation | Network";
							char[] strc=str.toCharArray();
							pw.write(strc);
							pw.flush();
						}
				} catch (IOException e) {
					e.printStackTrace();
				}finally
				{
					//关闭资源
					try {
						if(pw!=null) pw.close();
						if(os!=null) os.close();
						if(socket!=null) socket.close();
						if(br!=null) br.close();
						if(isr!=null) isr.close();
						if(is!=null) is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
         		
         	
            }
}
