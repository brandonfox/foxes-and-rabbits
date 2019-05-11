package io.muzoo.ooc.ecosystems;

import java.util.List;

public abstract class Actor {

    //The Actor's position
    protected Location location;

    public abstract void act(Field currentField, Field updatedField, List newActors);

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
