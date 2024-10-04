package javarush.finalproject.Animals.herbivores;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.predators.Wolf;
import javarush.finalproject.Island;
import javarush.finalproject.Plants.Plant;

public class Sheep extends Herbivore {
    public Sheep(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,  island);
    }

    @Override
    public void decreaseSatiety() {
        satiety -= 1.3;
        if (satiety <= 0) {
            die(false);
        }
    }
    @Override
    public Animal kid() {
        return new Sheep("Sheep", 50, 3, 3, 20,island) ;
    }


    @Override
    public double getWeight() {
        return this.weight;
    }
}
