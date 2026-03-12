package com.oycm.datastructure.binary_tree.bfs;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Solution_9 {

    /**
     * 993. <a href="https://leetcode.cn/problems/cousins-in-binary-tree/description/">二叉树的堂兄弟节点</a> 1288
     * 根节点位于深度 0 处, 每个深度为 k 的节点的子节点位于深度 k+1 处
     * <p>
     * 二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点
     *
     * @param root 具有唯一值的二叉树的根节点
     * @param x
     * @param y
     * @return x 和 y 互为堂兄弟节点 返回 true
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        /*
        bfs 遍历, 需要知道当前节点的父节点
        由于节点元素各不相同, 可以使用 map 记录当前节点的父节点
         */
        Map<Integer, TreeNode> childParent = new HashMap<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            /*
            判断上一层是否符合要求
             */
            if (childParent.get(x) != null && childParent.get(y) != null && childParent.get(x) != childParent.get(y)) {
                return true;
            } else if (childParent.containsKey(x) || childParent.containsKey(y)) {
                return false;
            }
            // 清空上一层
            childParent.clear();

            int n = q.size();
            while (n-- > 0) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    childParent.put(node.left.val, node);
                    q.add(node.left);
                }
                if (node.right != null) {
                    childParent.put(node.right.val, node);
                    q.add(node.right);
                }
            }
        }

        return false;
    }

    static class Solution_9_1 {

        public boolean isCousins(TreeNode root, int x, int y) {
            /*
            dfs 搜索
            递归过程中，玩下传节点的深度和当前节点的父节点，如果前面有找到任意一个节点 ，更新一次全局遍历；再次找到，判断深度和父节点是否相同
             */
            return dfs(root, null, 1, x, y);
        }

        private int depth;
        private TreeNode father;

        public boolean dfs(TreeNode node, TreeNode parent, int d, int x, int y) {
            if (node == null) return false;

            if (node.val == x || node.val == y) {
                if (depth > 0) {
                    return d == depth && father != parent;
                } else {
                    depth = d;
                    father = parent;
                }
            }

            return dfs(node.left, node, d + 1, x, y) || dfs(node.right, node, d + 1, x, y);
        }
    }

    static class Solution_9_2 {

        public boolean isCousins(TreeNode root, int x, int y) {
            /*
            dfs 搜索
            递归过程中，玩下传节点的深度和当前节点的父节点，如果前面有找到任意一个节点 ，更新一次全局遍历；再次找到，判断深度和父节点是否相同
            优化点一: 更改 dfs 返回值含义, 其表示 x 和 y 是否都找到, 如果两个都找到, 则返回 true, 这里如果 x 和 y 在左子树查到, 则会提前结束递归
            优化点二: 如果找到 一个数, d > depth 时, 则不玩下递归
             */
            dfs(root, null, 1, x, y);
            return ans;
        }

        private int depth;
        private TreeNode father;
        private boolean ans;

        public boolean dfs(TreeNode node, TreeNode parent, int d, int x, int y) {
            if (node == null || depth > 0 && d > depth)  return false;

            if (node.val == x || node.val == y) {
                if (depth > 0) {
                    ans = d == depth && father != parent;
                    // 如果 x 和 y 都在左子树查到，则不会进行右子树的递归
                    return true;
                } else {
                    depth = d;
                    father = parent;
                }
            }

            return dfs(node.left, node, d + 1, x, y) || dfs(node.right, node, d + 1, x, y);
        }

    }

}
