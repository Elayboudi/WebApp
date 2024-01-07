package Suivi;

import DAO.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SuiviDaoImpl implements SuiviDAO {
    private DAOFactory daoFactory;

    public SuiviDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    private String[] defaultIfNull(String[] array, String[] defaultValue) {
        return array != null ? array : defaultValue;
    }

    @Override
    public void createSuivi(Suivi suivi) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            
            // Créez votre requête SQL pour insérer les données du suivi dans la base de données
            String query = "INSERT INTO suivi (id_user, localisations, symptomes, aggravations, sentiments, degre_douleur) " +
                           "VALUES (?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);

            // Remplacez les placeholders par les valeurs réelles du suivi
            preparedStatement.setInt(1, suivi.getUser().getid());
            preparedStatement.setString(2, String.join(",", defaultIfNull(suivi.getLocalisations(), new String[0])));
            preparedStatement.setString(3, String.join(",", defaultIfNull(suivi.getSymptomes(), new String[0])));
            preparedStatement.setString(4, String.join(",", defaultIfNull(suivi.getAggravations(), new String[0])));
            preparedStatement.setString(5, String.join(",", defaultIfNull(suivi.getSentiments(), new String[0])));
            preparedStatement.setInt(6, suivi.getDegreDouleur());



            // Exécutez la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions correctement dans votre application
        } finally {
            // Fermez les ressources (Connection, PreparedStatement) ici dans le bloc finally
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Gérez les exceptions correctement dans votre application
            }
        }
    }
    @Override
    public List<Integer> getDegresDouleurByUserId(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Integer> degresDouleur = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT degre_douleur FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                degresDouleur.add(resultSet.getInt("degre_douleur"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions correctement dans votre application
        } finally {
            // Fermez les ressources (Connection, PreparedStatement, ResultSet) ici dans le bloc finally
            // ...
        }

        return degresDouleur;
    }
    
    public List<String> getDatesByUserId(int userId) {
        List<String> dates = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT date FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                dates.add(date);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception selon vos besoins
        } finally {
            // Fermez les ressources (ResultSet, PreparedStatement, Connection)
            // ...
        }

        return dates;
    }

    
    
    public Set<String> getDistinctSentimentsByUserId(int userId) {
        List<String> sentiments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT sentiments FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Diviser la chaîne en utilisant la virgule comme séparateur
                String[] sentimentsArray = resultSet.getString("sentiments").split(",");
                
                // Ajouter chaque sentiment à la liste
                sentiments.addAll(Arrays.asList(sentimentsArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return new HashSet<>(sentiments);
    }

    public Set<String> getDistinctLocalisationByUserId(int userId) {
        List<String> localisations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT localisations FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Diviser la chaîne en utilisant la virgule comme séparateur
                String[] localisationsArray = resultSet.getString("localisations").split(",");
                
                // Ajouter chaque localisation à la liste
                localisations.addAll(Arrays.asList(localisationsArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return new HashSet<>(localisations);
    }

    public Set<String> getDistinctAggravationsByUserId(int userId) {
        List<String> aggravations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT aggravations FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Diviser la chaîne en utilisant la virgule comme séparateur
                String[] aggravationsArray = resultSet.getString("aggravations").split(",");
                
                // Ajouter chaque aggravation à la liste
                aggravations.addAll(Arrays.asList(aggravationsArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return new HashSet<>(aggravations);
    }

    public Set<String> getDistinctSymptomesByUserId(int userId) {
        List<String> symptomes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT symptomes FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Diviser la chaîne en utilisant la virgule comme séparateur
                String[] symptomesArray = resultSet.getString("symptomes").split(",");
                
                // Ajouter chaque symptôme à la liste
                symptomes.addAll(Arrays.asList(symptomesArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return new HashSet<>(symptomes);
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    
    
    
    public List<String> getSymptomesByUserId(int userId) {
        List<String> symptomes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT symptomes FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] symptomesArray = resultSet.getString("symptomes").split(",");
                symptomes.addAll(Arrays.asList(symptomesArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return symptomes;
    }
    public List<String> getSentimentsByUserId(int userId) {
        List<String> sentiments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT sentiments FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] sentimentsArray = resultSet.getString("sentiments").split(",");
                sentiments.addAll(Arrays.asList(sentimentsArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return sentiments;
    }
    public List<String> getAggravationsByUserId(int userId) {
        List<String> aggravations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT aggravations FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] aggravationsArray = resultSet.getString("aggravations").split(",");
                aggravations.addAll(Arrays.asList(aggravationsArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return aggravations;
    }
    public List<String> getLocalisationsByUserId(int userId) {
        List<String> localisations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            String query = "SELECT localisations FROM suivi WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] localisationsArray = resultSet.getString("localisations").split(",");
                localisations.addAll(Arrays.asList(localisationsArray));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return localisations;
    }
    public Map<String, Double> calculatePercentage(List<String> elements) {
        Map<String, Integer> countMap = new HashMap<>();

        // Compter le nombre d'occurrences de chaque élément
        for (String element : elements) {
            countMap.put(element, countMap.getOrDefault(element, 0) + 1);
        }

        // Calculer le pourcentage pour chaque élément
        Map<String, Double> percentageMap = new HashMap<>();
        int totalElements = elements.size();
        DecimalFormat df = new DecimalFormat("#,##");
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String element = entry.getKey();
            int count = entry.getValue();
            double percentage = (count / (double) totalElements) * 100;
            percentage = Double.valueOf(df.format(percentage));
            System.out.println("Element: " + element + ", Count: " + count + ", TotalElements: " + totalElements + ", Percentage: " + percentage);
            percentageMap.put(element, percentage);
        }

       

        return percentageMap;
    }

    public Map<String, Double> getPercentageOfSentiments(int userId) {
        List<String> sentiments = getSentimentsByUserId(userId);
        return calculatePercentage(sentiments);
    }
    public Map<String, Double> getPercentageOfSymptomes(int userId) {
        List<String> symptomes = getSymptomesByUserId(userId);
        return calculatePercentage(symptomes);
    }

    public Map<String, Double> getPercentageOfAggravations(int userId) {
        List<String> aggravations = getAggravationsByUserId(userId);
        return calculatePercentage(aggravations);
    }

    public Map<String, Double> getPercentageOfLocalisations(int userId) {
        List<String> localisations = getLocalisationsByUserId(userId);
        return calculatePercentage(localisations);
    }

    // Ajoutez des fonctions similaires pour les autres éléments (aggravations, symptômes, localisations)

}
