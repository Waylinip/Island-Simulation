package javarush.finalproject.Animals.predators;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.Predator;
import javarush.finalproject.Animals.herbivores.*;
import javarush.finalproject.Island;

public class Bear extends Predator {
    public Bear(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,island);
    }


    @Override
    public Animal kid() {
        return new Bear("Bear", 500, 3, 2, 80,island) ;
    }


    @Override
    protected double getEatingProbability(Animal prey) {
        if (prey instanceof Deer) return 80.0;
        if (prey instanceof Hare) return 40.0;
        if (prey instanceof Boar) return 50.0;
        if (prey instanceof Cow) return 70.0;
        if (prey instanceof Duck) return 40.0;
        if (prey instanceof  Goat) return 80.0;
        if (prey instanceof Horse) return 30.0;
        if (prey instanceof Mouse) return 90.0;
        if (prey instanceof Sheep) return 70.0;
        if (prey instanceof Сaterpillar) return 100.0;

        return 30.0;
    }
    @Override
    public void decreaseSatiety() {
        satiety -= 5.33; // TODO: Take from each class
        if (satiety <= 0) {
            die(false);
        }
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}

