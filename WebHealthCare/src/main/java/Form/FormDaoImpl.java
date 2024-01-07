package Form;

import DAO.DAOException;
import DAO.DAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FormDaoImpl implements FormDAO {
	private DAOFactory daoFactory;

    // Déplacez la déclaration de la requête SQL à l'extérieur de la méthode
  

    public FormDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Questions> getAllQuestions() {
        // Mettez en œuvre la logique pour récupérer toutes les questions depuis la base de données
        // Vous devrez probablement effectuer une jointure avec la table reponse_mode pour obtenir le mode de réponse
        List<Questions> questions = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT q.id_question, q.question, q.has_choices, r.id_mode FROM questions q " +
                         "JOIN reponse_mode r ON q.id_mode= r.id_mode";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Questions question = new Questions();
                        question.setId_question(resultSet.getInt("id_question"));
                        question.setQuestion(resultSet.getString("question"));
                        question.setHas_choices(resultSet.getBoolean("has_choices"));
                        question.setId_mode(resultSet.getInt("mode_name"));
                        questions.add(question);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions appropriées selon votre application.
        }
        return questions;
    }

    @Override
    public List<String> getChoicesForQuestion(int questionId) {
        List<String> choices = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT choice_text FROM reponse_choices WHERE id_question = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, questionId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        choices.add(resultSet.getString("choice_text"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions appropriées selon votre application.
        }
        return choices;
    }

    @Override
    public void saveResponses(List<formulaire> responses) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "INSERT INTO formulaire (id_user, id_question, responses) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (formulaire response : responses) {
                    statement.setInt(1, response.getid_user());
                    statement.setInt(2, response.getid_question());
                    statement.setString(3, response.getresponse());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions selon votre application.
        }
    }
    public void addResponse(List<formulaire> responses) throws DAOException {
        final String SQL_INSERT = "INSERT INTO formulaire (id_user, id_question, responses) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);

            // Utiliser un lot pour améliorer les performances d'insertion
            for (formulaire response : responses) {
                preparedStatement.setInt(1, response.getid_user());
                preparedStatement.setInt(2, response.getid_question());
                preparedStatement.setString(3, response.getresponse());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException(e);
        } 
    }

    private PreparedStatement initRequestPrepare(Connection connection, String sql, Object... parameters)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // Set parameters based on their types
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            int parameterIndex = i + 1;

            if (parameter instanceof Integer) {
                preparedStatement.setInt(parameterIndex, (Integer) parameter);
            } else if (parameter instanceof String) {
                preparedStatement.setString(parameterIndex, (String) parameter);
            } else {
                // Handle other types as needed
                // You might need to add more cases based on your specific requirements
            }
        }

        return preparedStatement;
    }
}
