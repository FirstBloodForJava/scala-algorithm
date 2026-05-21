package com.oycm.hot100.dp;

import java.util.ArrayList;
import java.util.List;

public class Solution_82 {

    /**
     * 118. <a href="https://leetcode.cn/problems/pascals-triangle/description/">杨辉三角</a>
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        /*
        给定一个非负整数 numRows，生成 杨辉三角 的前 numRows 行。
        在 杨辉三角 中，每个数是它左上方和右上方的数的和。
         */
        /*
        初始化第一层 [1], 当 i < numRows
            前一层数组前后各增加一个 0，从 i = 1 遍历到结尾得到下一层结果，不断循环
         */
        /*
        这一层，第一个和最后一个补 1，i = 1 开始，等于上一层左边和正上方两数之和
         */
        List<List<Integer>> ans = new ArrayList<>(numRows);
        List<Integer> path = List.of(1);
        ans.add(path);
        for (int i = 1; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>(i + 1);
            cur.add(1);
            for (int j = 1; j < i; j++) {
                cur.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            cur.add(1);
            ans.add(cur);
        }

        return ans;
    }

}
