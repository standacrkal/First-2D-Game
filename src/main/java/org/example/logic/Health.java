package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Health {
    private Coordinates coord;
    private Size size;
    private BufferedImage healthImg;
    private int speed;

    public Health(int x, int y, int width, int height, String file) {
        this.coord = new Coordinates(x, y);
        this.size = new Size(width, height);
        try {
            this.healthImg = ImageIO.read(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.speed = 5;

    }
    public void setY(int y) {
        this.coord.y = y;
    }
    public void fallDown(){
        coord.y += speed;
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

    public BufferedImage getHealthImg() {
        return healthImg;
    }

}
