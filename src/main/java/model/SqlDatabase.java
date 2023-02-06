package model;

import java.sql.*;

public class SqlDatabase {
    private static final String url = "jdbc:derby://localhost:1527/B_Uebung";
    private static final String user = "B_Uebung";
    private static final String password = "B_Uebung";

    public static void insert(int id, String name, String wohnort) {
        try (Connection c = DriverManager.getConnection(url, user, password)) {
            int wohnortID = getWohnortID(wohnort);
            String sql2 = "INSERT INTO \"Person\" (ID, NAME, \"AdresseId\") VALUES (?,?,?)";

            PreparedStatement pstmt2 = c.prepareStatement(sql2);

            pstmt2.setInt(1, id);
            pstmt2.setString(2, name);
            pstmt2.setInt(3, wohnortID);
            pstmt2.executeUpdate();

            pstmt2.close();
            System.out.println("Inserted datas to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getWohnortID(String wohnort) {
        int id = 0;
        try (Connection c = DriverManager.getConnection(url, user, password)) {
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
                id = resultID.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
