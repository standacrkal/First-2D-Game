package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spider {
    private Coordinates coord;
    private Size size;
    private BufferedImage imageRight, imageLeft;
    private boolean move, moveImage;

    public Spider(int x, int y, int width, int height, String fileRight, String fileLeft ) {
        this.coord = new Coordinates(x,y);
        this.size = new Size(width, height);
        try {
            this.imageRight = ImageIO.read(new File(fileRight));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.imageLeft = ImageIO.read(new File(fileLeft));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void move(int speed){
        if (move){
            if (coord.x <= 900){
                coord.x += speed;
            } else {
                move = false;
                moveImage = true;
            }
        } else {
            if (coord.x >= -80){
                coord.x -= speed;
            }else{
                move = true;
                moveImage = false;

            }
        }
    }

    public BufferedImage getImage() {
        if (moveImage) {
            return imageRight;
        } else {
            return imageLeft;
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

}
