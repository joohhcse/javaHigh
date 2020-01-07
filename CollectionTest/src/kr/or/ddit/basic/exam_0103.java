package kr.or.ddit.basic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class exam_0103 {

	public static void main(String[] args) {
		
		Vector l1 = new Vector<>();
		
		//add
		System.out.println("<add>");
		l1.add("aaa");
		l1.add(1, "bbb");
		System.out.println(l1);
		System.out.println();
		
		//get(i)
		System.out.println("<get>");
		System.out.println(l1.get(0));
		System.out.println();
		
		//indexOf
		System.out.println("<indexOf>");
		System.out.println(l1.indexOf("aaa"));
		System.out.println();
		
		//lastIndexOf
		System.out.println("<lastIndexOf>");
		System.out.println(l1.lastIndexOf("bbb"));
		System.out.println();
		
		//listIterator
		System.out.println("<listIterator>");
		System.out.println(l1.listIterator());
		System.out.println(l1.listIterator(0));
		System.out.println();
		
		//remove
		System.out.println("<remove>");
		System.out.println(l1.remove(1));
		System.out.println();
		
		//set
		System.out.println("<set>");
		l1.set(0, "ccc");
		System.out.println(l1);
		System.out.println();
		
		//sort
		System.out.println("<sort>");
		List<String> list = new ArrayList<>();
		list.add("d1");
		list.add("b1");
		list.add("e1");
		list.add("a1");
		list.add("c1");

		System.out.println("before sort");
		System.out.println(list);
		
		System.out.println("after sort");
		Collections.sort(list);
		System.out.println(list);
		System.out.println();
		

		
		//subList
		l1.add(1, "ddd");
		l1.add(2, "eee");
		System.out.println("<subList>");
		System.out.println(l1.subList(0, 2));
		
		
	}
	
}
