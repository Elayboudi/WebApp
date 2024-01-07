package Suivi;


import java.time.LocalDate;

import User.User;

public class Suivi {
    private User user;
    private String[] localisations;
    private String[] symptomes;
    private String[] aggravations;
    private String[] sentiments;
    private int degreDouleur;
    private LocalDate date;

    // Ajoutez d'autres champs ou méthodes nécessaires

    // Constructeur
    public Suivi() {
        // Initialisation des tableaux pour éviter les nullPointerException
        this.localisations = new String[0];
        this.symptomes = new String[0];
        this.aggravations = new String[0];
        this.sentiments = new String[0];
    }

    // Getters et setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getLocalisations() {
        return localisations;
    }

    public void setLocalisations(String[] localisations) {
        this.localisations = localisations;
    }

    public String[] getSymptomes() {
        return symptomes;
    }

    public void setSymptomes(String[] symptomes) {
        this.symptomes = symptomes;
    }

    public String[] getAggravations() {
        return aggravations;
    }

    public void setAggravations(String[] aggravations) {
        this.aggravations = aggravations;
    }

    public String[] getSentiments() {
        return sentiments;
    }

    public void setSentiments(String[] sentiments) {
        this.sentiments = sentiments;
    }

    public int getDegreDouleur() {
        return degreDouleur;
    }

    public void setDegreDouleur(int degreDouleur) {
        this.degreDouleur = degreDouleur;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
