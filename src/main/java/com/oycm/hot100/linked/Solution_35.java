package com.oycm.hot100.linked;

import java.util.HashMap;
import java.util.Map;

public class Solution_35 {

    /**
     * 146. <a href="https://leetcode.cn/problems/lru-cache/description/">LRU 缓存</a>
     */
    class LRUCache {
        /*
        实现 LRUCache 类：
            LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存。
            int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
            void put(int key, int value)
                如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
                如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
         */

        // 双向链表
        private static class Node {
            int key, value;
            Node prev, next;

            Node(int k, int v) {
                key = k;
                value = v;
            }
        }

        private final int capacity;
        private final Node dummy = new Node(0, 0); // 哨兵节点
        private final Map<Integer, Node> keyToNode = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            dummy.prev = dummy;
            dummy.next = dummy;
        }

        public int get(int key) {
            Node node = getNode(key);
            return node == null ? -1 : node.value;
        }

        public void put(int key, int value) {
            // 存在旧的节点，数量肯定不会超过
            Node node = getNode(key);
            if (node != null) {
                node.value = value;
                return;
            }
            node = new Node(key, value);
            keyToNode.put(key, node);
            pushFront(node);
            if (keyToNode.size() > capacity) {
                // 移除最后的节点
                Node removed = dummy.prev;
                keyToNode.remove(removed.key);
                remove(removed);
            }

        }

        private Node getNode(int key) {
            // 链表中没有这个节点
            if (!keyToNode.containsKey(key)) {
                return null;
            }
            Node node = keyToNode.get(key);
            // 从链表中删除节点
            remove(node);
            // 插入到最前面
            pushFront(node);
            return node;
        }

        private void remove(Node x) {
            /*
            a ↔ x ↔ c => a ↔ c
            a.next == x; x.prev == a
            x.next == c; c.prev == x
             */
            x.prev.next = x.next;
            x.next.prev = x.prev;
        }

        private void pushFront(Node x) {
            /*
            d ↔ a => d ↔ x ↔ a
             */
            // dummy ← x
            x.prev = dummy;
            // x → a
            x.next = dummy.next;
            // dummy → x dummy.next = x
            x.prev.next = x;
            // x ← a
            x.next.prev = x;
        }
    }

}
