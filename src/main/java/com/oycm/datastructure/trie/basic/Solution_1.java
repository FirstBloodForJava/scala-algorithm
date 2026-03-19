package com.oycm.datastructure.trie.basic;

public class Solution_1 {

    /**
     * 208. <a href="https://leetcode.cn/problems/implement-trie-prefix-tree/description/">实现 Trie (前缀树)</a>
     */
    class Trie {

        /*
        题解思路: 假设字符串里面只有 a 和 b, 从左到右遍历, a 视为往左走, b 视为往右走. 这样可以通过二叉树来记录字符串

        insert("aab") -> 生成 root -> 左左右 的二叉树, 并标记最后一个节点为终止节点
        search("aab") 查找是否存在 左左右 的路径，并且最后一个节点为终止节点
        startsWith("aab") 查找是否存在 左左右 的路径
        把上面的两个字符, 扩展到 26 字符, 就得到了一颗 26 叉树
         */
        Trie[] son = new Trie[26];
        boolean end;

        public Trie() {

        }

        public void insert(String word) {
            /*
            初始化 cur = this, 查找当前字符在 cur 的哪个位置, 不存在则创建一个新的节点;
            cur 执行找到的 son, 继续遍历下一个字符
            到终点, 把当前节点标记为终点节点
             */
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.son[idx] == null) {
                    cur.son[idx] = new Trie();
                }
                cur = cur.son[idx];
            }
            cur.end = true;
        }

        public boolean search(String word) {
            return find(word) == 1;
        }

        public boolean startsWith(String prefix) {
            return find(prefix) != 0;
        }

        public int find(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.son[idx] == null) {
                    return 0;
                }
                cur = cur.son[idx];
            }
            return cur.end ? 1 : 2;
        }

    }

}
