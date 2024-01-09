package Comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import DAO.DAOFactory;
import User.User;

/**
 * Servlet implementation class AddCommentServlet
 */
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String commentText = request.getParameter("comment");
	    Integer blogId = Integer.parseInt(request.getParameter("blogId"));
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    System.out.println("Comment Text: " + commentText);
	    System.out.println("Blog ID: " + blogId);
	    System.out.println("User ID: " + user.getid());
	    // Créer un objet Comment
	    Comment comment = new Comment();
	    comment.setComment(commentText);
	    comment.setIdUser(user.getid());
	    comment.setIdBlog(blogId);

	    // Ajouter le commentaire à la base de données
	    CommentDAO commentDAO = new CommentDaoImpl(DAOFactory.getInstance());
	    commentDAO.addComment(comment);

	    // Rediriger vers la page des commentaires
	    response.sendRedirect("ViewBlogsServlet");
	}

}
