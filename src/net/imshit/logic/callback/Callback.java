package net.imshit.logic.callback;

/**
 * @author Jim
 */
public interface Callback<T> {
    /**
     * 事件发生时，触发action函数
     *
     * @param host 发送信号的对象
     */
    void action(T host);
}