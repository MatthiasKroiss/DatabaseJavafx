package model;

import java.sql.*;

public class SqlDatabase {
    private static final String url = "jdbc:derby://localhost:1527/B_Uebung";
    private static final String user = "B_Uebung";
    private static final String password = "B_Uebung";

    public static void insert(int id, String name, String wohnort) {
        try (Connection c = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO \"Adresse\" (ID, WOHNORT) VALUES (?,?)";

            //A PreparedStatement is being used explicit for the filepath.
            //  Filepath contains multiple backslashes and the backslahes get ignored
            //  with using the 'normal' Statement.
            //  With the help of PreparedStatement the backslashes will not get
            //  ignored during the insertion.
            PreparedStatement pstmt = c.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, wohnort);


            pstmt.executeUpdate();
            pstmt.close();

            String sql2 = "INSERT INTO \"Person\" (NAME, ID, \"AdresseId\") VALUES (?,?,?)";

            PreparedStatement pstmt2 = c.prepareStatement(sql2);

            pstmt2.setInt(1, id);
            pstmt2.setString(2, name);
            pstmt2.setInt(3, 4);
            pstmt2.executeUpdate();

            pstmt2.close();
            System.out.println("Inserted datas to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
