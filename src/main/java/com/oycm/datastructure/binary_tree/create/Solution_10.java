package com.oycm.datastructure.binary_tree.create;

import com.oycm.TreeNode;

public class Solution_10 {

    /**
     * 1028. <a href="https://leetcode.cn/problems/recover-a-tree-from-preorder-traversal/description/">从先序遍历还原二叉树</a> 1797
     *
     * @param traversal
     * @return
     */
    public TreeNode recoverFromPreorder(String traversal) {
        /*
        先序遍历: [根, 左子树前序遍历结果, 右子树前序遍历结果]
        根节点-左子树前序遍历结果-右子树前序遍历结果
        1-2--3--4-5--6--7
        - 前是根节点 2--3--4 5--6--7
        -- 2--3--4 不包含 --- 则是叶子节点
         */
        return dfs(traversal, "-");
    }

    public TreeNode dfs(String traversal, String depth) {
        if (traversal.isEmpty()) {
            return null;
        }
        if (!traversal.contains(depth)) {
            return new TreeNode(Integer.parseInt(traversal));
        }

        int firstIndex = -1;
        // 未找到，说明没有右节点，左边需要截取全部
        int lastIndex = traversal.length();
        char[] cs = traversal.toCharArray();
        int cnt = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '-') {
                cnt++;
            } else {
                if (cnt == depth.length()) {
                    if (firstIndex == -1) {
                        firstIndex = i - depth.length();
                    } else {
                        lastIndex = i - depth.length();
                        break;
                    }
                }
                cnt = 0;
            }
        }

        String nextDepth = depth + "-";
        TreeNode left = dfs(traversal.substring(firstIndex + depth.length(), lastIndex), nextDepth);
        TreeNode right = dfs(traversal.substring(lastIndex == traversal.length() ? lastIndex : lastIndex + depth.length()), nextDepth);

        return new TreeNode(Integer.parseInt(traversal.substring(0, firstIndex)), left, right);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new Solution_10().recoverFromPreorder("1-2--3---4-5--6---7");
        new Solution_10().recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println(treeNode);
    }

}
