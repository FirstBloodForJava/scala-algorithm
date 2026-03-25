package com.oycm.datastructure.binary_tree.other;

import java.util.ArrayList;
import java.util.List;

public class Solution_12 {

    /**
     * 2049. <a href="https://leetcode.cn/problems/count-nodes-with-the-highest-score/description/">统计最高分的节点数目</a> 1912
     *
     * @param parents 根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1, 其中 parents[i] 是节点 i 的父节点
     * @return
     */
    public int countHighestScoreNodes(int[] parents) {
        /*
        parents[0] == -1, i != 0, 0 <= parents[i] <= n-1
        子树的 大小: 为这个子树内节点的数目
        节点 分数: 将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树, 这些子树的乘积就是该节点的分数
        求 有 最高得分 节点的 数目
         */
        /*
        先根据 parents 还原二叉树的结构,
        dfs() 返回当前节点数量前  左子树数量, 右子树数量, n - 1 - ls - rs
         */
        int n = parents.length;
        this.n = n;
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode();
        }
        for (int i = 1; i < n; i++) {
            nodes[parents[i]].addNode(nodes[i]);
        }

        dfs(nodes[0]);
        return cnt;
    }

    long maxScore = 0;
    int cnt = 0;
    int n;

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        long score = (long) help(left) * help(right) * help(n - 1 - left - right);
        if (score == maxScore) {
            cnt++;
        } else if (score > maxScore) {
            cnt = 1;
            maxScore = score;
        }
        return left + right + 1;
    }

    public int help(int x) {
        return x == 0 ? 1 : x;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;

        public void addNode(TreeNode node) {
            if (left == null) {
                left = node;
            } else {
                right = node;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution_12().countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0}));
    }

    static class Solution_12_1 {

        public int countHighestScoreNodes(int[] parents) {
            /*
            官网题解思路
             */
            this.n = parents.length;
            child = new List[n];
            for (int i = 0; i < n; i++) {
                child[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                int parent = parents[i];
                if (parent != -1) {
                    child[parent].add(i);
                }
            }
            dfs(0);
            return cnt;
        }

        long maxScore = 0;
        int cnt = 0;
        int n;
        // 每个节点 有多少 子节点
        List<Integer>[] child;

        public int dfs(int node) {
            long score = 1;
            int size = n - 1;
            // node 的子节点数量
            for (Integer c : child[node]) {
                int cc = dfs(c);
                score *= cc;
                size -= cc;
            }
            if (node != 0) {
                score *= size;
            }
            if (score == maxScore) {
                cnt++;
            } else if (score > maxScore) {
                cnt = 1;
                maxScore = score;
            }
            return n - size;
        }
    }


}
