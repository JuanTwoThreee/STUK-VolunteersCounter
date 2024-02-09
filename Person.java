package stukVolonteer;

import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable{
    static int nbrOfPersons = 1;
    private String name;
    private int id;
    private int nbrPoints;
    private boolean isStyrelseMedlem = false;

    public Person(String namn){
        this.name = namn;
        this.id = nbrOfPersons;
        nbrOfPersons++;
    }

    public String getName(){
        //returnar namnet
        return this.name;
    }

    public Integer getNbrOfPoints(){
        return this.nbrPoints;
    }

    public boolean isQualifiedForTackMiddag(int treshold){
        return nbrPoints >= treshold;
    }

    public void setStyrelseMedlem(){
        this.isStyrelseMedlem = true;
    }

    public void addPoints(int points){
        this.nbrPoints += points;
    }

    @Override
    public String toString(){
        String finalString = getName() + ": " + getNbrOfPoints() + " poäng";

        if(isStyrelseMedlem){
            finalString = getName() + " (Styrelse) : " + getNbrOfPoints() + " poäng";
        }
        if (isQualifiedForTackMiddag(3)) {
            finalString += " ✅";
        }

        return finalString;
    }

    @Override
    public int compareTo(Person other) {
        // Implement comparison logic here
        // For example, compare names of persons for sorting
        return this.name.compareTo(other.getName());
    }

}