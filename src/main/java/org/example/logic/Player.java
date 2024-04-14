package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player implements KeyListener {
    private Coordinates coord;
    private Size size;
    private BufferedImage image;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean canMove;





    public Player(int x, int y, int width, int height, String file) {
        this.coord = new Coordinates(x, y);
        this.size = new Size(width, height);
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.canMove = true;
    }

    public void move(int speed){
       if (left){
           coord.x -= speed;
       }
       if (right){
           coord.x += speed;
       }
       if( coord.x <= -5){
           coord.x = -5;
       }
       if( coord.x >= 800){
            coord.x = 800;
       }
        if (up && coord.y == 540) {
            coord.y -= 120;
            up = false;
        } else if (coord.y < 540) {
            coord.y += 10;
        }


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

    public boolean checkCollision(Rectangle object){
        Rectangle playerRect = new Rectangle(coord.x, coord.y, size.width, size.height);
        return playerRect.intersects(object);
    }

    public int getX() {
        return coord.x;
    }

    public int getY() {
        return coord.y;
    }

    public int getWidth() {
        return size.width;
    }

    public int getHeight() {
        return size.height;
    }

    public BufferedImage getImage() {
        return image;

    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}

