package kr.or.ddit.creational.builder;

public class BuilderPatternDemo {
	public static void main(String[] args) {
		MealBuilder mealBuilder = new MealBuilder();
		
		Meal vegMeal = mealBuilder.prepareVegMeal();
		System.out.println("채식주의자 식사");
		vegMeal.showItems();
		System.out.println("총 비용 : " + vegMeal.getCost());
		
		Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
		System.out.println("\n\n 비채식주의자용 식사");
		nonVegMeal.showItems();
		System.out.println("총 비용 : " + nonVegMeal.getCost());
		
	}
}
