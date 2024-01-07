package Form;

import java.util.List;

public class Questions {
    private int id_question;
    private String question;
    private int id_mode;
    private Boolean has_choices;
    private List<String> choices;
    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId_mode() {
        return id_mode;
    }

    public void setId_mode(int id_mode) {
        this.id_mode = id_mode;
    }

    public Boolean getHas_choices() {
        return has_choices;
    }

    public void setHas_choices(Boolean has_choices) {
        this.has_choices = has_choices;
    }
    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
