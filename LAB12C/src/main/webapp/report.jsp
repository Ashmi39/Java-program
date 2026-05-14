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

    String query = "SELECT * FROM Emp WHERE Emp_Name LIKE ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1, ch + "%");

    ResultSet rs = ps.executeQuery();
%>

<h2>~~~~~~~~ Salary Report ~~~~~~~~</h2>

<%
while(rs.next()) {
%>
Emp_No : <%= rs.getInt("Emp_NO") %><br>
Emp_Name : <%= rs.getString("Emp_Name") %><br>
Basic : <%= rs.getInt("Basicsalary") %><br>
-----------------------------<br>
<%
}
con.close();
} catch(Exception e) {
    out.println(e);
}
%>