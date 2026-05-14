<%@ page import="java.sql.*" %>

<%
String ch = request.getParameter("ch");

try {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/Employee",
        "root",
        "ashmi@r@2426"
    );

    // 🔥 DELETE QUERY
    String deleteQuery = "DELETE FROM Emp WHERE Emp_Name LIKE ?";
    PreparedStatement ps1 = con.prepareStatement(deleteQuery);
    ps1.setString(1, ch + "%");
    ps1.executeUpdate();

    // 🔥 SELECT REMAINING RECORDS
    String selectQuery = "SELECT * FROM Emp";
    PreparedStatement ps2 = con.prepareStatement(selectQuery);
    ResultSet rs = ps2.executeQuery();
%>

<h2>~~~~~~~~ Salary Report ~~~~~~~~</h2>

<%
while(rs.next()) {
%>
Emp_No : <%= rs.getInt("Emp_NO") %><br>
Emp_Name : <%= rs.getString("Emp_Name") %><br>
Basic : <%= rs.getInt("Basicsalary") %><br>
----------------------------------<br>
<%
}
con.close();

} catch(Exception e) {
    out.println(e);
}
%>