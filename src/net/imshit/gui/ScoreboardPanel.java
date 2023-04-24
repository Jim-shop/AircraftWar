package net.imshit.gui;

import net.imshit.io.scoreboard.ScoreInfo;
import net.imshit.io.scoreboard.ScoreboardDaoFile;
import net.imshit.logic.config.Difficulty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏结束后的得分排行榜
 *
 * @author Jim
 */
public class ScoreboardPanel extends JPanel {

    private final JTable table = new JTable();
    private final String[] caption = {"Name", "Score", "Time"};
    private ScoreboardDaoFile dao;
    private List<ScoreInfo> rawData;
    private String[][] displayData;

    private final List<ScoreboardReturnCallback> callbacks = new LinkedList<>();
    private Difficulty difficulty;

    public ScoreboardPanel() {
        super(new GridBagLayout());
        var constrain = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        /*
        -----------------------------------------------------------------
        |                            <spacer0>                          |   1
        |                             caption                           |   2
        |                            <spacer1>                          |   1
        |                                                               |
        |                            scoreboard                         |   5
        |                                                               |
        |                            <spacer2>                          |   1
        | <spacerL> | buttonDelete | <spacerM> | buttonHome | <spacerR> |   2
        |                            <spacer3>                          |   1
        -----------------------------------------------------------------
              1     :       2      :     1     :      2     :     1
         */

        // spacer0
        constrain.gridx = 0;
        constrain.gridy = 0;
        constrain.gridwidth = 7;
        constrain.weightx = 7;
        this.add(new JPanel(), constrain);

        // caption
        constrain.gridy = 1;
        constrain.gridheight = 2;
        constrain.weighty = 2;
        this.add(new JLabel("Scoreboard", JLabel.CENTER), constrain);

        // spacer1
        constrain.gridy = 3;
        constrain.gridheight = 1;
        constrain.weighty = 1;
        this.add(new JPanel(), constrain);

        // scoreboard
        constrain.gridy = 4;
        constrain.gridheight = 5;
        constrain.weighty = 5;
        this.add(new JScrollPane(this.table), constrain);

        // spacer2
        constrain.gridy = 9;
        constrain.gridheight = 1;
        constrain.weighty = 1;
        this.add(new JPanel(), constrain);

        // spacerL
        constrain.gridx = 0;
        constrain.gridy = 10;
        constrain.gridwidth = 1;
        constrain.gridheight = 2;
        constrain.weightx = 1;
        constrain.weighty = 2;
        this.add(new JPanel(), constrain);

        // buttonDelete
        constrain.gridx = 1;
        constrain.gridwidth = 2;
        constrain.weightx = 2;
        var buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(event -> {
            var rows = table.getSelectedRows();
            for (var index : rows) {
                dao.deleteItem(rawData.get(index));
            }
            this.load();
        });
        this.add(buttonDelete, constrain);

        // spacerM
        constrain.gridx = 3;
        constrain.gridwidth = 1;
        constrain.weightx = 1;
        this.add(new JPanel(), constrain);

        // buttonHome
        constrain.gridx = 4;
        constrain.gridwidth = 2;
        constrain.weightx = 2;
        var buttonHome = new JButton("Home");
        buttonHome.addActionListener(event -> {
            for (var callback : callbacks) {
                callback.action(this);
            }
        });
        this.add(buttonHome, constrain);

        // spacerR
        constrain.gridx = 6;
        constrain.gridwidth = 1;
        constrain.weightx = 1;
        this.add(new JPanel(), constrain);

        // spacer3
        constrain.gridx = 0;
        constrain.gridy = 12;
        constrain.gridwidth = 7;
        constrain.gridheight = 1;
        constrain.weightx = 7;
        constrain.weighty = 1;
        this.add(new JPanel(), constrain);
    }

    public void addScoreboardReturnCallback(ScoreboardReturnCallback callback) {
        callbacks.add(callback);
    }

    public interface ScoreboardReturnCallback {
        /**
         * 当点击返回主页按钮后，执行回调函数
         *
         * @param host Scoreboard对象本身
         */
        void action(ScoreboardPanel host);
    }

    private void load() {
        this.dao = new ScoreboardDaoFile(switch (difficulty) {
            case EASY -> "./record-easy.dat";
            case MEDIUM -> "./record-medium.dat";
            case HARD -> "./record-hard.dat";
            default -> "./record.dat";
        });
        this.rawData = this.dao.getTopK(-1);
        this.displayData = new String[this.rawData.size()][];
        var index = 0;
        for (var item : this.rawData) {
            this.displayData[index++] = new String[]{item.name(), String.valueOf(item.score()), item.time().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)};
        }
        this.table.setModel(new DefaultTableModel(this.displayData, this.caption) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private void askAndSave(GamePanel host) {
        var name = JOptionPane.showInputDialog("Enter your name:", "player");
        dao.addItem(new ScoreInfo(name, host.getScore(), LocalDateTime.now()));
        this.load();
    }

    public void action(GamePanel host) {
        this.difficulty = host.getDifficulty();
        this.load();
        this.askAndSave(host);
    }
}
