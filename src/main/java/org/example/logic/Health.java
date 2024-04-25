package org.example.logic;

import org.example.GameLogic;

public class Health extends Entity {
    private GameLogic logic;
    public Health(int x, int y, int width, int height, String file, GameLogic logic) {
        super(x, y, width, height, file);
        this.logic = logic;

    }
    public void addHealth(){
        if (logic.getPlayer().getHealth()[2] == null && logic.getPlayer().getHealth()[1]!= null){
            logic.getPlayer().getHealth()[2] = new Health(110, 20, 40, 40, "srdce.png", logic);
        } else if (logic.getPlayer().getHealth()[1] == null) {
            logic.getPlayer().getHealth()[1] = new Health(70, 20, 40, 40, "srdce.png", logic);
        }
    }

}
