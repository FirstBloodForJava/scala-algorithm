package com.oycm.dualweek.lc2026.No190;

public class Solution_4 {

    /**
     * @param nums
     * @return
     */
    public int maxValidSplits(int[] nums) {
        /*
        给你一个整数数组 nums。
        你可以从 nums 中移除 至多一个 元素。记 arr 为按原始顺序保留其余元素后得到的数组，m 为其长度。
        如果 arr 的 分割位置 i 满足以下条件，则称其为 有效的 ：
            0 <= i < m - 1，且
            gcd(arr[0..i]) == gcd(arr[i + 1..m - 1])。
        长度为 1 的数组没有有效的分割位置。
        arr 的 得分 是其有效分割位置的数量。
        返回 arr 的 最大可能得分 。
        在这里，gcd(a) 表示数组 a 中所有元素的最大公约数。
         */
        /*
        gcd logTrick
        分割性质一：
        gcd(pre) = gcd(suf) = g, 那么 gcd(pre, suf) = g，整个数组 gcd 为 g
        性质二：gcd([0, i]) = gcd([0, i-1]) 删除 i 不会得到更优解。
        证明：假设删除元素为 nums[k]
        gcd(nums) = gcd([0, k], [k+1, n-1]) = gcd([0, k-1], [k+1, n-1])
        说明删除元素 nums[k] 不改变整个数组 nums 的 gcd，设 gcd(nums) = g，那么 nums[k] 是 g 的倍数。
        设删除 nums[k] 的数组为 a，长度为 n-1，假设 a 存在一个有效分割 j，那么：
            gcd(a[0, j]) = gcd(a[j+1, n-2]) = gcd(a) = g
        假设 k 在 前缀，当 gcd([0, k]) = gcd([0, k-1]) 时，删除 nums[k] 不影响 [0, j] 前缀的 gcd，如果在 a 能找到有效分割，那么也能在原数组中找到有效分割。
        k 在后缀中 gcd(a[j+1, n-2]) = g, gcd(g, nums[k]) 任然是 g。（这个思路更好证明后缀）
        在 a 中的有效分割，都能在 nums 中找到对应的有效分割，所以 a 的得分总数小于等于 nums 得分。
         */
        int ans = getScore(nums, -1);
        int g = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (g > 0 && x % g == 0) {
                continue;
            }
            g = gcd(x, g);
            // 枚举 i 删除
            ans = Math.max(ans, getScore(nums, i));
        }

        return ans;
    }

    public int getScore(int[] nums, int skip) {
        int n = nums.length;
        // suf[i] 表示 [i, n-1] 后缀 gcd
        int[] suf = new int[n + 1];
        for (int j = n - 1; j >= 0; j--) {
            if (j != skip) {
                suf[j] = gcd(nums[j], suf[j + 1]);
            } else {
                suf[j] = suf[j + 1];
            }
        }
        int ans = 0;
        int pre = 0;
        for (int j = 0; j < n; j++) {
            if (j != skip) {
                // pre 表示 [0, j] gcd
                pre = gcd(nums[j], pre);
                // j 作为分割点
                if (pre == suf[j + 1]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
