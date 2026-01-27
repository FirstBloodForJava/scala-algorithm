package com.oycm.datastructure.binary_tree.down_top_traverse;

import com.oycm.TreeNode;

public class Solution_7 {

    /**
     * 1379. <a href="https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/description/">找出克隆二叉树中的相同节点</a>
     *
     * @param original
     * @param cloned 是原始树 original 的一个 副本
     * @param target 位于原始树 original 中的目标节点
     * @return 在树 cloned 中，与 target 相同 的节点
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        /*
        题解思路: 比较节点地址
            如果 original 是空姐点, 返回 null (此时 cloned 也是 null)
            如果 original == target, 找到了节点, 当前 cloned 就是答案
                否则, 递归左子树寻找答案, 左子树存在答案, 则结束递归
                否则, 递归右子树寻找答案, 题目保证树在 original 中, 且不为 null
         */
        if (original == null || original == target) {
            return cloned;
        }
        TreeNode leftAns = getTargetCopy(original.left, cloned.left, target);
        if (leftAns != null) {
            return leftAns;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }

}
