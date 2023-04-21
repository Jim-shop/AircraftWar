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
        try {
            var audioIn = AudioSystem.getAudioInputStream(file);
            var clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(this.loop ? Clip.LOOP_CONTINUOUSLY : 0);
            try {
                while (!this.isInterrupted()) {
                    Thread.sleep(10);
                }
            } catch (InterruptedException ignored) {
            } finally {
                clip.stop();
                clip.close();
                audioIn.close();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
