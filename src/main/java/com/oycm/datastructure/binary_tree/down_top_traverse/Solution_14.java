package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_14 {

    /**
     * 606. <a href="https://leetcode.cn/problems/construct-string-from-binary-tree/description/">根据二叉树创建字符串</a>
     * <p>
     * 前序遍历 二叉树, root = root + (left) + (right)
     * 转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对
     *
     * @param root
     * @return
     */
    public String tree2str(TreeNode root) {
        /*
        题解思路: 递归右以下四种情况
            左右节点都存在, 两个结果都添加一层括号
            叶子节点后不需要添加任何括号
            当前节点只有左节点, 只需要在左孩子结果外添加一层括号
            当前节点只有右节点, 左节点需要先添加一个括号, 右节点结果外添加一层括号
         */
        if (root == null) return "";
        // 叶子节点
        if (root.left == null && root.right == null) {
            return String.valueOf(root.val);
        }
        if (root.right == null) {
            return root.val + "(" + tree2str(root.left) + ")";
        }
        return  root.val + "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")";

    }


}
