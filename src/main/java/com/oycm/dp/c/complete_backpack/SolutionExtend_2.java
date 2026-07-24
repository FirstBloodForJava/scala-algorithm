package com.oycm.dp.c.complete_backpack;

import java.util.Arrays;

public class SolutionExtend_2 {

    /**
     * 1449. <a href="https://leetcode.cn/problems/form-largest-integer-with-digits-that-add-up-to-target/description/">数位成本和为目标值的最大数字</a> 1927
     *
     * @param cost
     * @param target
     * @return
     */
    public String largestNumber(int[] cost, int target) {
        /*
        给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
            给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
            总成本必须恰好等于 target 。
            添加的数位中没有数字 0 。
        由于答案可能会很大，请你以字符串形式返回。
        如果按照上述要求无法得到任何整数，请你返回 "0" 。
         */
        /*
        完全背包，输出答案。
        找出 cost 和为 target 的最大长度，再从大到小生成所选的数
         */
        int[] f = new int[target + 1];
        Arrays.fill(f, Integer.MIN_VALUE);
        f[0] = 0;
        for (int c : cost) {
            for (int i = c; i <= target; i++) {
                f[i] = Math.max(f[i], f[i - c] + 1);
            }
        }
        if (f[target] < 0) {
            // 无法选择数字，消耗成本为 target
            return "0";
        }
        StringBuilder ans = new StringBuilder(f[target]);
        int j = target;
        // 从大到小填
        for (int i = 8; i >= 0; i--) {
            int c = cost[i];
            while (j >= c && f[j - c] + 1 == f[j]) {
                ans.append((char) ('1' + i));
                j -= c;
            }
        }

        return ans.toString();
    }

}
