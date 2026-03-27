package com.oycm.datastructure.trie.basic;

import java.util.HashMap;
import java.util.Map;

public class Solution_6 {

    /**
     * 677. <a href="https://leetcode.cn/problems/map-sum-pairs/description/">键值映射</a>
     */
    class MapSum {

        Trie root;
        Map<String, Integer> map;

        public MapSum() {
            this.root = new Trie();
            map = new HashMap<>();
        }

        /**
         * 键 key 已经存在，那么原来的键值对 key-value 将被替代
         * @param key
         * @param val
         */
        public void insert(String key, int val) {
            Trie cur = root;
            int decVal = map.getOrDefault(key, 0);
            for (char c : key.toCharArray()) {
                c -= 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new Trie();
                }
                cur = cur.son[c];
                cur.val += val - decVal;
            }
            map.put(key, val);
        }

        /**
         *
         * @param prefix
         * @return 返回所有以该前缀 prefix 开头的键 key 的值的总和
         */
        public int sum(String prefix) {
            Trie cur = root;
            for (char c : prefix.toCharArray()) {
                c -= 'a';
                if (cur.son[c] == null) {
                    return 0;
                }
                cur = cur.son[c];
            }
            return cur.val;
        }
    }

    class Trie {
        Trie[] son = new Trie[26];
        /*
        key val 一直累加
         */
        int val;
    }
}
