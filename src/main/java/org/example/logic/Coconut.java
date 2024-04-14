package org.example.logic;

import org.example.GameLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Coconut {
    private Coordinates coord;
    private Size size;
    private BufferedImage image;
    private int speed;

    public Coconut(GameLogic logic, int width, int height, String file) {
        this.coord = new Coordinates( logic.getMonkey().getX(), logic.getMonkey().getY());
        this.size = new Size(width, height);
        try {
            this.image = ImageIO.read(new File(file));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        this.speed = 25;

    }

    public void coconutFall (){
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

    public BufferedImage getImage() {
        return image;
    }

}
