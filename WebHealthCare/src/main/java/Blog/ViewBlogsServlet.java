package Blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import Comment.Comment;

import Comment.CommentDAO;
import DAO.DAOFactory;

/**
 * Servlet implementation class ViewBlogsServlet
 */
public class ViewBlogsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBlogsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
        BlogDAO blogDAO = daoFactory.getBlogDAO();
        List<Blog> blogs = blogDAO.getAllBlogs();

        // Pour chaque blog, récupérez les commentaires associés
        CommentDAO commentDAO = daoFactory.getCommentDAO();
        for (Blog blog : blogs) {
            List<Comment> comments = commentDAO.getAllCommentsByBlogId(blog.getID());
            blog.setComments(comments);
        }


        // Stocker la liste des blogs dans l'objet de requête
        request.setAttribute("blogs", blogs);
       
        // Rediriger vers la page JSP d'affichage des blogs
        request.getRequestDispatcher("viewBlogs.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
