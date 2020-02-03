package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/index.html")
public class Servlet extends HttpServlet {
	@EJB
	MyCalculator c;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        c.setA(1);
        c.setB(2);
        c.add();
        out.println(c.getA()+" + " + c.getB()+" = "+c.getC());
	}
}
