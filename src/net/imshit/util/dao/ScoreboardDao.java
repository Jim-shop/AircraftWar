package net.imshit.util.dao;

import java.io.Closeable;
import java.util.List;

/**
 * 计分板DAO模型
 *
 * @author Jim
 */
public interface ScoreboardDao extends Closeable {
    /**
     * 返回最好的k条记录
     *
     * @param topK 要返回的记录数。=-1时，返回所有记录
     * @return 返回记录列表
     */
    List<ScoreInfo> getTopK(int topK);

    /**
     * 向计分板添加项目（去重）
     *
     * @param item 项目
     */
    void addItem(ScoreInfo item);

    /**
     * 从计分板删除项目
     *
     * @param item 项目
     */
    void deleteItem(ScoreInfo item);

    /**
     * 从计分板删除项目
     *
     * @param indices 内部序号
     */
    void deleteItem(int[] indices);

    /**
     * 关闭计分板数据模型（通常来说意味着缓存写回）
     */
    void close();
}
