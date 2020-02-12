package kr.or.ddit.creational.singleFactory;

public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Rectangle 그리기 호출됨");
	}
}
