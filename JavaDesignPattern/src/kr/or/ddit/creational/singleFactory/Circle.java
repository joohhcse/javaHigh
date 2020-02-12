package kr.or.ddit.creational.singleFactory;

public class Circle implements Shape{

	@Override
	public void draw() {
		System.out.println("Circle 그리기 호출됨");
	}
}
