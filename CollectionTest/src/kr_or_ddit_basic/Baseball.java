package kr_or_ddit_basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Baseball {

	int[] num = new int[3];	//난수가 저장된 배열
	int[] user;	//사용자가 입력한 값을 저장할 배열
	
	int strike;	//스트라이크 개수
	int ball;	//볼 개수
	
	Scanner scan = new Scanner(System.in);
	
	public void getRndNum() {
		
		Set<Integer> bbNumSet = new HashSet<Integer>();
		
		//Set을 이용한 3개의 난수 만들기
		while(bbNumSet.size() < 3) {
			bbNumSet.add((int)(Math.random() * 9 + 1));
		}
		
		//Set의 자료를 배열에 저장하기
		Iterator<Integer> it = bbNumSet.iterator();
		
		int i = 0;	//배열의 첨자 역할
		while(it.hasNext()) {
			num[i++] = it.next().intValue();
		}
		
		//데이터 섞기 (0번째 자료와 난수번째 자료를 교환하는 방법으로 데이터를 섞는다)
		for (int j = 1; j <= 100; j++) {
			int rnd = (int)(Math.random() * num.length);
			int temp = num[0];
			num[0] = num[rnd];
			num[rnd] = temp;
		}
	}
			
		/*
		 * 사용자로부터 3개의 정수를 입력받아 배열에 저장하는 메서드
		 * (입력한 정수들을 서로 중복되지 않게 처리)
		 */
			
		public void inputNum() {
			int n1, n2, n3;	//입력한 값을 저장할 변수
			
			do {
				System.out.print("중복되지 않는 정수 3개 입력 => ");
				n1 = scan.nextInt();
				n2 = scan.nextInt();
				n3 = scan.nextInt();
				if (n1 == n2 || n1 == n3 || n2 == n3) {
					System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시 입력하세요.");
				}
			}while(n1 == n2 || n1 == n3 || n2 == n3);
			user = new int[] {n1, n2, n3};	//입력한 값들을 배열에 저장
		}
		
		//스트라이크와 볼 판정 및 출력하는 메서드
		public void ballCount() {
			strike = 0;
			ball = 0;
			
			for (int i = 0; i < num.length; i++) {
				for (int j = 0; j < user.length; j++) {
					if(num[i] == user[j]) {
						if(i == j) {
							strike++;
						}else {
							ball++;
						}
					}
				}
			}
			
			System.out.println(user[0] + " " + user[1] + " " + user[2] + " ==> " + strike + "스트라이크 " + 
			ball + "볼");
		}
		
		//게임을 시작하는 메서드
		
		public void gameStart() {
			//난수를 만드는 메서드 호출
			getRndNum();
			
			//확인용
			System.out.println("난수값 => " + num[0] + " " + num[1] + " " + num[2]);
			
			int cnt = 0;	//몇번만에 맞췄는지 저장하는 변수
			
			do {
				cnt++;
				inputNum();	//사용자 입력 메서드 호출
				ballCount(); //볼카운트하는 메서드 호출
			}while(strike != 3);	//3 스트라이크가 될때까지 반복
			
			System.out.println(cnt + "번째 만에 맞추셨습니다.");
			}
		
		public static void main(String[] args) {
			Baseball baseball = new Baseball();
			baseball.gameStart();
	}
}
		
	
		
	
