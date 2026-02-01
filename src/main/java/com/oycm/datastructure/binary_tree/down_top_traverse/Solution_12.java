package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

import java.util.*;

public class Solution_12 {

    /**
     * 508. <a href="https://leetcode.cn/problems/most-frequent-subtree-sum/">出现次数最多的子树元素和</a>
     * <p>
     * 子树元素和: 以该结点为根的二叉树上所有结点的元素之和(包括结点本身)
     *
     * @param root
     * @return 出现次数最多的子树元素和, 多个元素出现次数相同, 返回所有次数最多的子树元素和(不限顺序)
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        /*
        自底向上遍历, 记录每个子树的和的次数
         */
        dfs(root);
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> kv : cnt.entrySet()) {
            int c = kv.getValue();
            if (c == maxCnt) {
                ans.add(kv.getKey());
            }
        }
        int[] ints = new int[ans.size()];
        for (int i = 0; i < ans.size(); ++i) {
            ints[i] = ans.get(i);
        }
        return ints;
    }
    Map<Integer,Integer> cnt = new HashMap<>();
    int maxCnt = 1;

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int sum = node.val + left + right;
        cnt.merge(sum, 1, Integer::sum);
        maxCnt = Math.max(cnt.get(sum), maxCnt);
        return sum;
    }

}
