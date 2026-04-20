<%-- Name:Samira Tamer
     Course: CNT 4714 – Spring 2026 – Project Four
     Assignment title: A Three-Tier Distributed Web-Based Application
     Date: April 27, 2026 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>CNT 4714 Spring 2026 – Accountant App</title>
</head>
<body style="background-color:black; color:white; margin:0; padding:0; font-family:Arial;">

  <div style="text-align:center; padding:20px;">
    <h1 style="color:cyan;">
      Welcome to the Spring 2026 Project 4 Enterprise System
    </h1>
    <h2 style="color:green;">
      A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container
    </h2>
  </div>

  <hr style="border-color:white;">

  <p style="text-align:center; font-size:14px;">
    You are connected to the Project 4 Enterprise System database as an
    <b style="color:cyan;">accountant-level</b> user.<br>
    Please select the operation you would like to perform from the list below.
  </p>

  <form action="../AccountantUserServlet" method="post">
    <div style="background-color:gray; margin:0 60px; padding:20px;">
      <ul style="list-style-type:disc; text-align:left; font-size:15px; line-height:2.2;">
        <li>
          <input type="radio" name="reportChoice" value="1"/>
          <span style="color:blue; font-weight:bold;">
            Get The Maximum Status Value Of All Suppliers
          </span>
          (Returns a maximum value)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="2"/>
          <span style="color:blue; font-weight:bold;">
            Get The Total Weight Of All Parts
          </span>
          (Returns a sum)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="3"/>
          <span style="color:blue; font-weight:bold;">
            Get The Total Number of Shipments
          </span>
          (Returns the current number of shipments in total)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="4"/>
          <span style="color:blue; font-weight:bold;">
            Get The Name And Number Of Workers Of The Job With The Most Workers
          </span>
          (Returns two values)
        </li>
        <li>
          <input type="radio" name="reportChoice" value="5"/>
          <span style="color:blue; font-weight:bold;">
            List The Name And Status Of Every Supplier
          </span>
          (Returns a list of supplier names with their current status)
        </li>
      </ul>
      <div style="text-align:center; margin-top:12px;">
        <input type="submit" value="Execute Command"
               style="background-color:black; color:lime; border:2px solid lime;
                      font-weight:bold; padding:6px 12px; cursor:pointer; margin-right:8px;"/>
        <input type="button" value="Clear Results"
               onclick="document.getElementById('resultsArea').innerHTML='<b>Execution Results:</b>';"
               style="background-color:black; color:yellow; border:2px solid yellow;
                      font-weight:bold; padding:6px 12px; cursor:pointer;"/>
      </div>
    </div>
  </form>

  <hr style="border-color:white;">
  <p style="text-align:center; font-size:13px;">All execution results will appear below this line.</p>
  <hr style="border-color:white;">

  <div id="resultsArea" style="text-align:center; padding:20px;">
    <b>Execution Results:</b><br>
    <%
      String results = (String) request.getAttribute("results");
      if (results != null) { out.print(results); }
    %>
  </div>

</body>
</html>
