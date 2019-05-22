package io.muzoo.ooc.ecosystems;

import java.awt.*;

public class Application {
    public static void main(String[] args) {
        Simulator sim = new Simulator(100, 180);
        SimulatorView view = new SimulatorView(sim);
        view.setColor(Fox.class, Color.blue);
        view.setColor(Rabbit.class, Color.orange);
        sim.simulate(500);
        System.exit(0);
    }

}
