package io.muzoo.ooc.ecosystems;

import java.util.List;

public class Hunter extends Actor {

    @Override
    public void act(Field currentField, Field updatedField, List newActors) {
        //Hunter fires some random shots within a certain range then moves

        fireRandomShots(currentField,3,4);
        move(updatedField,currentField.freeAdjacentLocation(location));
    }

    /**
     * Fire random shots that kill any animal on a square
     * @param noOfShots the number of times to shoot
     * @param range The range to shoot at (Uses basic x + y value to determine range)
     */
    private void fireRandomShots(Field field,int noOfShots, int range){
        for(int i = 0; i < noOfShots; i++){
            int randomX = rand.nextInt(range);
            int randomY = rand.nextInt(range);
            Location shotLocation = new Location(location.getRow() + randomY, location.getCol() +randomX);
            shootAnimalAt(field,shotLocation);
        }
    }

    //Gotta decide whether to kill hunters by 'misfires' or not hmmmmm
    /**
     * Shoot and kill Animal on a particular location
     * @param field The field that the hunter is currently on
     * @param shotLocation The location to shoot
     */
    private void shootAnimalAt(Field field, Location shotLocation){
        try {
            Object actor = field.getObjectAt(shotLocation);
            if (actor instanceof Animal)
                ((Animal) actor).die();
        }catch(IndexOutOfBoundsException e){
            //Shot is outside of map just do nothing
        }
    }
}
