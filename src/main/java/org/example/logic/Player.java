package org.example.logic;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player extends Entity implements KeyListener {
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean canMove;
    private Health[] health;



    public Player(int x, int y, int width, int height, String file) {
        super(x, y, width, height, file);
        this.canMove = true;
        this.health = new Health[3];
        this.health[0] = new Health(30, 20, 40, 40, "srdce.png");
        this.health[1] = new Health(70, 20, 40, 40, "srdce.png");
        this.health[2] = new Health(110, 20, 40, 40, "srdce.png");
    }

    public void damage(){
        if (getHealth()[2] != null){
            getHealth()[2] = null;
        } else if (getHealth()[1] != null) {
            getHealth()[1] = null;
        } else if (getHealth()[0] != null) {
            getHealth()[0] = null;
        }
    }

    public void addHealth(){
        if (getHealth()[2] == null && getHealth()[1]!= null){
            getHealth()[2] = new Health(110, 20, 40, 40, "srdce.png");
        } else if (getHealth()[1] == null) {
            getHealth()[1] = new Health(70, 20, 40, 40, "srdce.png");
        }
    }

    public void slowDown(){
        if (canMove) {
            setCanMove(false);
        }
    }
    public void moveNormal(){
        setCanMove(true);
    }

    public void move(int speed){
       if (left){
           getCoord().x -= speed;
       }
       if (right){
           getCoord().x += speed;
       }
       if( getCoord().x <= -5){
           getCoord().x = -5;
       }
       if(getCoord().x >= 800){
            getCoord().x = 800;
       }
        if (up && getCoord().y == 540) {
            getCoord().y -= 120;
            up = false;
        } else if (getCoord().y < 540) {
            getCoord().y += 10;
        }


    }
    public void resetPosition(){
        getCoord().setX(400);
    }
    public void resetHealth(){
        getHealth()[0] = new Health(30, 20, 40, 40, "srdce.png");
        getHealth()[1] = new Health(70, 20, 40, 40, "srdce.png");
        getHealth()[2] = new Health(110, 20, 40, 40, "srdce.png");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (canMove) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                up = true;

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            up = false;
        }

    }





    public void setUp(boolean up) {
        this.up = up;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Health[] getHealth() {
        return health;
    }


}

