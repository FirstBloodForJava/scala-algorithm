package com.oycm.algorithm.d.binary_search.advance;


import com.oycm.DataCreateUtils;

public class Solution_15 {

    /**
     * LCP 08. <a href="https://leetcode.cn/problems/ju-qing-hong-fa-shi-jian/description/">剧情触发时间</a>
     * <p>
     * 初始化 C = 0, R = 0, H = 0
     *
     * @param increase     表示每天增加 [C, R, H] 的数组
     * @param requirements 触发剧情 [C, R, H] 要求数组, C >= requirements[i][0] && C >= requirements[i][1] && C >= requirements[i][2]
     * @return
     */
    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        /*
        维护一个 当前天 C, R, H 值的数组 crh, 在 crh 中的 0,1,2 的 值是递增，可以查询 crh[i][0] >= c && crh[i][1] >= r && crh[i][2] >= h 的第一个 index，index = n 时，无法触发剧情
         */
        int n = increase.length;
        int[][] crh = new int[n + 1][3];
        crh[0] = new int[]{0, 0, 0};
        for (int i = 0; i < increase.length; i++) {
            crh[i + 1] = new int[]{crh[i][0] + increase[i][0], crh[i][1] + increase[i][1], crh[i][2] + increase[i][2]};
        }
        int t = requirements.length;
        int[] ans = new int[t];
        for (int i = 0; i < t; i++) {
            int c = requirements[i][0], r = requirements[i][1], h = requirements[i][2];
            int left = -1, right = n + 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (crh[mid][0] >= c && crh[mid][1] >= r && crh[mid][2] >= h) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (right == n + 1) {
                ans[i] = -1;
            } else {
                ans[i] = right;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        getTriggerTime(DataCreateUtils.twoDimensionInts("[[2,8,4],[2,5,0],[10,9,8]]"),
                DataCreateUtils.twoDimensionInts("[[2,11,3],[15,10,7],[9,17,12],[8,1,14]]"));
    }
}
