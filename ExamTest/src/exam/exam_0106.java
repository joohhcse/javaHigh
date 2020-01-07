package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class exam_0106 {

	public static void main(String[] args) {
		
		List<Student> stuList = new ArrayList<>();
		
		stuList.add(new Student("202001", "홍길동", 70, 77, 88));
		stuList.add(new Student("202004", "변학도", 81, 91, 95));
		stuList.add(new Student("202006", "성춘향", 50, 60, 70));
		stuList.add(new Student("202003", "이순신", 73, 83, 93));
		stuList.add(new Student("202005", "강감찬", 88, 77, 66));
		stuList.add(new Student("202002", "일지매", 90, 80, 70));
		
		
		System.out.println("정렬전");
		
		for(Student stu : stuList)
		{
			System.out.println(stu);
		}
		System.out.println("============================================");
		
		Collections.sort(stuList);  //정렬하기
		
		System.out.println("학번의 오른차순으로 정렬 후 ");
		
		for(Student stu : stuList)
		{
			System.out.println(stu);
		}
		System.out.println("============================================");
		
		// 외부  정렬 기준을 이용한 정렬하기
		Collections.sort(stuList, new SortScoreDesc());
		
		System.out.println("총점의 내림차순으로 정렬 후 ");
		
		for(Student stu : stuList)
		{
			System.out.println(stu);
		}
		System.out.println("============================================");

	}
	
}

class Student implements Comparable<Student>{
	
	String num;
	String name;
	int korScore;
	int engScore;
	int mathScore;
	
	public Student(String num, String name, int korScore, int engScore, int mathScore) {
		super();
		this.num = num;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}
	
	@Override
	public int compareTo(Student stu) {
		return getNum().compareTo(stu.getNum());
	}
	
	@Override
	public String toString() {
		return "Student [num = " + num + ", name=" + name + ", totalScore=" + getTotal() + "]";
	}
	
	public int getTotal()
	{
		return this.korScore + this.engScore + this.mathScore;
	}
}

//class Descending implements Comparator<String> {
//	
//	@Override
//	public int compare(String str1, String str2) {
//		 return str1.compareTo(str2) * -1;			//default : ASC
//	}
//	
//}


class SortScoreDesc implements Comparator<Student> {

	@Override
	public int compare(Student stu1, Student stu2) {
		
		if(stu1.getTotal() > stu2.getTotal())
		{
			return -1;		//Descending
		}
		else if(stu1.getTotal() == stu2.getTotal())
		{
			return 0;
		}
		else
		{
			return 1;		//Ascending
		}
		
		//Wrapper 클래스에서 제공하는 메서드를 이용하는 방법1
		//내림차순일 경우에는 -1 을 곱해준다.
		//return Integer.compare(mem1.getNum(),  mem2.getNum()) * -1;
		
		//Wrapper 클래스에서 제공하는 메서드를 이용하는 방법2
		//return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
	}
}


