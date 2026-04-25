package com.oycm.algorithm.i.search;

import java.util.Arrays;

public class Solution_12 {

    /**
     * 473. <a href="https://leetcode.cn/problems/matchsticks-to-square/description/">火柴拼正方形</a>
     *
     * @param matchsticks 整数数组, matchsticks[i] 是第 i 个火柴棒的长度
     * @return
     */
    public boolean makesquare(int[] matchsticks) {
        /*
        要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
         */
        /*
        先计算所有长度和 sum, sum % 4 != 0 肯定无法拼凑
        接下来就看是否能把 matchsticks 分成 4 组 和为 sum / 4 的子集
         */
        int sum = 0;
        for (int x : matchsticks) {
            sum += x;
        }
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        for (int l = 0, r = matchsticks.length - 1; l < r; l++, r--) {
            int temp = matchsticks[l];
            matchsticks[l] = matchsticks[r];
            matchsticks[r] = temp;
        }
        return dfs(0, matchsticks, new int[4], sum / 4);
    }

    public boolean dfs(int i, int[] nums, int[] path, int target) {
        if (i == nums.length) {
            // 前面已经判断过总合，能进到之类，说明已经分好了
            return true;
        }
        for (int j = 0; j < path.length; j++) {
            /*
            优化：如果当前桶的值和前一个一样，前面选如果符合要求就不会到这里，所以这里再选也是无效的
             */
            if (j > 0 && path[j] == path[j - 1]) continue;

            if (path[j] + nums[i] > target) continue;
            path[j] += nums[i];
            if (dfs(i + 1, nums, path, target)) {
                return true;
            }
            path[j] -= nums[i];
        }

        return false;
    }

}
