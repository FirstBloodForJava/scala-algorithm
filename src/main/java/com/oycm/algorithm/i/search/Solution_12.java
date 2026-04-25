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

class Solution_698 {

    /**
     * 698. <a href="https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/description/">划分为k个相等的子集</a>
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
        return dfs(0, nums, new int[k], sum / k);
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

class Solution_2305 {

    /**
     * 2305. <a href="https://leetcode.cn/problems/fair-distribution-of-cookies/description/">公平分发饼干</a> 1887
     *
     * @param cookies 整数数组 cookies ，其中 cookies[i] 表示在第 i 个零食包中的饼干数量。length [2, 8]
     * @param k       等待分发零食包的孩子数量
     * @return
     */
    public int distributeCookies(int[] cookies, int k) {
        /*
        所有 零食包都需要分发，在同一个零食包中的所有饼干都必须分发给同一个孩子，不能分开。
        分发的不公平程度 定义为单个孩子在分发过程中能够获得饼干的最大总数。
         */
        /*
        优化：
        剪枝一：保证每个人分一袋饼干
        剪枝二：第一袋饼干，无论分给谁都一样，固定非给第一个人
        剪枝三：如果第 j 个人，j > 1 分得的饼干数量和前一个人一样，跳过
        剪枝四：如果一个人分得的饼干数量 > ans 了，跳过
         */
        Arrays.sort(cookies);
        for (int l = 0, r = cookies.length - 1; l < r; l++, r--) {
            int temp = cookies[l];
            cookies[l] = cookies[r];
            cookies[r] = temp;
        }
        dfs(0, cookies, new int[k], 0);
        return ans;
    }

    int ans = Integer.MAX_VALUE;

    public void dfs(int i, int[] cookies, int[] path, int max) {
        if (i == cookies.length) {
            // 发完了
            if (max < ans) {
                ans = max;
            }
            return;
        }
        int empty = 0;
        for (int x : path) {
            if (x == 0) {
                empty++;
            }
        }
        // 一
        if (empty > cookies.length - i) return;
        for (int j = 0; j < path.length; j++) {
            // 二
            if (i == 0 && j > 0) {
                return;
            }
            // 三
            if (j > 0 && path[j] == path[j -1]) continue;
            // 四
            if (path[j] + cookies[i] > ans) continue;
            path[j] += cookies[i];
            dfs(i + 1, cookies, path, Math.max(path[j], max));
            path[j] -= cookies[i];
        }
    }

}
