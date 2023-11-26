package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import User.User;
import DAO.DAOUtil;
public class UserDAO {
    private DAOFactory daoFactory;

    public UserDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public User getUserByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT * FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setid(resultSet.getInt("id"));
                user.setusername(resultSet.getString("username"));
                user.setemail(resultSet.getString("email"));
                user.setpassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtil.close(resultSet, preparedStatement, connection);
        }

        return user;
    }

    public boolean registerUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getusername());
            preparedStatement.setString(2, user.getemail());
            preparedStatement.setString(3, user.getpassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	DAOUtil.close(connection);
        	DAOUtil.close(preparedStatement);
        }
    }
    public User findUserByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = daoFactory.getConnection();
            String sql = "SELECT * FROM Users WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            // Gérer les exceptions appropriées ici
        } finally {
            // Fermer les ressources (resultSet, preparedStatement, connection) ici
        }

        return user;
    }

    private User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setid(resultSet.getInt("id"));
        user.setusername(resultSet.getString("username"));
        user.setemail(resultSet.getString("email"));
        user.setpassword(resultSet.getString("password"));
        return user;
    }
    public boolean createUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String sql = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getusername());
            preparedStatement.setString(2, user.getemail());
            preparedStatement.setString(3, user.getpassword());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            // Gérer les exceptions appropriées ici
        } finally {
            // Fermer les ressources (preparedStatement, connection) ici
        }

        return false;
    }

}
