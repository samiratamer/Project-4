<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>Data Entry System</title></head>
<body style="background-color:black; color:white; text-align:center;">

  <h1 style="color:yellow;">Welcome to the Spring 2026 Project 4 Enterprise System</h1>
  <h2 style="color:red;">Data Entry Application</h2>
  <p>You are connected as a <b style="color:yellow;">data-entry-level</b> user.<br>
     Enter data values below to add records to the database.</p>

  <hr>

  <%-- SUPPLIERS FORM --%>
  <fieldset style="border-color:white; text-align:center;">
    <legend style="color:white;">Suppliers Record Insert</legend>
    <form action="../AddSupplierServlet" method="post">
      <table align="center">
        <tr>
          <th style="color:yellow;">snum</th>
          <th style="color:yellow;">sname</th>
          <th style="color:yellow;">status</th>
          <th style="color:yellow;">city</th>
        </tr>
        <tr>
          <td><input type="text" name="snum" style="background-color:olive;"/></td>
          <td><input type="text" name="sname" style="background-color:olive;"/></td>
          <td><input type="text" name="status" style="background-color:olive;"/></td>
          <td><input type="text" name="city" style="background-color:olive;"/></td>
        </tr>
      </table>
      <input type="submit" value="Enter Supplier Record Into Database"
             style="background-color:green; color:red; font-weight:bold;"/>
      <input type="reset" value="Clear Data and Results"
             style="background-color:red; color:yellow; font-weight:bold; margin-left:10px;"/>
    </form>
  </fieldset>

  <%-- PARTS FORM --%>
  <fieldset style="border-color:white; text-align:center;">
    <legend style="color:white;">Parts Record Insert</legend>
    <form action="../AddPartServlet" method="post">
      <table align="center">
        <tr>
          <th style="color:yellow;">pnum</th>
          <th style="color:yellow;">pname</th>
          <th style="color:yellow;">color</th>
          <th style="color:yellow;">weight</th>
          <th style="color:yellow;">city</th>
        </tr>
        <tr>
          <td><input type="text" name="pnum" style="background-color:olive;"/></td>
          <td><input type="text" name="pname" style="background-color:olive;"/></td>
          <td><input type="text" name="color" style="background-color:olive;"/></td>
          <td><input type="text" name="weight" style="background-color:olive;"/></td>
          <td><input type="text" name="city" style="background-color:olive;"/></td>
        </tr>
      </table>
      <input type="submit" value="Enter Part Record Into Database"
             style="background-color:green; color:red; font-weight:bold;"/>
      <input type="reset" value="Clear Data and Results"
             style="background-color:red; color:yellow; font-weight:bold; margin-left:10px;"/>
    </form>
  </fieldset>

  <%-- JOBS FORM --%>
  <fieldset style="border-color:white; text-align:center;">
    <legend style="color:white;">Jobs Record Insert</legend>
    <form action="../AddJobServlet" method="post">
      <table align="center">
        <tr>
          <th style="color:yellow;">jnum</th>
          <th style="color:yellow;">jname</th>
          <th style="color:yellow;">numworkers</th>
          <th style="color:yellow;">city</th>
        </tr>
        <tr>
          <td><input type="text" name="jnum" style="background-color:olive;"/></td>
          <td><input type="text" name="jname" style="background-color:olive;"/></td>
          <td><input type="text" name="numworkers" style="background-color:olive;"/></td>
          <td><input type="text" name="city" style="background-color:olive;"/></td>
        </tr>
      </table>
      <input type="submit" value="Enter Job Record Into Database"
             style="background-color:green; color:red; font-weight:bold;"/>
      <input type="reset" value="Clear Data and Results"
             style="background-color:red; color:yellow; font-weight:bold; margin-left:10px;"/>
    </form>
  </fieldset>

  <%-- SHIPMENTS FORM --%>
  <fieldset style="border-color:white; text-align:center;">
    <legend style="color:white;">Shipments Record Insert</legend>
    <form action="../AddShipmentServlet" method="post">
      <table align="center">
        <tr>
          <th style="color:yellow;">snum</th>
          <th style="color:yellow;">pnum</th>
          <th style="color:yellow;">jnum</th>
          <th style="color:yellow;">quantity</th>
        </tr>
        <tr>
          <td><input type="text" name="snum" style="background-color:olive;"/></td>
          <td><input type="text" name="pnum" style="background-color:olive;"/></td>
          <td><input type="text" name="jnum" style="background-color:olive;"/></td>
          <td><input type="text" name="quantity" style="background-color:olive;"/></td>
        </tr>
      </table>
      <input type="submit" value="Enter Shipment Record Into Database"
             style="background-color:green; color:red; font-weight:bold;"/>
      <input type="reset" value="Clear Data and Results"
             style="background-color:red; color:yellow; font-weight:bold; margin-left:10px;"/>
    </form>
  </fieldset>

  <hr>
  <p><b>Execution Results:</b></p>
  <%
    String results = (String) request.getAttribute("results");
    if (results != null) out.print(results);
  %>

</body>
</html>
