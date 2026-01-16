package com.oycm.algorithm.d.binary_search_ans_max_to_min;


import java.util.Arrays;

public class Solution_1 {

    /**
     * 3281. <a href="https://leetcode.cn/problems/maximize-score-of-numbers-in-ranges/description/">范围内整数的最大得分</a> 1768
     * <p>
     * start 数组长为 n, [start[i], start[i] + d] 表示 n 个间隔的区间,
     * 需要选择 n 个整数, 其中 第 i 个整数必须属于 第 i 个区间,
     * 所选整数的得分定义: 所选整数两两之间最小绝对差
     *
     * @param start 整数数组, 长为 n
     * @param d     整数
     * @return 求所选整数的最大可能得分
     */
    public static int maxPossibleScore(int[] start, int d) {
        /*
        start = [6,0,3], d = 2
        {6,7,8}, {0,1,2}, {3,4,5} 最大得分越大, 越难符合要求
        考虑对 start 进行排序, 要想得分最大, start[0] 要最小, 如果要两两最小绝对差求最大, 记录新选择整数数组为 nums, 最小绝对差为 max
        则 i 在 [0, n-1) 中 nums[i+1] - nums[i] >= max, max 越大, 越不符合条件
        check(right) = false 则 >= right 都不符合条件
        理解两个点: start[i] + max < start[i+1] , 下一轮的 preMin = start[i+1], start[i] + max > start[i+1], 下一轮要符合要求, 则 preMin = start[i] + max
         */
        Arrays.sort(start);
        int n = start.length;
        int l = 0;
        int r = start[n - 1] + d - start[0] + 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(start, d, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static boolean check(int[] start, long d, int diff) {
        long preMin = start[0];
        for (int i = 1; i < start.length; i++) {
            preMin += diff;
            // 要求的值大于 可选范围, 如果, 如果 preMin < start[i] 因为 下一个数要和当前值比较, 所有后面取最大
            if (preMin > start[i] + d) {
                return false;
            }
            // preMin 不够时, 前面的需要 + d
            preMin = Math.max(preMin, start[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(maxPossibleScore(new int[]{0,1000000000}, 0));
    }

}
