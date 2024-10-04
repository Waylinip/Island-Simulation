package javarush.finalproject.Animals;

import javarush.finalproject.Coordinates;
import javarush.finalproject.Island;
import javarush.finalproject.Location;
import javarush.finalproject.Plants.Plant;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

public abstract class Animal implements Eatable,Reproduceable {

    final String id;
    protected String species;
    public double weight;
    protected int maxCountPerCell;
    protected int speed;
    protected double foodNeed;
    protected double satiety;

    protected boolean isAlive;
    protected Coordinates currentCoordinates;
    protected Island island;
    public boolean hasReproduced = false;

    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void setCurrentCoordinates(Coordinates currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    public Animal(String species, double weight, int maxCountPerCell, int speed, double foodNeed,Island island) {
        id = UUID.randomUUID().toString();
        this.species = species;
        this.weight = weight;
        this.maxCountPerCell = maxCountPerCell;
        this.speed = speed;
        this.foodNeed = foodNeed;
        this.satiety = foodNeed;
        this.isAlive = true;
        this.island = island;
    }

    public String getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public Location getCurrentLocation() {
        return island.getLocation(currentCoordinates.getX(), currentCoordinates.getY());
    }



    public boolean isAlive() {
        return isAlive;
    }

    // Животное умирает, если его голод достиг нуля
    public void die(boolean eaten) {
        this.isAlive = false;
        if (eaten) {

         //  System.out.println(species + " был съеден.");

        } else {
            System.out.println(species + " умер от голода.");
            Location currentLocation = getCurrentLocation();
            if (currentLocation != null) {
                island.removeAnimal(currentLocation.getCoordinates().getX(), currentLocation.getCoordinates().getY(), this);
            }
        }
    }
        public int getMaxCountPerCell () {
            return maxCountPerCell;
        }

        public abstract void eat (Island island, Eatable food);

    @Override
    public Animal reproduce() {
        Location currentLocation = getCurrentLocation();
        if (currentLocation != null) {
            long countSameSpecies = currentLocation.getAnimals().stream()
                    .filter(animal -> animal.getSpecies().equals(this.species) && animal.isAlive())
                    .count();

            if (countSameSpecies >= 2 && !hasReproduced) {  // Проверяем, размножалось ли животное
                Animal newAnimal = kid();
               currentLocation.addAnimals(newAnimal);
                if (newAnimal != null) {
                    newAnimal.setCurrentCoordinates(this.currentCoordinates);
                    currentLocation.addAnimals(newAnimal);
                    hasReproduced = true;
                    System.out.println(this.species + " размножились и родили нового " + newAnimal.getSpecies() + " на координатах " + newAnimal.getCurrentCoordinates() + "!");
                    return newAnimal;
                }
            }
        }
        return null;
    }


    public void resetReproductionFlag() {
        hasReproduced = false;
    }
    protected abstract Animal kid();

    public boolean canReproduce(Animal other) {
        if (!this.getSpecies().equals(other.getSpecies())) {
            return false;
        }
        if (!this.currentCoordinates.equals(other.getCurrentCoordinates())) {
            return false;
        }
        if (this.satiety <= 0.5 * this.foodNeed || other.satiety <= 0.5 * other.foodNeed) {
            return false;
        }
        return this.isAlive && other.isAlive && !this.hasReproduced && !other.hasReproduced;
    }


    public void move(Island island) {
        if (!isAlive) {
            return;
        }

        Random random = new Random();
        double stayProbability = 0.3;


        if (random.nextDouble() < stayProbability) {

            return;
        }

        var maxX = island.getWidth() - 1;
        var maxY = island.getHeight() - 1;


        Coordinates newCoordinates = getNextCoordinates(currentCoordinates, maxX, maxY);


        if (!newCoordinates.equals(currentCoordinates)) {
            Location currentLocation = island.getLocation(currentCoordinates.getX(), currentCoordinates.getY());
            Location newLocation = island.getLocation(newCoordinates.getX(), newCoordinates.getY());


            if (newLocation.hasSpaceAnimals()) {
                currentLocation.removeAnimals(this);
                newLocation.addAnimals(this);
                currentCoordinates = newCoordinates;
             //   System.out.println(species + " переместился на новые координаты: " + newCoordinates);
            } else {
              //  System.out.println("Невозможно переместить " + species + " на новые координаты: " + newCoordinates + ". Нет места.");
            }
        } else {
          //  System.out.println(species + " остался на своих координатах: " + currentCoordinates);
        }
    }
        public void eatInLoc (Island island){
            var location = island.getLocation(currentCoordinates.getX(), currentCoordinates.getY());


            if (this instanceof Predator) {
                Animal prey = null;
                for (Animal animal : location.getAnimals()) {
                    if (animal instanceof Herbivore && animal.isAlive()) {
                        prey = animal;
                        break;
                    }
                }

                if (prey != null) {
                    this.eat(island, prey);
                    location.removeDeadAnimals();
                } else {
                   //System.out.println("Нет доступной пищи для " + this.species);
                }
            } else if (this instanceof Herbivore) {
                Plant plant = null;
                for (Plant p : location.getPlants()) {
                    if (p.isAlive()) {
                        plant = p;
                        break;
                    }
                }
                if (plant != null) {
                    this.eat(island, plant);
                } else {
                  // System.out.println("Нет доступной пищи для " + this.species);
                }
            }

        }

        public double getSatiety () {
            return satiety;
        }

        public Coordinates getNextCoordinates (Coordinates currentCoordinates,int maxX, int maxY){
            var cellsCountToMove = RandomGenerator.getDefault().nextInt(speed + 1);
            if (cellsCountToMove != 0) {
                Coordinates newCoordinates = currentCoordinates;
                for (int i = 0; i < cellsCountToMove; i++) {
                    var nextDirection = getNextMoveDirection();
                    switch (nextDirection) {
                        case 0 -> {
                            if (newCoordinates.getY() + 1 <= maxY) {
                                newCoordinates = new Coordinates(
                                        newCoordinates.getX(),
                                        newCoordinates.getY() + 1
                                );
                            }
                        }
                        case 1 -> {
                            if (newCoordinates.getX() + 1 <= maxX) {
                                newCoordinates = new Coordinates(
                                        newCoordinates.getX() + 1,
                                        newCoordinates.getY()
                                );
                            }
                        }
                        case 2 -> {
                            if (newCoordinates.getY() - 1 >= 0) {
                                newCoordinates = new Coordinates(
                                        newCoordinates.getX(),
                                        newCoordinates.getY() - 1
                                );
                            }
                        }
                        case 3 -> {
                            if (newCoordinates.getX() - 1 >= 0) {
                                newCoordinates = new Coordinates(
                                        newCoordinates.getX() - 1,
                                        newCoordinates.getY()
                                );
                            }
                        }
                    }
                }
                return newCoordinates;
            } else {
                return currentCoordinates;
            }
        }

        private int getNextMoveDirection () {
            //0 - up
            //1 - right
            //2 - down
            //3 - left
            var nextDirection = RandomGenerator.getDefault().nextInt(4);
            return nextDirection;
        }

        public void increaseSatiety ( double amount){
            this.satiety += amount;
            if (this.satiety > foodNeed) {
                this.satiety = foodNeed;
            }
        }

        public void decreaseSatiety () {
            satiety -= 0;
            if (satiety <= 0) {
                die(false); // Умирает от голода
            }
        }


        @Override
        public String toString () {
            return species + " (Вес: " + weight + ", Сытость: " + satiety + ", Жив: " + isAlive + ", Координаты: " + currentCoordinates + ")";
        }
}
