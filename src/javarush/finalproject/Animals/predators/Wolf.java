package javarush.finalproject.Animals.predators;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.Predator;
import javarush.finalproject.Animals.herbivores.*;
import javarush.finalproject.Island;

public class Wolf extends Predator {
    public Wolf(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,island);
    }

    @Override
    protected double getEatingProbability(Animal prey) {
        if (prey instanceof Deer) return 50.0;
        if (prey instanceof Hare) return 70.0;
        if (prey instanceof Boar) return 40.0;
        if (prey instanceof Cow) return 60.0;
        if (prey instanceof Duck) return 70.0;
        if (prey instanceof Goat) return 60.0;
        if (prey instanceof Horse) return 30.0;
        if (prey instanceof Mouse) return 80.0;
        if (prey instanceof Sheep) return 70.0;
        if (prey instanceof Сaterpillar) return 0.0;

        return 30.0;
    }
//    protected double get1EatingProbability(Animal prey) {//switch case попробую
//        switch (prey.getClass()) {
//            case Deer -> 10.0
//        }
//    }

    @Override
    public void decreaseSatiety() {
        satiety -= 0.53;
        if (satiety <= 0) {
            die(false);
        }
    }

    @Override
    public Animal kid() {
        return new Wolf("Wolf", 50, 3, 3, 8,island) ;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
