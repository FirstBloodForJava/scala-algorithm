package com.oycm.string.kmp;

public class Solution_5 {

    /**
     * 1764. <a href="https://leetcode.cn/problems/form-array-by-concatenating-subarrays-of-another-array/description/">通过连接另一个数组的子数组得到一个数组</a> 1588
     *
     * @param groups
     * @param nums
     * @return
     */
    public boolean canChoose(int[][] groups, int[] nums) {
        /*
        给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums。
        你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，
        且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现的位置在第 i 个子数组前面。（也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）
        如果你可以找出这样的 n 个子数组，请你返回 true，否则返回 false。
        如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
         */
        /*
        i = 0, 找 groups[i] 在 [0, n-1] 查找是否存在子数组，找到了，下一轮从 该子数组后面开始查询，因为不能有交集，i 越小越靠前越好
        判断 groups[i] 是否未 nums 的子数组，可以使用 kmp
         */
        int ans = 0;
        out:for (int i = 0, j = 0; i < groups.length && j < nums.length && ans < groups.length; i++) {
            int[] pattern = groups[i];

            // 计算模式串的 next 数组
            int[] next = new int[groups[i].length];
            for (int k = 1, cnt = 0; k < next.length; k++) {
                while (cnt > 0 && pattern[k] != pattern[cnt]) {
                    cnt = next[cnt - 1];
                }
                if (pattern[k] == pattern[cnt]) {
                    cnt++;
                }
                next[k] = cnt;
            }
            int c = 0;
            while (j < nums.length) {
                while (c > 0 && nums[j] != pattern[c]) {
                    c = next[c - 1];
                }
                if (nums[j] == pattern[c]) {
                    c++;
                }
                j++;
                if (c == pattern.length) {
                    ans++;
                    continue out;
                }
            }

        }


        return ans == groups.length;
    }

    // 从 下标 k 开始 kmp 查找
    public int kmpSearch(int k, int[] s, int[] t) {
        int m = s.length;
        if (m + k > t.length) return -1;

        int[] next = new int[m];
        for (int i = 1, cnt = 0; i < m; i++) {
            while (cnt > 0 && s[i] != s[cnt]) {
                cnt = next[cnt - 1];
            }
            if (s[i] == s[cnt]){
                cnt++;
            }
            next[i] = cnt;
        }

        for (int i = k, j = 0; i < t.length; i++) {
            while (j > 0 && t[i] != s[j]) {
                j = next[j - 1];
            }
            if (t[i] == s[j]) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }

}
