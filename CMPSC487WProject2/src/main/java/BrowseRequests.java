import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class BrowseRequests
 */
@WebServlet("/BrowseRequests")
public class BrowseRequests extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "SELECT * FROM MaintenanceRequest";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ApartmentDB", "root", ""); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Maintenance Requests</h2>");
            out.println("<form action='BrowseRequests' method='post'>");
            out.println("<label for='apartmentNumber'>Apartment Number:</label>");
            out.println("<input type='text' name='apartmentNumber' id='apartmentNumber'>");
            out.println("<label for='area'>Area:</label>");
            out.println("<input type='text' name='area' id='area'>");
            out.println("<label for='startDate'>Start Date:</label>");
            out.println("<input type='date' name='startDate' id='startDate'>");
            out.println("<label for='endDate'>End Date:</label>");
            out.println("<input type='date' name='endDate' id='endDate'>");
            out.println("<label for='status'>Status:</label>");
            out.println("<select name='status' id='status'>");
            out.println("<option value=''>All</option>");
            out.println("<option value='pending'>Pending</option>");
            out.println("<option value='completed'>Completed</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Filter'>");
            out.println("</form>");
            out.println("<table border='1'>");
            out.println("<tr><th>Request ID</th><th>Tenant Name</th><th>Apartment Number</th><th>Area</th><th>Description</th><th>Date/Time</th><th>Status</th></tr>");

            while (rs.next()) {
                int requestId = rs.getInt("requestId");
                String tenantName = rs.getString("tenantName");
                int apartmentNumber = rs.getInt("apartmentNumber");
                String requestArea = rs.getString("area");
                String description = rs.getString("description");
                String dateTime = rs.getString("dateTime");
                String requestStatus = rs.getString("status");

                out.println("<tr>");
                out.println("<td>" + requestId + "</td>");
                out.println("<td>" + tenantName + "</td>");
                out.println("<td>" + apartmentNumber + "</td>");
                out.println("<td>" + requestArea + "</td>");
                out.println("<td>" + description + "</td>");
                out.println("<td>" + dateTime + "</td>");
                
                out.println("<td>");
                out.println("<form action='UpdateStatus' method='post'>");
                out.println("<input type='hidden' name='requestId' value='" + requestId + "'>");
                out.println("<select name='newStatus'>");
                out.println("<option value='pending' " + (requestStatus.equals("pending") ? "selected" : "") + ">Pending</option>");
                out.println("<option value='completed' " + (requestStatus.equals("completed") ? "selected" : "") + ">Completed</option>");
                out.println("</select>");
                out.println("<input type='submit' value='Update Status'>");
                out.println("</form>");
                out.println("</td>");
                
                out.println("</tr>");
            }

            out.println("</table>");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); 
    }
}

