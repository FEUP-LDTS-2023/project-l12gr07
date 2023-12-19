package com.projLDTSblackjack.controller.music;

import com.projLDTS.blackjack.controller.music.Music;
import com.projLDTS.blackjack.controller.music.MusicManager;
import com.projLDTS.blackjack.controller.music.MusicOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class MusicManagerTest {
    private MusicManager musicManager;

    @BeforeEach
    void setUp() {
        musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
    }

    @Test
    void testPlay() {
        Music music = Mockito.spy(new Music("src/main/resources/music/background_music.wav"));
        music.play();
        verify(music).play();
    }

    @Test
    void testLoop() {
        Music music = Mockito.spy(new Music("src/main/resources/music/background_music.wav"));
        music.loop();
        verify(music).loop();
    }

    @Test
    void testSetVolume() {
        Music music = Mockito.spy(new Music("src/main/resources/music/background_music.wav"));
        music.setVolume(0.5);
        verify(music).setVolume(0.5);
    }

    @Test
    void testPlaySelectButtonSound() {
        MusicManager musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
        musicManager.playMusicChoice(MusicOptions.OPTION_SELECT);
        verify(musicManager).playMusicChoice(MusicOptions.OPTION_SELECT);
    }
    @Test
    void testCardShuffleSound() {
        MusicManager musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
        musicManager.playMusicChoice(MusicOptions.SHUFFLE);
        verify(musicManager).playMusicChoice(MusicOptions.SHUFFLE);
    }

    @Test
    void testDealingCardSound() {
        MusicManager musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
        musicManager.playMusicChoice(MusicOptions.DEAL_CARD);
        verify(musicManager).playMusicChoice(MusicOptions.DEAL_CARD);
    }
    @Test
    void testGameOverSound() {
        MusicManager musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
        musicManager.playMusicChoice(MusicOptions.GAME_OVER);
        verify(musicManager).playMusicChoice(MusicOptions.GAME_OVER);
    }
    @Test
    void testClickButtonSound() {
        MusicManager musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
        musicManager.playMusicChoice(MusicOptions.OPTION_CLICK);
        verify(musicManager).playMusicChoice(MusicOptions.OPTION_CLICK);
    }
    @Test
    void testWinSound() {
        MusicManager musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
        musicManager.playMusicChoice(MusicOptions.WIN);
        verify(musicManager).playMusicChoice(MusicOptions.WIN);
    }

    @Test
    void testSetBackgroundSound() {
        MusicManager musicManager = Mockito.spy(new MusicManager());
        doNothing().when(musicManager).setBackgroundSound(any());
        musicManager.setBackgroundSound(MusicOptions.BACKGROUND_MUSIC);
        verify(musicManager).setBackgroundSound(MusicOptions.BACKGROUND_MUSIC);
    }

    @Test
    void testMusicManagerGetInstance() {
        MusicManager musicManager1 = MusicManager.getInstance();
        MusicManager musicManager2 = MusicManager.getInstance();
        assertSame(musicManager1, musicManager2);
    }
}
