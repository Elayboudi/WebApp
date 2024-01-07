package Auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.DAOFactory;
import DAO.UserDAO;
import User.User;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    UserDAO userDAO = new UserDAO(DAOFactory.getInstance());
	    User user = userDAO.findUserByUsername(username);

	    if (user != null && user.getpassword().equals(password)) {
	        // L'authentification a réussi, vous pouvez rediriger l'utilisateur vers la page home.jsp ou une autre page appropriée.
	        // Vous pouvez également définir la session ici pour marquer l'utilisateur comme authentifié.
	        HttpSession session = request.getSession();
	        session.setAttribute("user", user);
	        
	        // Redirection en utilisant directement la variable user
	        response.sendRedirect("home.jsp");
	    } else {
	        // L'authentification a échoué, redirigez l'utilisateur vers une page d'erreur de connexion.
	        // L'authentification a échoué, définissez l'attribut passwordError
	        request.setAttribute("passwordError", true);
	        request.getRequestDispatcher("Login.jsp").forward(request, response);
	    }
	}


}
