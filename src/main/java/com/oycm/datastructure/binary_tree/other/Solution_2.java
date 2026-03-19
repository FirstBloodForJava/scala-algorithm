package com.oycm.datastructure.binary_tree.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_2 {

    /**
     * 1104. <a href="https://leetcode.cn/problems/path-in-zigzag-labelled-binary-tree/description/">二叉树寻路</a> 1545
     *
     * @param label
     * @return
     */
    public List<Integer> pathInZigZagTree(int label) {
        /*
        在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记
        奇数行从左到右标记
        偶数行从右到左标记
        第 1 行 ->
        第 2 行 <- 3, 2
        第 3 行 -> 4, 5, 6, 7
        第 4 行 -> 15, ... 8
         */
        List<Integer> ans = new ArrayList<>();
        /*
        从底层往上遍历, 先计算 label 在第几层 lay 再自减 1,
            upMax = 1 << lay, 再计算 当前 label 在上一层 位于 第几位 上取整
            location = (label - upMax + 1 + 1) / 2
            由于左右顺序相邻两层顺序是反的, 所有在当前 第 i 位, 就是上一层反过来的 n - i,
                当前层 大到小 第 i 位, 上一层 小到大 第 i 位
                    上一层 label = upMax - i
         */
        int lay = 31 - Integer.numberOfLeadingZeros(label);

        ans.add(label);
        while (lay > 0) {
            int upMax = 1 << lay;
            label = upMax - (label - upMax) / 2 - 1;
            ans.add(label);
            lay--;
        }

        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_2().pathInZigZagTree(16));
    }

}
