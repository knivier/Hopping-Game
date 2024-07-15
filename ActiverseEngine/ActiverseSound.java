package ActiverseEngine;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Represents a sound player for playing audio files.
 * This class provides methods to load, play, and stop audio files.
 */
public class ActiverseSound {
    private final String filename;
    private Clip clip;

    /**
     * Constructs a new ActiverseSound object with the specified audio file.
     *
     * @param filename The path to the audio file.
     */
    public ActiverseSound(String filename) {
        this.filename = filename;
        try {
            // Load the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the loaded audio file.
     */
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * Stops the currently playing audio file.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * Checks if the audio file is currently playing.
     *
     * @return true if the audio file is playing, false otherwise.
     */
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    /**
     * Gets the filename of the audio file.
     *
     * @return The filename of the audio file.
     */
    public String getFilename() {
        return filename;
    }
}
