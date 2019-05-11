package io.muzoo.ooc.ecosystems;

import java.util.List;
import java.util.Random;

public abstract class Actor {

    //A shared random number generator for extra functions that require randomness
    protected static final Random rand = new Random();

    //The Actor's position
    protected Location location;

    public abstract void act(Field currentField, Field updatedField, List newActors);

    /**
     * Move the actor
     * @param updatedField The updated field where the new location will be saved
     * @param newLocation The location to move to
     */
    protected void move(Field updatedField, Location newLocation){
        if (newLocation != null) {
            setLocation(newLocation);
            updatedField.place(this, newLocation);
        }
    }

    /**
     * Set the Actor's location.
     *
     * @param row The vertical coordinate of the location
     * @param col The horizontal coordinate of the location.
     */
    public void setLocation(int row, int col) { this.location = new Location(row,col); }

    /**
     * Set the Actor's location
     *
     * @param location The animal's location
     */
    public void setLocation(Location location) { this.location = location; }

}
