package io.muzoo.ooc.ecosystems;

import java.util.List;

public abstract class Animal extends Actor {

    //The animal's age
    private int age;
    //Whether the animal is a live or not
    private boolean alive;

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

    /**\
     * The function to do animally stuff
     * (Aging and breeding and moving)
     * @param currentField The current field the animal is on
     * @param updatedField The future field the animal will (or wont) be on
     * @param newAnimals The list to add new animal pups to
     */
    public final void act(Field currentField, Field updatedField, List newAnimals){
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
    @Override
    protected void move(Field updatedField, Location locationToMove){
        if (locationToMove != null) {
            setLocation(locationToMove);
            updatedField.place(this, locationToMove);
        } else {
            // can neither move nor stay - overcrowding - all locations taken
            die();
        }
    }

    /**
     * Die :(
     */
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
     * Make little animal babies
     * @param updatedField The field to place the new animal babies on
     * @param newAnimals The list to add the animal babies to for bookkeeping purposes
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

    /**
     * @return The age at which an animal can breed
     */
    protected abstract int getBreedingAge();
    /**
     * @return The age to which this animal can live
     */
    protected abstract int getMaxAge();
    /**
     * @return The likelihood of this animal breeding
     */
    protected abstract double getBreedingProbability();
    /**
     * @return The maximum number of babies per litter
     */
    protected abstract int getMaxLitterSize();
    /**
     * @return New animal instance (Should be of same class)
     */
    protected abstract Animal getNewbornAnimal();

    /**
     * The function to get food and move on the map
     * @param currentField The field the animal is on
     * @param updatedField The field the animal will (or wont) be on
     * @param currentLocation The current location of the animal
     * @return The new location after finding food or moving
     */
    protected abstract Location huntOrMove(Field currentField,Field updatedField, Location currentLocation);

    /**
     * @return The food value of this animal when eaten
     */
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


}
