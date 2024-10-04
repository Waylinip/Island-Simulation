package javarush.finalproject;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Plants.Plant;

import java.util.*;
import java.util.random.RandomGenerator;

public class Island {
    private Location[][] map;

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Location[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = new Location(x, y);
            }
        }
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return map[x][y];
        } else {
            throw new IndexOutOfBoundsException("Invalid location index.");
        }
    }


    public void addAnimal(Animal animal) {
        int randomX = RandomGenerator.getDefault().nextInt(width);
        int randomY = RandomGenerator.getDefault().nextInt(height);
        Location location = map[randomX][randomY];
        location.addAnimals(animal);
        animal.setCurrentCoordinates(new Coordinates(randomX, randomY));
       // System.out.println(animal.getSpecies() + " добавлен в локацию (" + randomX + ", " + randomY + ")");
    }

    public void growPlants(int plantsToGrow) {
        Random random = new Random();


        for (int i = 0; i < plantsToGrow; i++) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);
            Location location = getLocation(randomX, randomY);

            if (location.hasSpacePlants()) {
                Plant newPlant = new Plant("Grass",3,3);
                location.addPlant(newPlant);
                newPlant.setCurrentCoordinates(new Coordinates(randomX, randomY));
              // System.out.println("Растение добавлено в локацию (" + randomX + ", " + randomY + ")");
            } else {

                i--;
            }
        }
    }

    public void removeAnimal(int x, int y, Animal animal) {
        Location location = getLocation(x, y);
        location.removeAnimals(animal);
    }
    public void resetReproductionFlags() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Location location = map[x][y];
                location.getAnimals().forEach(Animal::resetReproductionFlag);
            }
        }
    }
    public void simulateReproduction(List<Animal> allAnimals) {
        resetReproductionFlags();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Location location = map[x][y];

                location.checkReproduction(allAnimals, location);
            }
        }
    }


    public void printAnimalsInMap() {
        System.out.println("Список всех живых животных на острове:");

        Map<String, Long> animalCountMap = new HashMap<>();

        for (Location[] row : map) {
            for (Location loc : row) {
                loc.getAnimals().forEach(animal -> {
                    if (animal.isAlive()) {

                        animalCountMap.merge(animal.getSpecies(), 1L, Long::sum);
                    }
                });
            }
        }

        // Выводим список животных и их количество
        if (animalCountMap.isEmpty()) {
            System.out.println("Нет живых животных.");
        } else {
            System.out.println("Живые животные:");
            animalCountMap.forEach((species, count) -> System.out.println("- " + species + ": " + count));
        }

        // Подсчет общего количества растений на острове
        long plantCount = Arrays.stream(map)
                .flatMap(Arrays::stream)
                .flatMap(loc -> loc.getPlants().stream())
                .count();

        System.out.println("Количество растений на острове: " + plantCount);
    }
}




