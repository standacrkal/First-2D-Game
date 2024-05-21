package org.example;

import org.example.logic.*;
import org.example.logic.Menu;


import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

    public class GameLogic  {
        private Player player;
        private Timer timer;
        private Monkey monkey, monkey2;
        private Spider spider;
        private int score, highScore;
        private Random random;
        private Menu menu;
        private Health bubbleHealth;
        private Thistle[] thistles;
        private int level;
        private boolean loading;

        private Sound menuMusic, gameMusic;
        private int spiderTimer;
        private boolean checkSpiderTimer;


        public void initialize() {

            this.random = new Random();
            this.player = new Player(400, 540, 90, 90, "mikyr.png");
            this.monkey = new Monkey(random.nextInt(50, 850), 90, 90, 90, "opica.png");
            this.monkey2 = new Monkey(0, 90, 70, 70, "opica.png");
            this.spider = new Spider(900, 610, 70, 50, "pavouk.png");
            this.score = 0;
            this.level = 1;
            this.menu = new Menu("menu.png");
            this.bubbleHealth = new Health(random.nextInt(50, 850), - 100, 40, 40, "bubble.png");
            this.highScore = loadHighScoreFile();
            this.thistles = new Thistle[4];
            this.thistles[0] = new Thistle(random.nextInt(50, 850), 700,  40, 40, "kytka.png");
            this.thistles[1] = new Thistle(random.nextInt(50, 850), 700,  40, 40, "kytka.png");
            this.thistles[2] = new Thistle(random.nextInt(50, 850), 700,  40, 40, "kytka.png");
            this.thistles[3] = new Thistle(random.nextInt(50, 850), 700,  40, 40, "kytka.png");
            this.loading = true;
            this.timer = new Timer(2000, new ActionListener() { //prvni kokos se spawne zhruba po 2000 sekundach
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (menu.getPage() == 2) {
                        monkey.throwCoconut();
                        if (level == 4) {
                            monkey2.throwCoconut();
                        }



                    }
                    timer.setDelay(random.nextInt(700, 1400)); //dalsi nahodne
                }
            });
            this.timer.start();

            this.menuMusic = new Sound("intro.wav");
            this.gameMusic = new Sound("awimawe.wav");

            this.spiderTimer = 0;
            this.checkSpiderTimer = false;

        }

        public GameLogic() {

        }


        public void update() {

            //TAHLE PODMÍNKA SLOUŽÍ HLAVNĚ PRO GRAFIKU
            if (menuMusic.getClip().isRunning()){
                loading = false;
            }

            //Zapínání a vypínání muziky
            if (menu.getPage() == 1){
                menuMusic.start();

                if (!menu.isMenuMute()){
                    menuMusic.start();
                }else {
                    menuMusic.stop();
                }

            }

            if(menu.getPage() == 2 ) {
                menuMusic.stop();

                if (!menu.isGameMute()) {
                    gameMusic.start();
                }else {
                    gameMusic.stop();
                }

                //pohyb opice
                monkey.sideMove(16);

                player.move(10);

                score = monkey.getCoconutDodge();

                //zde volám metodu updateCoconut z tridy monkey
                monkey.updateCoconuts();


                //kolize s kokosem co hází 1. opice
                for (Coconut coconut: monkey.getCoconuts()) {
                    if (player.checkCollision(coconut.getRectangle())) {
                        player.damage();

                        //jakmile mě zasáhne kokos posunu ho na x mimo mapu, aby vypadalo, že mě zasáhl, jakmile pak dosáhne dane y souradnice bude vymazan z iteratoru
                        coconut.destroy(1000);

                    }
                }


                //kolize s kokosem co hází 2. opice
                for (Coconut coconut: monkey2.getCoconuts()){
                    if (player.checkCollision(coconut.getRectangle())){
                        player.damage();
                        coconut.destroy(1000);
                    }
                }

                //pádání bubliny s hp od score vetsi nebo rovno 5
                if (score >= 5){
                    bubbleHealth.fallDown(7);
                }

                //pohyb spidera od score vetsi nebo rovno 15
                if (score >= 15) {
                    spider.sideMove(6);
                    level = 2;
                }

                //kolize s pavoukem a omezení pohybu
                if (player.checkCollision(spider.getRectangle())) {
                    player.slowDown();
                    checkSpiderTimer = true;
                }
                if (checkSpiderTimer){
                    spiderTimer++;
                }
                if (spiderTimer >= 80){
                    checkSpiderTimer = false;
                    player.moveNormal();
                    spiderTimer = 0;

                }



                //kolize mezi hráčem a bublinou se srdíčkem
                if (player.checkCollision(bubbleHealth.getRectangle())){
                    player.addHealth();

                    //posunout bublinu na jiné x, aby při srazce s bublinou vypadalo, že praskla
                    bubbleHealth.destroy(1000);

                }

                //jakmile bublina dosáhne y vetsi nebo rovno 3000 vrací se zpet na horu, aby mohla padat
                if (bubbleHealth.getCoord().y >= 3000){
                    bubbleHealth.resetPosition(random.nextInt(50, 850), -150);
                }


                // thistle/bodlak kolize
                if (score >= 30) {
                    level = 3;
                    for (Thistle thistle : thistles) {
                        thistle.grow(2);
                        thistle.setCollided(false);

                        //kontroluje zda se  2 bodlaky spawnou na sobe, jestli ano zapne se isCollided
                        for (Thistle thistle1 : thistles){
                            if (thistle != thistle1 && thistle.checkThistleCollision(thistle1.getRectangle())) {
                                thistle.setCollided(true);
                                break;
                            }
                        }

                        //jestli je isCollided true změní se X souřadnice jednoho z bodlaku
                        if (thistle.isCollided()) {
                            thistle.changePosition();
                        }

                        //když se bodlak dotkne nohou hráče ubere mu hp
                        if (thistle.getCoord().y <= 590){
                            if (player.checkCollision(thistle.getRectangle())){
                                player.damage();
                            }
                        }
                    }



                }
                if (score >= 40){
                    level = 4;
                    monkey2.sideMove(4);
                    monkey2.updateCoconuts();
                }


            }

            //prepnuti hry
            if (player.getHealth()[0] == null && player.getHealth()[1] == null && player.getHealth()[2] == null) {
                menu.switchPage(4);
            }

            //resetovani
            if (menu.getPage() == 4){
                resetGame();
                showHighScore();
                gameMusic.stop();
            }

        }

        public void resetGame(){
                player.resetXPosition(400);

                player.resetHealth();

                monkey.resetXPosition(random.nextInt(50, 850));

                monkey2.resetXPosition(30);

                bubbleHealth.resetXPosition(1000);

                spider.resetPosition(900, 610);
                spiderTimer = 0;
                player.moveNormal();

                thistles[0].resetPosition(random.nextInt(50, 850), 700);
                thistles[1].resetPosition(random.nextInt(50, 850), 700);
                thistles[2].resetPosition(random.nextInt(50, 850), 700);
                thistles[3].resetPosition(random.nextInt(50, 850), 700);

                monkey.resetCoconutDodge();

                level = 1;

        }
        public void showHighScore(){
            if(score > highScore){
                highScore = score;
                saveHighScoreToFile(highScore);
            }
        }


        public int loadHighScoreFile(){
            try (BufferedReader reader = new BufferedReader(new FileReader("highScore.txt"))) {
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                return 0;
            }
        }


        private void saveHighScoreToFile(int score){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("highScore.txt"))) {
                writer.write(String.valueOf(score));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        public Menu getMenu() {
            return menu;
        }

        public Player getPlayer() {
            return player;
        }

        public Monkey getMonkey() {
            return monkey;
        }

        public int getScore() {
            return score;
        }

        public Spider getSpider() {
            return spider;
        }

        public Health getBubbleHealth() {
            return bubbleHealth;
        }

        public Thistle[] getThistles() {
            return thistles;
        }

        public int getLevel() {
            return level;
        }

        public int getHighScore() {
            return highScore;
        }


        public Monkey getMonkey2() {
            return monkey2;
        }

        public boolean isLoading() {
            return loading;
        }
    }





