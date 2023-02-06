package model;

import java.sql.*;

public class SqlDatabase {
    private static final String url = "jdbc:derby://localhost:1527/B_Uebung";
    private static final String user = "B_Uebung";
    private static final String password = "B_Uebung";

    public static void insert(int id, String name, String wohnort) {
        try (Connection c = DriverManager.getConnection(url, user, password)) {
            int wohnortID = getWohnortID(wohnort);
            String sql = "INSERT INTO \"Adresse\" (ID, WOHNORT) VALUES (?,?)";

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

    private static int getWohnortID(String wohnort) {
        try (Connection c = DriverManager.getConnection(url, user, password)) {
            int id = 0;

            String selectWohnort = "SELECT * FROM \"Adresse\" where WOHNORT like '" + wohnort + "'";
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(selectWohnort);

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            } else {
                String insertWohnort = "INSERT INTO \"Adresse\" (WOHNORT) values (?)";
                PreparedStatement preparedStatement = c.prepareStatement(insertWohnort, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, wohnort);
                preparedStatement.executeUpdate();

                ResultSet resultID = preparedStatement.getGeneratedKeys();
                resultID.next();
                id = resultSet.getInt(1);
            }

            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
