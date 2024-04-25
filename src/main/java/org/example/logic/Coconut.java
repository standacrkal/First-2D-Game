package org.example.logic;


import org.example.GameLogic;


public class Coconut extends Entity {
    private GameLogic logic;

    public Coconut(int x, int y, int width, int height, String file, GameLogic logic) {
        super(x,y,width,height, file);
        this.logic = logic;

    }
    public void damagePlayer(){
        if (logic.getPlayer().getHealth()[2] != null){
            logic.getPlayer().getHealth()[2] = null;
        } else if (logic.getPlayer().getHealth()[1] != null) {
            logic.getPlayer().getHealth()[1] = null;
        } else if (logic.getPlayer().getHealth()[0] != null) {
            logic.getPlayer().getHealth()[0] = null;
        }
    }
}
