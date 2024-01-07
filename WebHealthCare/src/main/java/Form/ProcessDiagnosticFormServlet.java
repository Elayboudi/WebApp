package Form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import User.User;
import DAO.DAOFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessDiagnosticFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Get a DAOFactory instance
        DAOFactory daoFactory = DAOFactory.getInstance();

        // Get a QuestionsDAO instance
        QuestionsDAO questionsDAO = new QuestionsDaoImpl(daoFactory);

        // Get questions with choices
        List<Questions> questionsList = questionsDAO.getQuestionsWithChoices();

        // Get a FormDAO instance
        FormDAO formDAO = daoFactory.getFormDao();

        // Get responses from the form
        List<formulaire> responses = new ArrayList<>();
        for (Questions question : questionsList) {
        	 String paramName = Integer.toString(question.getId_question());  // Utiliser l'ID de la question directement
        	    String[] values = request.getParameterValues(paramName);

        	    if (values != null && values.length > 0) {
        	        for (String value : values) {
        	            formulaire reponse = new formulaire();
        	            reponse.setid_user(user.getid()); // Set the user ID
        	            reponse.setid_question(question.getId_question());
        	            reponse.setresponse(value);
        	            responses.add(reponse);
        	        }
        	    }
        }

        // Save responses in the database
        formDAO.addResponse(responses);

        // Calculate the diagnostic result and forward to a JSP page
        DiagnosticResultCalculator resultCalculator = new DiagnosticResultCalculator();
        String result = resultCalculator.calculateResult(request.getParameterMap());
        List<Questions> updatedQuestions = questionsDAO.getQuestionsWithChoices();
        session.setAttribute("questions", updatedQuestions);
        session.setAttribute("responses", responses);
        request.setAttribute("diagnosticResult", result);
        request.getRequestDispatcher("diagnosticResult.jsp").forward(request, response);
    }
}
