package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
   /* 	final String paramName = "result";
    	final String urlS = "http://192.168.1.111:9999/WebService/kalkulacka/sectixml";
    	final String cislo1Name = "cislo1"; int cislo1 = 10;
    	final String cislo2Name = "cislo2"; int cislo2 = 50;
    	while(true)
    	{
    		try (Scanner inp = new Scanner(System.in);){
    			
    			System.out.println("Enter first number");
    			cislo1 = inp.nextInt();
    	        System.out.println("Enter second number");
    	        cislo2 = inp.nextInt();
    			
    			
            	URL url = new URL(urlS+"/"+cislo1+"&"+cislo2);
            	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            	conn.setRequestMethod("GET");
            	conn.setRequestProperty("Accept", "TEXT/XML");
            	if (conn.getResponseCode() != 200) {
                	throw new RuntimeException("Failed : HTTP Error code : "
                			+ conn.getResponseCode());
            	}
            	InputStreamReader in = new InputStreamReader(conn.getInputStream());
            	BufferedReader br = new BufferedReader(in);
            	String s = br.readLine();
            	System.out.println(cislo1+"+"+cislo2+"="+s.substring(s.indexOf("<"+paramName+">")+paramName.length()+2, s.indexOf("</"+paramName+">")));
            	//System.out.println(s);
        	} catch (java.util.InputMismatchException e) {
        		break;
			} catch (Exception e) {
				System.out.println("Exception in NetClientGet:- " + e);
        		break;
			}
    	}*/
    	
    	final String totalName = "soucet";
    	final String timeName = "time";
    	final String urlS = "http://localhost:8080/webservice002/hello";
    	final String cislo1Name = "cislo1";
    	int cislo1 = 10;
    	final String cislo2Name = "cislo2";
    	int cislo2  = 50;
    	Scanner inp = new Scanner(System.in);
    	while(true)
    	{
    		try {
    			System.out.println("Enter first number");
    			cislo1 = inp.nextInt();
    	        System.out.println("Enter second number");
    	        cislo2 = inp.nextInt();
    			
    			
            	URL url = new URL(urlS+"?"+cislo1Name+"="+cislo1+"&"+cislo2Name+"="+cislo2);
            	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            	conn.setRequestMethod("GET");
            	conn.setRequestProperty("Accept", "TEXT/XML");
            	if (conn.getResponseCode() != 200) {
                	throw new RuntimeException("Failed : HTTP Error code : "
                			+ conn.getResponseCode());
            	}
            	InputStreamReader in = new InputStreamReader(conn.getInputStream());
            	BufferedReader br = new BufferedReader(in);
            	String s = br.readLine();
            	int i =0;
            	while (s.indexOf("</"+totalName+">",i)<s.lastIndexOf("</"+totalName+">"))
            	{
            		System.out.println(s.substring(s.indexOf("<"+timeName+">",i)+timeName.length()+2, s.indexOf("</"+timeName+">",i))+"  \t"+s.substring(s.indexOf("<"+cislo1Name+">",i)+cislo1Name.length()+2, s.indexOf("</"+cislo1Name+">",i))+"+"+s.substring(s.indexOf("<"+cislo2Name+">",i)+cislo2Name.length()+2, s.indexOf("</"+cislo2Name+">",i))+"="+s.substring(s.indexOf("<"+totalName+">",i)+totalName.length()+2, s.indexOf("</"+totalName+">",i)));
            		i=s.indexOf("</"+totalName+">",i)+totalName.length()+3;
            	}
            	System.out.println("\nLatest\n"+s.substring(s.indexOf("<"+timeName+">",i)+timeName.length()+2, s.indexOf("</"+timeName+">",i))+"  \t"+s.substring(s.indexOf("<"+cislo1Name+">",i)+cislo1Name.length()+2, s.indexOf("</"+cislo1Name+">",i))+"+"+s.substring(s.indexOf("<"+cislo2Name+">",i)+cislo2Name.length()+2, s.indexOf("</"+cislo2Name+">",i))+"="+s.substring(s.indexOf("<"+totalName+">")+totalName.length()+2, s.indexOf("</"+totalName+">")));
           
        	} catch (java.util.InputMismatchException e) {
        		break;
			} catch (Exception e) {
				System.out.println("Exception in NetClientGet:- " + e);e.printStackTrace();
        		break;
			}
    	}
    	inp.close();
    }
}