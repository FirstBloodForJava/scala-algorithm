package com.oycm.week.No491;

import com.oycm.utils.DataCreateUtils;

public class Solution_3 {

    /**
     * 3858. <a href="https://leetcode.cn/problems/minimum-bitwise-or-from-grid/description/">按位或的最小值</a>
     *
     * @param grid
     * @return 每一行选择一个整数, 求最小的按位或 可能值
     */
    public int minimumOR(int[][] grid) {
        /*
        值的范围是 [1, 100000], 可表示的二进制最长为 17 位，最大值为 (1 << 17) - 1
        题解思路: 从高到低，判断答案的第 i 位能否是 0
         */
        int max = 0;
        for (int[] row : grid) {
            for (int x : row) {
                max = Math.max(max, x);
            }
        }
        int bitLength = 32 - Integer.numberOfLeadingZeros(max);

        int ans = 0;
        for (int i = bitLength - 1; i >= 0; i--) {
            // 试填法: ans 从高到低的第 i 位 能否为 0
            // 当每一行中，可选的数字都存在第 i 位是 0 的数，则 ans 的第 i 位可为 0，否则必须是 1
            int mask = ans | ((1 << i) - 1);
            next:
            for (int[] row : grid) {
                for (int x : row) {
                    // x 高位可为 0, 这里会排掉 x 高位为 1 的数，确保后续选择的数都是符合要求的
                    if ((x | mask) == mask) {
                        continue next;
                    }
                }
                // 这一行可选数字中, 第 i 位全是 1, 选 1, 进行下一位选择
                ans |= 1 << i;
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_3().minimumOR(DataCreateUtils.twoDimensionInts("[[2,5],[0,0]]")));


    }


}
