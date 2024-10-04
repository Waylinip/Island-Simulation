package javarush.finalproject;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.herbivores.*;
import javarush.finalproject.Animals.predators.*;
import javarush.finalproject.Plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class Simulation {

    private final Island island;
    private final List<Animal> animals;
    private final List<Plant> plants;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // Пул для запуска основных циклов симуляции
//    private final ExecutorService animalTaskPool = Executors.newFixedThreadPool(26); // Пул для задач животных
    private final ExecutorService animalTaskPool = Executors.newVirtualThreadPerTaskExecutor(); // Пул для задач животных

    private static final Map<Class<? extends Animal>, String> symbolsByAnimal = new HashMap<>();
    static {
        symbolsByAnimal.put(Wolf.class, "\uD83D\uDC3A");
        symbolsByAnimal.put(Deer.class, "\uD83E\uDD8C");
        symbolsByAnimal.put(Cow.class, "\uD83D\uDC04");
        symbolsByAnimal.put(Duck.class, "\uD83E\uDD86");
        symbolsByAnimal.put(Sheep.class, "\uD83D\uDC11");
        symbolsByAnimal.put(Bear.class, "\uD83D\uDC3B");
        symbolsByAnimal.put(Goat.class, "\uD83D\uDC10");
        symbolsByAnimal.put(Snake.class, "\uD83D\uDC0D");
        symbolsByAnimal.put(Boar.class, "\uD83D\uDC17");
        symbolsByAnimal.put(Horse.class, "\uD83D\uDC34");
        symbolsByAnimal.put(Hare.class, "\uD83D\uDC30");
        symbolsByAnimal.put(Fox.class, "\uD83E\uDD8A");
        symbolsByAnimal.put(Eagle.class, "\uD83E\uDD85");
        symbolsByAnimal.put(Mouse.class, "\uD83D\uDC2D");
        symbolsByAnimal.put(Сaterpillar.class , "\uD83D\uDC1B");
    }
    private static final Map<Class<? extends Plant>, String> symbolsByPlant = Map.of(
            Plant.class, "\uD83C\uDF31"
    );

    public Simulation(Island island, List<Animal> animals, List<Plant> plants) {
        this.island = island;
        this.animals = animals;
        this.plants = plants;
    }

    public void runSimulation(int steps) {
        island.growPlants(200);
//        Runnable simulationTask = () -> {
            for (int step = 0; step < steps; step++) {
                System.out.println("\nСостояние локации перед шагом " + (step + 1) + ": ");
                showStat();


                runTaskInParallel(animals, this::makeTurnEat);

                island.simulateReproduction(animals);

                runTaskInParallel(animals, this::makeTurnMove);
                island.growPlants(50);

                plants.forEach(this::makeTurnPlant);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                }

                System.out.println("\nСостояние локации после шага " + (step + 1) + ": ");
                showStat();
                island.printAnimalsInMap();
            }

            shutdownExecutors();
//        };


//        scheduler.schedule(simulationTask, 0, TimeUnit.SECONDS);
    }

    private void runTaskInParallel(List<Animal> animals, Consumer<Animal> action) {
        List<Future<?>> futures = new ArrayList<>();
        for (Animal animal : animals) {
            futures.add(animalTaskPool.submit(() -> action.accept(animal)));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void makeTurnMove(Animal animal) {
        if (animal.isAlive()) {
            synchronized (animal) {
                animal.move(island);
                animal.decreaseSatiety();
            }
        }
    }

    private void makeTurnEat(Animal animal) {
        if (animal.isAlive()) {
            synchronized (animal) {
           // System.out.println(animal.getSpecies() + " пытается поесть. Сытость перед: " + animal.getSatiety());
                animal.eatInLoc(island);
               // System.out.println(animal.getSpecies() + " завершил попытку поесть. Сытость после: " + animal.getSatiety());
            }



        }
    }

    private void makeTurnPlant(Plant plant) {
        synchronized (plant) {
            plant.grow();
        }
    }

    private void showStat() {
        int maxSize = 0;
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                var location = island.getLocation(x, y);
                var currentSize = location.getAnimals().size() + location.getPlants().size();
                if (currentSize > maxSize) {
                    maxSize = currentSize;
                }
            }
        }

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);
                List<String> animalSymbols = new ArrayList<>();
                for (Animal animal : location.getAnimals()) {
                    String orDefault = symbolsByAnimal.getOrDefault(animal.getClass(), "*");
                    animalSymbols.add(orDefault);
                }

                List<String> plantsSymbols = new ArrayList<>();
                for (Plant plant : location.getPlants()) {
                    String orDefault = symbolsByPlant.getOrDefault(plant.getClass(), "*");
                    plantsSymbols.add(orDefault);
                }

                StringBuilder outputString = new StringBuilder()
                        .append("|")
                        .append(String.join("", animalSymbols))
                        .append(String.join("", plantsSymbols));
                var currentSize = animalSymbols.size() + plantsSymbols.size();
                if (currentSize < maxSize) {
                    outputString.append("〰".repeat(Math.max(0, maxSize - currentSize)));
                }
                outputString.append("|");
                System.out.print(outputString);
            }
            System.out.println();
        }
    }

    private void shutdownExecutors() {
        try {
            scheduler.shutdown();
            animalTaskPool.shutdown();
            if (!animalTaskPool.awaitTermination(1, TimeUnit.SECONDS)) {
                animalTaskPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            animalTaskPool.shutdownNow();
        }
    }
}
