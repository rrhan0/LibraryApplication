package ui;


import java.io.*;
import javax.sound.sampled.*;

// represents the audio player for .wav files
public class AudioPlayer {

    // EFFECTS: plays the file given in the path
    public static void playSound(String path) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(path)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
