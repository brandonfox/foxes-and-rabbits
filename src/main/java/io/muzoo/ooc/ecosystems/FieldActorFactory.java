package io.muzoo.ooc.ecosystems;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Acts as an animal factory purely for the initial map generation
 */
public class FieldActorFactory {

    private final Map<String,Double> animalProbabilities;
    private final Random random = new Random();

    public FieldActorFactory(){
        animalProbabilities = new HashMap<>();
    }
    public void setAnimalProbability(String animal, double probability){
        animalProbabilities.put(animal,probability);
    }
    public Animal getNewRandomActor(){
        float animalDecider = random.nextFloat();
        for (String a:animalProbabilities.keySet()) {
            animalDecider -= animalProbabilities.get(a);
            if(animalDecider <= 0){
                return createNewAnimal(a);
            }
        }
        //This should never happen but if it does function above is wrong
        return null;
    }
    private Animal createNewAnimal(String animal){
        switch(animal){
            case "rabbit":
                return new Rabbit(true);
            case "fox":
                return new Fox(true);
            case "tiger":
                return new Tiger(true);
            default:
                System.out.println("Animal '" + animal + "' not recognised.");
                return null;
        }
    }
}