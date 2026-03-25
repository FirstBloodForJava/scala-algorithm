package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_11 {

    /**
     * 173. <a href="https://leetcode.cn/problems/binary-search-tree-iterator/description/">二叉搜索树迭代器</a>
     *
     */
    class BSTIterator {
        /*
        暴力的做法是 中序遍历 root 树
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur;

        public BSTIterator(TreeNode root) {
            cur = root;
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 最左边
            cur = stack.pop();
            int res = cur.val;
            // 下一个为 cur.right
            cur = cur.right;
            return res;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }

    }


}
