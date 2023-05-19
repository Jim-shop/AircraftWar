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

package net.imshit.util.control;

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
                var x = e.getX();
                var y = e.getY();
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
