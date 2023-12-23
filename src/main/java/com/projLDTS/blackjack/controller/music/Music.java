package com.projLDTS.blackjack.controller.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.File;
import java.io.IOException;


public class Music {
    private final Clip music;
    private float volume;
    private final FloatControl floatControl;
    private Clip clip;

    public Music(String musicFile) {
        this.music = load(musicFile);
        this.floatControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
        setVolume(0.5);
    }
    private Clip load(String musicFile) throws NullPointerException {
        try {
            File file = new File(musicFile);
            if (!file.exists()) throw new RuntimeException("Music: file not found: " + musicFile);
            AudioInputStream musicInput = AudioSystem.getAudioInputStream(file);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(musicInput);
            // musicInput.close();
            return musicClip;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public void play() {
        music.setFramePosition(0);
        music.start();
    }
    public void loop() {
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void setVolume(double volume) {
        if (volume < 0.0 || volume > 1.0) {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
        float value = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        floatControl.setValue(value);
        this.volume = value;
    }
    public void close() {
        if (music.isOpen()) {
            music.close();
        }
    }

}
