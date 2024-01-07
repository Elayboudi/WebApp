package Form;

import java.util.List;

public interface ResponseChoicesDAO {
	List<String> getChoicesForQuestion(int questionId);
}
