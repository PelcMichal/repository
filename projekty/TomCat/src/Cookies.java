import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Cookies.jsp")
public class Cookies extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // print out cookies
        boolean t = true;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null)
        {
        	for (int i = 0; i < cookies.length; i++) {
        		if(cookies[i].getName().equals("B"))
        		{
        			out.println("Welcome Back for " + cookies[i].getValue()+". time.");
        			response.addCookie(new Cookie("B",(Integer.valueOf(cookies[i].getValue()) +1)+""));
        			t = false;
        			break;
        		}
        	}
        }
        if(t)
        {
        	out.println("Welcome.");
        	response.addCookie(new Cookie("B",1+""));
        }
        request.setAttribute("result", "This is the result of the servlet call");
        request.getRequestDispatcher("JSP.jsp").forward(request, response);
        
    }
}