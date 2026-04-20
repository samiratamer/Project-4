<%-- Name: Samira Tamer
     Course: CNT 4714 – Spring 2026 – Project Four
     Assignment title: A Three-Tier Distributed Web-Based Application
     Date: April 27, 2026 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>CNT 4714 Spring 2026 – Data Entry System</title>
</head>
<body style="background-color:black; color:white; margin:0; padding:0; font-family:Arial;">

  <div style="text-align:center; padding:20px;">
    <h1 style="color:yellow;">
      Welcome to the Spring 2026 Project 4 Enterprise System
    </h1>
    <h2 style="color:red;">Data Entry Application</h2>
  </div>

  <hr style="border-color:white;">

  <p style="text-align:center; font-size:14px;">
    You are connected to the Project 4 Enterprise System database as a
    <b style="color:yellow;">data-entry-level</b> user.<br>
    Enter the data values in a form below to add a new record to the corresponding database table.
  </p>

  <hr style="border-color:white;">

  <%-- ===== SUPPLIERS FORM ===== --%>
  <fieldset style="border:1px solid white; margin:10px 20px; padding:10px;">
    <legend style="color:white;">Suppliers Record Insert</legend>
    <form action="../AddSupplierServlet" method="post">
      <table align="center" cellpadding="4">
        <tr>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">snum</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:160px;">sname</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">status</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">city</th>
        </tr>
        <tr>
          <td><input type="text" name="snum"   style="background-color:olive; width:115px;"/></td>
          <td><input type="text" name="sname"  style="background-color:olive; width:155px;"/></td>
          <td><input type="text" name="status" style="background-color:olive; width:115px;"/></td>
          <td><input type="text" name="city"   style="background-color:olive; width:115px;"/></td>
        </tr>
      </table>
      <div style="text-align:center; margin-top:8px;">
        <input type="submit" value="Enter Supplier Record Into Database"
               style="background-color:green; color:red; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer; margin-right:8px;"/>
        <input type="reset" value="Clear Data and Results"
               style="background-color:red; color:yellow; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer;"/>
      </div>
    </form>
  </fieldset>

  <%-- ===== PARTS FORM ===== --%>
  <fieldset style="border:1px solid white; margin:10px 20px; padding:10px;">
    <legend style="color:white;">Parts Record Insert</legend>
    <form action="../AddPartServlet" method="post">
      <table align="center" cellpadding="4">
        <tr>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:110px;">pnum</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:140px;">pname</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:110px;">color</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:110px;">weight</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:110px;">city</th>
        </tr>
        <tr>
          <td><input type="text" name="pnum"   style="background-color:olive; width:105px;"/></td>
          <td><input type="text" name="pname"  style="background-color:olive; width:135px;"/></td>
          <td><input type="text" name="color"  style="background-color:olive; width:105px;"/></td>
          <td><input type="text" name="weight" style="background-color:olive; width:105px;"/></td>
          <td><input type="text" name="city"   style="background-color:olive; width:105px;"/></td>
        </tr>
      </table>
      <div style="text-align:center; margin-top:8px;">
        <input type="submit" value="Enter Part Record Into Database"
               style="background-color:green; color:red; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer; margin-right:8px;"/>
        <input type="reset" value="Clear Data and Results"
               style="background-color:red; color:yellow; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer;"/>
      </div>
    </form>
  </fieldset>

  <%-- ===== JOBS FORM ===== --%>
  <fieldset style="border:1px solid white; margin:10px 20px; padding:10px;">
    <legend style="color:white;">Jobs Record Insert</legend>
    <form action="../AddJobServlet" method="post">
      <table align="center" cellpadding="4">
        <tr>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">jnum</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:160px;">jname</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:140px;">numworkers</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">city</th>
        </tr>
        <tr>
          <td><input type="text" name="jnum"       style="background-color:olive; width:115px;"/></td>
          <td><input type="text" name="jname"      style="background-color:olive; width:155px;"/></td>
          <td><input type="text" name="numworkers" style="background-color:olive; width:135px;"/></td>
          <td><input type="text" name="city"       style="background-color:olive; width:115px;"/></td>
        </tr>
      </table>
      <div style="text-align:center; margin-top:8px;">
        <input type="submit" value="Enter Job Record Into Database"
               style="background-color:green; color:red; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer; margin-right:8px;"/>
        <input type="reset" value="Clear Data and Results"
               style="background-color:red; color:yellow; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer;"/>
      </div>
    </form>
  </fieldset>

  <%-- ===== SHIPMENTS FORM ===== --%>
  <fieldset style="border:1px solid white; margin:10px 20px; padding:10px;">
    <legend style="color:white;">Shipments Record Insert</legend>
    <form action="../AddShipmentServlet" method="post">
      <table align="center" cellpadding="4">
        <tr>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">snum</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">pnum</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">jnum</th>
          <th style="color:yellow; border:1px solid yellow; padding:6px; width:120px;">quantity</th>
        </tr>
        <tr>
          <td><input type="text" name="snum"     style="background-color:olive; width:115px;"/></td>
          <td><input type="text" name="pnum"     style="background-color:olive; width:115px;"/></td>
          <td><input type="text" name="jnum"     style="background-color:olive; width:115px;"/></td>
          <td><input type="text" name="quantity" style="background-color:olive; width:115px;"/></td>
        </tr>
      </table>
      <div style="text-align:center; margin-top:8px;">
        <input type="submit" value="Enter Shipment Record Into Database"
               style="background-color:green; color:red; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer; margin-right:8px;"/>
        <input type="reset" value="Clear Data and Results"
               style="background-color:red; color:yellow; font-weight:bold;
                      border:none; padding:6px 12px; cursor:pointer;"/>
      </div>
    </form>
  </fieldset>

  <hr style="border-color:white;">

  <div style="text-align:center; padding:16px;">
    <b>Execution Results:</b><br>
    <%
      String results = (String) request.getAttribute("results");
      if (results != null) { out.print(results); }
    %>
  </div>

</body>
</html>
