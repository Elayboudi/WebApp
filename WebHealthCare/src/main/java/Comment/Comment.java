package Comment;
import User.User;
import java.sql.Date;

import Blog.Blog;
public class Comment {

    private int id;
    private String comment;
    private Date date;
    private User user;
    private Blog blog;
    private int id_user;
    private int id_blog;
   
    public Comment() {
        
    }

 
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    public int getIdBlog() {
        return id_blog;
    }

    public void setIdBlog(int id_blog) {
        this.id_blog = id_blog;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }}
