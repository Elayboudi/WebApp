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
                // Convertir l'ID du blog en entier
                Long blogId = Long.parseLong(blogIdParam);

                // Récupérer les commentaires du blog depuis la base de données
                CommentDAO commentDAO = new CommentDaoImpl(DAOFactory.getInstance());
                List<Comment> comments = commentDAO.getAllCommentsByBlogId(blogId);

                // Ajouter les commentaires à la requête pour les afficher dans comments.jsp
                request.setAttribute("comments", comments);

                // Rediriger vers comments.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("comments.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'exception si l'ID du blog n'est pas un entier valide
                response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur si nécessaire
            }
        } else {
            response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur si l'ID du blog est manquant
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
