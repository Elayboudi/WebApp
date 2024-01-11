package Form;

import java.util.List;
import java.util.Map;

public class DiagnosticResultCalculator {

    public String calculateResult(Map<String, String[]> responses) {
        int totalScore = 0;
 for (Map.Entry<String, String[]> entry : responses.entrySet()) {
        	String questionId = entry.getKey();
            int questionIdInt = Integer.parseInt(questionId);
            String[] responseValues = entry.getValue();
            int questionScore = calculateQuestionScore(questionIdInt , responseValues);
            totalScore += questionScore;
        }
        if (totalScore <= 10) {
            return "Low Risk";
        } else if (totalScore <= 20) {
            return "Medium Risk";
        } else {
            return "High Risk";
        }
    }
    public int calculateTotalScore(List<Questions> questions, List<formulaire> responses) {
        int totalScore = 0;

        for (Questions question : questions) {
            for (formulaire response : responses) {
                if (response.getid_question() == question.getId_question()) {
                    String[] responseValues = { response.getresponse() };
                    totalScore += calculateQuestionScore(question.getId_question(), responseValues);
                }
            }
        }

        return totalScore;
    }
    
    private int calculateQuestionScore(int questionId, String[] responseValues) {
       int score = 0;
    	 switch (questionId) {
        case 1:
            // Example: +2 points for "Avant l'âge de 11 ans", +1 point for "À partir de 11 ans"
            if ("Avant l'age de 11 ans".equalsIgnoreCase(responseValues[0])) {
                score += 1;
            } else if ("À partir de 11 ans".equalsIgnoreCase(responseValues[0])) {
                score += 0;
            }
            break;
        case 2:
            // Example: +2 points for "Moins de 27 jours", +1 point for "Plus de 27 jours"
            if ("Moins de 27 jours".equalsIgnoreCase(responseValues[0])) {
                score += 0;
            } else if ("Plus de 27 jours".equalsIgnoreCase(responseValues[0])) {
                score += 1;
            }
            else score=0;
            break;
        case 3:
            
            if ("Oui".equalsIgnoreCase(responseValues[0])) {
                score += 1;
            } else if ("Non".equalsIgnoreCase(responseValues[0])) {
                score += 0;
            }
           
            break;
         case 4:
            
            if ("oui".equalsIgnoreCase(responseValues[0])) {
                score += 1;
            } else if ("Non".equalsIgnoreCase(responseValues[0])) {
                score += 0;
            }
           
           break;
         case 5:
             
             if ("Oui".equalsIgnoreCase(responseValues[0])) {
                 score += 1;
             } else if ("Non".equalsIgnoreCase(responseValues[0])) {
                 score += 0;
             }
             break;
         case 6:
        	 score+=1;
            break;
         case 7:
             // Example: +2 points for "Moins de 27 jours", +1 point for "Plus de 27 jours"
             if ("0-2".equalsIgnoreCase(responseValues[0])) {
                 score += 0;
             } else if ("3-5".equalsIgnoreCase(responseValues[0])) {
                 score += 1;
             }else if ("6-8".equalsIgnoreCase(responseValues[0])) {
                 score += 2;
             }else if ("9-10".equalsIgnoreCase(responseValues[0])) {
                 score += 3;
             }
            
             break;
         case 8:
             // Example: +1 point for each selected option
             for (String response : responseValues) {
                 if (response != null && !response.isEmpty()) {
                     score += 1;
                 }
             }
             break;
         case 9:
             // Example: +2 points for "Moins de 27 jours", +1 point for "Plus de 27 jours"
             if ("0-2".equalsIgnoreCase(responseValues[0])) {
                 score += 0;
             } else if ("3-5".equalsIgnoreCase(responseValues[0])) {
                 score += 1;
             }else if ("6-8".equalsIgnoreCase(responseValues[0])) {
                 score += 2;
             }else if ("9-10".equalsIgnoreCase(responseValues[0])) {
                 score += 3;
             }
            
             break;
         case 10:
             // Example: +1 point for each selected option
             for (String response : responseValues) {
                 if (response != null && !response.isEmpty()) {
                     score += 1;
                 }
             }
             break;
         case 11:
             // Example: +2 points for "Moins de 27 jours", +1 point for "Plus de 27 jours"
             if ("1 à 7 jours".equalsIgnoreCase(responseValues[0])) {
                 score += 0;
             } else if ("Plus de 7 jours".equalsIgnoreCase(responseValues[0])) {
                 score += 1;
             }break;
         case 12:
             // Example: +2 points for "Moins de 27 jours", +1 point for "Plus de 27 jours"
             if ("Abondantes".equalsIgnoreCase(responseValues[0])) {
                 score += 0;
             } else if ("Modérées".equalsIgnoreCase(responseValues[0])) {
                 score += 1;
             }else if ("Légères".equalsIgnoreCase(responseValues[0])) {
                 score += 2;
             }
            
             break;
         case 13:
             // Example: +2 points for "Moins de 27 jours", +1 point for "Plus de 27 jours"
             if ("Régulier".equalsIgnoreCase(responseValues[0])) {
                 score += 0;
             } else if ("Irrégulier".equalsIgnoreCase(responseValues[0])) {
                 score += 1;
             }else if ("Saignement ou spotting entre les périodes".equalsIgnoreCase(responseValues[0])) {
                 score += 2;
             }
            
             break;
         case 14:
             // Example: +1 point for each selected option
             for (String response : responseValues) {
                 if (response != null && !response.isEmpty()) {
                     score += 1;
                 }
             }
             break;
         case 15:
             // Example: +2 points for "Avant l'âge de 11 ans", +1 point for "À partir de 11 ans"
             if ("Oui".equalsIgnoreCase(responseValues[0])) {
                 score += 1;
             } else if ("Non".equalsIgnoreCase(responseValues[0])) {
                 score += 0;
             }
             break;
        }
        return score;
    }
}
