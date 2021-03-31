package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력 받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초 안에 입력이 없음녀 게임을 진것으로 처리한다. 
 * 
 * 5초 안에 입력이 완료되면 승패를 출력한다. 
 * 
 * 결과예시)
 * === 결과 ===
 * 컴퓨터 : 가위
 *  나     : 바위
 * 결  과  : 당신이 이겼습니다. 
 */

public class T07_ThreadGame {
	
	public static boolean inputcheck = false;
	
	public static void main(String[] args) {

		GameTime gt = new GameTime();
		
		String[] rcp = {"가위", "바위", "보"};
		int ran = (int)(Math.random()*3);
		String com = rcp[ran];
		
		String man = null;
		
		gt.start();
		
		do {
			man = JOptionPane.showInputDialog("가위 바위 보 중 하나를 입력하세요");
		} while (!man.equals("가위") && !man.equals("바위") && !man.equals("보"));
		
		inputcheck = true;
		
		String result = "";
		
		if (man.equals(com)) {
			result = "비겼습니다.";
		}else if ((man.equals("가위") && com.equals("보")) 
						|| (man.equals("바위") && com.equals("가위")) 
						|| (man.equals("보") && com.equals("바위"))){
					result = "당신이 이겼습니다.";
		}else {
			result = "당신이 졌습니다.";
		}
		
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터  : " + com);
		System.out.println(" 나 : " + man);
		System.out.println("결 과 : " + result);
	}
}


class GameTime extends Thread {
	
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (T07_ThreadGame.inputcheck == true) {
				return;
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println("5초가 모두 지났습니다. 당신이 패하였습니다.");
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
	}
}