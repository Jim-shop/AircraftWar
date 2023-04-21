package net.imshit.io.control;

import net.imshit.Config;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.gui.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 英雄机控制类
 * 监听鼠标，控制英雄机的移动
 *
 * @author hitsz
 */
public class HeroController {
    public HeroController(GamePanel game, HeroAircraft heroAircraft) {
        var mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getX();
                int y = e.getY();
                if (x < 0 || x > Config.WINDOW_WIDTH || y < 0 || y > Config.WINDOW_HEIGHT) {
                    // 防止超出边界
                    return;
                }
                heroAircraft.setLocation(x, y);
            }
        };
        game.addMouseListener(mouseAdapter);
        game.addMouseMotionListener(mouseAdapter);
    }
}
