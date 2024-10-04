package javarush.finalproject.Animals;

import javarush.finalproject.Coordinates;
import javarush.finalproject.Island;
import javarush.finalproject.Location;

public abstract class Predator extends Animal {

    public Predator(String species, double weight, int maxCountPerCell, int speed, double foodNeed,Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,island);
    }




    @Override
    public void eat(Island island, Eatable food) {
        if (satiety < foodNeed) {
            if (food instanceof Herbivore && this.isAlive && ((Animal) food).isAlive()) {
                Animal prey = (Animal) food;
                double probability = getEatingProbability(prey);
                if (Math.random() * 100 < probability) {
                   System.out.println(this.species + " ate " + prey.getSpecies());
                    prey.die(true);
                    this.increaseSatiety(prey.getWeight());



                    Location location = island.getLocation(this.currentCoordinates.getX(), this.currentCoordinates.getY());
                    location.removeAnimals((Animal) food);
                } else {
                   // System.out.println(this.species + " не смог скушать " + prey.getSpecies());
                }
            }
        } else {
          //  System.out.println(this.species + " не голоден.");
        }


    }



    protected double getEatingProbability(Animal prey) {
        return 50.0;
    }
}