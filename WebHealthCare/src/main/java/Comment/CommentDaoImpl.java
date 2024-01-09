package Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOFactory;
import User.User;

public class CommentDaoImpl implements CommentDAO {

	 private DAOFactory daoFactory;

	    public CommentDaoImpl(DAOFactory daoFactory) {
	        this.daoFactory = daoFactory;
	    }

	    @Override
	    public List<Comment> getAllCommentsByBlogId(int blogId) {
	        List<Comment> comments = new ArrayList<>();
	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement statement = connection.prepareStatement(
	                     "SELECT * FROM comment WHERE id_blog = ?")) {

	            statement.setLong(1, blogId);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    Comment comment = mapResultSetToComment(resultSet);
	                    comments.add(comment);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	        return comments;
	    }

	    @Override
	    public Comment getCommentById(int commentId) {
	        Comment comment = null;
	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement statement = connection.prepareStatement(
	                     "SELECT * FROM comment WHERE id = ?")) {

	            statement.setLong(1, commentId);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    comment = mapResultSetToComment(resultSet);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	        return comment;
	    }

	    @Override
	    public void addComment(Comment comment) {
	    	 Connection connection = null;
	         PreparedStatement preparedStatement = null;
	         try {
	             // Récupération de la connexion depuis la fabrique DAO
	             connection = daoFactory.getConnection();

	             // Requête SQL pour insérer un blog dans la base de données
	             String query = "INSERT INTO comment (comment,  id_user, id_blog) VALUES (?, ?, ?)";

	             // Préparation de la requête avec la gestion des clés générées
	             preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

	             // Définition des valeurs des paramètres de la requête
	             preparedStatement.setString(1, comment.getComment());
	             preparedStatement.setInt(2, comment.getIdUser());
	             preparedStatement.setInt(3, comment.getIdBlog());
	             
	          
	        
	             preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }

	    @Override
	    public void updateComment(Comment comment) {
	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement statement = connection.prepareStatement(
	                     "UPDATE comment SET comment=?, date=?, id_user=?, id_blog=? WHERE id=?")) {

	            statement.setString(1, comment.getComment());
	            statement.setDate(2, comment.getDate());
	            statement.setInt(3, comment.getUser().getid());
	            statement.setInt(4, comment.getBlog().getID());
	            statement.setInt(5, comment.getId());

	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }

	    @Override
	    public void deleteComment(int commentId) {
	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement statement = connection.prepareStatement(
	                     "DELETE FROM comment WHERE id=?")) {

	            statement.setLong(1, commentId);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }

	    private Comment mapResultSetToComment(ResultSet resultSet) throws SQLException {
	        Comment comment = new Comment();
	        comment.setId(resultSet.getInt("id"));
	        comment.setComment(resultSet.getString("comment"));
	        comment.setDate(resultSet.getDate("date"));
	        // Assume you have appropriate methods in the User and Blog classes to set their properties
	       
	        comment.setIdUser(resultSet.getInt("id_user"));
	        comment.setIdBlog(resultSet.getInt("id_blog"));
	        return comment;
	    }

	    // Implement methods like getUserById and getBlogById to retrieve User and Blog entities
	    // based on their respective IDs.

public List<Comment> getAllCommentsWithUsersByBlogId(int blogId) {
    List<Comment> commentsWithUsers = new ArrayList<>();

    try (Connection connection = daoFactory.getConnection();
         PreparedStatement statement = connection.prepareStatement(
                 "SELECT c.*, u.id_user, u.username, u.email " +
                 "FROM comment c " +
                 "JOIN users u ON c.id_user = u.id " +
                 "WHERE c.id_blog = ?")) {

        statement.setInt(1, blogId);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Comment comment = mapResultSetToCommentWithUser(resultSet);
                commentsWithUsers.add(comment);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }

    return commentsWithUsers;
}

private Comment mapResultSetToCommentWithUser(ResultSet resultSet) throws SQLException {
    Comment comment = mapResultSetToComment(resultSet);

    // Create a User object and set its properties
    User user = new User();
    user.setid(resultSet.getInt("id_user"));
    user.setusername(resultSet.getString("username"));
    user.setemail(resultSet.getString("email"));

    // Set the User object in the Comment
    comment.setUser(user);

    return comment;
}
}