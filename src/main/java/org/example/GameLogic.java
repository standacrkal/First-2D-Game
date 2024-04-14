    package org.example;

    import org.example.logic.*;
    import org.example.logic.Menu;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.Random;



    public class GameLogic  {
        private Player player;
        private Timer timer;
        private Monkey monkey;
        private ArrayList<Coconut> coconuts;
        private Spider spider;
        private int gameActive;
        private int score;
        private Random random;

        private Health[] health;
        private Menu menu;
        private Health bubbleHealth;
        private FlowerSpike[] flowerSpikes;

        public void initialize() {

            this.player = new Player(400, 540, 90, 90, "mikyr.png");
            this.monkey = new Monkey(90, 90, 90, "opica.png");
            this.spider = new Spider(-100, 610, 90, 60, "pavouk.png", "pavoukRight.png");
            this.coconuts = new ArrayList<>();
            this.health = new Health[3];
            this.health[0] = new Health(30, 20, 40, 40, "srdce.png");
            this.health[1] = new Health(70, 20, 40, 40, "srdce.png");
            this.health[2] = new Health(110, 20, 40, 40, "srdce.png");
            this.gameActive = 1;
            this.score = 0;
            this.menu = new Menu("menu.png");
            this.random = new Random();
            this.bubbleHealth = new Health(random.nextInt(50, 850), - 100, 40, 40, "bubble.png");
            this.flowerSpikes = new FlowerSpike[4];

            this.flowerSpikes[0] = new FlowerSpike(750,  40, 40, "vykricnik.png", "kytka.png");
            this.flowerSpikes[1] = new FlowerSpike(750,  40, 40, "vykricnik.png", "kytka.png");
            this.flowerSpikes[2] = new FlowerSpike(750,  40, 40, "vykricnik.png", "kytka.png");
            this.flowerSpikes[3] = new FlowerSpike(750,  40, 40, "vykricnik.png", "kytka.png");



            this.timer = new Timer(1750, new ActionListener() { //prvni kokos se spawne zhruba po 1,75 sekundach
                @Override
                public void actionPerformed(ActionEvent e) {
                    spawnCoconut();
                    timer.setDelay(random.nextInt(500, 1400)); //dalsi nahodne od
                }
            });
            this.timer.start();


        }

        public GameLogic() {

        }

        public void update() {

            if (menu.getPage() == 2){
                    gameActive = 2;
            }
            if (menu.getPage() == 3) {
                    gameActive = 3;
            }
            if (menu.getPage() == 1){
                gameActive = 1;
            }


            if(gameActive == 2 ) {
                //pohyb opice
                monkey.moveSide();


                //spawnovani kokosu
                Iterator<Coconut> coconuts = getCoconuts().iterator();
                while (coconuts.hasNext()) {
                    //kontroluje jestli existuje  dalsi kokos

                    Coconut coconut = coconuts.next();
                    coconut.coconutFall();

                    //kolize s kokosem
                    if (player.checkCollision(new Rectangle(coconut.getX(), coconut.getY(), coconut.getWidth(), coconut.getHeight()))) {
                        if (coconut.getY() <= 530) {
                            if (health[2] != null) {
                                health[2] = null;
                            } else if (health[1] != null) {
                                health[1] = null;
                            } else if (health[0] != null) {
                                health[0] = null;
                            }
                        }

                    }

                    if (coconut.getY() >= 700) {
                        score += 1;
                        coconuts.remove();
                        // odstranění kokosuu diky iteratoru
                    }

                }
                //pohyb spidera
                if (score >= 15) {
                    spider.move(6);
                }

                //kolize s pavoukem
                if (player.checkCollision(new Rectangle(spider.getX(), spider.getY(), spider.getWidth(), spider.getHeight()))) {
                    player.setCanMove(false);
                } else {
                    player.move(10);
                    player.setCanMove(true);
                }

                //bubbleHealth
                if (score >= 5){
                   monkey.setSpeed(16);
                   bubbleHealth.fallDown();
                }
                if (player.checkCollision(new Rectangle(bubbleHealth.getX(), bubbleHealth.getY(), bubbleHealth.getWidth(), bubbleHealth.getHeight()))){
                    if (health[2] == null && health[1] != null){
                        health[2] = new Health(110, 20, 40, 40, "srdce.png");
                    } else if (health[1] == null) {
                        health[1] = new Health(70, 20, 40, 40, "srdce.png");
                    }
                    bubbleHealth.setY(1000);
                }
                if (bubbleHealth.getY() >= 3000){
                    bubbleHealth.setY(-100);
                }
                //flowerSpike kolize
                if (score >= 40) {
                    for (FlowerSpike flowerSpike : flowerSpikes) {
                        flowerSpike.grow();
                        flowerSpike.setCollided(false);
                        for (FlowerSpike flowerSpike1: flowerSpikes){
                            if (flowerSpike != flowerSpike1 && flowerSpike.checkCollisionBetweenFlowers(new Rectangle(flowerSpike1.getX(), flowerSpike1.getY(), flowerSpike1.getWidth(), flowerSpike1.getHeight()))) {
                                flowerSpike.setCollided(true);
                                break;
                            }
                        }
                        if (flowerSpike.isCollided()) {
                            flowerSpike.setX(random.nextInt(50, 850));
                        }
                        System.out.println(flowerSpike.getY());
                        if (flowerSpike.getY() <= 590){
                            if (player.checkCollision(new Rectangle(flowerSpike.getX(), flowerSpike.getY(), flowerSpike.getWidth(), flowerSpike.getHeight()))){
                                if (health[2] != null) {
                                    health[2] = null;
                                } else if (health[1] != null) {
                                    health[1] = null;
                                } else if (health[0] != null) {
                                    health[0] = null;
                                }
                            }
                        }
                    }
                }


            }

            //prepnuti hry
            if (health[0] == null && health[1] == null && health[2] == null) {
                gameActive = 4;
            }


        }

        //funkce na spawnovani kokosu
        public void spawnCoconut() {
            if (gameActive == 2) {
                Coconut newCoconut = new Coconut(this, 40, 40, "kokos.png");
                coconuts.add(newCoconut);
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

        public ArrayList<Coconut> getCoconuts() {
            return coconuts;
        }

        public int getScore() {
            return score;
        }

        public int getGameActive() {
            return gameActive;
        }
        public Spider getSpider() {
            return spider;
        }

        public Health[] getHealth() {
            return health;
        }

        public Health getBubbleHealth() {
            return bubbleHealth;
        }

        public FlowerSpike[] getFlowerSpikes() {
            return flowerSpikes;
        }
    }





