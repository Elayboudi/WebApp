package Blog;

import java.sql.Date;
import java.util.List;

import Comment.Comment;
import User.User;

public class Blog {
	private int id;
	private int id_user;
	private String title;
	private String description;
	private String image;
	private Date date;
	private List<Comment> comments;
	// getters et setters

	private User user;
	 public List<Comment> getComments() {
	        return comments;
	    }

	    // Setter pour comments
	    public void setComments(List<Comment> comments) {
	        this.comments = comments;
	    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	public int getID() {
		return this.id;
	}
	
	public int getUserID() {
		return this.id_user;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUserID(int id_user) {
		this.id_user = id_user;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}