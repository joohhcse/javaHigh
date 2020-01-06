package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T03_ArrayListTest {
	public static void main(String[] args) {
		//문제 ) 5명의 사람 이름을 입력하여 ArrayList에 저장하고 이중에 '김'씨 성의 이름을 출력하시오
		// 단, 입력은 Scanner를 이용하여 입력받는다.
		
		List<String> list1 = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);

//		for(int i =  0 ; i < 5 ; i++)
//		{
//			System.out.println("입력하세요 ! >> ");
//			String strInput = sc.nextLine();
//			list1.add(strInput);
//		}
		
		list1.add("김지선");
		list1.add("이누리");
		list1.add("정대석");
		list1.add("박종민");
		list1.add("정재영");

		
		for(int j = 0 ; j < list1.size() ; j++)
		{
			String name = list1.get(j);
			
			if(name.indexOf("김") == 0)
				System.out.println(name);

		}


		
	}
}
