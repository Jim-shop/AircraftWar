package net.imshit.gui;

import net.imshit.logic.config.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏开始前选择难度的Panel
 *
 * @author Jim
 */
public class SettingPanel extends JPanel {
    private final List<DifficultyChangeCallback> callbacks;
    private Difficulty difficulty = Difficulty.NOTSET;
    private boolean musicOn = true;

    public SettingPanel() {
        super(new GridLayout(2, 1));
        callbacks = new LinkedList<>();

        var difficultyGroup = new JPanel(new GridLayout(2, 1));
        this.add(difficultyGroup);

        var difficultyPrompt = new JLabel("Choose difficulty:");
        var difficultyButtons = new JPanel(new FlowLayout());
        difficultyGroup.add(difficultyPrompt);
        difficultyGroup.add(difficultyButtons);

        var buttonEasy = new JButton("Easy");
        var buttonMedium = new JButton("Medium");
        var buttonHard = new JButton("Hard");
        buttonEasy.addActionListener(event -> {
            this.difficulty = Difficulty.EASY;
            notifyCaller();
        });
        buttonMedium.addActionListener(event -> {
            this.difficulty = Difficulty.MEDIUM;
            notifyCaller();
        });
        buttonHard.addActionListener(event -> {
            this.difficulty = Difficulty.HARD;
            notifyCaller();
        });
        difficultyButtons.add(buttonEasy);
        difficultyButtons.add(buttonMedium);
        difficultyButtons.add(buttonHard);

        var soundGroup = new JPanel(new GridLayout(1, 2));
        this.add(soundGroup);

        var soundPrompt = new JLabel("Sound:");
        var checkboxMusic = new JCheckBox("Music", this.musicOn);
        soundGroup.add(soundPrompt);
        soundGroup.add(checkboxMusic);

        checkboxMusic.addActionListener(event -> {
            this.musicOn = checkboxMusic.isSelected();
        });
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean getMusicOn() {
        return musicOn;
    }

    public void addDifficultyChangeCallback(DifficultyChangeCallback callback) {
        callbacks.add(callback);
    }

    private void notifyCaller() {
        for (var callback : callbacks) {
            callback.action(this);
        }
    }

    public interface DifficultyChangeCallback {

        /**
         * 当游戏难度被选择时，调用回调函数
         *
         * @param host 难度选择面板本身
         */
        void action(SettingPanel host);
    }
}
