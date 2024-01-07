package Blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOFactory;
import User.User;

public class BlogDaoImpl implements BlogDAO {
    private DAOFactory daoFactory;

    public BlogDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void createBlog(Blog blog) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // Récupération de la connexion depuis la fabrique DAO
            connection = daoFactory.getConnection();

            // Requête SQL pour insérer un blog dans la base de données
            String query = "INSERT INTO blog (id_user, title, description, image) VALUES (?, ?, ?, ?)";

            // Préparation de la requête avec la gestion des clés générées
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Définition des valeurs des paramètres de la requête
            preparedStatement.setInt(1, blog.getUserID());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getDescription());
            preparedStatement.setString(4, blog.getImage());
         
            // Exécution de la requête
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating blog failed, no rows affected.");
            }

            // Récupération de l'ID généré automatiquement
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    blog.setID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating blog failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée (journalisation, remontée, etc.)
        } finally {
            // Fermeture des ressources (connexion et déclaration préparée)
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer l'exception de fermeture de la connexion
            }
        }
    }


    @Override
    public Blog getBlogById(int blogId) {
        Blog blog = null;
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM blog WHERE id = ?")) {

            statement.setInt(1, blogId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    blog = mapResultSetToBlog(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return blog;
    }

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT blog.*, users.username " +
                     "FROM blog " +
                     "JOIN users ON blog.id_user = users.id")) {

            while (resultSet.next()) {
                Blog blog = mapResultSetToBlog(resultSet);
                
                // Ajouter également les informations de l'utilisateur
                User user = new User();
                user.setid(resultSet.getInt("id_user"));
                user.setusername(resultSet.getString("username"));
                blog.setUser(user);

                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return blogs;
    }


    @Override
    public List<Blog> getBlogsByUserId(int userId) {
        List<Blog> blogs = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM blog WHERE id_user = ?")) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog blog = mapResultSetToBlog(resultSet);
                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return blogs;
    }

    
    @Override
    public void updateBlog(Blog blog) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE blog SET title=?, description=?, image=? WHERE id=?")) {

            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getDescription());
            statement.setString(3, blog.getImage());
            statement.setInt(4, blog.getID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    @Override
    public void deleteBlog(int blogId) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM blog WHERE id=?")) {

            statement.setInt(1, blogId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private Blog mapResultSetToBlog(ResultSet resultSet) throws SQLException {
        Blog blog = new Blog();
        blog.setID(resultSet.getInt("id"));
        blog.setUserID(resultSet.getInt("id_user"));
        blog.setTitle(resultSet.getString("title"));
        blog.setDescription(resultSet.getString("description"));
        blog.setImage(resultSet.getString("image"));
        blog.setDate(resultSet.getDate("date"));
        return blog;
    }
}
