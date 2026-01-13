package com.oycm.datastructure.linked.integrate;


public class Solution_4 {

    /**
     * 146. <a href="https://leetcode.cn/problems/lru-cache/description/">LRU 缓存</a>
     *
     */
    static class LRUCache {
        int capacity;

        /**
         *
         * @param capacity 初始化 LRU 缓存
         */
        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        /**
         *
         * @param key
         * @return 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
         */
        public int get(int key) {
            return -1;
        }

        /**
         * 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
         * @param key
         * @param value
         */
        public void put(int key, int value) {

        }
    }

}
