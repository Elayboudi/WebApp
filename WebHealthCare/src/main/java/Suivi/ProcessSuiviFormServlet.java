package Suivi;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import DAO.DAOFactory;
import Form.FormDAO;
import User.User;
import Suivi.SuiviDAO;
import Suivi.SuiviDaoImpl;

/**
 * Servlet implementation class ProcessSuiviFormServlet
 */
public class ProcessSuiviFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessSuiviFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

        DAOFactory daoFactory = DAOFactory.getInstance();
        SuiviDAO suiviDAO = daoFactory.getSuiviDAO();

        Set<String> sentiments = suiviDAO.getDistinctSentimentsByUserId(user.getid());
        Set<String> aggravations = suiviDAO.getDistinctAggravationsByUserId(user.getid());
        Set<String> symptomes = suiviDAO.getDistinctSymptomesByUserId(user.getid());
        Set<String> localisations = suiviDAO.getDistinctLocalisationByUserId(user.getid());

        // Mettre à jour la session avec les nouvelles données
        request.getSession().setAttribute("sentiments", sentiments);
        request.getSession().setAttribute("aggravations", aggravations);
        request.getSession().setAttribute("symptomes", symptomes);
        request.getSession().setAttribute("localisations", localisations);

        Double MoyenneDouleur = calculateMoyenne(suiviDAO, user.getid());
        request.getSession().setAttribute("moyenneDouleur", MoyenneDouleur);

        Map<String, Double> aggravationPercentages = suiviDAO.getPercentageOfAggravations(user.getid());
        request.getSession().setAttribute("aggravationPercentages", aggravationPercentages);

        Map<String, Double> sentimentsPercentages = suiviDAO.getPercentageOfSentiments(user.getid());
        request.getSession().setAttribute("sentimentsPercentages", sentimentsPercentages);

        Map<String, Double> symptomesPercentages = suiviDAO.getPercentageOfSymptomes(user.getid());
        request.getSession().setAttribute("symptomesPercentages", symptomesPercentages);

        Map<String, Double> localisationPercentages = suiviDAO.getPercentageOfLocalisations(user.getid());
        request.getSession().setAttribute("localisationPercentages", localisationPercentages);

        List<String> dates = suiviDAO.getDatesByUserId(user.getid());
        List<Integer> degrees = suiviDAO.getDegresDouleurByUserId(user.getid());
        request.getSession().setAttribute("dates", dates);
        request.getSession().setAttribute("degrees", degrees);

        response.sendRedirect("result.jsp");
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    User user = (User) request.getSession().getAttribute("user");

	    DAOFactory daoFactory = DAOFactory.getInstance();
	    SuiviDAO suiviDAO = daoFactory.getSuiviDAO();
	    // Récupérer les données du formulaire
	    Suivi suivi = new Suivi();
	    suivi.setUser(user);
	    suivi.setLocalisations(request.getParameterValues("localisation_douleurs"));
	    suivi.setSymptomes(request.getParameterValues("symptomes"));
	    suivi.setAggravations(request.getParameterValues("ce_qui_aggrave_douleur"));
	    suivi.setSentiments(request.getParameterValues("sentiments"));
	    suivi.setDegreDouleur(Integer.parseInt(request.getParameter("degre_douleur")));

	    // Utiliser le DAO pour enregistrer les données
	    
	    suiviDAO.createSuivi(suivi);
	    Set<String> sentiments = suiviDAO.getDistinctSentimentsByUserId(user.getid());
	    Set<String> aggravations = suiviDAO.getDistinctAggravationsByUserId(user.getid());
	    Set<String> symptomes = suiviDAO.getDistinctSymptomesByUserId(user.getid());
	    Set<String> localisations = suiviDAO.getDistinctLocalisationByUserId(user.getid());
	    
	   
	    // Mettre à jour la session avec les nouvelles données
	    request.getSession().setAttribute("sentiments", sentiments);
	    request.getSession().setAttribute("aggravations", aggravations);
	    request.getSession().setAttribute("symptomes", symptomes);
	    request.getSession().setAttribute("localisations", localisations);
	    // Calculer la nouvelle moyenne avec les nouvelles données
	    
	    Double MoyenneDouleur = calculateMoyenne(suiviDAO, user.getid());
	    
	    // Mettre à jour la session avec la nouvelle moyenne
	    request.getSession().setAttribute("moyenneDouleur",MoyenneDouleur);
	    
	    Map<String, Double> aggravationPercentages = suiviDAO.getPercentageOfAggravations(user.getid());
	    request.getSession().setAttribute("aggravationPercentages", aggravationPercentages);
	    
		Map<String, Double> sentimentsPercentages = suiviDAO.getPercentageOfSentiments(user.getid());
		request.getSession().setAttribute("sentimentsPercentages", sentimentsPercentages);
		
		Map<String, Double> symptomesPercentages = suiviDAO.getPercentageOfSymptomes(user.getid());
		request.getSession().setAttribute("symptomesPercentages", symptomesPercentages);
			   
	    Map<String, Double> localisationPercentages = suiviDAO.getPercentageOfLocalisations(user.getid());
	    request.getSession().setAttribute("localisationPercentages", localisationPercentages);

	
	    List<String> dates = suiviDAO.getDatesByUserId(user.getid());
	    List<Integer> degrees = suiviDAO.getDegresDouleurByUserId(user.getid());
	    System.out.println("Dates: " + dates);
	    System.out.println("Degrees: " + degrees);
	    request.getSession().setAttribute("dates", dates);
	    request.getSession().setAttribute("degrees", degrees);

	    
	    response.sendRedirect("result.jsp");
	}
	
	private double calculateMoyenne(SuiviDAO suiviDAO, int userId) {
	    List<Integer> degresDouleur = suiviDAO.getDegresDouleurByUserId(userId);
	   
	    if (degresDouleur.isEmpty()) {
	        return 0.0; // Ou une valeur par défaut appropriée
	    }

	    int somme = 0;
	    for (int degre : degresDouleur) {
	        somme += degre;
	    }
	    
	    double moyenne = (double) somme / degresDouleur.size();

	    // Utilisation de DecimalFormat pour formater la moyenne
	    DecimalFormat df = new DecimalFormat("#,##");
	    String formattedMoyenne = df.format(moyenne);

	    // Conversion de la moyenne formatée en double
	    return Double.parseDouble(formattedMoyenne);}




}