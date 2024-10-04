package javarush.finalproject.Animals.herbivores;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.predators.Eagle;
import javarush.finalproject.Island;

public class Сaterpillar extends Herbivore {
    public Сaterpillar(String species, double weight, int maxCountPerCell, int speed, double foodNeed, Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,island);
    }

    @Override
    public void decreaseSatiety() {
        satiety -= 0.01; // TODO: Take from each class
        if (satiety <= 0) {
            die(false);
        }
    }
    @Override
    public Animal kid() {
        return new Сaterpillar("Сaterpillar", 0.01, 10, 0, 2,island) ;
    }



    @Override
    public double getWeight() {
        return this.weight;
    }
}
