package javarush.finalproject.Plants;

import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Coordinates;

public class Plant implements Eatable {
    protected String species;
    public double weight;
    protected final int maxCountPerCell;
    protected boolean isAlive;
    protected Coordinates currentCoordinates;

    public Plant(String name,double weight,int maxCountPerCell) {
        this.species = name;
        this.weight = weight;
        this.isAlive = true;
        this.maxCountPerCell= maxCountPerCell;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getSpecies() {
        return species;
    }
    public double getWeight(){
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void setCurrentCoordinates(Coordinates currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    public void grow() {
        this.weight += 1;
    }


    public int getMaxCountPerCell() {
        return maxCountPerCell;
    }
    @Override
    public String toString() {
        return species + " (Жив: " + isAlive() + ")";
    }
    public void die() {
        this.isAlive = false;
       // System.out.println(this.species + " было съедено.");
    }

    }
