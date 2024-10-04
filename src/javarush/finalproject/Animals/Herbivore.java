package javarush.finalproject.Animals;

import javarush.finalproject.Coordinates;
import javarush.finalproject.Island;
import javarush.finalproject.Location;
import javarush.finalproject.Plants.Plant;

public abstract class Herbivore extends Animal{

    public Herbivore(String species, double weight, int maxCountPerCell, int speed, double foodNeed,Island island) {
        super(species, weight, maxCountPerCell, speed, foodNeed,island);
    }



    @Override
    public void eat(Island island, Eatable food) {
        if (satiety < foodNeed) {
            if (food instanceof Plant && this.isAlive) {
                Plant plant = (Plant) food;
               // System.out.println(this.species + " съел " + plant.getSpecies());
                this.satiety = Math.min(this.satiety + plant.getWeight(), this.foodNeed);
                plant.die();


                Location location = island.getLocation(this.currentCoordinates.getX(), this.currentCoordinates.getY());
                location.removePlant(plant); // Удаление растения из локации
            }
        } else {
           // System.out.println(this.species + " не голоден.");
        }
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
