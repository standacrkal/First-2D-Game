package org.example.logic;

import java.awt.*;
import java.util.Random;


public class Thistle extends Entity{

    private boolean isCollided;
    private Random random;

    public Thistle(int x, int y, int height, int width, String file) {
        super(x, y , width, height, file);
        this.random = new Random();
    }

    public boolean checkThistleCollision(Rectangle object){
        Rectangle thistleRect = new Rectangle(getCoord().x, getCoord().y, getSize().width, getSize().height);
        return thistleRect.intersects(object);
    }
    public void changePosition(){
        getCoord().setX(random.nextInt(50, 850));
    }
    public void resetPosition(){
        setCoord(new Coordinates(random.nextInt(50, 850), 750));
    }





    public boolean isCollided() {
        return isCollided;
    }
    public void setCollided(boolean collided) {
        isCollided = collided;
    }


}
