package net.imshit.gui;

import net.imshit.io.scoreboard.ScoreInfo;
import net.imshit.io.scoreboard.ScoreboardDaoFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 游戏结束后的得分排行榜
 *
 * @author Jim
 */
public class ScoreboardPanel extends JPanel {

    private final JTable table = new JTable();
    private final String[] caption = {"Name", "Score", "Time"};
    private final ScoreboardDaoFile dao = new ScoreboardDaoFile("./record");
    private List<ScoreInfo> rawData;
    private String[][] displayData;

    public ScoreboardPanel() {
        super(new GridLayout(3, 1));

        var prompt = new JLabel("Scoreboard");
        var scoreboard = new JScrollPane(this.table);
        var buttons = new JPanel(new FlowLayout());
        this.add(prompt);
        this.add(scoreboard);
        this.add(buttons);

        var buttonDelete = new JButton("Delete");
        var buttonHome = new JButton("Home");
        buttonDelete.addActionListener(event -> {
            var rows = table.getSelectedRows();
            for (var index : rows) {
                dao.deleteItem(rawData.get(index));
            }
            this.load();
        });
        buttonHome.addActionListener(event -> {
            // TODO
        });
        buttons.add(buttonDelete);
        buttons.add(buttonHome);
    }

    private void load() {
        this.rawData = this.dao.getTopKItem(-1);
        this.displayData = new String[this.rawData.size()][];
        var index = 0;
        for (var item : this.rawData) {
            this.displayData[index] = new String[]{item.name(), String.valueOf(item.score()), item.time().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)};
            index++;
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
        this.load();
        this.askAndSave(host);
    }
}
