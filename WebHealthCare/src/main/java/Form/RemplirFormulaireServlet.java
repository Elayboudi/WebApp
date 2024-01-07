package Form;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DAO.DAOFactory;
/**
 * Servlet implementation class RemplirFormulaireServlet
 */
public class RemplirFormulaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemplirFormulaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les questions avec les choix depuis le DAO
        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionsDAO questionsDAO = daoFactory.getQuestionsDao();
        List<Questions> questionsList = questionsDAO.getQuestionsWithChoices();

        // Récupérer les modes de réponse depuis le DAO
        List<ResponseMode> responseModes = questionsDAO.getResponseModes();

        // Stocker les modes de réponse dans une Map pour un accès facile
        Map<Integer, String> responseModesMap = responseModes.stream()
                .collect(Collectors.toMap(ResponseMode::getIdMode, ResponseMode::getMode));

        // Mettre les questions et les modes de réponse dans la requête pour les rendre disponibles dans la JSP
        request.setAttribute("questionsList", questionsList);
        request.setAttribute("responseModesMap", responseModesMap);

        // Envoyer la requête à la JSP appropriée
        RequestDispatcher dispatcher = request.getRequestDispatcher("diagnosticForm.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
