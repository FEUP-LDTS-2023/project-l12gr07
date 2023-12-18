package com.projLDTS.blackjack.controller.music;

public class MusicManager {
    private static MusicManager INSTANCE = null;
    private final Music backgroundMusic;
    private final Music optionSelectMusic;
    private final Music winMusic;
    private final Music gameOverMusic;
    private final Music shuffleMusic;
    private final Music dealCardMusic;
    private final Music optionClickMusic;

    private MusicManager() {
        this.backgroundMusic = new Music("src/main/resources/music/background_music.wav");
        this.optionSelectMusic = new Music("src/main/resources/music/select.wav");
        this.winMusic = new Music("src/main/resources/music/win.wav");
        this.gameOverMusic = new Music("src/main/resources/music/game_over.wav");
        this.shuffleMusic = new Music("src/main/resources/music/card_shuffle.wav");
        this.dealCardMusic = new Music("src/main/resources/music/dealing_card.wav");
        this.optionClickMusic = new Music("src/main/resources/music/select_sucessfully.wav");
        this.backgroundMusic.setVolume(0.05);
    }
    public static MusicManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusicManager();
        }
        return INSTANCE;
    }
    public void setBackgroundSound(Music music) {
        backgroundMusic.loop();
    }
    public void playMusicChoice(MusicOptions music) {
        switch (music) {
            case OPTION_SELECT:
                optionSelectMusic.play();
                break;
            case WIN:
                winMusic.play();
                break;
            case GAME_OVER:
                gameOverMusic.play();
                break;
            case SHUFFLE:
                shuffleMusic.play();
                break;
            case DEAL_CARD:
                dealCardMusic.play();
                break;
            case OPTION_CLICK:
                optionClickMusic.play();
                break;
        }
    }
}
