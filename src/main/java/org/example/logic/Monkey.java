package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Monkey {
    private Coordinates coord;
    private Size size;
    private BufferedImage image;
    private boolean move;
    private  int speed;
    private Random random;

    public Monkey( int y, int width, int height, String file) {
        this.random = new Random();
        this.coord = new Coordinates( random.nextInt(50, 850 ), y);
        this.size = new Size(width, height);
        try {
            this.image = ImageIO.read(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.move = true;
        this.speed = 15;

    }

    public void moveSide(){
        if (move){
            if (coord.x < 825){
                coord.x += speed;
            }else {
                move = false;
            }
        }
        else{
            if (coord.x > 0){
                coord.x-= speed;
            }else {
                move = true;
            }
        }
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getImage() {
        return image;
    }
}
