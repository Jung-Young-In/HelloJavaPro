package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 호텔 관리 프로그램(Map사용)
 * 종료시 파일 저장
 * 실행시 파일로부터 데이터 불러오기
 */

public class hotelMapIOHW {
	
	private Scanner sc = new Scanner(System.in);
	
	private Map<String, Hotel> manage = new HashMap<String, Hotel>();
	
	
	/**
	 * 객체를 스트림 형식으로 파일에 저장하는 메서드
	 */
	public void save() {
		//객체를 파일에 저장
		try {
			//출력 스트림 객체 생성
			ObjectOutputStream oos 
				= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/hotelManage.txt")));
			
			oos.writeObject(manage);
			System.out.println("쓰기 작업 완료");
			
			oos.close(); 	//완료 후 스트림 닫기
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 스트림 형식으로 저장한 객체 읽어오는 메서드
	 */
	public void load() {
		//저장 객체 읽어오기
		try {
			//입력 스트림 객체 생성
			ObjectInputStream ois 
				= new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/hotelManage.txt")));
			
			Object obj = null;
			
			try {
				while((obj = ois.readObject()) != null) {
				
					//읽어온 데이터를 원래 객체형으로 변환 후 사용
					Map<String, Hotel> ho = (Map<String, Hotel>) obj;
					manage = ho;
				}
				
				System.out.println("출력 작업 완료");
				
				ois.close(); 	//완료 후 스트림 닫기
				
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		} catch (IOException ex) {
			//더이상 읽어올 객체가 없으면 예외 발생
			ex.printStackTrace();
			
		}
	}
	
	/**
	 * 메뉴 선택 메서드
	 */
	private void menu() {
		System.out.println("********************************");
		System.out.println("원하시는 업무를 선택해 주세요");
		System.out.println("[1]체크인 [2]체크아웃 [3]객실상태 [4]종료");
		System.out.println("********************************");
		System.out.println();
	}
	
	/**
	 * 시작화면 메서드
	 */
	private void start() {
		load();
		System.out.println("********************************");
		System.out.println("HOTEL OPEN");
		System.out.println("********************************");
		System.out.println();
		
		
		int input = 0;
		
		while(true) {
			
			sc = new Scanner(System.in);
			
			menu();
			System.out.print("업무선택> ");
			
			input = sc.nextInt();
			
			switch (input) {
			case 1:
				checkIn();
				break;

			case 2:
				checkOut();
				break;

			case 3:
				roomState();
				break;
			
			case 4:
				System.out.println("업무 종료");
				save();
				System.exit(0);
				break;
			default:
				System.out.println("[1~4] 사이의 숫자만 입력 가능");
			}
		}
	}

	/**
	 * 체크인 메서드
	 */
	private void checkIn() {

		System.out.println("체크인을 원하시는 방을 선택해 주세요.");
		System.out.print("방번호 입력> ");
		String roomNum = sc.next();
		
		System.out.println("체크인을 원하시는 이름을 입력해 주세요.");
		System.out.print("이름> ");
		String userName = sc.next();
		
		if (manage.get(roomNum) != null) {
			System.out.println(roomNum + "번 방에는 이미 투숙객이 존재합니다.");
			checkIn();
		} else {
			//Map에 체크인 정보 추가 => key값은 방번호
			manage.put(roomNum, new Hotel(roomNum, userName));
			
			System.out.println(roomNum + "번 방에 체크인이 완료되었습니다.");
		}
	}

	/**
	 * 체크아웃 메서드
	 */
	private void checkOut() {

		System.out.println();
		System.out.println("체크아웃을 원하시는 방을 선택해 주세요");
		System.out.print("방번호 입력> ");
		String roomNum = sc.next();
		
		//remove(key) : 삭제 성공시 삭제된 value값 반환, 실패시 null 반환
		if (manage.remove(roomNum) == null) {
			System.out.println("현재 사용중인 방이 아닙니다.");
		} else {
			System.out.println(roomNum + "번 방이 체크아웃 되었습니다.");
		}
	}

	/**
	 * 객실상태 조회 메서드
	 */
	private void roomState() {
		//keySet() 메서드 이용하여 key값을 읽어와 자료 출력
		//Hotel Map의 key값들만 읽어와 Set형으로 반환
		Set<String> roomSet = manage.keySet();
		
		System.out.println("================================");
		System.out.println("<<현재 객실 상태>>");
		System.out.println("================================");
		System.out.println("방번호\t투숙객");
		System.out.println("================================");

		if (roomSet.size() == 0) {
			System.out.println("예약된 객실이 없습니다.");
		} else {
			Iterator<String> it = roomSet.iterator();
			while (it.hasNext()) {	//hasNext boolean값 리턴(true면 next()메서드로 값을 가져옴)
				String roomNum = it.next();		//key값 가져오기
				Hotel ht = manage.get(roomNum);
				System.out.println(ht.getRoomNum() + "\t" + ht.getUserName());	//get(key) ==> key에 해당한는 value값을 가져옴
				System.out.println("=======================================");
				
			}
		}
	}
	/**
	 * 메인메서드
	 */
	public static void main(String[] args) {
		hotelMapIOHW hm = new hotelMapIOHW();
		hm.start();
	}
}
/**
 * Hotel VO
 */

class Hotel implements Serializable {	//직렬화 하기 위해 Serializable 사용
	
	private String roomNum;	//방번호
	private String userName;	//고객이름
	
	public Hotel(String roomNum, String userName) {
		super();
		this.roomNum = roomNum;
		this.userName = userName;
	}

	//getter & setter 생성
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	@Override
	public String toString() {
		return "Hotel [roomNum=" + roomNum + ", userName=" + userName + "]";
		}
	}

	