<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>Accountant App</title></head>
<body style="background-color:black; color:white; text-align:center;">

  <h1 style="color:cyan;">Welcome to the Spring 2026 Project 4 Enterprise System</h1>
  <h2 style="color:green;">
    A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container
  </h2>
  <hr>
  <p>You are connected as an <b style="color:cyan;">accountant-level</b> user.<br>
     Please select the operation you would like to perform from the list below.</p>

  <form action="../AccountantUserServlet" method="post">
    <div style="background-color:gray; text-align:left; padding:20px; display:inline-block;">
      <ul>
        <li>
          <input type="radio" name="reportChoice" value="1"/>
          <span style="color:blue;">Get The Maximum Status Value Of All Suppliers</span>
          (Returns a maximum value)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="2"/>
          <span style="color:blue;">Get The Total Weight Of All Parts</span>
          (Returns a sum)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="3"/>
          <span style="color:blue;">Get The Total Number of Shipments</span>
          (Returns the current number of shipments in total)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="4"/>
          <span style="color:blue;">Get The Name And Number Of Workers Of The Job With The Most Workers</span>
          (Returns two values)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="5"/>
          <span style="color:blue;">List The Name And Status Of Every Supplier</span>
          (Returns a list of supplier names with their current status)
        </li>
      </ul>
      <div style="text-align:center;">
        <input type="submit" value="Execute Command"
               style="background-color:black; color:lime; border:2px solid lime; font-weight:bold;"/>
        <input type="button" value="Clear Results"
               onclick="document.getElementById('results').innerHTML='<b>Execution Results:</b>'"
               style="background-color:black; color:yellow; border:2px solid yellow; font-weight:bold; margin-left:10px;"/>
      </div>
    </div>
  </form>

  <hr>
  <p>All execution results will appear below this line.</p>
  <hr>

  <div id="results">
    <b>Execution Results:</b><br>
    <%
      String results = (String) request.getAttribute("results");
      if (results != null) out.print(results);
    %>
  </div>

</body>
</html>
