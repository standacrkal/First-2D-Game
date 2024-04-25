package org.example.logic;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu implements KeyListener {
    private BufferedImage imgMenu;
    private int page;

    public Menu(String file) {
        try {
            this.imgMenu = ImageIO.read(new File("src/main/resources/" + file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        this.page = 1;
    }

    public BufferedImage getImgMenu() {
        return imgMenu;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (page == 1) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                page = 2;
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }

            if (e.getKeyCode() == KeyEvent.VK_P) {
                page = 3;
            }
        }

        if (page == 3 || page == 4) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                page = 1;
            }
        }


        if( page == 4){
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                page = 2;
            }
        }





    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
