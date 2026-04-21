package com.oycm.algorithm.i.search;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 967. <a href="https://leetcode.cn/problems/numbers-with-same-consecutive-differences/description/">连续差相同的数字</a> 1433
     *
     * @param n
     * @param k
     * @return 返回所有长度为 n 且满足其每两个连续位上的数字之间的差的绝对值为 k 的 非负整数，且数字不能为有前导零
     */
    public int[] numsSameConsecDiff(int n, int k) {
        /*
        枚举选哪个
         */
        int[] path = new int[n];
        List<Integer> ans = new ArrayList<>();
        dfs(0, path, 0, ans, k);
        return ans.stream().mapToInt(o -> o).toArray();
    }

    public void dfs(int i, int[] path, int num, List<Integer> ans, int k) {
        if (i == path.length) {
            ans.add(num);
            return;
        }
        for (int j = 0; j < 10; j++) {
            if (i == 0 && j > 0) {
                path[i] = j;
                dfs(i + 1, path, num * 10 + j, ans, k);
            }
            if (i > 0 && Math.abs(j - path[i - 1]) == k) {
                path[i] = j;
                dfs(i + 1, path, num * 10 + j, ans, k);
            }
        }
    }


}
