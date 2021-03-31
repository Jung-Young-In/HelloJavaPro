package kr_or_ddit_basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

/* 
 *  Comparable 및 Comparator 예제
 */

public class T04_ListSortTest {

	public static void main(String[] args) {
	
		List<Member> memList = new ArrayList<Member>();
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "이순신", "010-4444-4444"));
		memList.add(new Member(6, "강감찬", "010-5555-5555"));
		memList.add(new Member(2, "일지매", "010-6666-6666"));
		
		System.out.println("정렬 전 : " );
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("==============================");
		
		Collections.sort(memList);	//정렬하기
		
		System.out.println("이름의 오름차순으로 정렬 후 ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("===============================");
	
		// 외부 정렬 기준을 이용한 정렬하기
		Collections.sort(memList, new SortNumDesc());
		System.out.println("번호의 내림차순으로 정렬 후 ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("================================");
	}
}
/*
 *  Member의 번호(num)의 내림차순으로 정렬하기
 */

class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
//		if (mem1.getNum() > mem2.getNum()) {
//			return -1;
//		}else if(mem1.getNum() == mem2.getNum()) {
//			return 0;
//		}else {
//			return 1;
//		}
		
		// Wrapper 클래스에서 제공되는 메서드를 이용하는 방법1
		// 내림차순인 경우에는 -1을 곱해준다
		return new Integer(mem1.getNum())
					.compareTo(mem2.getNum()) * -1;
	}
}

//

class Member implements Comparable<Member>{

	private int num;		//번호
	private String name;	//이름
	private String tel;		//전화번호
	//위와 같은 것을 value object(VO) 라고 함
	
	public Member(int num, String name, String tel) {	//필드를 이용한 생성자 생성(우클릭 후 source에서 generate constructor using fields 클릭하면 자동 생성)
		super();
		this.num = num;		//this는 현재 만들고 있는 객체의 멤버변수를 의미, static변수로 설정할 경우에는 클래스명. 혹은 this. 으로 받을 수 있고 static 변수가 아닌 전역변수?로 설정할 경우는 this. 으로만 받을 수 있음
		this.name = name;
		this.tel = tel;
	}
	//우클릭 후 source에서 generate getters and setters 클릭하면 자동 생성 
	//(getters and setters를 사용하지 않으려면 위에서 private이 아닌 public으로 생성하면 되나, 
	//보안이 취약하여 아무곳에서 변경할 수 있으므로 private 접근제어자를 사용하여 제한해주고 getters and setters로 값을 받고 변경해주는 것)
	//그러므로 열어두고 싶은 값만 getter and setters를 넣어 열어둠(예: 이름만 getter and setters를 설정해 주는 등)
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	//우클릭 후 source에서 generate toString 클릭하면 자동생성
	@Override
	public String toString() {		//toString을 하지 않을 경우 객체의 위치만 출력될 뿐 원하는 결과값이 출력되지 않음
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	/*
	 *  이름을 기준으로 오름차순 정렬이 되도록 설정한다
	 * 
	 */
	
	@Override
	public int compareTo(Member mem) {
		return getName().compareTo(mem.name);	//기본은 오름차순으로 정렬되고 내림차순으로 정렬하고 싶다면 -1을 곱하면 됨
	}
	
}
