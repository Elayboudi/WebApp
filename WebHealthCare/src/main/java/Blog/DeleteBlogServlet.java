package Blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.DAOFactory;

/**
 * Servlet implementation class DeleteBlogServlet
 */
public class DeleteBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int blogId = Integer.parseInt(request.getParameter("blogId"));

        // Supprimer le blog de la base de données
        DAOFactory daoFactory = DAOFactory.getInstance();
        BlogDAO blogDAO = daoFactory.getBlogDAO();
        blogDAO.deleteBlog(blogId);

        // Rediriger vers la page d'affichage des blogs de l'utilisateur
        response.sendRedirect("UserBlogsServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
