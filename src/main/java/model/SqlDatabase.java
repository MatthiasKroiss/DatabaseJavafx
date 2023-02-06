package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlDatabase {
    private static final String url = "jdbc:derby://localhost:1527/B_Uebung";
    private static final String user = "B_Uebung";
    private static final String password = "B_Uebung";

    public static void insert(String id, String name, String wohnort) {
        try (Connection c = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Adresse (ID, WOHNORT) VALUES (?,?)";

            //A PreparedStatement is being used explicit for the filepath.
            //  Filepath contains multiple backslashes and the backslahes get ignored
            //  with using the 'normal' Statement.
            //  With the help of PreparedStatement the backslashes will not get
            //  ignored during the insertion.
            PreparedStatement pstmt = c.prepareStatement(sql);

            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, wohnort);


            pstmt.executeUpdate();

            sql = "INSERT INTO Person (NAME, ID, AdresseID) VALUES (?,?)";

            pstmt.close();
            System.out.println("Inserted datas to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
