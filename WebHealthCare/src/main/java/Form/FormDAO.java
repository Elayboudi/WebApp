package Form;
import java.util.List;



public interface FormDAO {
	List<Questions> getAllQuestions();  
    List<String> getChoicesForQuestion(int questionId);
    void saveResponses(List<formulaire> responses);
    void addResponse(List<formulaire> responses);
	}
