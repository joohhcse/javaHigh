package kr.or.ddit.creational.singleFactory;

public class FactoryPatternDemo {
	public static void main(String[] args) {
		
		// 팩토리 객체 생성
		ShapeFactory shapeFactory = new ShapeFactory();
		
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		shape1.draw();
		
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		shape2.draw();
		
		Shape shape3 = shapeFactory.getShape("SQUARE");
		shape3.draw();
		
	}
}
