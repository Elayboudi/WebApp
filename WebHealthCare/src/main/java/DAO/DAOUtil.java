package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtil {
    // Méthode pour fermer un ResultSet
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour fermer un PreparedStatement
    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour fermer une connexion
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour fermer un ResultSet, un PreparedStatement et une connexion
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        close(resultSet);
        close(preparedStatement);
        close(connection);
    }
}
