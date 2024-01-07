package Form;

import java.util.List;

public interface QuestionsDAO {
    List<Questions> getQuestionsWithChoices();
    List<ResponseMode> getResponseModes();
}
