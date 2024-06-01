package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity implements KeyListener {
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean canMove;
    private Health[] health;
    private BufferedImage damageImg, starImg, healImg;
    private boolean damage;
    private  int damageTimer;
    private boolean heal;
    private int healTimer;

    public Player(int x, int y, int width, int height, String file, String fileRed, String fileStar, String fileHeal) {
        super(x, y, width, height, file);
        this.canMove = true;
        this.health = new Health[3];
        this.damageTimer = 0;
        this.damage = false;
        this.health[0] = new Health(30, 20, 40, 40, "srdce.png");
        this.health[1] = new Health(70, 20, 40, 40, "srdce.png");
        this.health[2] = new Health(110, 20, 40, 40, "srdce.png");
        try {
            this.starImg = ImageIO.read(getClass().getResource("/" + fileStar));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.damageImg = ImageIO.read(getClass().getResource("/" + fileRed));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.healImg = ImageIO.read(getClass().getResource("/" + fileHeal));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void damageTimer(){
        if (damage){
            damageTimer++;
        }
        if (damageTimer >= 20){
            damage = false;
            damageTimer = 0;
        }
    }

    public void healTimer(){
        if (heal){
            healTimer++;
        }
        if (healTimer >= 30){
            heal = false;
            healTimer = 0;
        }
    }

    public void healTimerActive(){
        this.heal = true;
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
       if(getCoord().x >= 820){
            getCoord().x = 820;
       }
       if (up && getCoord().y == 540) {
            getCoord().y -= 120;
            up = false;
       } else if (getCoord().y < 540) {
            getCoord().y += 10;
       }
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

    public void activeDamageImage() {
        this.damage = true;
    }

    public void deactiveDamageImage(){
        this.damage = false;
    }

    public void resetDamageTimer() {
        this.damageTimer = 0;
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

    public BufferedImage getDmgImg() {
        return damageImg;
    }

    public BufferedImage getStarImg() {
        return starImg;
    }

    public boolean isDamage() {
        return damage;
    }

    public BufferedImage getHealImg() {
        return healImg;
    }
    public boolean isHeal() {
        return heal;
    }
}

