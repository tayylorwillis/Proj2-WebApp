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

@WebServlet("/ManageTenants")
public class ManageTenants extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Manage Tenants</title></head><body>");
        out.println("<h2>Manage Tenants</h2>");

        out.println("<h3>Add New Tenant</h3>");
        out.println("<form method='post' action='ManageTenants'>");
        out.println("Name: <input type='text' name='name' required><br>");
        out.println("Phone Number: <input type='text' name='phoneNumber' required><br>");
        out.println("Email: <input type='email' name='email' required><br>");
        out.println("Check-in Date: <input type='date' name='checkInDate' required><br>");
        out.println("Check-out Date: <input type='date' name='checkOutDate'><br>");
        out.println("Apartment Number: <input type='number' name='apartmentNumber' required><br>");
        out.println("<input type='submit' value='Add Tenant'>");
        out.println("</form>");

        out.println("<h3>Delete Tenant</h3>");
        out.println("<form method='post' action='ManageTenants'>");
        out.println("Tenant ID: <input type='number' name='tenantIdToDelete' required><br>");
        out.println("<input type='submit' value='Delete Tenant'>");
        out.println("</form>");

        out.println("<h3>Current Tenants</h3>");
        out.println("<table border='1'>");
        out.println("<tr><th>Tenant ID</th><th>Name</th><th>Phone Number</th><th>Email</th><th>Check-in Date</th><th>Check-out Date</th><th>Apartment Number</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ApartmentDB", "root", "");
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tenant");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int tenantId = rs.getInt("tenantId");
                String tenantName = rs.getString("name");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String checkInDate = rs.getString("checkInDate");
                String checkOutDate = rs.getString("checkOutDate");
                int apartmentNumber = rs.getInt("apartmentNumber");

                out.println("<tr>");
                out.println("<td>" + tenantId + "</td>");
                out.println("<td>" + tenantName + "</td>");
                out.println("<td>" + phoneNumber + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + checkInDate + "</td>");
                out.println("<td>" + checkOutDate + "</td>");
                out.println("<td>" + apartmentNumber + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String apartmentNumberStr = request.getParameter("apartmentNumber");
        String tenantIdToDeleteStr = request.getParameter("tenantIdToDelete");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ApartmentDB", "root", "");

            if (name != null && apartmentNumberStr != null) {
                int apartmentNumber = Integer.parseInt(apartmentNumberStr);
                PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO Tenant (name, phoneNumber, email, checkInDate, checkOutDate, apartmentNumber) VALUES (?, ?, ?, ?, ?, ?)");
                insertStmt.setString(1, name);
                insertStmt.setString(2, phoneNumber);
                insertStmt.setString(3, email);
                insertStmt.setString(4, checkInDate);
                insertStmt.setString(5, checkOutDate);
                insertStmt.setInt(6, apartmentNumber);
                insertStmt.executeUpdate();
                insertStmt.close();
            }

            if (tenantIdToDeleteStr != null) {
                int tenantIdToDelete = Integer.parseInt(tenantIdToDeleteStr);
                PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM Tenant WHERE tenantId = ?");
                deleteStmt.setInt(1, tenantIdToDelete);
                deleteStmt.executeUpdate();
                deleteStmt.close();
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ManageTenants");
    }
}
