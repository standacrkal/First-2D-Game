package org.example.logic;



public class Coconut extends Entity {

    public Coconut(int x, int y, int width, int height, String file) {
        super(x,y,width,height, file);

    }

    public void destroy() {
        this.getCoord().setX(1000);
    }
}
