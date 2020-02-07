package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * 소켓에서 메시지를 받아서 화면에 출력하는 역할을 담당함
 */
public class Receiver extends Thread{
	Socket socket;
	DataInputStream dis;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			dis = new DataInputStream(socket.getInputStream());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dis != null)
		{
			try {
				System.out.println(dis.readUTF());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
