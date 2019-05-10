package io.muzoo.ooc.ecosystems;

public class Tiger extends Predator {

    public Tiger(boolean randomAge){
        super(randomAge);
    }

    //The age at which a tiger can start to breed
    @Override
    protected int getBreedingAge() {return 50;}
    //The likelihood of a tiger breeding
    @Override
    protected double getBreedingProbability() {return 0.03;}
    //The age to which a tiger can live
    @Override
    protected int getMaxAge() {return 400;}
    //The maximum number of births
    @Override
    protected int getMaxLitterSize() {return 3;}
    //The food value of a tiger
    @Override
    protected int getFoodValue() {return 20;}
    //The amount of food a tiger can store
    @Override
    protected int getBellySize() {return 15;}
    //The predator level of this predator
    @Override
    protected int getPredatorLevel() {return 2;}

    @Override
    protected Animal getNewbornAnimal() {
        return new Tiger(false);
    }
}
