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
    public User getUserById(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            // Obtenez la connexion à la base de données (vous devez remplacer cette logique par votre propre logique de connexion)
        	connection = daoFactory.getConnection();
            // Écrivez la requête SQL pour récupérer l'utilisateur par ID
            String query = "SELECT id, username, email, password FROM user WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            // Exécutez la requête
            resultSet = statement.executeQuery();

            // Vérifiez si un utilisateur a été trouvé
            if (resultSet.next()) {
                user = new User();
                user.setid(resultSet.getInt("id"));
                user.setusername(resultSet.getString("username"));
                user.setemail(resultSet.getString("email"));
                user.setpassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL de manière appropriée dans votre application
        } finally {
            // Fermez les ressources de la base de données (ResultSet, PreparedStatement, Connection)
            // Gérez les exceptions appropriées ici aussi
        }

        return user;
    }
    public boolean updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String query = "UPDATE users SET username =?, email = ?, password = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, user.getemail());
            preparedStatement.setString(1, user.getusername());
            preparedStatement.setString(3, user.getpassword());
            preparedStatement.setInt(4, user.getid()); // Assurez-vous d'avoir une méthode getId() dans votre classe User

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

}
