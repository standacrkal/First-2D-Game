package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game {
    private GameLogic logic;
    public static void main(String[] args) {
        new Game();

    }
    public Game(){
        logic = new GameLogic();
        logic.initialize();
        GameGraphics graphic = new GameGraphics(logic);
        Timer timer = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.update();
                graphic.render(logic);

            }
        });
        timer.start();
    }
}
