package io.muzoo.ooc.ecosystems;

import java.util.Random;

public abstract class Animal {

    //A shared random number generator to control breeding
    private static final Random rand = new Random();

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
    protected void incrementAge(){
        age++;
        if(age > getMaxAge()){
            alive = false;
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return the number of births (may be zero).
     */
    protected int breed(){
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
