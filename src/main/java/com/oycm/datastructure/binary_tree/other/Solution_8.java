package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution_8 {

    /**
     * 449. <a href="https://leetcode.cn/problems/serialize-and-deserialize-bst/description/">序列化和反序列化二叉搜索树</a>
     */
    public class Codec {

        /*
        可以根据 二叉树 前序遍历 + 中序遍历 或 后序遍历 + 中序遍历 还原二叉树
         */

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            postDfs(root, list);
            String data = list.toString();
            return data.substring(1, data.length() - 1);
        }

        public void postDfs(TreeNode node, List<Integer> list) {
            if (node == null) return;
            postDfs(node.left, list);
            postDfs(node.right, list);
            list.add(node.val);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] arr = data.split(",");
            Deque<Integer> stack = new ArrayDeque<>();
            /*
            后序遍历, 栈顶元素是根节点
             */
            for (int i = 0; i < arr.length; i++) {
                stack.push(Integer.valueOf(arr[i]));
            }
            return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
        }

        public TreeNode construct(int lower, int upper, Deque<Integer> stack) {
            if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper) {
                return null;
            }
            Integer val = stack.pop();
            TreeNode root = new TreeNode(val);
            /*
            这里顺序不能换
             */
            root.right = construct(val, upper, stack);
            root.left = construct(lower, val, stack);
            return root;
        }
    }

}
