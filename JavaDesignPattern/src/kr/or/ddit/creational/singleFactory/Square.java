package kr.or.ddit.creational.singleFactory;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Square 그리기 호출됨");
	}
}
