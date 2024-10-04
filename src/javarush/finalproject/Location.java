package javarush.finalproject;

import javarush.finalproject.Animals.Animal;
import javarush.finalproject.Animals.predators.Bear;
import javarush.finalproject.Animals.predators.Wolf;
import javarush.finalproject.Plants.Plant;

import java.util.*;

public class Location {

    private final Coordinates coordinates;

    private List<Animal> animals;
    private List<Plant> plants;

    private static final int MAX_ANIMALS_PER_LOCATION = 4;
    private static final int MAX_PLANTS_PER_LOCATION = 4;




    public Location(int x, int y) {
        this.coordinates = new Coordinates(x, y);
        this.animals = Collections.synchronizedList(new ArrayList<>());
        this.plants = Collections.synchronizedList(new ArrayList<>());
    }



    public Coordinates getCoordinates() {
        return coordinates;
    }



    public void addAnimals(Animal animal) {
        if (canAddAnimal(animal)) {
            animals.add(animal);
            animal.setCurrentCoordinates(getCoordinates());
//            System.out.println(animal.getSpecies() + " добавлен на локацию.");
        } else {
          //  System.out.println("Не хватает места для животных.");
        }
    }


    public void removeAnimals(Animal animal) {
        animals.remove(animal);
    }


    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
//        System.out.println("Текущее состояние растений:");

        return plants;
    }




    public void addPlant(Plant plant) {
        if(canAddPlant(plant)){
            plants.add(plant);
            plant.setCurrentCoordinates(getCoordinates());
//            System.out.println(plant.getSpecies() + " добавлен на локацию.");
    } else {
      //  System.out.println("Не хватает места для растения.");
    }
    }
    public void removePlant(Plant plant) {
        plants.removeIf(p -> p == plant);
    }




    public boolean hasSpaceAnimals() {
        return animals.size() < MAX_ANIMALS_PER_LOCATION;
    }

    public boolean hasSpacePlants() {
        return plants.size() < MAX_PLANTS_PER_LOCATION;
    }
    public void checkReproduction(List<Animal> allAnimals, Location location) {
        List<Animal> newAnimals = new ArrayList<>();


        List<Animal> initialList = new ArrayList<>(location.getAnimals());

        Set<String> checkedPairs = new HashSet<>();

        for (int i = 0; i < initialList.size(); i++) {
            Animal firstAnimal = initialList.get(i);
            if (firstAnimal.hasReproduced) continue;

            for (int j = i + 1; j < initialList.size(); j++) {
                Animal secondAnimal = initialList.get(j);
                if (secondAnimal.hasReproduced) continue;

                if (firstAnimal.getSpecies().equals(secondAnimal.getSpecies())
                        && !checkedPairs.contains(getPairKey(firstAnimal, secondAnimal))) {

                    if (firstAnimal.canReproduce(secondAnimal) && location.canAddAnimal(firstAnimal)) {
                        Animal kid = firstAnimal.reproduce();
                        if (kid != null) {
                            newAnimals.add(kid);
                            location.addAnimals(kid);
                            allAnimals.add(kid);

                           // System.out.println(firstAnimal.getSpecies() + firstAnimal.getId() + " и " + secondAnimal.getSpecies() + secondAnimal.getId() + " размножились.");
                            firstAnimal.hasReproduced = true;
                            secondAnimal.hasReproduced = true;
                            break;
                        }
                    }

                    checkedPairs.add(getPairKey(firstAnimal, secondAnimal));
                }
            }
        }
    }

    private String getPairKey(Animal animal1, Animal animal2) {

        String minId = animal1.getId().compareTo(animal2.getId()) < 0 ? animal1.getId() : animal2.getId();
        String maxId = animal1.getId().compareTo(animal2.getId()) > 0 ? animal1.getId() : animal2.getId();

        return minId + "-" + maxId;
    }


    public boolean canAddAnimal(Animal animal) {
        // Проверяем тип животного
        long countSameSpecies = 0L;
        for (Iterator<Animal> iterator = animals.iterator(); iterator.hasNext(); ) {
            Animal a = iterator.next();
            if (a.getClass().equals(animal.getClass())) {
                countSameSpecies++;
            }
        }
        return countSameSpecies < animal.getMaxCountPerCell() && animals.size() < MAX_ANIMALS_PER_LOCATION;
    }
    public boolean canAddPlant(Plant plant) {
        // Проверяем тип растения
        long countSameSpecies = 0L;
        for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext(); ) {
            Plant p = iterator.next();
            if (p.getClass().equals(plant.getClass())) {
                countSameSpecies++;
            }
        }
        return countSameSpecies < plant.getMaxCountPerCell() && plants.size() < MAX_PLANTS_PER_LOCATION;
    }
    public void removeDeadAnimals() {
        animals.removeIf(animal -> !animal.isAlive());
    }
}