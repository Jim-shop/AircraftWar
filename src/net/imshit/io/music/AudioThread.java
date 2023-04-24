package net.imshit.io.music;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * 播放音乐的线程
 *
 * @author Jim
 */
public class AudioThread extends Thread {

    private final File file;
    private final boolean loop;

    public AudioThread(File file, boolean loop) {
        this.file = file;
        this.loop = loop;
    }

    @Override
    public void run() {
        try (var audioIn = AudioSystem.getAudioInputStream(file); var clip = AudioSystem.getClip()) {
            clip.open(audioIn);
            if (this.loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
            do {
                Thread.sleep(500);
            } while (!this.isInterrupted() && clip.isActive());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (InterruptedException ignored) {
        }
    }
}
