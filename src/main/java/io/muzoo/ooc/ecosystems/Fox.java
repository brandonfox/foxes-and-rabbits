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
public class Fox extends Predator{
    // Characteristics shared by all foxes (static fields).

    // The age at which a fox can start to breed.
    protected int getBreedingAge(){return 10;}
    // The age to which a fox can live.
    protected int getMaxAge(){return 150;}
    // The likelihood of a fox breeding.
    protected double getBreedingProbability(){return 0.09;}
    // The maximum number of births.
    protected int getMaxLitterSize(){return 3;}
    // The maximum food this animal can store
    @Override
    protected int getBellySize() {return 4;}
    //The food value of this fox
    @Override
    protected int getFoodValue() {return 7;}
    //The predator level of this predator
    @Override
    protected int getPredatorLevel() {return 1;}


    public Fox(boolean randomAge){
        super(randomAge);
    }

    @Override
    protected Animal getNewbornAnimal() {
        return new Fox(false);
    }

}
