package com.oycm.datastructure.linked.integrate;

import java.util.HashMap;
import java.util.Map;

/**
 * 460. <a href="https://leetcode.cn/problems/lfu-cache/description/">LFU 缓存</a>
 */
public class LFUCache {

    /*
    思路 和 LRU 大致相同, 访问的时候同时增加次数, 关键是数量满了, 怎么找出 范围次数最少, 且最久未访问的节点
     */
    static class Node {
        int key, value, cnt;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
            cnt = 1;
        }
    }

    private int capacity;
    private final Map<Integer, Node> keyNode = new HashMap<>();
    private final Map<Integer, Node> cntNode = new HashMap<>();
    private int minCnt;

    public LFUCache(int capacity) {
        this.capacity = capacity;

    }

    /**
     * @param key
     * @return 如果键 key 存在于缓存中，则获取键的值，否则返回 -1
     */
    public int get(int key) {
        Node node = getNode(key);
        if (node == null) {
            return -1;
        }
        return node.value;
    }

    /**
     * 如果 键 key 已存在，则变更其值；如果 键 key 不存在，则插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的 键 key。
     * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该移除 最久未使用的 键 key。
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return ;
        }
        if (keyNode.size() == capacity) {
            // 删除最低频次的末尾节点
            Node dummy = cntNode.get(minCnt);
            Node last = dummy.prev;
            keyNode.remove(last.key);
            remove(last);
            if (dummy.next == dummy) {
                cntNode.remove(minCnt);
            }
        }
        node = new Node(key, value);
        keyNode.put(key, node);
        pushFront(node);
        minCnt = 1;
    }

    // 创建新频次的头节点
    private Node newHead() {
        Node dummy = new Node(0, 0);
        dummy.next = dummy;
        dummy.prev = dummy;
        return dummy;
    }

    private Node getNode(int key) {
        if (!keyNode.containsKey(key)) {
            return null;
        }
        Node node = keyNode.get(key);
        // 删除节点
        remove(node);
        Node dummy = cntNode.get(node.cnt);
        if (dummy.next == dummy) {
            cntNode.remove(node.cnt);
            if (minCnt == node.cnt) {
                minCnt++;
            }
        }
        // 加入到新的频次的头节点
        node.cnt++;
        pushFront(node);
        return node;
    }

    private void remove(Node x) {
            /*
            删除 b
            a -> b -> c x.prev.next = x.next; 切断这里
            a <- b <- c x.next.prev = x.prev; 切断这里
             */
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    private void pushFront(Node x) {
        // 不存在则添加
        Node dummy = cntNode.computeIfAbsent(x.cnt, n -> newHead());
        // 指向前一个
        x.prev = dummy;
        // 指向后一个
        x.next = dummy.next;
        // 前一个指向当前
        x.prev.next = x;
        // 后一个指向当前
        x.next.prev = x;
    }
}
