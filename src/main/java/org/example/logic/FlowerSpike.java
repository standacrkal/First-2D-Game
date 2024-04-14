package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FlowerSpike {
    private Coordinates coord;
    private Size size;
    private BufferedImage imgWarning, imgSpike;
    private int speed;
    private Random random;
    private boolean grow;
    private boolean isCollided;


    public FlowerSpike(int y, int height, int width, String warning, String spike) {
        this.random = new Random();
        this.coord = new Coordinates(random.nextInt(50, 850), y);

        this.size = new Size(height, width);

        try {
            this.imgSpike = ImageIO.read(new File(spike));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.imgWarning = ImageIO.read(new File(warning));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        speed = 1;
        this.grow = true;

    }
    public void grow(){
        if (grow){
            if (coord.y > 590){
                coord.y -= speed;
            }else {
                grow = false;
            }
        }
        else{
            if (coord.y > 0 && coord.y < 1000){
                coord.y += speed;
            }else {
                grow = true;
            }
        }
        if (coord.y >= 1000){
            coord.x = random.nextInt(50, 850);
        }

    }

    public BufferedImage getImg() {
        if (coord.y >= 590 && grow == true) {
            return imgWarning;
        } else if (grow == false) {
            return imgSpike;
        } else {
            return imgSpike;
        }

    }

    public boolean checkCollisionBetweenFlowers(Rectangle object){
        Rectangle flowerRect = new Rectangle(coord.x, coord.y, size.width, size.height);
        return flowerRect.intersects(object);
    }

    public void setX(int x) {
        this.coord.x = x;
    }

    public int getX() {
        return coord.x;
    }

    public int getY() {
        return coord.y;
    }

    public int getHeight() {
        return size.height;
    }

    public int getWidth() {
        return size.width;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }

    public boolean isCollided() {
        return isCollided;
    }
}
