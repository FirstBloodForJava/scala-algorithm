package com.oycm.dualweek2025.No170;

public class Solution_3 {

    /**
     * 3752. <a href="https://leetcode.cn/problems/lexicographically-smallest-negated-permutation-that-sums-to-target/description/">字典序最小和为目标值且绝对值是排列的数组</a> 1827
     *
     * @param n
     * @param target
     * @return
     */
    public int[] lexSmallestNegatedPerm(int n, long target) {
        /*
        给你一个正整数 n 和一个整数 target。
        请返回一个大小为 n 的 字典序最小 的整数数组，并满足：
            其元素 和 等于 target。
            其元素的 绝对值 组成一个大小为 n 的 排列。
        如果不存在这样的数组，则返回一个空数组。
        如果数组 a 和 b 在第一个不同的位置上，数组 a 的元素小于 b 的对应元素，则认为数组 a 字典序小于 数组 b。
        大小为 n 的 排列 是对整数 1, 2, ..., n 的重新排列。
         */
        /*
        1 <= n <= 1e5
        -1e10 <= target <= 1e10
         */
        /*
        在 [1, 2, 3, ..., n] 数组中，给一些数的前面添加符号 +/-，使得和为 target
        全部为 + 时 sum = (1+n)*n / 2
        全部为 - 时 sum = -(1+n)*n / 2
        min <= target <= max
        分类讨论：
        当 target >= 0 && target <= max 时，
            先假设所有符号选择 +，修改一个符号为 -，则 sum -= 2 * ?，sum 的奇偶性不变；
        当 target < 0 && target >= min 时，
            先假设所有符号选择 -，修改一个符号为 +，则 sum += 2 * ?，sum 的奇偶性不变；
        target 和 max 的奇偶性要一样，且 min <= target <= max，则一定存在一个答案

        当 target >= 0 时，要找出字典序尽可能小，则第一个添加负号的值要尽可能大
            判断 n 是否添加负号，(sum - 2*n) / 2 <= n-1，选择最大的一些数使得和为  (sum - target) / 2
        当 target < 0 时，要找出字典序尽可能小，则第一个添加正号的值要尽可能小
            要从小到大遍历，同上要判断剩余要补的数是否在后面存在，例如要从 [-1, -2, -3, -4, -5] 选择绝对值和为 7 的数添加号
            按 1, 2, 3 最后剩余 1，无法从后面添加
        上面 target < 0 前面正数取的小，会导致后面可以取到更小的负数变成正数
         */
        /*
        设选择正数的和为 posSum，负数的和为 negSum，所有元素和为 sum，则有
            posSum - negSum = target; posSum = sum - negSum;
            negSum = (sum - target) / 2，
        由于都是正整数，(sum - target) 必须是偶数，target 取值范围为 [-sum, sum]
        由于要取字典序最小，所以优先要选择更大值作为负数，
        由于 negSum <= sum，当 negSum >= n 时，由于 negSum <= (n+1)*n/2，所以 negSum - n <= n*(n-1)/2
        [1, 2, 3, ..., n] 中至多选一次，可得到 [0, sum] 任意值。
            初始值，和为 0
            1 选或不选，可以得到和为 0, 1；
            2 选或不选，不选得到 0, 1；选得到 2, 3；合并后 0, 1, 2, 3。
            3 选或不选，不选得到 0, 1, 2, 3；选得到 3, 4, 5, 6；合并后 0, 1, 2, 3, 4, 5, 6。
         */
        long sum = (1L + n) * n / 2;
        if (Math.abs(target) > sum || (target - sum) % 2 != 0) {
            return new int[0];
        }
        int[] ans = new int[n];

        int l = 0;
        int r = n - 1;
        long negSum = (sum - target) / 2;
        for (int i = n; i > 0; i--) {
            if (negSum >= i) {
                negSum -= i;
                ans[l++] = -i;
            } else {
                ans[r--] = i;
            }
        }
        return ans;
    }

}
