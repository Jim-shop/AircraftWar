package net.imshit.scoreboard;

import java.util.List;

public interface ScoreboardDao {
    /**
     * 返回最好的k条记录
     * @param topK 要返回的记录数。=-1时，返回所有记录
     * @return 返回记录列表
     */
    List<ScoreInfo> getTopKItem(int topK);
    void addItem(ScoreInfo item);
    void deleteItem(ScoreInfo item);
}
