package com.oycm.algorithm.aba.advance;

import com.oycm.DataCreateUtils;

import java.util.Arrays;
import java.util.Comparator;


public class Solution_7 {

    /**
     * 2271. <a href="https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet/description/">毯子覆盖的最多白色砖块数</a> 2022
     *
     * @param tiles     二维整数数组, tiles[i] = [l, r], [l, r] 之间的瓷砖位置都被涂成了白色
     * @param carpetLen 表示放在任何位置一块毯子的长度
     * @return 求 毯子 能盖住多少块白色瓷砖
     */
    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        /*
        注意：瓷砖不会重叠
        可以对瓷砖 按 l 进行排序
        毯子能盖住的瓷砖数量不会超过 carpetLen
            如果 carpetLen 长度支持跨越 多块瓷砖，左边全部的瓷砖块 + 当前瓷砖能覆盖的亮，玩右边移动一次，左边就要减少 1，除非当前瓷砖块数 >= carpetLen
            l == r, nums[r][1] - nums[l][0] + 1 >= carpetLen 可以结束循环
         */
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
        int n = tiles.length;
        int pre = 0;
        int ans = 0;
        int l = 0, r = 0;
        while (r < n) {
            while (tiles[r][1] - tiles[l][0] + 1 > carpetLen) {
                if (l == r) {
                    return carpetLen;
                }
                // 更新 [l, r) 部分的最大长度 maxR(不可取) = nums[l][0] + carpetLen
                int maxR = tiles[l][0] + carpetLen;
                if (maxR > tiles[r][0]) {
                    // pre + 这个差值
                    ans = Math.max(ans, pre + maxR - tiles[r][0]);
                }
                // 减少前缀数量
                pre -= tiles[l][1] - tiles[l][0] + 1;
                l++;
            }
            pre += tiles[r][1] - tiles[r][0] + 1;
            ans = Math.max(ans, pre);
            r++;
        }

        return ans;
    }

    public static void main(String[] args) {

        System.out.println(maximumWhiteTiles(DataCreateUtils.twoDimensionInts("[[3745,3757],[3663,3681],[3593,3605],[3890,3903],[3529,3539],[3684,3686],[3023,3026],[2551,2569],[3776,3789],[3243,3256],[3477,3497],[2650,2654],[2264,2266],[2582,2599],[2846,2863],[2346,2364],[3839,3842],[3926,3935],[2995,3012],[3152,3167],[4133,4134],[4048,4058],[3719,3730],[2498,2510],[2277,2295],[4117,4128],[3043,3054],[3394,3402],[3921,3924],[3500,3514],[2789,2808],[3291,3294],[2873,2881],[2760,2760],[3349,3362],[2888,2899],[3802,3822],[3540,3542],[3128,3142],[2617,2632],[3979,3994],[2780,2781],[3213,3233],[3099,3113],[3646,3651],[3956,3963],[2674,2691],[3860,3873],[3363,3370],[2727,2737],[2453,2471],[4011,4031],[3566,3577],[2705,2707],[3560,3565],[3454,3456],[3655,3660],[4100,4103],[2382,2382],[4032,4033],[2518,2531],[2739,2749],[3067,3079],[4068,4074],[2297,2312],[2489,2490],[2954,2974],[2400,2418],[3271,3272],[3628,3632],[3372,3377],[2920,2940],[3315,3330],[3417,3435],[4146,4156],[2324,2340],[2426,2435],[2373,2376],[3621,3626],[2826,2832],[3937,3949],[3178,3195],[4081,4082],[4092,4098],[3688,3698]]"), 1638));

    }

}
