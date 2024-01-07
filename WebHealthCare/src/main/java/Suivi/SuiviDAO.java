package Suivi;
import java.util.List;
import java.util.Map;
import java.util.Set;
public interface SuiviDAO {
    void createSuivi(Suivi suivi);
    List<Integer> getDegresDouleurByUserId(int userId);
    
    Set<String> getDistinctAggravationsByUserId(int userId);
    Set<String> getDistinctLocalisationByUserId(int userId);
    Set<String> getDistinctSymptomesByUserId(int userId);
    Set<String> getDistinctSentimentsByUserId(int userId);
    List<String> getDatesByUserId(int userId);
    
    Map<String, Double> getPercentageOfLocalisations(int userId);
    Map<String, Double> getPercentageOfAggravations(int userId);
    Map<String, Double> getPercentageOfSymptomes(int userId);
    Map<String, Double> getPercentageOfSentiments(int userId);
}