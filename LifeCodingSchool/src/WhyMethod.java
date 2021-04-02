
public class WhyMethod {

	//static 사용 결정은 공용 용도인지 아닌지로 판단
	//static을 사용한다는 것은 다른 객체들이 모두 같은 곳(메모리 할당)을 보게 하는 역할을 하기 때문에
	//여러곳에서 함께 사용할 목적이라면 static을 붙여주는 것이 적절하다.
	public static void main(String[] args) {
		
		//1000000000
		printTwoTimes("a", "-");
		
		//1000000000
		printTwoTimes("a", "*");
		
		//1000000000
		printTwoTimes("a", "&");		
		printTwoTimes("b", "!");		
	}
									 //매개변수(parameter) : 사용자가 입력한 값을 메소드 안으로 흘려 보내주는 매개자라는 의미
	public static void printTwoTimes(String text, String delimiter) {	//delimiter는 구분자를 의미(단순 변수명으로 사용한 것임)
		
		System.out.println(delimiter);
		System.out.println(text);
		System.out.println(text);

	}
	
}
