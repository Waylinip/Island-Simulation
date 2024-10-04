package javarush.finalproject.Animals.predators;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.Predator;
import javarush.finalproject.Animals.herbivores.*;
import javarush.finalproject.Island;

public class Fox extends Predator {
    public Fox(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,island);
    }




    protected double getEatingProbability(Animal prey) {
        if (prey instanceof Deer) return 0.0;
        if (prey instanceof Hare) return 70.0;
        if (prey instanceof Boar) return 0.0;
        if (prey instanceof Cow) return 0.0;
        if (prey instanceof Duck) return 60.0;
        if (prey instanceof  Goat) return 40.0;
        if (prey instanceof Horse) return 0.0;
        if (prey instanceof Mouse) return 90.0;
        if (prey instanceof Sheep) return 50.0;
        if (prey instanceof Сaterpillar) return 0.0;

        return 30.0;
    }
    @Override
    public void decreaseSatiety() {
        satiety -= 0.26;
        if (satiety <= 0) {
            die(false);
        }
    }

    @Override
    public Animal kid() {
        return new Fox("Fox", 15, 2, 2, 4,island) ;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
