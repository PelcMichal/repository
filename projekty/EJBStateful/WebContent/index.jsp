<%@ page import= "ejb.ListElementsRemote" %>
<%@ page import= "javax.naming.InitialContext" %>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.Properties"%>
<%@ page import= "javax.naming.Context"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%!
	private static ListElementsRemote values;
	public void jspInit()
	{
		Properties properties = new Properties();
	    properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.OpenEJBInitialContextFactory");
	    
		try
		{
			final Context ctx = new InitialContext(properties);
			values = (ListElementsRemote)(ctx.lookup("stateful123"));
		}
		catch(Exception e){}
	}
%>
<%
	if(request.getParameter("addNum")!=null)
	{
		values.addElement(Integer.valueOf(request.getParameter("t1")));
	}
	if(request.getParameter("remNum")!=null)
	{
		values.remElement(Integer.valueOf(request.getParameter("t1")));
	}
%>


<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>JSP Stateful</title>
	</head>
	<body>
		<h1>Welcome</h1>
		<form action = "/EJBStateful/" method = "post">
			<input Type = 'text' name = 't1'><br>
			<input Type = 'submit' value = "Add" name = 'addNum'><br>
			<input Type = 'submit' value = "Remove" name = 'remNum'><br>
			<%
				if(values!=null)
				{
					List<Integer> nums = values.getElements();
					for(int value : nums)
					{
						out.println("<br>"+value);
					}
					out.println("<br><br> Size = "+nums.size());
				}
			%>
		</form>
	</body>
</html>