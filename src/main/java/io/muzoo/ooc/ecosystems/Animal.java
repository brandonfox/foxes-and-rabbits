package io.muzoo.ooc.ecosystems;

import java.util.List;
import java.util.Random;

public abstract class Animal {

    //A shared random number generator to control breeding
    protected static final Random rand = new Random();

    //The animal's age
    protected int age;
    //Whether the animal is a live or not
    protected boolean alive;
    //The animal's position
    protected Location location;

    public Animal(boolean randomAge){
        age = 0;
        alive = true;
        if(randomAge){
            age = rand.nextInt(getMaxAge());
        }
    }
    private void incrementAge(){
        age++;
        if(age > getMaxAge()){
            die();
        }
    }
    public final void beAnimal(Field currentField, Field updatedField, List newAnimals){
        incrementValues();
        if(isAlive()){
            breed(updatedField, newAnimals);
            Location newLocation = huntOrMove(currentField,updatedField,location);
            move(updatedField,newLocation);
        }
    }

    /**
     * Move the animal. If null animal dies
     * @param updatedField The updated where the new move location will be saved
     * @param locationToMove the location where the animal will be moved to
     */
    private void move(Field updatedField, Location locationToMove){
        if (locationToMove != null) {
            setLocation(locationToMove);
            updatedField.place(this, locationToMove);
        } else {
            // can neither move nor stay - overcrowding - all locations taken
            die();
        }
    }
    public void die(){
        alive = false;
    }

    /**
     * This function to increment values (age, hunger etc..)
     */
    protected void incrementValues(){
        incrementAge();
    }

    /**
     * Use this function to find food and move animal
     */

    private void breed(Field updatedField, List newAnimals){
        int births = getNewBirths();
        for (int b = 0; b < births; b++) {
            Animal newAnimal = getNewbornAnimal();
            newAnimals.add(newAnimal);
            Location loc = updatedField.randomAdjacentLocation(location);
            newAnimal.setLocation(loc);
            updatedField.place(newAnimal, loc);
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return the number of births (may be zero).
     */
    protected int getNewBirths(){
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()){
            births = rand.nextInt(getMaxLitterSize());
        }
        return births;
    }

    //The age at which this animal can breed.
    protected abstract int getBreedingAge();
    //The age to which this animal can live.
    protected abstract int getMaxAge();
    //The likelihood of this animal breeding.
    protected abstract double getBreedingProbability();
    //The maximum number of births at a time.
    protected abstract int getMaxLitterSize();
    //Function to return animal type (Must be overriden in inheriting classes)
    protected abstract Animal getNewbornAnimal();
    //Function to find out where to move the animal to
    protected abstract Location huntOrMove(Field currentField,Field updatedField, Location currentLocation);
    //Function to retrieve value of this animal as a food
    protected abstract int getFoodValue();
    /**
     * An animal can breed if it has reached the breeding age.
     */
    private boolean canBreed(){return age >= getBreedingAge();}

    /**
     * Check whether the animal is alive or not
     *
     * @return True if the animal is still alive
     */
    public boolean isAlive() {return alive;}

    /**
     * Set the animal's location.
     *
     * @param row The vertical coordinate of the location
     * @param col The horizontal coordinate of the location.
     */
    public void setLocation(int row, int col) { this.location = new Location(row,col); }

    /**
     * Set the animal's location
     *
     * @param location The animal's location
     */
    public void setLocation(Location location) { this.location = location; }
}
