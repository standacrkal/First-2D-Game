package org.example.logic;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spider extends Entity {
    private int spiderTimer;
    private boolean checkSpiderTimer;
    private BufferedImage secondImage;

    public Spider(int x, int y, int width, int height, String file, String fileLeft ) {
        super(x, y, width, height, file);
        this.spiderTimer = 0;
        this.checkSpiderTimer = false;
        try {
            this.secondImage = ImageIO.read(getClass().getResource("/" + fileLeft));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stunTimer(){
        if (checkSpiderTimer){
            spiderTimer++;
        }
        if (spiderTimer >= 80){
            checkSpiderTimer = false;
            spiderTimer = 0;
        }
    }

    public void spiderTimerActive(){
        checkSpiderTimer = true;
    }

    public void spiderTimerDeactive(){
        checkSpiderTimer = false;
    }
    public void resetSpiderTimer(){
        spiderTimer = 0;
    }


    public BufferedImage getSecondImage() {
        return secondImage;
    }

    public int getSpiderTimer() {
        return spiderTimer;
    }

    public boolean isCheckSpiderTimer() {
        return checkSpiderTimer;
    }
}
