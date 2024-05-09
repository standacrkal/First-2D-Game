package org.example.logic;


import java.util.Random;

public class Health extends Entity {
    private Random random;
    public Health(int x, int y, int width, int height, String file) {
        super(x, y, width, height, file);
        this.random = new Random();

    }

    public void destroy() {
        getCoord().setX(1000);
    }
    public void resetPosition(){
        setCoord(new Coordinates(random.nextInt(50, 850), -150));
    }
}
