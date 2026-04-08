package com.oycm.month2026.april;

import com.oycm.utils.DataCreateUtils;

public class Solution_8 {

    /**
     * 3653. <a href="https://leetcode.cn/problems/xor-after-range-multiplication-queries-i/description/">区间乘法查询后的异或 I</a> 1556
     *
     * @param nums    nums.length = [1, 1e3]
     * @param queries queries[i] = [l, r, k, v]; queries.length = [1, 1e3]
     * @return
     */
    public int xorAfterQueries(int[] nums, int[][] queries) {
        /*
        对于每个查询，按以下步骤执行操作：
            设定 idx = l；
            当 idx <= r 时：
                nums[idx] = (nums[idx] * v) % (1e9 + 7)
                idx += k
         */
        /*
        暴力模拟整个过程
         */
        for (int[] query : queries) {
            int idx = query[0];
            while (idx <= query[1]) {
                nums[idx] = (int) ((long) nums[idx] * query[3] % 1000000007);
                idx += query[2];
            }
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }

    public static void main(String[] args) {
        Solution_8 solution = new Solution_8();
        System.out.println(solution.xorAfterQueries(new int[]{483, 414}, DataCreateUtils.twoDimensionInts("[[0,0,1,17]]")));
    }

}
