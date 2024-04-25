package org.example;

import org.example.logic.*;
import org.example.logic.Menu;

import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.Random;

    public class GameLogic  {
        private Player player;
        private Timer timer;
        private Monkey monkey;
        private Spider spider;
        private int score;
        private Random random;
        private Menu menu;
        private Health bubbleHealth;
        private FlowerSpike[] flowerSpikes;

        public void initialize() {

            this.random = new Random();
            this.player = new Player(400, 540, 90, 90, "mikyr.png", this);
            this.monkey = new Monkey(random.nextInt(50, 850), 90, 90, 90, "opica.png", this);
            this.spider = new Spider(900, 610, 90, 60, "pavouk.png", this);
            this.score = 0;
            this.menu = new Menu("menu.png");
            this.bubbleHealth = new Health(random.nextInt(50, 850), - 100, 40, 40, "bubble.png", this);

            this.flowerSpikes = new FlowerSpike[4];
            this.flowerSpikes[0] = new FlowerSpike(random.nextInt(50, 850), 750,  40, 40, "kytka.png", this);
            this.flowerSpikes[1] = new FlowerSpike(random.nextInt(50, 850), 750,  40, 40, "kytka.png", this);
            this.flowerSpikes[2] = new FlowerSpike(random.nextInt(50, 850), 750,  40, 40, "kytka.png", this);
            this.flowerSpikes[3] = new FlowerSpike(random.nextInt(50, 850), 750,  40, 40, "kytka.png", this);

                this.timer = new Timer(2000, new ActionListener() { //prvni kokos se spawne zhruba po 2000 sekundach
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (menu.getPage() == 2) {
                            monkey.throwCoconut();
                        }
                        timer.setDelay(random.nextInt(500, 1400)); //dalsi nahodne
                    }
                });
                this.timer.start();


        }

        public GameLogic() {

        }

        public void update() {


            if(menu.getPage() == 2 ) {
                //pohyb opice
                monkey.sideMove(16);
                //zde volám metodu updateCoconut z tridy monkey

                player.move(10);

                score = monkey.getScore();

                monkey.updateCoconuts();

                //kolize s kokosem a ubírání životů
                for (Coconut coconut: monkey.getCoconuts()) {
                    if (player.checkCollision(new Rectangle(coconut.getCoord().x, coconut.getCoord().y, coconut.getSize().width, coconut.getSize().height))) {
                        if (coconut.getCoord().y <= 530) {
                            coconut.damagePlayer();

                            //jakmile mě zasáhne kokos posunu ho na x mimo mapu, aby vypadalo, že mě zasáhl, jakmile pak dosáhne dane y souradnice bude vymazan z iteratoru
                            coconut.getCoord().setX(1000);
                        }

                    }
                }

                //pohyb spidera od score vetsi nebo rovno 15
                if (score >= 15) {
                    spider.sideMove(6);
                }

                //kolize s pavoukem a omezení pohybu
                if (player.checkCollision(new Rectangle(spider.getCoord().x, spider.getCoord().y, spider.getSize().width, spider.getSize().height))) {
                    spider.slowDownPlayer();
                } else {
                    player.setCanMove(true);
                }

                //pádání bubliny s hp od score vetsi nebo rovno 5
                if (score >= 5){
                   bubbleHealth.fallDown(7);
                }

                //kolize mezi hráčem a bublinou se srdíčkem
                if (player.checkCollision(new Rectangle(bubbleHealth.getCoord().x, bubbleHealth.getCoord().y, bubbleHealth.getSize().width, bubbleHealth.getSize().height))){
                    bubbleHealth.addHealth();

                    //posunout bublinu na jiné x, aby při srazce s bublinou vypadalo, že praskla
                    bubbleHealth.getCoord().setX(1000);

                }

                //jakmile bublina dosáhne y vetsi nebo rovno 3000 vrací se zpet na horu, aby mohla padat
                if (bubbleHealth.getCoord().y >= 3000){
                    bubbleHealth.setCoord(new Coordinates(random.nextInt(50, 850), -150));
                }


                //flowerSpike kolize
                if (score >= 30) {
                    for (FlowerSpike flowerSpike : flowerSpikes) {
                        flowerSpike.grow(2);
                        flowerSpike.setCollided(false);

                        //kontroluje zda se  2 flowerspiky spawnou na sobe, jestli ano zapne se isCollided
                        for (FlowerSpike flowerSpike1: flowerSpikes){
                            if (flowerSpike != flowerSpike1 && flowerSpike.checkCollisionBetweenFlowers(new Rectangle(flowerSpike1.getCoord().x, flowerSpike1.getCoord().y, flowerSpike1.getSize().width, flowerSpike1.getSize().height))) {
                                flowerSpike.setCollided(true);
                                break;
                            }
                        }

                        //jestli je isCollided true změní se X souřadnice jednoho z flowerspiků
                        if (flowerSpike.isCollided()) {
                            flowerSpike.getCoord().setX(random.nextInt(50, 850));
                        }

                        //když se flowerspike dotkne nohou hráče ubere mu hp
                        if (flowerSpike.getCoord().y <= 590){
                            if (player.checkCollision(new Rectangle(flowerSpike.getCoord().x, flowerSpike.getCoord().y, flowerSpike.getSize().width, flowerSpike.getSize().height))){
                                flowerSpike.doubleDamagePlayer();
                            }
                        }
                    }
                }


            }

            //prepnuti hry
            if (player.getHealth()[0] == null && player.getHealth()[1] == null && player.getHealth()[2] == null) {
                menu.setPage(4);
            }
            //resetovani
            if (menu.getPage() == 4){
                resetGame();
            }


        }

        public void resetGame(){
                player.getCoord().setX(400);

                player.getHealth()[0] = new Health(30, 20, 40, 40, "srdce.png", this);
                player.getHealth()[1] = new Health(70, 20, 40, 40, "srdce.png", this);
                player.getHealth()[2] = new Health(110, 20, 40, 40, "srdce.png", this);

                monkey.getCoord().setX(random.nextInt(50, 850));


                bubbleHealth.setCoord(new Coordinates(random.nextInt(50, 850), - 100));

                spider.setCoord(new Coordinates(900, 610));

                flowerSpikes[0].setCoord(new Coordinates(random.nextInt(50, 850), 750));
                flowerSpikes[1].setCoord(new Coordinates(random.nextInt(50, 850), 750));
                flowerSpikes[2].setCoord(new Coordinates(random.nextInt(50, 850), 750));
                flowerSpikes[3].setCoord(new Coordinates(random.nextInt(50, 850), 750));

                //zde musím nastavit score na -1, protože z nějakého důvodu když zapnu hru tak se mi tam ukáže score 1 když tam mám nastavenou 0 a pak to neodpovida danému počtu vyhnutých kokosů
                monkey.setScore(-1);

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

        public FlowerSpike[] getFlowerSpikes() {
            return flowerSpikes;
        }

    }





