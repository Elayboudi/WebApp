package Form;

import DAO.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponseChoicesDAOImpl implements ResponseChoicesDAO {
    private DAOFactory daoFactory;

    public ResponseChoicesDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<String> getChoicesForQuestion(int questionId) {
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
}
