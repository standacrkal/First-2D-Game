package org.example.logic;


public class Spider extends Entity {

    public Spider(int x, int y, int width, int height, String file ) {
        super(x, y, width, height, file);

    }
    public void resetPosition(){
        setCoord(new Coordinates(900, 610));
    }

}
