package Comment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DAO.DAOFactory;
import DAO.UserDAO;
import User.User;

/**
 * Servlet implementation class GetAllCommentsByBlogServlet
 */
public class GetAllCommentsByBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllCommentsByBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blogIdParam = request.getParameter("blogId");

        if (blogIdParam != null && !blogIdParam.isEmpty()) {
            try {
                
                int blogId = Integer.parseInt(blogIdParam);
               UserDAO userDAO=new UserDAO(DAOFactory.getInstance());
                
                CommentDAO commentDAO = new CommentDaoImpl(DAOFactory.getInstance());
                List<Comment> comments = commentDAO.getAllCommentsByBlogId(blogId);
              
                List<Comment> commentsWithUsers = commentDAO.getAllCommentsWithUsersByBlogId(blogId);

                request.setAttribute("commentsWithUsers", commentsWithUsers);

                request.setAttribute("comments", comments);

                RequestDispatcher dispatcher = request.getRequestDispatcher("comments.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace(); 
                response.sendRedirect("error.jsp"); 
            }
        } else {
        }
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
