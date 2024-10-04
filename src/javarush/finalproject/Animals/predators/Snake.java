package javarush.finalproject.Animals.predators;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.Predator;
import javarush.finalproject.Animals.herbivores.*;
import javarush.finalproject.Island;

public class Snake extends Predator {
    public Snake(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,island);
    }


    @Override

    public Animal kid() {
        return new Snake("Snake", 8, 2, 1, 3,island) ;
    }

    @Override
    protected double getEatingProbability(Animal prey) {
        if (prey instanceof Deer) return 0.0;
        if (prey instanceof Hare) return 80.0;
        if (prey instanceof Boar) return 0.0;
        if (prey instanceof Cow) return 0.0;
        if (prey instanceof Duck) return 60.0;
        if (prey instanceof  Goat) return 3.0;
        if (prey instanceof Horse) return 0.0;
        if (prey instanceof Mouse) return 80.0;
        if (prey instanceof Sheep) return 3.0;
        if (prey instanceof Сaterpillar) return 00.0;

        return 30.0;
    }

    @Override
    public void decreaseSatiety() {
        satiety -= 0.2; // TODO: Take from each class
        if (satiety <= 0) {
            die(false);
        }
    }
    @Override
    public double getWeight() {
        return this.weight;
    }
}

