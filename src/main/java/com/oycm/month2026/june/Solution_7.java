package com.oycm.month2026.june;

import com.oycm.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution_7 {

    /**
     * 2196. <a href="https://leetcode.cn/problems/create-binary-tree-from-descriptions/description/">根据描述创建二叉树</a> 1644
     *
     * @param descriptions
     * @return
     */
    public TreeNode createBinaryTree(int[][] descriptions) {
        /*
        给你一个二维整数数组 descriptions ，其中 descriptions[i] = [parenti, childi, isLefti]
        表示 parenti 是 childi 在 二叉树 中的 父节点，二叉树中各节点的值 互不相同 。此外：
            如果 isLefti == 1 ，那么 childi 就是 parenti 的左子节点。
            如果 isLefti == 0 ，那么 childi 就是 parenti 的右子节点。
        请你根据 descriptions 的描述来构造二叉树并返回其 根节点 。
         */
        /*
        父节点肯定不会在 childi descriptions[i][1] 中出现
        根节点优化思路：树上所以节点都进行异或运算，子节点在进行依次异或运算，最终的值就是 root 节点值
         */
        int n = descriptions.length;
        Map<Integer,TreeNode> nodeMap = new HashMap<>(n + 1, 1);
        int root = 0;
        for (int[] row : descriptions) {
            int p = row[0], c = row[1];
            /*
            节点被创建时异或一次，子节点自己再异或一次
             */
            if (!nodeMap.containsKey(p)) {
                nodeMap.put(p, new TreeNode(p));
                root ^= p;
            }
            if (!nodeMap.containsKey(c)) {
                nodeMap.put(c, new TreeNode(c));
                root ^= c;
            }

            if (row[2] == 1) {
                nodeMap.get(p).left = nodeMap.get(c);
            } else {
                nodeMap.get(p).right = nodeMap.get(c);
            }
            root ^= c;
        }

        return nodeMap.get(root);
    }

}
