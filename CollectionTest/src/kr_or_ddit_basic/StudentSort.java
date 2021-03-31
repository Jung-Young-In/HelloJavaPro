package kr_or_ddit_basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StudentSort implements Comparable<StudentSort>{
	
	private String sNum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;
	
	StudentSort(){
		
	}
	
	StudentSort(String sNum, String name, int kor, int eng, int math) {
		super();
		this.sNum = sNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;
		this.rank = 1;
	}
	
	@Override
	public String toString() {
		return "StudentSort [sNum=" + sNum + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", rank=" + rank + "]";
	}
	
	
	public String getsNum() {
		return sNum;
	}
	
	
	public void setsNum(String sNum) {
		this.sNum = sNum;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getKor() {
		return kor;
	}
	
	public void setKor(int kor) {
		this.kor = kor;
	}
	
	public int getEng() {
		return eng;
	}
	
	public void setEng(int eng) {
		this.eng = eng;
	}
	
	public int getMath() {
		return math;
	}
	
	public void setMath(int math) {
		this.math = math;
	}
	
	
	public int getTotal() {
		return total;
	}
	
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	public int getRank() {
		return rank;
	}
	
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public int compareTo(StudentSort stu) {
		return this.getsNum().compareTo(stu.getsNum());
	}
	public static void setRanked(List<StudentSort> studentList) {
		for(StudentSort st1 : studentList) {
			int rank = st1.rank;
			for(StudentSort st2 : studentList) {
				if(st1.getTotal() < st2.getTotal()) {
					rank++;
				}
			}
			st1.setRank(rank);
		}
	}
	

	public static void main(String[] args) {
		
		List<StudentSort> studentList = new ArrayList<StudentSort>();
		studentList.add(new StudentSort("170123", "그레이", 90, 90, 80));
		studentList.add(new StudentSort("180920", "놀란", 90, 75, 84));
		studentList.add(new StudentSort("190302", "더글라스", 68, 95, 80));
		studentList.add(new StudentSort("174232", "레이", 76, 90, 80));
		studentList.add(new StudentSort("156543", "맷데이먼", 77, 90, 80));
		studentList.add(new StudentSort("164342", "바트", 90, 100, 65));
		studentList.add(new StudentSort("174324", "세인", 90, 90, 80));
		studentList.add(new StudentSort("184325", "에이미", 88, 90, 70));
		studentList.add(new StudentSort("167869", "존", 50, 90, 60));
		studentList.add(new StudentSort("131231", "하이디", 90, 90, 80));
		
		setRanked(studentList);
		
		System.out.println("정렬 전 : ");
		for (StudentSort stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("=================================");
		
		Collections.sort(studentList);
		
		System.out.println("학번의 오름차순으로 정렬 후 : ");
		for (StudentSort stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("=================================");
		
		Collections.sort(studentList, new SortSumDesc());
		System.out.println("총점 같을 경우 학번 정렬 후 : ");
		for (StudentSort stu : studentList) {
			System.out.println(stu);
		}
	}
}
	
class SortSumDesc implements Comparator<StudentSort>{

	@Override
	public int compare(StudentSort stu1, StudentSort stu2) {
		if (stu1.getTotal() == stu2.getTotal()) {
			return stu1.getsNum().compareTo(stu2.getsNum()) * -1;
		}else {
			return Integer.compare(stu1.getTotal(), stu2.getTotal()) * -1;
		}
	}
}
	


	

	



