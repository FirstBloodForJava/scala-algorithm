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

    /**
     * 119. 杨辉三角 II
     * <br>
     * 119. <a href="https://leetcode.cn/problems/pascals-triangle-ii/description/">杨辉三角 II</a>
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        init();
        return c[rowIndex];
    }

    private static final List<Integer>[] c = new List[34];
    private static boolean initiated = false;

    public void init() {
        if (initiated) return;
        initiated = true;
        c[0] = List.of(1);
        for (int i = 1; i < c.length; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(c[i - 1].get(j - 1) + c[i - 1].get(j));
            }
            row.add(1);
            c[i] = row;
        }
    }

    public List<Integer> getRow_com(int rowIndex) {
        /*
        c(n, m) = (n! / (n-m)!) / m!
        c(n, m-1) = (n! / (n-m+1)) / (m-1)!
        c(n, m) = c(n, m-1) * n-m+1 / m
        c(n, 0) = 1
         */
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            ans.add((int) ((long) ans.get(i - 1) * (rowIndex - i + 1) / i));
        }

        return ans;
    }
}
