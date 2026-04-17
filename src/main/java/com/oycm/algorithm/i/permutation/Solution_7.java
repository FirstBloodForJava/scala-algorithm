package com.oycm.algorithm.i.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_7 {

    /**
     * 1718. <a href="https://leetcode.cn/problems/construct-the-lexicographically-largest-valid-sequence/description/">构建字典序最大的可行序列</a> 2080
     *
     * @param n [1, 20]
     * @return 返回满足条件中 字典序最大 的序列
     */
    public int[] constructDistancedSequence(int n) {
        /*
        整数 n ，请你找到满足下面条件的一个序列
            整数 1 在序列中只出现一次。
            2 到 n 之间每个整数都恰好出现两次。
            对于每个 2 到 n 之间的整数 i ，两个 i 之间出现的距离恰好为 i 。
        序列里面两个数 a[i] 和 a[j] 之间的 距离 ，我们定义为它们下标绝对值之差 |j - i| 。
         */
        /*
        构造这个答案，每次选最大的数，如果能构造答案，则返回，不能构造答案，则选次大的数，下一个数放在哪个位置？
         */
        int[] ans = new int[2 * n - 1];
        List<Integer> enable = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            enable.add(i);
        }
        dfs(enable, ans);
        return ans;
    }


    public boolean dfs(List<Integer> enable, int[] ans) {

        if (enable.isEmpty()) {
            return true;
        }

        for (Integer x : enable) {
            int j = x == 1 ? 0 : x;
            // 错误这样选，不能保证前面的位置选到较大的数
            for (int i = 0; i < ans.length; i++) {
                if (ans[i] == 0 && i + j < ans.length && ans[i + j] == 0) {
                    ans[i] = ans[i + j] = x;
                    List<Integer> next = new ArrayList<>(enable);
                    next.remove(x);
                    if (dfs(next, ans)) {
                        return true;
                    }
                    ans[i] = ans[i + j] = 0;

                }
            }
        }

        return false;
    }

    public boolean dfs(int idx, boolean[] enable, int[] ans, int n) {
        // 更早的位置填大数
        while (idx < ans.length && ans[idx] != 0) {
            idx++;
        }
        if (idx == ans.length) {
            return true;
        }
        for (int x = n; x > 0; x--) {
            int j = x == 1 ? idx : idx + x;
            // 被使用
            if (enable[x]) {
                continue;
            }
            if (j < ans.length && ans[j] == 0) {
                ans[idx] = ans[j] = x;
                enable[x] = true;
                // 找到的第一个最大
                if (dfs(idx + 1, enable, ans, n)) {
                    return true;
                }
                // 未找到回溯
                ans[idx] = ans[j] = 0;
                enable[x] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_7 solution = new Solution_7();
        System.out.println(Arrays.toString(solution.constructDistancedSequence(5)));
    }


}
