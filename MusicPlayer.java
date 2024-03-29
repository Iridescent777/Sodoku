package sudoku;

import java.io.*;
import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;

    public MusicPlayer(String filepath) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void GameOveralMusicplay() {
        clip.setFramePosition(0); // set the playback position to the beginning
        clip.loop(Clip.LOOP_CONTINUOUSLY); // plays the music in a loop
    }


    public void GameLastSecondsMusicplay() {
        clip.setFramePosition((int)(1 * clip.getFormat().getFrameRate())); // set the playback position to the beginning
        clip.loop(Clip.LOOP_CONTINUOUSLY); // plays the music in a loop
    }

    public void HomeMusicplay() {
        clip.setFramePosition(0); // set the playback position to the beginning
        clip.loop(Clip.LOOP_CONTINUOUSLY); // plays the music in a loop
    }


    public void stop() {
        clip.stop(); // stops the music
    }

}