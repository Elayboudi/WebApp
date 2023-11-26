package Auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.DAOFactory;

/**
 * Servlet implementation class ResetPasswordServlet
 */
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
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
		String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String token = request.getParameter("token");

        // Vérifier si les mots de passe correspondent
        if (newPassword.equals(confirmPassword)) {
            // Réinitialiser le mot de passe dans la base de données
            if (resetPasswordInDatabase(token, newPassword)) {
                // Mot de passe réinitialisé avec succès, rediriger vers une page de confirmation
                response.sendRedirect("PasswordResetConfirmation.jsp");
            } else {
                // Échec de la réinitialisation du mot de passe, rediriger vers une page d'erreur
                response.sendRedirect("PasswordResetError.jsp");
            }
        } else {
            // Les mots de passe ne correspondent pas, rediriger vers une page d'erreur
            response.sendRedirect("err.jsp");
        }
	}
	private boolean resetPasswordInDatabase(String token, String newPassword) {
        // Mettez à jour le mot de passe dans la base de données en utilisant le jeton
        try (Connection conn = DAOFactory.getInstance().getConnection()) {
            // Utilisez une requête SQL pour mettre à jour le mot de passe associé au jeton.
            String sql = "UPDATE users SET password = ? WHERE reset_token = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, token);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            // Gérez les erreurs de base de données correctement.
            e.printStackTrace();
            return false;
        }
    }

}
