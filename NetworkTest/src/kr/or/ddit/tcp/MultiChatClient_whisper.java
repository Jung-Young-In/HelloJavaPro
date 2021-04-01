package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class MultiChatClient_whisper {

	Scanner scan = new Scanner(System.in);
	private String nickName; 	//대화명
	
	//프로그램 시작
	public void startClient() {
		//대화명 입력 받는 부분
		System.out.print("대화명 >> ");
		nickName = scan.next();
		
		//socket 접속
		Socket socket = null;
		
		try {
			socket = new Socket("192,168.43.43", 7777);
			
			System.out.println("서버에 연결되었습니다.");

			// 송신용 스레드 생성
			ClientSender sender = new ClientSender(socket, nickName);
			
			//수신용 스레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class ClientSender extends Thread {
		Socket socket;
		DataOutputStream dos;
		String name;
		Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				//시작할때 자신의 대화명을 서버로 전송하는 부분
				if(dos != null) {
					dos.writeUTF(name);
				}
				
				//키보드로 입력받은 메시지를 서버로 전송하는 부분
				while(dos != null) {
					dos.writeUTF(scan.nextLine());
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//수신용 스레드
	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					//서버에서 수신한 메시지 출력하는 부분
					System.out.println(dis.readUTF());
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient_whisper().startClient();
	}
}
