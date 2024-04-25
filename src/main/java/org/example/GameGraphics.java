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
            image = ImageIO.read(new File("src/main/resources/bg.png"));
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
            super.paintComponent(g);
            if (logic.getMenu().getPage() == 1 ){
                g.drawImage(logic.getMenu().getImgMenu(), 0, 0, 900, 720, this);
            }
            if (logic.getMenu().getPage() == 3){
                BufferedImage rules;
                try {
                    rules = ImageIO.read(new File("src/main/resources/rules.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(rules, 0, 0, 900, 700, this);
            }
            if (logic.getMenu().getPage() == 4){
                BufferedImage deadScreen;
                try {
                    deadScreen = ImageIO.read(new File("src/main/resources/gameover.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(deadScreen, 0, 0, 900, 690, this);
            }

            if (logic.getMenu().getPage() == 2 ) {
                this.font = new Font("MINECRAFT", Font.BOLD, 25);

                g.setFont(font);

                g.drawImage(image, 0, 0, this);
                g.drawImage(logic.getPlayer().getImage(), logic.getPlayer().getCoord().x, logic.getPlayer().getCoord().y, logic.getPlayer().getSize().width, logic.getPlayer().getSize().height, this);
                g.drawImage(logic.getMonkey().getImage(), logic.getMonkey().getCoord().x, logic.getMonkey().getCoord().y, logic.getMonkey().getSize().width, logic.getMonkey().getSize().height, this);
                g.drawImage(logic.getBubbleHealth().getImage(), logic.getBubbleHealth().getCoord().x, logic.getBubbleHealth().getCoord().y, logic.getBubbleHealth().getSize().width, logic.getBubbleHealth().getSize().height, this);

                for (Coconut coconut : logic.getMonkey().getCoconuts()) {
                    g.drawImage(coconut.getImage(), coconut.getCoord().x, coconut.getCoord().y,
                            coconut.getSize().width, coconut.getSize().height, this);
                }
                for (FlowerSpike flowerSpike: logic.getFlowerSpikes()){
                    g.drawImage(flowerSpike.getImage(), flowerSpike.getCoord().x, flowerSpike.getCoord().y, flowerSpike.getSize().width, flowerSpike.getSize().height, this);
                }

                g.drawString("Score: " + Integer.toString(logic.getScore()), 720, 60);
                for (Health health: logic.getPlayer().getHealth()){
                    if (health != null) {
                        g.drawImage(health.getImage(), health.getCoord().x, health.getCoord().y, health.getSize().width, health.getSize().height, this);
                    }
                }

                if (logic.getScore() >= 15){
                    g.drawImage(logic.getSpider().getImage(), logic.getSpider().getCoord().x, logic.getSpider().getCoord().y, logic.getSpider().getSize().width, logic.getSpider().getSize().height, this);
                }
            }

        }
    }
}
