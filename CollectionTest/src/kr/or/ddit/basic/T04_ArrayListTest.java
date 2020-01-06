package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T04_ArrayListTest {

	public static void main(String[] args) {
		
		// 문제1) 5명의 별명을 입력하여 ArrayList에 저장하고 별명의 길이가 제일 긴 별명을 출력하시오
		//	단, 별명의 길이는 모두 다르게 입력한다.

		// 문제2) 문제1에서 별명의 길이가 같은 것을 여러개 입력했을 때도 처리되도록 하시오

//		List<String> NickList = new ArrayList<String>();
//		
//		Scanner sc = new Scanner(System.in);
//
//		for(int i =  0 ; i < 5 ; i++)
//		{
//			System.out.println("입력하세요 ! >> ");
//			String strInput = sc.nextLine();
//			NickList.add(strInput);
//		}
//		
//		int len = 0;
//		int lenMax = 0;
//		int indexMax = 0;
//		
//		for(int j = 0 ; j < NickList.size() ; j++)
//		{
//			String name = NickList.get(j);
//			
//			len = name.length();
//			if(lenMax < len)
//			{
//				lenMax = len;
//				indexMax = j;
//			}
//
//		}
//		
//		System.out.println(NickList.get(indexMax));
		

	//쌤 답 > 
	Scanner scan = new Scanner(System.in);
	List<String> aliasList = new ArrayList<String>();
		
	System.out.println("별명 5개를 입력하세요");
	for(int i=1; i<=5; i++)
	{
		System.out.print(i + "번째 별명 : ");
		String alias = scan.next();
		aliasList.add(alias);
	}
	
	// maxLen 은 제일 긴 별명의 길이를 저장하는 변수
	int maxLen = aliasList.get(0).length();
	for(int i = 1 ; i < aliasList.size(); i++)
	{
		if(maxLen < aliasList.get(i).length())
		{
			maxLen = aliasList.get(i).length();
		}
	}
	
	System.out.println("제일 긴 별명들");
	for(int i = 0 ; i < aliasList.size(); i++)
	{
		if(maxLen == aliasList.get(i).length())
		{
			System.out.println( aliasList.get(i));
		}
	}
	
		
	}
}
