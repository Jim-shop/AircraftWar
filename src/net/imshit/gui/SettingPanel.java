package net.imshit.gui;

import net.imshit.logic.config.Difficulty;
import net.imshit.utils.callback.Callback;

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
    private final List<Callback<SettingPanel>> callbacks = new LinkedList<>();
    private Difficulty difficulty = Difficulty.NOTSET;
    private boolean musicOn = true;

    public SettingPanel() {
        super(new GridBagLayout());
        var constrain = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        /*
              1     :          2        :     1
        ---------------------------------------------
        | <spacerL> |     <spacer0>     | <spacerR> |   1
        |           |     buttonEasy    |           |   2
        |           |     <spacer1>     |           |   1
        |           |    buttonMedium   |           |   2
        |           |     <spacer2>     |           |   1
        |           |     buttonHard    |           |   2
        |           |     <spacer3>     |           |   3
        |           |   checkboxMusic   |           |   2
        |           |     <spacer4>     |           |   1
        ---------------------------------------------
         */

        // 左
        // spacerL
        constrain.gridx = 0;
        constrain.gridy = 0;
        constrain.gridwidth = 1;
        constrain.weightx = 1;
        this.add(new JPanel(), constrain);

        // 右
        // spacerR
        constrain.gridx = 3;
        constrain.gridy = 0;
        constrain.gridwidth = 1;
        constrain.weightx = 1;
        this.add(new JPanel(), constrain);

        // 中
        constrain.gridx = 1;
        constrain.gridwidth = 2;
        constrain.weightx = 2;

        // spacer0
        constrain.gridy = 0;
        constrain.gridheight = 1;
        constrain.weighty = 1;
        this.add(new JPanel(), constrain);
        // buttonEasy
        constrain.gridy = 1;
        constrain.gridheight = 2;
        constrain.weighty = 2;
        var buttonEasy = new JButton("Easy");
        buttonEasy.addActionListener(event -> {
            this.difficulty = Difficulty.EASY;
            notifyCaller();
        });
        this.add(buttonEasy, constrain);
        // spacer1
        constrain.gridy = 3;
        constrain.gridheight = 1;
        constrain.weighty = 1;
        this.add(new JPanel(), constrain);
        // buttonMedium
        constrain.gridy = 4;
        constrain.gridheight = 2;
        constrain.weighty = 2;
        var buttonMedium = new JButton("Medium");
        buttonMedium.addActionListener(event -> {
            this.difficulty = Difficulty.MEDIUM;
            notifyCaller();
        });
        this.add(buttonMedium, constrain);
        // spacer2
        constrain.gridy = 6;
        constrain.gridheight = 1;
        constrain.weighty = 1;
        this.add(new JPanel(), constrain);
        // buttonHard
        constrain.gridy = 7;
        constrain.gridheight = 2;
        constrain.weighty = 2;
        var buttonHard = new JButton("Hard");
        buttonHard.addActionListener(event -> {
            this.difficulty = Difficulty.HARD;
            notifyCaller();
        });
        this.add(buttonHard, constrain);
        // spacer3
        constrain.gridy = 9;
        constrain.gridheight = 3;
        constrain.weighty = 3;
        this.add(new JPanel(), constrain);
        // checkboxMusic
        constrain.gridy = 12;
        constrain.gridheight = 2;
        constrain.weighty = 2;
        var checkboxMusic = new JCheckBox("Music", this.musicOn);
        checkboxMusic.addActionListener(event -> {
            this.musicOn = checkboxMusic.isSelected();
        });
        this.add(checkboxMusic, constrain);
        // spacer4
        constrain.gridy = 14;
        constrain.gridheight = 1;
        constrain.weighty = 1;
        this.add(new JPanel(), constrain);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean getMusicOn() {
        return musicOn;
    }

    public void addDifficultyChangeCallback(Callback<SettingPanel> callback) {
        callbacks.add(callback);
    }

    private void notifyCaller() {
        for (var callback : callbacks) {
            callback.action(this);
        }
    }

    public void action(ScoreboardPanel scoreboardPanel) {
    }
}
