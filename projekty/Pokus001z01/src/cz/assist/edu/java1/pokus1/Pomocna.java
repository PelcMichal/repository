package cz.assist.edu.java1.pokus1;

import java.util.ArrayList;
import java.util.Scanner;

public class Pomocna {
	private ArrayList a = new ArrayList<String>();
	Scanner myObj = new Scanner(System.in);
	public void addGreeting(String b)
	{
		a.add(b);
	}
	
	public String getGreeting()
	{
		if (0==a.size())
			return "";
		return (String) a.get((int)(Math.random()*a.size()));
	}
	public int pocetPozdravu()
	{
		return a.size();
	}
	public boolean start()
	{
		int i = 3;
		System.out.println("Co chces delat? 0 = konec 1 = pridat pozdrav 2 = ziskat pozdrav");
		
		try {
		i = myObj.nextInt();
		
		switch(i)
		{
		case 0:return false;
		case 1:	{
					System.out.println("pridej pozdrav");
					String t = myObj.next(); 
					addGreeting(t);
				start();}
		case 2: {System.out.println(getGreeting());start();}
		default: start();
		}
		}catch(Exception InputMismatchException) { return true; }
		return  false;
	}
}
