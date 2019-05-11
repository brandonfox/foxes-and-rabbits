package io.muzoo.ooc.ecosystems;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Acts as an animal factory purely for the initial map generation
 */
public class FieldActorFactory {

    private final Map<String,Double> actorProbabilities;
    private final Random random = new Random();

    public FieldActorFactory(){
        actorProbabilities = new HashMap<>();
    }
    public void setActorProbability(String actor, double probability){
        actorProbabilities.put(actor,probability);
    }
    public Actor getNewRandomActor(){
        float animalDecider = random.nextFloat();
        for (String a: actorProbabilities.keySet()) {
            animalDecider -= actorProbabilities.get(a);
            if(animalDecider <= 0){
                return createNewActor(a);
            }
        }
        //This should never happen but if it does function above is wrong
        return null;
    }
    private Actor createNewActor(String actor){
        switch(actor){
            case "rabbit":
                return new Rabbit(true);
            case "fox":
                return new Fox(true);
            case "tiger":
                return new Tiger(true);
            case "hunter":
                return new Hunter();
            default:
                System.out.println("Actor '" + actor + "' not recognised.");
                return null;
        }
    }
}