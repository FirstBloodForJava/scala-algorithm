package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_22 {


    /**
     * 1530. <a href="https://leetcode.cn/problems/number-of-good-leaf-nodes-pairs/description/">好叶子节点对的数量</a> 1746
     * <p>
     * 好叶子节点对: 两个 叶子节点之间的 最短路径长度 小于或者等于 distance
     *
     * @param root
     * @param distance
     * @return
     */
    public int countPairs(TreeNode root, int distance) {
        /*
        要想 路径最短, 则要找到 两个叶子节点 的最近公共祖先
        对于每个节点, 查找它 所有左子树 叶子节点到当前节点的距离, 以及右子树所有叶子节点到当前节点的距离
        记 cnt[] 表示距离节点 i 距离的叶子节点数量
            当节点是空时，返回一个 全是 0 的数组
            叶子节点 cnt[0] = 1
            leftCnt 和 rightCnt 计算复核要求的答案
            更新下一个节点到叶子节点的节点数量 cnt[i+1] = leftCnt[i] + rightCnt[i]
        cnt 的长度定多少合适? cnt 的索引表示该节点下, 长度为 i 的叶子节点数量
        由于要成对 所以任意一边长为最多只能时 distance - 1;
         */
        this.distance = distance;
        dfs(root);
        return ans;
    }

    int ans = 0;
    int distance;

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[distance];
        if (node.left == null && node.right == null) {
            int[] cnt = new int[distance];
            cnt[0] = 1;
            return cnt;
        }
        int[] leftCnt = dfs(node.left);
        int[] rightCnt = dfs(node.right);
        for (int i = 0; i < distance; i++) {
            for (int j = 0; j <= distance - i - 2; j++) {
                ans += leftCnt[i] * rightCnt[j];
            }
        }
        int[] cnt = new int[distance];
        for (int i = 0; i < distance - 1; i++) {
            cnt[i + 1] = leftCnt[i] + rightCnt[i];
        }
        return cnt;
    }

}
