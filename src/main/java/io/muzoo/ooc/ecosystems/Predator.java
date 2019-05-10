package io.muzoo.ooc.ecosystems;

import java.util.Iterator;

public abstract class Predator extends Animal {

    private int foodLevel;

    public Predator(boolean randomAge){
        super(randomAge);
        if(randomAge){
            foodLevel = rand.nextInt(getBellySize());
        }else{
            foodLevel = getBellySize();
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

    /**
     * Tell the Predator to look for Animals adjacent to its current location.
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
            Object fieldObj = field.getObjectAt(where);
            if (fieldObj instanceof Animal) {
                Animal animal = (Animal)fieldObj;
                if (animal.isAlive()) {
                    if(eat(animal))
                        return where;
                }
            }
        }
        return null;
    }

    @Override
    protected void incrementValues() {
        super.incrementValues();
        incrementHunger();
    }

    /**
     * Make this predator more hungry. This could result in the predator's death.
     */
    private void incrementHunger() {
        foodLevel -= getPredatorLevel();
        if (foodLevel <= 0) {
            die();
        }
    }

    /**
     * Attempt to eat animal
     * @param The animal to eat
     * @return Whether this animal can eat the animal or not
     */
    private boolean eat(Animal a){
        if(a instanceof Predator){
            Predator p = (Predator) a;
            if(p.getPredatorLevel() >= this.getPredatorLevel())
                return false;
        }
        a.die();
        foodLevel += a.getFoodValue();
        if (foodLevel > getBellySize())
            foodLevel = getBellySize();
        return true;
    }

    /**
     * @return The maximum food this animal can store
     */
    protected abstract int getBellySize();
    /**
     * Get the 'predator level' of this predator. Predators
     * can only eat predators of a lower level
     * Predator level also determines how much food this predator
     * uses per tick
     * @return The predator level of this animal
     */
    protected abstract int getPredatorLevel();

}
