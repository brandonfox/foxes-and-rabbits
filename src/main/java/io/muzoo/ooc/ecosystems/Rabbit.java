package io.muzoo.ooc.ecosystems;

import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Rabbit extends Animal{
    // Characteristics shared by all rabbits (static fields).


    // The age at which a rabbit can start to breed.
    protected int getBreedingAge(){return 5;}
    // The age to which a rabbit can live.
    protected int getMaxAge(){return 50;}
    // The likelihood of a rabbit breeding.
    protected double getBreedingProbability(){return 0.15;}
    // The maximum number of births.
    protected int getMaxLitterSize(){return 5;}


    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the rabbit will have a random age.
     */
    public Rabbit(boolean randomAge) {
        super(randomAge);
    }

    @Override
    protected Animal getNewbornAnimal() {
        return new Rabbit(false);
    }

    @Override
    protected Location huntOrMove(Field currentField,Field updatedField, Location currentLocation) {
        return updatedField.freeAdjacentLocation(currentLocation);
    }

    @Override
    protected int getFoodValue() {
        return 4;
    }
}
