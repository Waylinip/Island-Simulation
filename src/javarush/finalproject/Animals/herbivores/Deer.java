package javarush.finalproject.Animals.herbivores;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.predators.Wolf;
import javarush.finalproject.Island;
import javarush.finalproject.Plants.Plant;

public class Deer extends Herbivore {


    public Deer(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed, island);
    }

    @Override
    public Animal kid() {
        return new Deer("Deer", 300, 3, 4, 50,island) ;
    }

    @Override
    public void decreaseSatiety() {
        satiety -= 5;
        if (satiety <= 0) {
            die(false);
        }
    }





    @Override
    public double getWeight() {
        return this.weight;
    }
}
