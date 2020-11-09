import java.sql.*;
import java.util.*;

public class main {


    static public void main(String[] args) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/zi2?useUnicode=true&serverTimezone=UTC",
                    "zi2","proba");
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("select * from `persons`");



            try {
                int count = 0;
                if (rs != null) {
                    while (rs.next()) {
                        String name = rs.getString("Name");
                        Integer age = rs.getInt("Age");
                        System.out.println("Wiersz="+count+" | name= "+name +" , age= "+age);
                        count++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement ps = conn.prepareStatement("INSERT INTO persons (Name, Age) VALUES (?, ?)");

            Random rand = new Random();

            ps.setString(1, "NATANIEL");
            ps.setInt(2, rand.nextInt(10000));

            ps.executeUpdate();

            List<String> lss = new ArrayList<>();
            lss.add("JAROSŁAW");
            lss.add("GRZGEORZ");
            lss.add("WŁADYSŁAW");
            lss.add("BARBARA");
            lss.add("ADRIAN");
            lss.add("PIOTR");
            lss.add("KACPER");
            lss.add("JAKUB");
            lss.add("OLIWIA");
            lss.add("NATALIA");

            for (String el:
                 lss) {
                ps.setString(1, el);
                ps.setInt(2, rand.nextInt(10000));
                ps.addBatch();
                ps.clearParameters();
            }
            ps.executeBatch();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
