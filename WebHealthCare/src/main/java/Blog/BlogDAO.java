package Blog;
import java.util.List;

public interface BlogDAO {
    void createBlog(Blog blog);
    Blog getBlogById(int blogId);
    List<Blog> getAllBlogs();
    List<Blog> getBlogsByUserId(int userId);
    void updateBlog(Blog blog);
    void deleteBlog(int blogId);
}

