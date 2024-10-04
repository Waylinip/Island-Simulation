package javarush.finalproject.Animals.herbivores;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.predators.Wolf;
import javarush.finalproject.Island;
import javarush.finalproject.Plants.Plant;

public class Hare extends Herbivore {
    public Hare(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,  island);
    }



    @Override
    public void decreaseSatiety() {
        satiety -= 0.045;
        if (satiety <= 0) {
            die(false);
        }
    }
    @Override
    public Animal kid() {
        return new Hare("Hare", 20, 10, 2, 0.45,island) ;
    }


    @Override
    public double getWeight() {
        return this.weight;
    }
}
