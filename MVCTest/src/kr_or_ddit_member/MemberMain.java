package kr_or_ddit_member;
import java.util.List;
import java.util.Scanner;

import kr_or_ddit_member.service.IMemberService;
import kr_or_ddit_member.service.MemberServiceImpl;
import kr_or_ddit_member.vo.MemberVO;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class MemberMain {

	//서비스객체 멤버변수 선언
	private IMemberService memService;
	
	public MemberMain() {
		memService = new MemberServiceImpl();
	}
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("======================");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  [1] 자료 입력");
		System.out.println("  [2] 자료 삭제");
		System.out.println("  [3] 자료 수정");
		System.out.println("  [4] 전체 자료 출력");
		System.out.println("  [5] 작업 끝.");
		System.out.println("======================");
		System.out.print("원하는 작업 선택 > ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업 종료");
					break;
				default :
					System.out.println("번호를 잘못 입력하였습니다. 다시 입력해주세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 회원정보를 삭제하는 메서드(입력받은 회원ID를 이용하여 삭제한다)
	 */
	private void deleteMember() {
		
		System.out.println();
		System.out.println("삭제할 회원 ID를 입력해 주세요.");
		System.out.println("회원 ID > ");
		String memId = scan.next();
		
		int cnt = memService.deleteMember(memId);
	
		if(cnt > 0) {
			System.out.println(memId + "회원 정보 삭제 성공");
		}else {
			System.out.println(memId + "회원 정보 삭제 실패");
		}
	}

	
	/**
	 * 회원정보를 수정하는 메서드
	 */
	private void updateMember() {

		boolean chk = false;
		String memId = null;
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력해 주세요.");
			System.out.println("회원 ID > ");
			memId = scan.next();
			
			chk = memService.checkMember(memId);
			
			if(chk == false) {
				System.out.println("회원 ID가 " + memId + "인 회원이 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		}while(chk == false);
		
		System.out.println("회원 이름 > ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 > ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		System.out.println("회원 주소 > ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.updateMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 정보 수정 성공");
		}else {
			System.out.println(memId + "회원 정보 수정 실패");
		}
	}


	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayAll() {
		System.out.println();
		System.out.println("===========================================");
		System.out.println(" ID\t이 름\t전화번호\t주 소");
		System.out.println("===========================================");
		
		List<MemberVO> memList = memService.getAllMemberList();
		
		for (MemberVO mv : memList) {
			System.out.println(mv.getMemId() + "\t" + mv.getMemName() 
								+ "\t" + mv.getMemTel() + "\t" + mv.getMemAddr());
		}
		
		System.out.println("===========================================");
		System.out.println("출력 완료");
	}

	private void insertMember() {
		
		boolean chk = false;
		String memId = null;
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력해 주세요.");
			System.out.println("회원 ID > ");
			memId = scan.next();
			
			chk = memService.checkMember(memId);
			
			if(chk == true) {
				System.out.println("회원 ID가 " + memId + "인 회원이 이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		}while(chk == true);
		
		System.out.println("회원 이름 > ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 > ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		System.out.println("회원 주소 > ");
		String memAddr = scan.nextLine();

		MemberVO mv = new MemberVO();
		
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.insertMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 추가 성공");
		}else {
			System.out.println(memId + "회원 추가 실패");
		}
	}

	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}

}






