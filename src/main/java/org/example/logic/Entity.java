package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Random;

public class Entity {
    private Coordinates coord;
    private Size size;
    private BufferedImage image;
    private boolean move, grow;
    private Random random;

    public Entity(int x, int y, int width, int height, String file){
        this.coord = new Coordinates(x, y);
        this.size = new Size(width, height);
        try {
            this.image = ImageIO.read(new File("src/main/resources/" + file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.random = new Random();
    }



    public void fallDown(int speed){
        coord.y += speed;
    }
    public void sideMove(int speed){
        if (move){
            if (coord.x <= 900){
                coord.x += speed;
            } else {
                move = false;

            }
        } else {
            if (coord.x >= -80){
                coord.x -= speed;
            }else{
                move = true;

            }
        }
    }

    public void grow(int speed){
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
    public Rectangle getRectangle(){
        return new Rectangle(coord.x, coord.y, size.width, size.height);
    }

    public boolean checkCollision(Rectangle object){
        return getRectangle().intersects(object);
    }





    public Coordinates getCoord() {
        return coord;
    }

    public Size getSize() {
        return size;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }


}
