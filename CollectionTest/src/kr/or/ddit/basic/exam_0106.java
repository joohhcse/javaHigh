package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class exam_0106 {

	public static void main(String[] args) {
		
	}
	
}

class Student  {
	
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
}

class Descending implements Comparator<String> {
	
	@Override
	public int compare(String str1, String str2) {
		 return str1.compareTo(str2) * -1;			//default : ASC
	}
	
}



