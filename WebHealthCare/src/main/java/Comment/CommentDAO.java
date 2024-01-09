package Comment;

import java.util.List;

public interface CommentDAO {

    List<Comment> getAllCommentsByBlogId(int blogId);

    Comment getCommentById(int commentId);

    void addComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(int commentId);

    List<Comment> getAllCommentsWithUsersByBlogId(int blogId);}
