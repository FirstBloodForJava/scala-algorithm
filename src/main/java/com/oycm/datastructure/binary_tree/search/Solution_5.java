package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_5 {

    /**
     * 501. <a href="https://leetcode.cn/problems/find-mode-in-binary-search-tree/description/">二叉搜索树中的众数</a>
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        /*
        中序遍历 记录 当前数字及出现的次数 以及最大次数
         */
        dfs(root);
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

    private List<Integer> ans = new ArrayList<>();
    private int cnt = 0;
    private int maxCnt = 0;
    private int base = 0;

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        update(node.val);
        dfs(node.right);
    }

    private void update(int x) {
        if (x == base) {
            cnt++;
        } else {
            cnt = 1;
            base = x;
        }
        if (cnt == maxCnt) {
            ans.add(x);
        }
        if (cnt > maxCnt) {
            maxCnt = cnt;
            ans.clear();
            ans.add(x);
        }
    }
}
