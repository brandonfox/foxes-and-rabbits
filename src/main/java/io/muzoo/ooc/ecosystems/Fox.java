package io.muzoo.ooc.ecosystems;

import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Fox extends Animal{
    // Characteristics shared by all foxes (static fields).

    // The age at which a fox can start to breed.
    protected int getBreedingAge(){return 10;}
    // The age to which a fox can live.
    protected int getMaxAge(){return 150;}
    // The likelihood of a fox breeding.
    protected double getBreedingProbability(){return 0.09;}
    // The maximum number of births.
    protected int getMaxLitterSize(){return 3;}
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 4;

    // Individual characteristics (instance fields).

    // The fox's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with random age.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     */
    public Fox(boolean randomAge) {
        super(randomAge);
        if (randomAge) {
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        } else {
            // leave age at 0
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }

    @Override
    protected Location huntOrMove(Field currentField, Field updatedField, Location currentLocation) {
        Location newLocation = findFood(currentField,location);
        if(newLocation == null){ //No food found - move randomly
            newLocation = updatedField.freeAdjacentLocation(location);
        }
        return newLocation;
    }

    @Override
    protected Animal getNewbornAnimal() {
        return new Fox(false);
    }

    @Override
    protected void incrementValues() {
        super.incrementValues();
        incrementHunger();
    }

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            alive = false;
        }
    }

    /**
     * Tell the fox to look for rabbits adjacent to its current location.
     *
     * @param field    The field in which it must look.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Field field, Location location) {
        Iterator adjacentLocations =
                field.adjacentLocations(location);
        while (adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.die();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
}
