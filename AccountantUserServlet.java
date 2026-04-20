/* Name:Samira Tamer
   Course: CNT 4714 – Spring 2026 – Project Four
   Assignment title: A Three-Tier Distributed Web-Based Application
   Date: April 27, 2026
*/
package project4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class AccountantUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String choice = request.getParameter("reportChoice");
        StringBuilder resultHTML = new StringBuilder();

        Properties props = new Properties();
        String propPath = getServletContext()
                .getRealPath("/WEB-INF/conf/accountant.properties");

        Connection conn = null;
        try {
            props.load(new FileInputStream(propPath));
            Class.forName(props.getProperty("db.driver"));
            conn = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password"));

            // MUST use CallableStatement – no exceptions
            CallableStatement cs = null;
            ResultSet rs = null;

            if (choice == null) {
                resultHTML.append("Please select a report option.");

            } else switch (choice) {

                case "1":
                    // Get max status of all suppliers
                    // Check your project4DBscript.sql for the exact procedure name
                    cs = conn.prepareCall("{CALL Get_The_Maximum_Status_Of_All_Suppliers()}");
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.execute();
                    resultHTML.append(buildSingleTable(
                            "Maximum_Status_Of_All_Suppliers",
                            String.valueOf(cs.getInt(1))));
                    break;

                case "2":
                    // Get total weight of all parts
                    cs = conn.prepareCall("{CALL Get_The_Sum_Of_All_Parts_Weights()}");
                    cs.registerOutParameter(1, Types.DOUBLE);
                    cs.execute();
                    resultHTML.append(buildSingleTable(
                            "Sum_Of_All_Part_Weights",
                            String.valueOf(cs.getDouble(1))));
                    break;

                case "3":
                    // Get total number of shipments
                    cs = conn.prepareCall("{CALL Get_The_Total_Number_Of_Shipments()}");
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.execute();
                    resultHTML.append(buildSingleTable(
                            "The_Total_Number_Of_Shipments",
                            String.valueOf(cs.getInt(1))));
                    break;

                case "4":
                    // Get job name and numworkers for job with most workers
                    // This one returns a ResultSet (two columns)
                    cs = conn.prepareCall("{CALL Get_The_Name_Of_The_Job_With_The_Most_Workers()}");
                    rs = cs.executeQuery();
                    resultHTML.append(buildHTMLTable(rs));
                    rs.close();
                    break;

                case "5":
                    // List sname and status of every supplier
                    cs = conn.prepareCall("{CALL List_The_Name_And_Status_Of_All_Suppliers()}");
                    rs = cs.executeQuery();
                    resultHTML.append(buildHTMLTable(rs));
                    rs.close();
                    break;

                default:
                    resultHTML.append("Invalid selection.");
            }

            if (cs != null) cs.close();

        } catch (Exception e) {
            resultHTML.append(
                "<div style='background-color:red; color:yellow; padding:8px; "
                + "display:inline-block; font-family:Arial;'>"
                + "Error: " + e.getMessage() + "</div>");
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception ignore) {}
        }

        request.setAttribute("results", resultHTML.toString());
        RequestDispatcher rd =
                request.getRequestDispatcher("/Front-End-Pages/accountantHome.jsp");
        rd.forward(request, response);
    }

    /** For procedures that return a single scalar value via OUT parameter. */
    private String buildSingleTable(String columnName, String value) {
        return "<table border='1' style='border-collapse:collapse; margin:8px auto;'>"
             + "<tr><th style='background-color:red; color:yellow; padding:6px 10px; "
             + "font-family:Arial;'>" + columnName + "</th></tr>"
             + "<tr><td style='padding:6px 14px; text-align:center; font-family:Arial; "
             + "background-color:white; color:black;'>" + value + "</td></tr>"
             + "</table>";
    }

    /** For procedures that return a ResultSet. */
    private String buildHTMLTable(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1' style='border-collapse:collapse; margin:8px auto;'><tr>");
        for (int i = 1; i <= cols; i++) {
            sb.append("<th style='background-color:red; color:yellow; "
                    + "padding:6px 10px; font-family:Arial;'>")
              .append(meta.getColumnName(i)).append("</th>");
        }
        sb.append("</tr>");
        while (rs.next()) {
            sb.append("<tr>");
            for (int i = 1; i <= cols; i++) {
                sb.append("<td style='padding:5px 10px; text-align:center; "
                        + "font-family:Arial; background-color:white; color:black;'>")
                  .append(rs.getString(i)).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
