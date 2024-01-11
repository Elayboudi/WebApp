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
           
            connection = daoFactory.getConnection();

           
            String query = "INSERT INTO blog (id_user, title, description, image) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                       preparedStatement.setInt(1, blog.getUserID());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getDescription());
            preparedStatement.setString(4, blog.getImage());
         
           
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating blog failed, no rows affected.");
            }

           
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    blog.setID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating blog failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace(); 
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
                
                
                User user = new User();
                user.setid(resultSet.getInt("id_user"));
                user.setusername(resultSet.getString("username"));
                blog.setUser(user);

                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
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
            e.printStackTrace(); 
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
            e.printStackTrace(); 
        }
    }


    @Override
    public void deleteBlog(int blogId) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM blog WHERE id=?")) {

            statement.setInt(1, blogId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
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
