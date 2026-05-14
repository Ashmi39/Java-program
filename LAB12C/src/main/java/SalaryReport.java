import java.sql.*;

public class SalaryReport {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Employee",
                "root",
                "ashmi@r@2426"
            );

            String query = "SELECT * FROM Emp WHERE Emp_Name LIKE 'R%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("~~~~~~~~ Salary Report ~~~~~~~~");

            while (rs.next()) {
                System.out.println("Emp_No : " + rs.getInt("Emp_NO"));
                System.out.println("Emp_Name : " + rs.getString("Emp_Name"));
                System.out.println("Basic : " + rs.getInt("Basicsalary"));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}