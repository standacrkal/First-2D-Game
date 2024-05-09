package org.example.logic;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private Clip clip;

    public Sound(String file) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src/main/resources/" + file)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loop() {
        if (clip != null) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }
    public void start() {
        if (clip != null) {
            if (!clip.isRunning()) { // Check if the clip is not already playing
                clip.setFramePosition(0); // Reset position to start
                clip.start();
            }
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }

}

