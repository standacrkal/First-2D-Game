package org.example.logic;

import org.example.GameLogic;

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
            this.imgMenu = ImageIO.read(new File(file));
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            page = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_P){
            page = 3;
        }

            if (e.getKeyCode() == KeyEvent.VK_J) {
                page = 1;
            }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getPage() {
        return page;
    }
}
