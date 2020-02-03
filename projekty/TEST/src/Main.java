
import java.math.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String s1 = "a";						//hodnota == a
		String s2 = new String("a");			//hodnota == odkaz na hodnotou stringu kter8 je a
		
		
		System.out.println(s1 == "a");
		System.out.println(s2 == "a");
		System.out.println(s1 == s2);
		System.out.println("a" == "a");*/
		Class a = new Class(1,"a"); 
		Class b = new Class(1,"a"); 
		Class c = new Class(1,"b"); 
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
	}

}
