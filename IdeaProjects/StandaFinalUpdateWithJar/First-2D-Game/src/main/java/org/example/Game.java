package org.example;

public class Game {
    private GameLogic logic;
    public static void main(String[] args) {
        new Game();
    }

    public Game(){
        logic = new GameLogic();
        logic.initialize();
        GameGraphics graphic = new GameGraphics(logic);
        Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    graphic.render(logic);
                    logic.update();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();

    }
}
