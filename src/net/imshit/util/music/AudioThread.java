/*
 * Copyright (c) 2023 Jim-shop
 * AircraftWar is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

package net.imshit.util.music;

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
