package User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.DAOFactory;
import DAO.UserDAO;

/**
 * Servlet implementation class ModifyUserServlet
 */
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Obtenez l'utilisateur actuellement authentifié (vous devez implémenter la logique d'authentification)
        User authenticatedUser = (User) request.getSession().getAttribute("authenticatedUser");

        // Pré-remplir les paramètres du formulaire avec les anciennes valeurs de l'utilisateur
        request.setAttribute("user", authenticatedUser);

        // Rediriger vers la page de modification
        request.getRequestDispatcher("modifyUser.jsp").forward(request, response);
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("password");
       
        // Obtenir l'utilisateur actuellement authentifié (vous devez implémenter la logique d'authentification)
        User user = (User) request.getSession().getAttribute("user");
        user.setusername(username);
        // Mettre à jour les informations de l'utilisateur
        user.setemail(email);
        user.setpassword(newPassword);

        // Appeler la méthode de mise à jour dans le DAO
        UserDAO userDAO = new UserDAO(DAOFactory.getInstance());
       
        boolean success = userDAO.updateUser(user);

        if (success) {
            // Rediriger vers une page de succès
            response.sendRedirect("home.jsp");
        } 
    
	}

}
