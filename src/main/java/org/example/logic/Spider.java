package org.example.logic;

import org.example.GameLogic;

public class Spider extends Entity {

    private GameLogic logic;
    public Spider(int x, int y, int width, int height, String file, GameLogic logic ) {
        super(x, y, width, height, file);
        this.logic = logic;

    }
    public void slowDownPlayer(){
        if (logic.getPlayer().isCanMove()) {
            logic.getPlayer().setCanMove(false);
        }
    }
}
