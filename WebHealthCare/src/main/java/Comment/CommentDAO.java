package Comment;
import java.util.List;

public interface CommentDAO {

    List<Comment> getAllCommentsByBlogId(Long blogId);

    Comment getCommentById(Long commentId);

    void addComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Long commentId);
}
