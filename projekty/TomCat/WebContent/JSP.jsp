<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Hello World!<br/>
	<%@ page import="test.Test" %>
	<%!//import java.awt.Color;
	
	private class NdBadIdea {

		public NdBadIdea(){}
		
  		public String getFoo() {
    		return "sadasdasdas";
  		}	
	}%>
		<%int t = 10;%>
    	<% out.println(t);%>
   		<%!int i = 0;%> 
		<%!NdBadIdea n= new NdBadIdea();%>
		<%!test.Test x= new test.Test();%>
		<% out.println(x.toString());%>
		<% out.println(n.getFoo());%>
    <p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
    
    <form name="TrainerMenu" action="TrainerMenu" method="get">
   <input type="button" value="Page2" name="CreateCourse"
    onclick="openPage('Page2.jsp')"/>
   <input type="button" value="Page3" name="AuthorizationManager"
    onclick="openPage('Page3.jsp')" />

	</form>
	<script type="text/javascript">
 	function openPage(pageURL)
 	{
 		window.location.href = pageURL;
	 }
 	function getCookie(cname) {
 		  var name = cname + "=";
 		  var decodedCookie = decodeURIComponent(document.cookie);
 		  var ca = decodedCookie.split(';');
 		  for(var i = 0; i <ca.length; i++) {
 		    var c = ca[i];
 		    while (c.charAt(0) == ' ') {
 		      c = c.substring(1);
 		    }
 		    if (c.indexOf(name) == 0) {
 		      return c.substring(name.length, c.length);
 		    }
 		  }
 		  return "";
 		}
	</script>
    
    
</body>
</html>