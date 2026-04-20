<%-- clientHome.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>CNT 4714 Spring 2026 – Client User</title></head>
<body style="background-color:black; color:white;">

  <h1 style="color:red; text-align:center;">
    Welcome to the Spring 2026 Project 4 Enterprise System
  </h1>
  <h2 style="color:cyan; text-align:center;">
    A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container
  </h2>

  <hr style="border-color:white;">

  <p style="text-align:center;">
    You are connected to the Project 4 Enterprise System database as a
    <span style="color:red; font-weight:bold;">client-level</span> user.<br>
    Please enter any SQL query or update command in the box below.
  </p>

  <form action="../ClientUserServlet" method="post">
    <div style="text-align:center;">
      <textarea name="sqlCommand" rows="8" cols="80"
        style="background-color:blue; color:lime; font-size:14px;"><%
          String cmd = (String) request.getAttribute("sqlCommand");
          if (cmd != null) out.print(cmd);
        %></textarea>
    </div>
    <div style="text-align:center; margin-top:10px;">
      <input type="submit" value="Execute Command"
             style="background-color:black; color:lime; border:2px solid lime; font-weight:bold;"/>
      <input type="reset" value="Reset Form"
             style="background-color:red; color:yellow; font-weight:bold; margin-left:10px;"/>
      <input type="button" value="Clear Results"
             onclick="document.getElementById('results').innerHTML='<b>Execution Results:</b>'"
             style="background-color:black; color:yellow; border:2px solid yellow; font-weight:bold; margin-left:10px;"/>
    </div>
  </form>

  <hr style="border-color:white;">
  <p style="text-align:center;">All execution results will appear below this line.</p>
  <hr style="border-color:white;">

  <div id="results" style="text-align:center; background-color:black; padding:10px;">
    <b>Execution Results:</b><br>
    <%
      String results = (String) request.getAttribute("results");
      if (results != null) out.print(results);
    %>
  </div>

</body>
</html>
