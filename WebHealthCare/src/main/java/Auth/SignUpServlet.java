package Auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.DAOFactory;
import DAO.UserDAO;
import User.User;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Effectuez une validation appropriée des données du formulaire ici

        User newUser = new User();
        newUser.setusername(username);
        newUser.setemail(email);
        newUser.setpassword(password);

        UserDAO userDAO = new UserDAO(DAOFactory.getInstance());
        boolean userCreated = userDAO.createUser(newUser);

        if (userCreated) {
            // L'inscription a réussi, vous pouvez rediriger l'utilisateur vers la page de connexion, par exemple.
            response.sendRedirect("Login.jsp");
        } else {
            // L'inscription a échoué, vous pouvez rediriger l'utilisateur vers une page d'erreur d'inscription, par exemple.
            response.sendRedirect("signup.jsp?error=true");
        }
    }

}
