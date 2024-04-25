package org.example.logic;

import java.awt.*;



public class FlowerSpike extends Entity{

    private boolean isCollided;

    public FlowerSpike(int x, int y, int height, int width, String file) {
        super(x, y , width, height, file);

    }

    public boolean checkCollisionBetweenFlowers(Rectangle object){
        Rectangle flowerRect = new Rectangle(getCoord().x, getCoord().y, getSize().width, getSize().height);
        return flowerRect.intersects(object);
    }

    public boolean isCollided() {
        return isCollided;
    }
    public void setCollided(boolean collided) {
        isCollided = collided;
    }


}
