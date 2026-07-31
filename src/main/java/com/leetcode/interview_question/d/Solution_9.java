package com.leetcode.interview_question.d;

import com.oycm.TreeNode;

import java.util.*;

public class Solution_9 {

    /**
     * 面试题 04.09. <a href="https://leetcode.cn/problems/bst-sequences-lcci/description/">二叉搜索树序列</a>
     *
     * @param root
     * @return
     */
    public List<List<Integer>> BSTSequences(TreeNode root) {
        /*
        从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
        给定一个由不同节点组成的二叉搜索树 root，输出所有可能生成此树的数组。
         */
        /*
        题解思路：回溯
        相当不跨越任何节点遍历这棵树，有哪些遍历结果。
        方案数，可以通过排列组合计算出来
         */
        path = new LinkedList<>();
        result = new ArrayList<>();
        Deque<TreeNode> dq = new ArrayDeque<>();
        if (root != null) {
            dq.add(root);
        }

        dfs(dq);

        return result;
    }

    private LinkedList<Integer> path;
    private List<List<Integer>> result;

    public void dfs(Deque<TreeNode> dq) {
        if (dq.isEmpty()) {
            result.add(new ArrayList<>(path));
            return;
        }
        int size = dq.size();
        while (size > 0) {
            TreeNode first = dq.pollFirst();
            path.add(first.val);
            int children = 0;
            if (first.left != null) {
                children++;
                dq.addLast(first.left);
            }
            if (first.right != null) {
                children++;
                dq.addLast(first.right);
            }

            dfs(dq);

            while (children-- > 0) {
                dq.pollLast();
            }

            dq.addLast(first);

            path.removeLast();
            size--;
        }
    }

}
