package net.imshit.element;

import net.imshit.Config;
import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.util.resource.ImageManager;

import java.awt.image.BufferedImage;

/**
 * 可飞行对象的父类
 *
 * @author jim
 */
public abstract class AbstractFlyingObject {

    /**
     * 图片中心 x 轴坐标
     */
    protected float locationX;

    /**
     * 图片中心 y 轴坐标
     */
    protected float locationY;

    /**
     * x 轴移动速度
     */
    protected float speedX;

    /**
     * y 轴移动速度
     */
    protected float speedY;

    /**
     * 图片
     * null 表示未设置
     */
    protected BufferedImage image = null;

    /**
     * x 轴长度，根据图片尺寸获得
     * -1 表示未设置
     */
    protected float width = -1;

    /**
     * y 轴长度，根据图片尺寸获得
     * -1 表示未设置
     */
    protected float height = -1;

    /**
     * 有效（生存）标记，
     * 通常标记为 false的对象会在下次刷新时清除
     */
    protected boolean isValid = true;

    public AbstractFlyingObject(float locationX, float locationY, float speedX, float speedY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * 可飞行对象根据速度移动
     * 若飞行对象触碰到横向边界，横向速度反向
     */
    public void forward() {
        locationX += speedX * Config.REFRESH_INTERVAL;
        locationY += speedY * Config.REFRESH_INTERVAL;
        if (locationX < 0 || locationX >= Config.WINDOW_WIDTH || locationY >= Config.WINDOW_HEIGHT) {
            vanish();
        }
    }

    /**
     * 碰撞检测，当对方坐标进入我方范围，判定我方击中
     * 对方与我方覆盖区域有交叉即判定撞击。
     * <br />
     * 非飞机对象区域：
     * 横向，[x - width/2, x + width/2]
     * 纵向，[y - height/2, y + height/2]
     * <br />
     * 飞机对象区域：
     * 横向，[x - width/2, x + width/2]
     * 纵向，[y - height/4, y + height/4]
     *
     * @param flyingObject 撞击对方
     * @return true: 我方被击中; false 我方未被击中
     */
    public boolean crash(AbstractFlyingObject flyingObject) {
        // 缩放因子，用于控制 y轴方向区域范围
        var factor = this instanceof AbstractAircraft ? 2 : 1;
        var fFactor = flyingObject instanceof AbstractAircraft ? 2 : 1;

        var fLocationX = flyingObject.getLocationX();
        var fLocationY = flyingObject.getLocationY();
        var xLimit = (this.getWidth() + flyingObject.getWidth()) / 2f;
        var yLimit = (this.getHeight() / factor + flyingObject.getHeight() / fFactor) / 2f;

        return Math.abs(locationX - fLocationX) < xLimit && Math.abs(locationY - fLocationY) < yLimit;
    }

    public float getLocationX() {
        return locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocation(float locationX, float locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public float getSpeedY() {
        return speedY;
    }

    public BufferedImage getImage() {
        if (image == null) {
            image = ImageManager.get(this);
        }
        return image;
    }

    public float getWidth() {
        if (width < 0) {
            // 若未设置，则查询图片宽度并设置
            width = ImageManager.get(this).getWidth();
        }
        return width;
    }

    public float getHeight() {
        if (height < 0) {
            // 若未设置，则查询图片高度并设置
            height = ImageManager.get(this).getHeight();
        }
        return height;
    }

    public boolean notValid() {
        return !this.isValid;
    }

    public boolean valid() {
        return this.isValid;
    }

    /**
     * isValid = false.
     * notValid() => true.
     */
    public void vanish() {
        isValid = false;
    }

}

