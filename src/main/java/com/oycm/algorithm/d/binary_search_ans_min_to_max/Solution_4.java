package com.oycm.algorithm.d.binary_search_ans_min_to_max;


public class Solution_4 {

    /**
     * 1760. <a href="https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/description/">袋子里最少数目的球</a> 1940
     * <p>
     * 至多进行 maxOperations 操作:
     * 选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 正整数 个球。
     * 开销是单个袋子里球数目的 最大值
     *
     * @param nums          nums[i] 表示第 i 个袋子里球的数目
     * @param maxOperations
     * @return 求 至多进行 maxOperations 操作后 的最小开销
     */
    public static int minimumSize(int[] nums, int maxOperations) {
        /*
        operation 操作次数越多, expense 越小, expense 随着 operation 递增而递减, 找到 operation <= maxOperations 最小 expense
         */
        int l = 0, r = 0;
        for (int num : nums) {
            r = Math.max(r, num);
        }
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, maxOperations, mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static boolean check(int[] nums, int maxOpt, int expense) {
        long cnt = 0;
        for (int num : nums) {
//            cnt += (num + expense - 1) / expense - 1;
            cnt += (num - 1) / expense;
        }
        return cnt <= maxOpt;
    }

    public static void main(String[] args) {
        System.out.println(minimumSize(new int[]{1000000000, 1000000000, 1000000000}, 1000000000));
    }

}
