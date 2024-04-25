package org.example.logic;


import org.example.GameLogic;

import java.awt.*;



public class FlowerSpike extends Entity{

    private GameLogic logic;
    private boolean isCollided;

    public FlowerSpike(int x, int y, int height, int width, String file, GameLogic logic) {
        super(x, y , width, height, file);
        this.logic = logic;
    }

    public boolean checkCollisionBetweenFlowers(Rectangle object){
        Rectangle flowerRect = new Rectangle(getCoord().x, getCoord().y, getSize().width, getSize().height);
        return flowerRect.intersects(object);
    }
    public void doubleDamagePlayer(){
        if (logic.getPlayer().getHealth()[2] != null){
            logic.getPlayer().getHealth()[2] = null;
        } else if (logic.getPlayer().getHealth()[1] != null) {
            logic.getPlayer().getHealth()[1] = null;
        } else if (logic.getPlayer().getHealth()[0] != null) {
            logic.getPlayer().getHealth()[0] = null;
        }
    }

    public boolean isCollided() {
        return isCollided;
    }
    public void setCollided(boolean collided) {
        isCollided = collided;
    }


}
