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

package net.imshit.element.animation;

import net.imshit.Config;
import net.imshit.element.AbstractFlyingObject;
import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.bullet.AbstractBullet;

import java.awt.image.BufferedImage;

/**
 * 死亡动画
 *
 * @author Jim
 */
public class DyingAnimation extends AbstractFlyingObject {
    private static final int LIGHT_INCREMENT = 0x20;
    private static final int LIVE_MS = 500;
    private static final int FLASH_MS = 100;
    private final BufferedImage baseImage, lightImage;
    private int life = LIVE_MS;

    public DyingAnimation(AbstractAircraft<? extends AbstractBullet> aircraft) {
        super(aircraft.getLocationX(), aircraft.getLocationY(), 0, 0);
        this.baseImage = aircraft.getImage();
        this.lightImage = this.makeLightImage(this.baseImage);
        this.image = this.baseImage;
    }

    private BufferedImage makeLightImage(BufferedImage image) {
        var width = image.getWidth();
        var height = image.getHeight();
        BufferedImage lightImage = new BufferedImage(width, height, image.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var argb = image.getRGB(i, j);
                var a = (argb) >> 24 & 0xff;
                var r = Math.min(((argb >> 16) & 0xff) + LIGHT_INCREMENT, 0xff);
                var g = Math.min(((argb >> 8) & 0xff) + LIGHT_INCREMENT, 0xff);
                var b = Math.min((argb & 0xff) + LIGHT_INCREMENT, 0xff);
                lightImage.setRGB(i, j, (a << 24) | (r << 16) | (g << 8) | b);
            }
        }
        return lightImage;
    }

    @Override
    public void forward() {
        this.life -= Config.REFRESH_INTERVAL;
        if (this.life <= 0) {
            this.vanish();
        }
        if ((this.life / FLASH_MS) % 2 == 1) {
            this.image = this.baseImage;
        } else {
            this.image = this.lightImage;
        }
    }
}
