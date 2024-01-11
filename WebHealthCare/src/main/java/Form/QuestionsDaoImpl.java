package Form;

import DAO.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Form.ResponseChoicesDAO;
public class QuestionsDaoImpl implements QuestionsDAO {
    private DAOFactory daoFactory;
    private ResponseChoicesDAO responseChoicesDAO;

    public QuestionsDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
       
        this.responseChoicesDAO = daoFactory.getResponseChoicesDao();
    }
  
    public List<Questions> getQuestionsWithChoices() {
        List<Questions> questionsList = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM questions");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Questions question = new Questions();
                question.setId_question(resultSet.getInt("id_question"));
                question.setQuestion(resultSet.getString("question"));
                question.setId_mode(resultSet.getInt("id_mode"));
                question.setHas_choices(resultSet.getBoolean("has_choices"));
  if (question.getHas_choices()) {
                    List<String> choices = responseChoicesDAO.getChoicesForQuestion(question.getId_question());
                    question.setChoices(choices);
                }

                questionsList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }

        return questionsList;
    }

   
    private List<String> getChoicesForQuestion(int questionId) {
        List<String> choices = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT choice_text FROM reponse_choices WHERE id_question = ?")) {

            statement.setInt(1, questionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    choices.add(resultSet.getString("choice_text"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }

        return choices;
    }
    

    

    public List<ResponseMode> getResponseModes() {
        List<ResponseMode> responseModes = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM reponse_mode");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ResponseMode mode = new ResponseMode();
                mode.setIdMode(resultSet.getInt("id_mode"));
                mode.setMode(resultSet.getString("mode"));
                responseModes.add(mode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        return responseModes;
    }
}
