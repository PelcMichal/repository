package ejb;

import javax.ejb.Stateless;

@Stateless
public class MyCalculator {
	private int a,b,c;

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void add() {
		c = a+b;
	}
	
}
