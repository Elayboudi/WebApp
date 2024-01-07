package Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOFactory;

public class CommentDaoImpl implements CommentDAO {

	 private DAOFactory daoFactory;

	    public CommentDaoImpl(DAOFactory daoFactory) {
	        this.daoFactory = daoFactory;
	    }

	    @Override
	    public List<Comment> getAllCommentsByBlogId(Long blogId) {
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
	    public Comment getCommentById(Long commentId) {
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
	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement statement = connection.prepareStatement(
	                     "INSERT INTO comment (comment, date, id_user, id_blog) VALUES (?, ?, ?, ?)")) {

	            statement.setString(1, comment.getComment());
	            statement.setDate(2, comment.getDate());
	            statement.setInt(3, comment.getIdUser());
	            statement.setInt(4, comment.getIdBlog());

	            statement.executeUpdate();
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
	            statement.setLong(3, comment.getUser().getid());
	            statement.setLong(4, comment.getBlog().getID());
	            statement.setLong(5, comment.getId());

	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }

	    @Override
	    public void deleteComment(Long commentId) {
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
	        comment.setId(resultSet.getLong("id"));
	        comment.setComment(resultSet.getString("comment"));
	        comment.setDate(resultSet.getDate("date"));
	        // Assume you have appropriate methods in the User and Blog classes to set their properties
	       
	        comment.setIdUser(resultSet.getInt("id_user"));
	        comment.setIdBlog(resultSet.getInt("id_blog"));
	        return comment;
	    }

	    // Implement methods like getUserById and getBlogById to retrieve User and Blog entities
	    // based on their respective IDs.
}
