package kr.or.ddit.behavioral.iterator;

public class NameRepository implements Container {

	public String names[] = {"가","나","다","라","마","바","사","아"};
	
	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}
	
	private class NameIterator implements Iterator {
		int index;
		
		@Override
		public boolean hasNext() {
			if(index < names.length)
				return true;
			
			return false;
		}
		
		@Override
		public Object next() {
			if(this.hasNext())
				return names[index++];
			
			return null;
		}
	}
	
}
