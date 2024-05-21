package org.example.logic;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Monkey extends Entity {
    private ArrayList<Coconut> coconuts;
    private int coconutDodge;
    private Random random;


    public Monkey( int x, int y, int width, int height, String file) {
        super(x, y, width, height, file);
        this.coconuts = new ArrayList<>();
        this.coconutDodge = 0;
        this.random = new Random();

    }

    public void updateCoconuts(){
        Iterator<Coconut> coconutIterator = coconuts.iterator();
        while (coconutIterator.hasNext()) {
            //kontroluje jestli existuje  dalsi kokos
            Coconut coconut = coconutIterator.next();
            coconut.fallDown(25);
            // odstranění kokosuu diky iteratoru
            if (coconut.getCoord().y >= 700 && coconut.getCoord().x <= 950) { // to s tím x tam dávám protože v logice mi metoda destroy posune kokos na x = 1000 takže aby se mi nepricitalo skore i pri zasahu
                coconutDodge ++;
                coconutIterator.remove();
            }

        }
    }


    public void throwCoconut() {
        Coconut newCoconut = new Coconut(getCoord().x, getCoord().y, 40, 40, "kokos.png");
        // Podmínka, aby kokosy nebyly generovány mimo herní okno
        if (getCoord().x >= 0 && getCoord().x <= 850 ) {
            coconuts.add(newCoconut);

        }
    }


    public void resetCoconutDodge(){
        setCoconutDodge(-1);
    }
    




    public int getCoconutDodge() {
        return coconutDodge;
    }

    public ArrayList<Coconut> getCoconuts() {
        return coconuts;
    }

    public void setCoconutDodge(int coconutDodge) {
        this.coconutDodge = coconutDodge;
    }
}
