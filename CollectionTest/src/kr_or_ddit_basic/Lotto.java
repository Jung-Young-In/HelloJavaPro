package kr_or_ddit_basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lotto {

	Scanner Scan = new Scanner(System.in);
	
	//메뉴를 출력 및 실행할 메뉴 번호 입력 받는 메서드
	
	public int menu() {
		System.out.println("===============================");
		System.out.println("LOTTO 프로그램");
		System.out.println("===============================");
		System.out.println("[1] LOTTO 구입");
		System.out.println("[2] 프로그램 종료");
		System.out.println("===============================");
		System.out.println("[메뉴 선택]");
		int num = Scan.nextInt();
		return num;
	}
	
	//프로그램 시작 메서드
	
	public void lottoStart() {
		while(true) {
			int select = menu();
			switch (select) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println();
				System.out.println("감사합니다");
				return;
			default:
				System.out.println("잘못된 번호 입력입니다. 1 또는 2를 입력하세요.");
			}
		}
	}
	
	//로또 구매 메서드
	public void buyLotto() {
		System.out.println();
		System.out.println("LOTTO 구입 시작");
		System.out.println();
		System.out.println("1000원에 로또 번호 하나 입니다.");
		System.out.println("금액 입력 : ");
		int cash = Scan.nextInt();
		
		if(cash < 1000) {
			System.out.println("입력 금액이 적습니다. 구입에 실패하였습니다.");
			return;
		}else if (cash >= 100001) {
			System.out.println("입력 금액을 초과하였습니다. 구입에 실패하였습니다.");
			return;
		}
		
		getLotto(cash);	//로또번호 생성하는 메서드 호출
		
		System.out.println("받은 금액은 " + cash + "원이며, 거스름돈은 " + (cash%1000) + "원 입니다.");
		}
	
	//금액에 맞는 로또 번호 생성
	
	public void getLotto(int cash) {
		Set<Integer> lottoSet = new HashSet<>();
		
		int amo = cash / 1000;	//로또 개수
		
		System.out.println();
		System.out.println("로또 번호는 아래와 같습니다.");
		
		for (int i = 1; i < amo+1; i++) {
			while(lottoSet.size() < 6) {
				lottoSet.add((int)(Math.random() * 45 + 1));
			}
			ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
			
			Collections.sort(lottoList);
			System.out.println("로또번호" + i + " : " + lottoList);
			lottoSet.clear();
	}
}
	
	public static void main(String[] args) {
		new Lotto().lottoStart();
	}
}
