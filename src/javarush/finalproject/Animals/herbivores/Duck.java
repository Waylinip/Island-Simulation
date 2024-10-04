package javarush.finalproject.Animals.herbivores;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.predators.Wolf;
import javarush.finalproject.Island;
import javarush.finalproject.Location;
import javarush.finalproject.Plants.Plant;

import java.util.List;

public class Duck extends Herbivore {
    public Duck(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed, island);
    }
    @Override
    public void decreaseSatiety() {
        satiety -= 0.03;
        if (satiety <= 0) {
            die(false);
        }
    }
    @Override
    public Animal kid() {
        return new Duck("Duck", 3, 3, 4, 1,island) ;
    }




    @Override
    public double getWeight() {
        return this.weight;
    }
}
