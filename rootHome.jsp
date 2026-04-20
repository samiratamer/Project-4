<%-- Name:Samira Tamer
     Course: CNT 4714 – Spring 2026 – Project Four
     Assignment title: A Three-Tier Distributed Web-Based Application
     Date: April 27, 2026 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>CNT 4714 Spring 2026 – Root User App</title>
</head>
<body style="background-color:black; color:white; margin:0; padding:0;">

  <div style="text-align:center; padding:20px; background-color:black;">
    <h1 style="color:green; font-family:Arial;">
      Welcome to the Spring 2026 Project 4 Enterprise System
    </h1>
    <h2 style="color:orange; font-family:Arial;">
      A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container
    </h2>
  </div>

  <hr style="border-color:white;">

  <div style="text-align:center; padding:10px; background-color:black;">
    <p style="font-family:Arial; font-size:14px;">
      You are connected to the Project 4 Enterprise System database as a
      <span style="color:red; font-weight:bold;">root-level</span> user.<br>
      Please enter any SQL query or update command in the box below.
    </p>

    <form action="../RootUserServlet" method="post">
      <div>
        <textarea name="sqlCommand" rows="8" cols="80"
          style="background-color:blue; color:lime; font-family:monospace;
                 font-size:14px; border:2px solid lime; padding:4px;"><%
          String cmd = (String) request.getAttribute("sqlCommand");
          if (cmd != null) { out.print(cmd); }
        %></textarea>
      </div>
      <div style="margin-top:12px;">
        <input type="submit" value="Execute Command"
               style="background-color:black; color:lime; border:2px solid lime;
                      font-weight:bold; padding:6px 12px; cursor:pointer; margin-right:8px;"/>
        <input type="button" value="Reset Form"
               onclick="document.querySelector('textarea[name=sqlCommand]').value='';"
               style="background-color:red; color:yellow; border:none;
                      font-weight:bold; padding:6px 12px; cursor:pointer; margin-right:8px;"/>
        <input type="button" value="Clear Results"
               onclick="document.getElementById('resultsArea').innerHTML='<b>Execution Results:</b>';"
               style="background-color:black; color:yellow; border:2px solid yellow;
                      font-weight:bold; padding:6px 12px; cursor:pointer;"/>
      </div>
    </form>
  </div>

  <hr style="border-color:white;">
  <p style="text-align:center; font-family:Arial; font-size:13px;">
    All execution results will appear below this line.
  </p>
  <hr style="border-color:white;">

  <div id="resultsArea" style="text-align:center; background-color:black;
                                padding:20px; font-family:Arial;">
    <b>Execution Results:</b><br>
    <%
      String results = (String) request.getAttribute("results");
      if (results != null) { out.print(results); }
    %>
  </div>

</body>
</html>
