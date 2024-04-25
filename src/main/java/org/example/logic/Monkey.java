package org.example.logic;



import org.example.GameLogic;

import java.util.ArrayList;
import java.util.Iterator;

public class Monkey extends Entity {
    private ArrayList<Coconut> coconuts;
    private int score;
    private GameLogic logic;


    public Monkey( int x, int y, int width, int height, String file, GameLogic logic) {
        super(x, y, width, height, file);
        this.coconuts = new ArrayList<>();
        this.score = 0;
        this.logic = logic;
    }

    public void updateCoconuts(){
        Iterator<Coconut> coconutIterator = coconuts.iterator();
        while (coconutIterator.hasNext()) {

            //kontroluje jestli existuje  dalsi kokos
            Coconut coconut = coconutIterator.next();
            coconut.fallDown(25);

            // odstranění kokosuu diky iteratoru
            if (coconut.getCoord().y >= 700) {
                score ++;
                coconutIterator.remove();
            }

        }
    }

    public void throwCoconut() {
        Coconut newCoconut = new Coconut(getCoord().x, getCoord().y, 40, 40, "kokos.png", logic );

        // Podmínka, aby kokosy nebyly generovány mimo herní okno
        if (getCoord().x >= 0 && getCoord().x <= 850 ) {
            coconuts.add(newCoconut);
        }

    }

    public int getScore() {
        return score;
    }

    public ArrayList<Coconut> getCoconuts() {
        return coconuts;
    }
}
