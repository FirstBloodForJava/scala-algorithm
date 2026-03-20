package com.oycm.datastructure.binary_tree.other;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {

    /**
     * 987.<a href="https://leetcode.cn/problems/vertical-order-traversal-of-a-binary-tree/description/">二叉树的垂序遍历</a> 1676
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        /*
        垂序遍历: 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1)
        树的根结点位于 (0, 0)
        二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表
        如果同行同列上有多个结点，则按结点的值从小到大进行排序
         */
        /*
        row 左右子树都会 加上 1, row 相当于节点的层号
        坐标相同的点 其 左右 右左 节点的坐标相同
        col < 0 时 negList 记录 List<List<int[]>> int[] 0 表示值, 1 表示 row
        col >= 0 时 posList  记录 List<List<int[]>> int[] 0 表示值, 1 表示 row
         */
        dfs(root, 0 ,0);
        List<List<Integer>> ans = new ArrayList<>(negList.size() + posList.size());
        for (int i = negList.size() - 1; i >= 0; i--) {
            add(ans, negList.get(i));
        }
        for (List<int[]> ints : posList) {
            add(ans, ints);
        }

        return ans;
    }

    private List<List<int[]>> negList = new ArrayList<>();
    private List<List<int[]>> posList = new ArrayList<>();

    public void dfs(TreeNode node, int row, int col) {
        if (node == null) return;
        if (col < -negList.size()) {
            negList.add(new ArrayList<>());
        } else if (col == posList.size()) {
            posList.add(new ArrayList<>());
        }
        (col < 0 ? negList.get(-1 - col) : posList.get(col)).add(new int[]{node.val, row});
        dfs(node.left, row + 1, col - 1);
        dfs(node.right, row + 1, col + 1);
    }

    public void add(List<List<Integer>> ans, List<int[]> ints) {
        ints.sort((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        List<Integer> col = new ArrayList<>(ints.size());
        for (int[] kv : ints) {
            col.add(kv[0]);
        }
        ans.add(col);
    }

}
