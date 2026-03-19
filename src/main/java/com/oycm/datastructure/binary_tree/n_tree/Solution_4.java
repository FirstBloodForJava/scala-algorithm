package com.oycm.datastructure.binary_tree.n_tree;

import com.oycm.Node;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 429. <a href="https://leetcode.cn/problems/n-ary-tree-level-order-traversal/description/">N 叉树的层序遍历</a>
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        List<Node> cur = new ArrayList<>();
        cur.add(root);
        while (!cur.isEmpty()) {
            List<Node> next = new ArrayList<>();
            List<Integer> curLayer = new ArrayList<>();
            for (Node node : cur) {
                curLayer.add(node.val);
                // cur 的下一层
                for (Node child : node.children) {
                    next.add(child);
                }
            }
            ans.add(curLayer);
            cur = next;
        }

        return ans;
    }
}
