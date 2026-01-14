package com.oycm.datastructure.linked.integrate;


import java.util.HashMap;
import java.util.Map;

public class Solution_4 {

    /**
     * 146. <a href="https://leetcode.cn/problems/lru-cache/description/">LRU 缓存</a>
     *
     */
    static class LRUCache {
        int capacity;
        private final Node dummy = new Node(0, 0);
        private final Map<Integer, Node> keyToNode = new HashMap<>();

        /**
         *
         * @param capacity 初始化 LRU 缓存
         */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            // 哨兵节点指向自己
            dummy.prev = dummy;
            dummy.next = dummy;
        }

        /**
         *
         * @param key
         * @return 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
         */
        public int get(int key) {
            Node node = getNode(key);
            return node != null ? node.value : -1;
        }

        /**
         * 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            Node node = getNode(key);
            if (node != null) {
                node.value = value;
                return;
            }
            // 插入
            node = new Node(key, value);
            keyToNode.put(key, node);
            pushFront(node);
            if (keyToNode.size() > capacity) {
                // 删除最后一个节点
                Node last = dummy.prev;
                keyToNode.remove(last.key);
                remove(last);
            }
        }

        /**
         * 从双向链表中 删除 x 节点
         * @param x
         */
        private void remove(Node x) {
            /*
            删除 b
            a -> b -> c x.prev.next = x.next; 切断这里
            a <- b <- c x.next.prev = x.prev; 切断这里
             */
            x.prev.next = x.next;
            x.next.prev = x.prev;
        }

        /**
         * 添加到头节点
         * @param x
         */
        private void pushFront(Node x) {
            // 指向前一个
            x.prev = dummy;
            // 指向后一个
            x.next = dummy.next;
            // 前一个指向当前
            x.prev.next = x;
            // 后一个指向当前
            x.next.prev = x;
        }

        /**
         * 获取节点, 节点存在则移动至链首
         * @param key
         * @return
         */
        private Node getNode(int key) {
            // 节点不存在
            if (!keyToNode.containsKey(key)) {
                return null;
            }
            Node node = keyToNode.get(key);
            remove(node);
            pushFront(node);
            return node;
        }

    }

    static class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

}
