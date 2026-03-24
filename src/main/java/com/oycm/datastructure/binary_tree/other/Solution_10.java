package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

import java.util.*;

public class Solution_10 {

    /**
     * 652. <a href="https://leetcode.cn/problems/find-duplicate-subtrees/description/">寻找重复的子树</a>
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<>(set);
    }

    Map<String, TreeNode> map = new HashMap<>();
    Set<TreeNode> set = new HashSet<>();

    public String dfs(TreeNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val).append(",");
        sb.append(dfs(node.left));
        sb.append(",");
        sb.append(dfs(node.right));
        // 后续遍历
        String res = sb.toString();
        if (map.containsKey(res)) {
            set.add(map.get(res));
        } else {
            map.put(res, node);
        }
        return res;
    }

    static class Solution_10_1 {


        /**
         * @param root
         * @return
         */
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            dfs(root);
            return new ArrayList<>(set);
        }

        private record Pair(TreeNode node, int idx) {
        }

        Map<String, Pair> map = new HashMap<>();
        Set<TreeNode> set = new HashSet<>();
        int idx = 0;

        public int dfs(TreeNode node) {
            if (node == null) return 0;
            int[] res = {node.val, dfs(node.left), dfs(node.right)};
            String ans = Arrays.toString(res);
            if (map.containsKey(ans)) {
                Pair pair = map.get(ans);
                set.add(pair.node);
                return pair.idx;
            } else {
                map.put(ans, new Pair(node, ++idx));
                return idx;
            }
        }
    }

}
