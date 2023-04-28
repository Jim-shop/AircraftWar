package net.imshit.gui;

import net.imshit.util.dao.ScoreInfo;
import net.imshit.util.dao.ScoreboardDao;
import net.imshit.util.dao.ScoreboardDaoFile;
import net.imshit.util.callback.Callback;
import net.imshit.util.config.Difficulty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Closeable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏结束后的得分排行榜
 *
 * @author Jim
 */
public class ScoreboardPanel extends JPanel implements Closeable {

    private final JTable table = new JTable();
    private final String[] caption = {"Name", "Score", "Time"};
    private final List<Callback<ScoreboardPanel>> callbacks = new LinkedList<>();
    private ScoreboardDao dao;
    private Difficulty difficulty;
    private final DefaultTableModel model;

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
        buttonDelete.addActionListener(this::removeItems);
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

        /* 扫尾 */
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));

        this.model = new DefaultTableModel(null, this.caption) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table.setModel(model);
    }

    public void addScoreboardReturnCallback(Callback<ScoreboardPanel> callback) {
        callbacks.add(callback);
    }

    private void load() {
        if (this.dao != null) {
            this.dao.close();
        }
        this.dao = new ScoreboardDaoFile(switch (difficulty) {
            case EASY -> "./record-easy.dat";
            case MEDIUM -> "./record-medium.dat";
            case HARD -> "./record-hard.dat";
            default -> "./record.dat";
        });
        this.refresh();
    }

    private void refresh() {
        this.model.setRowCount(0);
        for (var item : this.dao.getTopK(-1)) {
            this.model.addRow(new String[]{item.name(), String.valueOf(item.score()), item.time().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)});
        }
    }

    private void askAndSave(GamePanel host) {
        var name = JOptionPane.showInputDialog("Enter your name:", "player");
        if (name != null) {
            this.dao.addItem(new ScoreInfo(name, host.getScore(), LocalDateTime.now()));
            this.refresh();
        }
    }

    public void action(GamePanel host) {
        this.difficulty = host.getDifficulty();
        this.load();
        this.askAndSave(host);
    }

    @Override
    public void close() {
        if (this.dao != null) {
            this.dao.close();
        }
    }

    private void removeItems(ActionEvent event) {
        this.dao.deleteItem(this.table.getSelectedRows());
        this.refresh();
    }
}
