import java.sql.*;


public class main {

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/sg44522?useUnicode=true&serverTimezone=UTC",
                    "zi2","proba");
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("select * from `offices`");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            int count = 0;
            if (rs != null) {
                while (rs.next()) {
                    String officeCode = rs.getString("officeCode");
                    String city = rs.getString("city");
                    String country = rs.getString("country");
                    System.out.println("Wiersz="+count+" | officeCode= "+officeCode+" , city= "+city+" , country= "+country);
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
