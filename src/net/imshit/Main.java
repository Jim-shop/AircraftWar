package net.imshit;

import net.imshit.gui.GamePanel;
import net.imshit.gui.ScoreboardPanel;
import net.imshit.gui.SettingPanel;
import net.imshit.io.resource.ImageManager;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

/**
 * 程序入口
 *
 * @author jim-shop
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            /* 加载材质 */
            try {
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            /* 初始化 Frame */
            var frame = new JFrame(Config.GAME_NAME);
            frame.setIconImage(ImageManager.ICON);
            frame.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
            frame.setResizable(false);
            // 设置屏幕居中
            frame.setLocationRelativeTo(null);
            // 设置退出策略
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            /* 设置默认布局 */
            var cardLayout = new CardLayout(0, 0);
            var panel = new JPanel(cardLayout);
            frame.add(panel);
            frame.setVisible(true);

            /* 游戏界面 */
            var setting = new SettingPanel();
            var game = new GamePanel();
            var scoreboard = new ScoreboardPanel();
            panel.add(setting);
            panel.add(game);
            panel.add(scoreboard);

            /* 界面切换逻辑 */
            setting.addDifficultyChangeCallback(host -> {
                cardLayout.next(panel);
                game.action(host);
            });
            game.addGameOverCallback(host -> {
                cardLayout.next(panel);
                scoreboard.action(host);
            });
            scoreboard.addScoreboardReturnCallback(host -> {
                cardLayout.first(panel);
                setting.action(host);
            });
        });
    }
}
