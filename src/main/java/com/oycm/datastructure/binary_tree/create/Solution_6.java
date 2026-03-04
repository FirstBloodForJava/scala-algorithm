package com.oycm.datastructure.binary_tree.create;

import com.oycm.DataCreateUtils;
import com.oycm.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_6 {

    /**
     * 2196. <a href="https://leetcode.cn/problems/create-binary-tree-from-descriptions/description/">根据描述创建二叉树</a> 1644
     *
     * @param descriptions
     * @return
     */
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer,TreeNode> map = new HashMap<>();
        Set<Integer> cs = new HashSet<>();
        for (int[] row : descriptions) {
            TreeNode parent = map.getOrDefault(row[0], new TreeNode(row[0]));
            cs.add(row[1]);
            // 二叉树中各节点的值 互不相同, child 在 descriptions[i][1] 只会出现一次
            // child 可能作为 其它的 parent 创建了
            TreeNode child = map.getOrDefault(row[1], new TreeNode(row[1]));
            if (row[2] == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            map.put(row[0], parent);
            map.put(row[1], child);
        }
        /*
        map 中极了父节点 和 子节点, 怎么找出父节点?
        map 中没有在 cs 中出现的就是父节点
         */
        for (Map.Entry<Integer, TreeNode> kv : map.entrySet()) {
            if (!cs.contains(kv.getKey())) {
                return kv.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_6().createBinaryTree(DataCreateUtils.twoDimensionInts("[[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]")));
    }

}
