package ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ListElementsRemote values;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.OpenEJBInitialContextFactory");
        
     /*   ListElementsRemote bank = (ListElementsRemote)ctx.lookup("stateful123");*/
        
		try
		{
			final Context ctx = new InitialContext(properties);
			values = (ListElementsRemote)(ctx.lookup("stateful123"));
		}
		catch(Exception e){out.println(e.getMessage());}
		
		if(request.getParameter("addNum")!=null)
		{
			values.addElement(Integer.valueOf(request.getParameter("t1")));
		}
		if(request.getParameter("remNum")!=null)
		{
			values.remElement(Integer.valueOf(request.getParameter("t1")));
		}
		
		out.println("<html>\r\n" + 
				"	<head>\r\n" + 
				"		<meta charset=\"ISO-8859-1\">\r\n" + 
				"		<title>JSP Stateful</title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<h1>Welcome</h1>\r\n" + 
				"		<from action=\"index.jsp\">\r\n" + 
				"			<input Type = 'text' name = 't1'><br>\r\n" + 
				"			<input Type = 'submit' value = \"Add\" name = 'addNum'><br>\r\n" + 
				"			<input Type = 'submit' value = \"Remove\" name = 'remNum'><br>");
		
		if(values!=null)
		{
			out.println("works");
			List<Integer> nums = values.getElements();
			for(int value : nums)
			{
				out.println("<br>"+value);
			}
			out.println("<br><br> Size = "+nums.size());
		}
		out.println("		</from>\r\n" + 
				"	</body>\r\n" + 
				"</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.OpenEJBInitialContextFactory");
        
     /*   ListElementsRemote bank = (ListElementsRemote)ctx.lookup("stateful123");*/
        
		try
		{
			final Context ctx = new InitialContext(properties);
			values = (ListElementsRemote)(ctx.lookup("stateful123"));
		}
		catch(Exception e){out.println(e.getMessage());}
		
		if(request.getParameter("addNum")!=null)
		{
			values.addElement(Integer.valueOf(request.getParameter("t1")));
		}
		if(request.getParameter("remNum")!=null)
		{
			values.remElement(Integer.valueOf(request.getParameter("t1")));
		}
		
		out.println("<html>\r\n" + 
				"	<head>\r\n" + 
				"		<meta charset=\"ISO-8859-1\">\r\n" + 
				"		<title>JSP Stateful</title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<h1>Welcome</h1>\r\n" + 
				"		<from action=\"index.jsp\">\r\n" + 
				"			<input Type = 'text' name = 't1'><br>\r\n" + 
				"			<input Type = 'submit' value = \"Add\" name = 'addNum'><br>\r\n" + 
				"			<input Type = 'submit' value = \"Remove\" name = 'remNum'><br>");
		
		if(values!=null)
		{
			out.println("works");
			List<Integer> nums = values.getElements();
			for(int value : nums)
			{
				out.println("<br>"+value);
			}
			out.println("<br><br> Size = "+nums.size());
		}
		out.println("		</from>\r\n" + 
				"	</body>\r\n" + 
				"</html>");
	
	}

}
