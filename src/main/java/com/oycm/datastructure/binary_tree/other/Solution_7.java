package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_7 {

    /**
     * 297. 二叉树的序列化与反序列化
     *
     * @param root
     * @return
     */
    public static String serialize(TreeNode root) {
        /*
        层序遍历序列化
         */
        if (root == null) return "[]";
        StringBuilder data = new StringBuilder("[");
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                // null 后续的节点能够省略
                data.append("null,");
            } else {
                data.append(node.val).append(",");
                q.add(node.left);
                q.add(node.right);
            }
        }
        data.deleteCharAt(data.length() - 1);
        data.append("]");
        return data.toString();
    }

    public static TreeNode deserialize(String data) {
        System.out.println(data);
        if (data.equals("[]")) return null;
        String[] arr = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (!arr[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(node.left);
            }
            i++;
            if (i < arr.length && !arr[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(node.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = deserialize("[1,2,3,4,null,5,6,null,null,null,7,8,null,null,null,null,null]");
        System.out.println(serialize(root));
    }

}
