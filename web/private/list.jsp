<%-- 
    Document   : list
    Created on : 2019/11/28, 下午 03:53:27
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="add.html"><button>ADD</buttin></a><br/>
        <a href="../logout.jsp">LOGOUT</a><br/>
        <table border="1">
            <%
//              Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Change to NewServletListener.java -> Listener
              Connection con = DriverManager.getConnection("jdbc:derby://localhost/sample", "app", "app");
              Statement stmt = con.createStatement();
              ResultSet rs = stmt.executeQuery("select * from LOGIN");
              while (rs.next()) {
                  out.println("<tr>");
                  out.println("<td>" + rs.getString("ID") + "</td>");
                  out.println("<td>" + rs.getString("PASSWORD") + "</td>");
                  out.println("<td>");
                  out.println("<a href='../delete?id="+rs.getString("ID")+"'> DELETE </a>");
                  out.println("</td>");
                  out.println("</tr>");
              }
              con.close();
            %>
        </table>
    </body>
</html>
