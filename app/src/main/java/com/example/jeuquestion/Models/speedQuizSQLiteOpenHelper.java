package com.example.jeuquestion.Models;

import java.util.ArrayList;
import java.util.List;

public class speedQuizSQLiteOpenHelper {

    private final ArrayList<Question> questionsList = new ArrayList<>();

    public  speedQuizSQLiteOpenHelper() {

        questionsList.add(new Question("La Terre est plate ?", false));
        questionsList.add(new Question("Java est un langage de programmation ?", true));
        questionsList.add(new Question("Le Canada est le plus grand pays du monde ?", false));
        questionsList.add(new Question("L'eau bout à 100 degrés Celsius ?", true));
        questionsList.add(new Question("Les éléphants peuvent voler ?", false));
        questionsList.add(new Question("Le Japon est une île ?", true));
        questionsList.add(new Question("La pizza a été inventée en Italie ?", true));
        questionsList.add(new Question("Les pingouins peuvent voler ?", true));
        questionsList.add(new Question("Les dauphins sont des poissons ?", false));
        questionsList.add(new Question("Le soleil est une étoile ?", true));
        questionsList.add(new Question("Les serpents ont des pattes ?", false));
        questionsList.add(new Question("Le miel est produit par les abeilles ?", true));
        questionsList.add(new Question("Les ordinateurs peuvent penser ?", false));
        questionsList.add(new Question("Le soleil tourne autour de la Terre ?", false));
        questionsList.add(new Question("Le café contient de la caféine ?", true));
        questionsList.add(new Question("Le Kilimandjaro est la plus haute montagne d'Afrique ?", true));
        questionsList.add(new Question("Les kangourous vivent en Amérique du Sud ?", false));
        questionsList.add(new Question("La glace est chaude ?", false));
        questionsList.add(new Question("Le Python est un langage de programmation ?", true));
        questionsList.add(new Question("La Lune est habitée ?", false));
    }
    public List<Question> getQuestions() {
        return questionsList;
    }
    public boolean getAnswer(int index){
        return questionsList.get(index).getReponse();
    }
}

