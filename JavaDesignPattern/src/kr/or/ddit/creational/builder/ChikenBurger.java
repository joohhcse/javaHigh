package kr.or.ddit.creational.builder;

public class ChikenBurger extends Burger {

	@Override
	public String name() {
		return "치킨버거";
	}

	@Override
	public float price() {
		return 4.4f;
	}
	
}
