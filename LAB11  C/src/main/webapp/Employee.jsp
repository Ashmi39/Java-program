<%@ page import="java.sql.*" %>

<!DOCTYPE html>

<html>
<head>
    <title>Salary Report</title>
</head>
<body>

<%
String empno = request.getParameter("empno");
String empname = request.getParameter("empname");
String salary = request.getParameter("salary");

Connection con = null;
PreparedStatement ps = null;
Statement st = null;
ResultSet rs = null;

try {
// Load MySQL Driver
Class.forName("com.mysql.cj.jdbc.Driver");


// Establish Connection
con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/Employee",
    "root",
    "ashmi@r@2426"
);

// Insert only if values are present
if(empno != null && empname != null && salary != null) {
    String insertQuery = "INSERT INTO Emp VALUES (?, ?, ?)";
    ps = con.prepareStatement(insertQuery);

    ps.setInt(1, Integer.parseInt(empno));
    ps.setString(2, empname);
    ps.setInt(3, Integer.parseInt(salary));

    ps.executeUpdate();
}

// Fetch all records
st = con.createStatement();
rs = st.executeQuery("SELECT * FROM Emp");

int total = 0;


%>

<h2>Salary Report</h2>
<hr>

<%
while(rs.next()){
%>

Emp_No : <%= rs.getInt("Emp_NO") %> <br>
Emp_Name : <%= rs.getString("Emp_Name") %> <br>
Basic Salary : <%= rs.getInt("Basicsalary") %>

<hr>

<%
total += rs.getInt("Basicsalary");
}
%>

<h3>Grand Salary : <%= total %></h3>

<%
} catch(Exception e){
out.println("Error: " + e);
} finally {
try { if(rs != null) rs.close(); } catch(Exception e){}
try { if(st != null) st.close(); } catch(Exception e){}
try { if(ps != null) ps.close(); } catch(Exception e){}
try { if(con != null) con.close(); } catch(Exception e){}
}
%>

</body>
</html>
