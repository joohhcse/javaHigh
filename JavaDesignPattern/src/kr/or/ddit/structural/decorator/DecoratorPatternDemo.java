package kr.or.ddit.structural.decorator;

public class DecoratorPatternDemo {
	public static void main(String[] args) {
		Shape circle = new Circle();
		
		Shape redCircle = new RedShapeDecorator(new Circle());
		
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		
		System.out.println("일반 원 그리기");
		circle.draw();
		
		System.out.println("경계선이 빨간 색 원 그리기 시작>>>");
		redCircle.draw();
		
		System.out.println("경계선이 빨간 색 사각형 그리기 시작>>>");
		redRectangle.draw();
	}
}
