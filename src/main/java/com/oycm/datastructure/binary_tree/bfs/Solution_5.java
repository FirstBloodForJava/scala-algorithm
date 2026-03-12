package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_5 {

    /**
     * 513. <a href="https://leetcode.cn/problems/find-bottom-left-tree-value/description/">找树左下角的值</a>
     *
     * @param root
     * @return 二叉树的 最底层 最左边 节点的值
     */
    public int findBottomLeftValue(TreeNode root) {
        /*
        从右到左遍历每一层, 最后一个元素 就是最底层 最左边的元素
        1 => 右 左
        2 右 =>  左 2 右左
        如果下一层有元素, 则 右到左 添加元素到下一层, 最后一个遍历的元素, 就是最左边的
         */
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }

        }

        return root.val;
    }

}
