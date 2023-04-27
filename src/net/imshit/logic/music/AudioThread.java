package net.imshit.logic.music;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * 播放音乐的线程
 *
 * @author Jim
 */
public class AudioThread extends Thread {

    private final URL url;
    private final boolean loop;

    public AudioThread(URL url, boolean loop) {
        this.url = url;
        this.loop = loop;
    }

    @Override
    public void run() {
        try (var audioIn = AudioSystem.getAudioInputStream(url); var clip = AudioSystem.getClip()) {
            clip.open(audioIn);
            if (this.loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
            synchronized (this) {
                do {
                    this.wait(500);
                } while (!this.isInterrupted() && clip.isActive());
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (InterruptedException ignored) {
        }
    }
}
