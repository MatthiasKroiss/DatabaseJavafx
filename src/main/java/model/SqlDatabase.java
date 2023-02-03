package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlDatabase {
    private static final String url = "jdbc:derby://localhost:1527/B_Uebung";
    private static final String user = "B_Uebung";
    private static final String password = "B_Uebung";

    public static void insert() {
        try (Connection c = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO file (name, type, path, size) VALUES (?,?,?,?)";

            //A PreparedStatement is being used explicit for the filepath.
            //  Filepath contains multiple backslashes and the backslahes get ignored
            //  with using the 'normal' Statement.
            //  With the help of PreparedStatement the backslashes will not get
            //  ignored during the insertion.
            PreparedStatement pstmt = c.prepareStatement(sql);
            /*
            pstmt.setString(1, fssFile.getFilename());
            pstmt.setString(2, fssFile.getFiletype());
            pstmt.setString(3, fssFile.getFilepath());
            pstmt.setString(4, fssFile.getFilesize());

             */
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Inserted datas to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
