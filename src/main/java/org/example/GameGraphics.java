package org.example;

import org.example.logic.Coconut;
import org.example.logic.FlowerSpike;
import org.example.logic.Health;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameGraphics extends JFrame{
    private GameLogic logic;
    private Draw draw;
    private BufferedImage image;

    public GameGraphics(GameLogic logic){
        setSize(900, 720);
        setTitle("GAME");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        this.draw = new Draw();
        this.logic = logic;
        add(draw);
        try {
            image = ImageIO.read(new File("bg.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addKeyListener(logic.getPlayer());
        addKeyListener(logic.getMenu());
    }

    public void render(GameLogic logic) {
        this.logic = logic;
        repaint();
    }

    public class Draw extends JPanel{
        private Font font;
        @Override
        protected  void paintComponent(Graphics g){
            if (logic.getGameActive() == 1 ){
                g.drawImage(logic.getMenu().getImgMenu(), 0, 0, 900, 720, this);
            }
            if (logic.getGameActive() == 3){
                BufferedImage rules;
                try {
                    rules = ImageIO.read(new File("rules.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(rules, 0, 0, 900, 700, this);
            }

            if (logic.getGameActive() == 2 ) {
                super.paintComponent(g);
                this.font = new Font("MINECRAFT", Font.BOLD, 25);

                g.setFont(font);

                g.drawImage(image, 0, 0, this);
                g.drawImage(logic.getPlayer().getImage(), logic.getPlayer().getX(), logic.getPlayer().getY(), logic.getPlayer().getWidth(), logic.getPlayer().getHeight(), this);
                g.drawImage(logic.getMonkey().getImage(), logic.getMonkey().getX(), logic.getMonkey().getY(), logic.getMonkey().getWidth(), logic.getMonkey().getHeight(), this);
                g.drawImage(logic.getBubbleHealth().getHealthImg(), logic.getBubbleHealth().getX(), logic.getBubbleHealth().getY(), logic.getBubbleHealth().getWidth(), logic.getBubbleHealth().getHeight(), this);

                for (Coconut coconut : logic.getCoconuts()) {
                    g.drawImage(coconut.getImage(), coconut.getX(), coconut.getY(),
                            coconut.getWidth(), coconut.getHeight(), this);
                }
                for (FlowerSpike flowerSpike: logic.getFlowerSpikes()){
                    g.drawImage(flowerSpike.getImg(), flowerSpike.getX(), flowerSpike.getY(), flowerSpike.getWidth(), flowerSpike.getHeight(), this);
                }

                g.drawString("Score: " + Integer.toString(logic.getScore()), 720, 60);
                for (Health health: logic.getHealth()){
                    if (health != null) {
                        g.drawImage(health.getHealthImg(), health.getX(), health.getY(), health.getWidth(), health.getHeight(), this);
                    }
                }

                if (logic.getScore() >= 20){
                    g.drawImage(logic.getSpider().getImage(), logic.getSpider().getX(), logic.getSpider().getY(), logic.getSpider().getWidth(), logic.getSpider().getHeight(), this);
                }
            }

        }
    }
}
