package javarush.finalproject;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.Eatable;
import javarush.finalproject.Animals.Herbivore;
import javarush.finalproject.Animals.Predator;
import javarush.finalproject.Animals.herbivores.*;
import javarush.finalproject.Animals.predators.*;
import javarush.finalproject.Plants.Plant;

import java.util.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int maxX = 20;
        int maxY = 20;

        Island island = new Island(maxX, maxY);

        List<Animal> animals = new ArrayList<>(List.of(
                new Deer("Deeer", 80, 3, 4, 50, island),
                new Deer("Deer", 80, 3, 4, 50, island),
                new Deer("Deer", 80, 3, 4, 50, island),
                new Deer("Deer", 80, 3, 4, 50, island),
                new Deer("Deer", 80, 3, 4, 50, island),
                new Deer("Deer", 80, 3, 4, 50, island),
                new Deer("Deer", 80, 3, 4, 50, island),
                new Wolf("Wolf", 50, 3, 3, 8, island),
                new Wolf("Wolf", 50, 3, 3, 8, island),
                new Wolf("Wolf", 50, 3, 3, 8, island),
                new Wolf("Wolf", 50, 3, 3, 8, island),
                new Wolf("Wolf", 50, 3, 3, 8, island),
                new Wolf("Wolf", 50, 3, 3, 8, island),
                new Cow("Cow", 400, 3, 3, 60,island),
                new Cow("Cow", 400, 3, 3, 60,island),
                new Cow("Cow", 400, 3, 3, 60,island),
                new Cow("Cow", 400, 3, 3, 60,island),
                new Cow("Cow", 400, 3, 3, 60,island),
                new Goat("Goat",60,3,2,20,island),
                new Goat("Goat",60,3,2,20,island),
                new Goat("Goat",60,3,2,20,island),
                new Goat("Goat",60,3,2,20,island),
                new Goat("Goat",60,3,2,20,island),
                new Sheep("Sheep", 50, 3, 2, 20, island),
                new Sheep("Sheep", 50, 3, 2, 20, island),
                new Sheep("Sheep", 50, 3, 2, 20, island),
                new Sheep("Sheep", 50, 3, 2, 20, island),
                new Sheep("Sheep", 50, 3, 2, 20, island),
                new Sheep("Sheep", 50, 3, 2, 20, island),
                new Bear("Bear", 500, 3, 2, 70,island),
                new Bear("Bear", 500, 3, 2, 70,island),
                new Bear("Bear", 500, 3, 2, 70,island),
                new Bear("Bear", 500, 3, 2, 70,island),
                new Bear("Bear", 500, 3, 2, 70,island),
                new Boar("Boar", 400, 3, 2, 50,island),
                new Boar("Boar", 400, 3, 2, 50,island),
                new Boar("Boar", 400, 3, 2, 50,island),
                new Boar("Boar", 400, 3, 2, 50,island),
                new Boar("Boar", 400, 3, 2, 50,island),
                new Boar("Boar", 400, 3, 2, 50,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Duck("Duck", 3, 3, 4, 1,island),
                new Snake("Snake", 8, 3, 1, 3,island),
                new Snake("Snake", 8, 3, 1, 3,island),
                new Snake("Snake", 8, 3, 1, 3,island),
                new Snake("Snake", 8, 3, 1, 3,island),
                new Snake("Snake", 8, 3, 1, 3,island),
                new Horse("Horse", 400, 3, 4, 60,island),
                new Horse("Horse", 400, 3, 4, 60,island),
                new Horse("Horse", 400, 3, 4, 60,island),
                new Horse("Horse", 400, 3, 4, 60,island),
                new Hare("Hare", 20, 3, 4, 0.45,island),
                new Hare("Hare", 20, 3, 4, 0.45,island),
                new Hare("Hare", 20, 3, 4, 0.45,island),
                new Hare("Hare", 20, 3, 4, 0.45,island),
                new Hare("Hare", 20, 3, 4, 0.45,island),
                new Hare("Hare", 20, 3, 4, 0.45,island),
                new Hare("Hare", 20, 3, 4, 0.45,island),
                new Fox("Fox", 15, 3, 2, 4,island),
                new Fox("Fox", 15, 3, 2, 4,island),
                new Fox("Fox", 15, 3, 2, 4,island),
                new Fox("Fox", 15, 3, 2, 4,island),
                new Fox("Fox", 15, 3, 2, 4,island),
                new Eagle("Eagle", 6, 3, 4, 2,island),
                new Eagle("Eagle", 6, 3, 4, 2,island),
                new Eagle("Eagle", 6, 3, 4, 2,island),
                new Eagle("Eagle", 6, 3, 4, 2,island),
                new Eagle("Eagle", 6, 3, 4, 2,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Mouse("Mouse", 0.05, 3, 1, 0.01,island),
                new Сaterpillar("Сaterpillar", 0.01, 1, 0, 1,island),
                new Сaterpillar("Сaterpillar", 0.01, 1, 0, 1,island),
                new Сaterpillar("Сaterpillar", 0.01, 1, 0, 1,island),
                new Сaterpillar("Сaterpillar", 0.01, 1, 0, 1,island)
        ));

        List<Plant> plants = new ArrayList<>(List.of(
                new Plant("Трава", 4.0, 3),
                new Plant("Трава", 5.0, 3)
        ));


        animals.forEach(island::addAnimal);


        Simulation simulation = new Simulation(island, animals, plants);
        simulation.runSimulation(40);
    }
}








//        Island island = new Island(10, 10, 100);
//        Location[][] map = island.getMap();
//        int maxX = map.length;
//        int maxY = map[0].length;
//
//        for (int x = 0; x < map.length; x++) {
//            for (int y = 0; y < map[x].length; y++) {
//                var location = map[x][y];
//                location.getAnimals().forEach(animal -> {
//                    var nextCoordinates = animal.getNextCoordinates(location.getCoordinates(), maxX, maxY);
//                });
//            }


//    private static void showStat(Island island) {
//        Location[][] map = island.getMap();
//
//         Var 1
       // for (int x = 0; x < map.length; x++) {
//            for (int y = 0; y < map[x].length; y++) {
//                Location location = map[x][y];
//                System.out.println("(" + x + ", " + y + ")");
//                System.out.println("\t");
//                location.getPlants().forEach(plant -> System.out.println("\t" + plant));
//                location.getAnimals().forEach(animal -> System.out.println("\t" + animal));
//            }
//        }
        // Var 2
//        for (int x = 0; x < map.length; x++) {
//            for (int y = 0; y < map[x].length; y++) {
//                Location location = map[x][y];
//                System.out.println("(" + x + ", " + y + ")");
//                System.out.println("\t");
//                location.getPlants().forEach(plant -> System.out.println("\t" + plant));
//                location.getAnimals().forEach(animal -> System.out.println("\t" + animal));
//            }
//        }
//    }

