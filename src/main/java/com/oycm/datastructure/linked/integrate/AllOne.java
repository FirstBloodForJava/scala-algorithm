package com.oycm.datastructure.linked.integrate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. <a href="https://leetcode.cn/problems/all-oone-data-structure/description/">全 O(1) 的数据结构</a>
 * 设计存储字符串计数的数据结构, 能够返回计数最小和最大的字符串
 */
public class AllOne {

    /*
    考虑和 LFU 一样, 维护 [1, n] 计数的一摞字符串, 同时维护 次数的最小值 和 最大值
    关键是最大值 和 最小值 是否 好维护?
    最大值:
        增加次数时: 可以在 插入的时候不断和当前的 cnt 比较, 如果比当前大, 则更新
        减少次数时: 如果 max = 当前次数, max 的 一摞字符串为 空, 则 max--;
    最小值:
        增加次数时: key 不存在, 则 min = 1; 如果 key 存在; min = cnt, 次数为 1 一摞字符串为 空, 则 min++;
        减少次数时: 最小次数 key 被删除后, 不知道最小次数 key 是哪个? 如果使用 TreeMap, 增加操作不是 O(1) 的
    使用双向链表维护一个 单调递增的 相同次数 keys 节点
    inc 操作:
        如果 key 不在链表中, 如果链表为空 或 链表的头节点数量大于 1, 插入到头节点; 头节点数量为 1, 把 key 加入到节点中; keyNode 记录 key 和 node
        如果 key 在链表中, 获取 key 所在的节点, node.next.cnt == node.cnt + 1, node 中移除 key, 把 key 插入到 node.next;
            否则 在 node 后 插入 一个新的 node; 同时更新 key 的 node; 如果 node.keys 为空, 则需要从链表中删除该节点
    dec 操作: 获取 key 在链表中的节点, 根据 node.cnt 情况执行不同操作
        node.cnt == 1: keyNode 删除 key;
        node.cnt > 1:
            如果 前一个节点是 头节点 或 pre.cnt < cur.cnt - 1, 在 pre 后插入新节点 Node(key, cur.cnt-1); keyNode 更新 key value
            如果 前一个节点数量 == cur.cnt - 1, 在 pre.keys 中 添加 key; keyNode 更新 key value
        公共操作: node.keys 中删除 key; 如果 node.keys 是空的 删除 node 节点

     */
    private Map<String,Node> keyNode = new HashMap<>();
    private Node dummy;
    public AllOne() {
        dummy = new Node("", 0);
        dummy.next = dummy;
        dummy.prev = dummy;
    }

    /**
     * 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
     *
     * @param key
     */
    public void inc(String key) {
        if (!keyNode.containsKey(key)) {
            if (dummy.next == dummy || dummy.next.cnt > 1) {
                Node node = dummy.insert(new Node(key, 1));
                keyNode.put(key, node);
            } else {
                keyNode.put(key, dummy.next);
                dummy.next.keys.add(key);
            }
        } else {
            Node node = keyNode.get(key);
            if (node.next.cnt == node.cnt + 1) {
                node.next.keys.add(key);
                keyNode.put(key, node.next);
            } else {
                Node insert = node.insert(new Node(key, node.cnt + 1));
                keyNode.put(key, insert);
            }
            node.keys.remove(key);
            if (node.keys.isEmpty()) {
                node.remove();
            }
        }
    }

    /**
     * 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。
     * 测试用例保证：在减少计数前，key 存在于数据结构中。
     *
     * @param key
     */
    public void dec(String key) {
        Node node = keyNode.get(key);
        if (node.cnt == 1) {
            keyNode.remove(key);
        } else {
            if (node.prev.cnt == node.cnt - 1) {
                node.prev.keys.add(key);
                keyNode.put(key, node.prev);
            } else {
                keyNode.put(key, node.prev.insert(new Node(key, node.cnt - 1)));
            }
        }
        node.keys.remove(key);
        if (node.keys.isEmpty()) {
            node.remove();
        }
    }

    /**
     * @return 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 ""
     */
    public String getMaxKey() {
        return dummy.prev.keys.iterator().next();
    }

    /**
     * @return 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
     */
    public String getMinKey() {
        return dummy.next.keys.iterator().next();
    }

    static class Node{
        public Node prev;
        public Node next;
        public Set<String> keys;
        public int cnt;
        public Node(String key, int cnt) {
            this.cnt = cnt;
            this.keys = new HashSet<>();
            keys.add(key);
        }

        /**
         * 当前节点后插入节点
         * @param node
         * @return node
         */
        public Node insert(Node node) {
            node.prev = this;
            node.next = this.next;
            node.prev.next = node;
            node.next.prev = node;
            return node;
        }

        /**
         * 链表中删除该节点
         */
        public void remove() {
            this.next.prev = this.prev;
            this.prev.next = this.next;
        }
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        System.out.println(allOne.getMaxKey());
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

}
