package io.muzoo.ooc.ecosystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldFactory {

    public static Field createField(int height, int width){
        Field field = new Field(height,width);
        return field;
    }

    /**
     * Populate a field with animals.
     *
     * @param field The field to be populated.
     */
    public static List<Actor> populate(Field field) {
        field.clear();
        List<Actor> actors = new ArrayList<>();
        FieldActorFactory factory = new FieldActorFactory();
        factory.setActorProbability("rabbit",0.08);
        factory.setActorProbability("fox",0.02);
        factory.setActorProbability("tiger",0.005);
        factory.setActorProbability("hunter",0.001);
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Actor newActor = factory.getNewRandomActor();
                if(newActor != null) {
                    actors.add(newActor);
                    newActor.setLocation(row, col);
                    field.place(newActor, row, col);
                }
                // else leave the location empty.
            }
        }
        Collections.shuffle(actors);
        return actors;
    }

}
