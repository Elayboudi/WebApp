package Comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.DAOFactory;

/**
 * Servlet implementation class EditCommentServlet
 */
public class EditCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long commentId = Long.parseLong(request.getParameter("commentId"));

        // Récupérer le commentaire de la base de données
        CommentDAO commentDAO = new CommentDaoImpl(DAOFactory.getInstance());
        Comment comment = commentDAO.getCommentById(commentId);

        // Mettre le commentaire dans la session pour l'édition
        request.getSession().setAttribute("editComment", comment);

        // Rediriger vers la page d'édition
        response.sendRedirect("edit-comment.jsp");
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer les données du formulaire
        Long commentId = Long.parseLong(request.getParameter("commentId"));
        String updatedCommentText = request.getParameter("comment");

        // Récupérer le commentaire depuis la session
        Comment comment = (Comment) request.getSession().getAttribute("editComment");

        // Mettre à jour le commentaire
        comment.setComment(updatedCommentText);

        // Mettre à jour le commentaire dans la base de données
        CommentDAO commentDAO = new CommentDaoImpl(DAOFactory.getInstance());
        commentDAO.updateComment(comment);

        // Supprimer le commentaire de la session après la mise à jour
        request.getSession().removeAttribute("editComment");

        // Rediriger vers la page des commentaires
        response.sendRedirect("comments.jsp?blogId=" + comment.getIdBlog());
    }
	}

