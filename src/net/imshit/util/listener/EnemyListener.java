package net.imshit.util.listener;

/**
 * 敌方监听端口
 *
 * @author Jim
 */
public interface EnemyListener {
    /**
     * 告诉被调者，发生甚么事了
     *
     * @param e 事件
     */
    void notify(Event e);
}
