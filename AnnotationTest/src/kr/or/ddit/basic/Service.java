package kr.or.ddit.basic;

/**
 * 어노테이션 사용 예제
 */

public class Service {
	
	@PrintAnnotation
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "%")	//타입값이 value 하나만 가지고 있다면 "value=" 없이 "%"만 사용해도 가능
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "#", count = 30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
}
