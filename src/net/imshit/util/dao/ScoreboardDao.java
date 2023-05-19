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
