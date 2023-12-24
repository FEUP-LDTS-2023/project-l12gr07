//package com.projLDTS.blackjack.controller.music;
//
//import com.projLDTS.blackjack.controller.music.Music;
//import com.projLDTS.blackjack.controller.music.MusicManager;
//import com.projLDTS.blackjack.controller.music.MusicOptions;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class MusicManagerTest {
//    private static MusicManager musicManager;
//    private Music musicMock;
//
//    @BeforeEach
//    void setUp() {
//        musicManager = Mockito.spy(new MusicManager());
//        doNothing().when(musicManager).setBackgroundSound(any());
//        musicMock = Mockito.spy(new Music("src/main/resources/music/background_music.wav"));
//    }
//    @AfterEach
//    void tearDown() {
//        musicManager = null;
//        musicMock.close();
//
//    }
//    @Test
//    void testPlay() {
//        doNothing().when(musicMock).play();
//        musicMock.play();
//        verify(musicMock).play();
//    }
//
//    @Test
//    void testLoop() {
//        doNothing().when(musicMock).play();
//        musicMock.play();
//        verify(musicMock).play();
//    }
//
//    @Test
//    void testSetVolume() {
//        doNothing().when(musicMock).setVolume(0.5);
//        musicMock.setVolume(0.5);
//        verify(musicMock, times(1)).setVolume(0.5);
//    }
//
//    @Test
//    void testPlaySelectButtonSound() {
//        Music music = mock(Music.class);
//        doNothing().when(musicManager).playMusicChoice(MusicOptions.OPTION_SELECT);
//        musicManager.playMusicChoice(MusicOptions.OPTION_SELECT);
//        verify(musicManager, times(1)).playMusicChoice(MusicOptions.OPTION_SELECT);
//    }
////    @Test
////    void testCardShuffleSound() {
////        doNothing().when(musicManager).playMusicChoice(MusicOptions.SHUFFLE);
////        musicManager.playMusicChoice(MusicOptions.SHUFFLE);
////        verify(musicManager).playMusicChoice(MusicOptions.SHUFFLE);
////    }
//
//    @Test
//    void testClickButtonSound() {
//        musicManager.playMusicChoice(MusicOptions.OPTION_CLICK);
//        verify(musicManager, times(1)).playMusicChoice(MusicOptions.OPTION_CLICK);
//    }
//    @Test
//    void testWinSound() {
//        doNothing().when(musicManager).playMusicChoice(MusicOptions.WIN);
//        musicManager.playMusicChoice(MusicOptions.WIN);
//        verify(musicManager).playMusicChoice(MusicOptions.WIN);
//    }
//
//    @Test
//    void testSetBackgroundSound() {
//        musicManager.setBackgroundSound(MusicOptions.BACKGROUND_MUSIC);
//        verify(musicManager, times(1)).setBackgroundSound(MusicOptions.BACKGROUND_MUSIC);
//    }
////    @Test
////    void testDealingCardSound() {
////        doNothing().when(musicManager).playMusicChoice(MusicOptions.DEAL_CARD);
////        musicManager.playMusicChoice(MusicOptions.DEAL_CARD);
////        verify(musicManager, times(1)).playMusicChoice(MusicOptions.DEAL_CARD);
////    }
//
////    @Test
////    void testGameOverSound() {
////        doNothing().when(musicManager).playMusicChoice(MusicOptions.GAME_OVER);
////        musicManager.playMusicChoice(MusicOptions.GAME_OVER);
////        verify(musicManager, times(1)).playMusicChoice(MusicOptions.GAME_OVER);
////    }
////    @Test
////    void testMusicManagerGetInstance() {
////        MusicManager musicManager1 = MusicManager.getInstance();
////        MusicManager musicManager2 = MusicManager.getInstance();
////        assertSame(musicManager1, musicManager2);
////    }
//}
