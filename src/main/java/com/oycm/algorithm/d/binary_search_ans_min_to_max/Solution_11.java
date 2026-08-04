package com.oycm.algorithm.d.binary_search_ans_min_to_max;

public class Solution_11 {

    /**
     * 2513. <a href="https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays/description/">最小化两个数组中的最大值</a> 2302
     *
     * @param divisor1
     * @param divisor2
     * @param uniqueCnt1
     * @param uniqueCnt2
     * @return
     */
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        /*
        给你两个数组 arr1 和 arr2 ，它们一开始都是空的。你需要往它们中添加正整数，使它们满足以下条件：
            arr1 包含 uniqueCnt1 个 互不相同 的正整数，每个整数都 不能 被 divisor1 整除 。
            arr2 包含 uniqueCnt2 个 互不相同 的正整数，每个整数都 不能 被 divisor2 整除 。
            arr1 和 arr2 中的元素 互不相同 。
        给你 divisor1 ，divisor2 ，uniqueCnt1 和 uniqueCnt2 ，请你返回两个数组中 最大元素 的 最小值 。
         */
        /*
        最大元素越大，越能满足条件。可以二分答案，
        divisor1, divisor2 分别记为 d1, d2，lcm 记为 d1 和 d2 的最小公倍数。
        因为：
            能被 d1 整除但不能被 d2 整除的数，能在 arr2 中，且不能在 arr1 中；
            能被 d2 整除但不能被 d1 整除的数，能在 arr1 中，且不能在 arr2 中；
            既不能被 d1 整除，也不能被 d2 整除的数，可以在 arr1 和 arr2 中。
        二分答案 x，则：
            x/d1 - x/lcm 个数只能 arr2 使用；
            x/d2 - x/lcm 个数只能 arr1 使用；
            有 x - x/d1 - x/d2 + x/lcm 个数，arr1 和 arr2 都能使用(x/d1 和 x/d2 包含了 x/lcm)。
        二分判断条件：可共享 >= max(uniqueCnt1 - arr1 独享, 0) + max(uniqueCnt2 - arr2 独显, 0)
        开区间二分：
            下界：uniqueCnt1 + uniqueCnt2 - 1
            上界：由于 di 越小，不能在数组的元素越多，数组的最大元素越大，最坏情况是 d1 = d2 = 2，自能选奇数，上界为 (uniqueCnt1 + uniqueCnt2) * 2 - 1;
         */
        int left = uniqueCnt1 + uniqueCnt2 - 1;
        int right = (uniqueCnt1 + uniqueCnt2) * 2 - 1;
        // gcd 会超过 int 最大值
        long l = lcm(divisor1, divisor2);
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(mid, divisor1, divisor2, l, uniqueCnt1, uniqueCnt2)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    public int gcd(int a, int b) {
        while (a != 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

    public long lcm(int a, int b) {
        return (long) a / gcd(a, b) * b;
    }

    public boolean check(int x, int d1, int d2, long l, int u1, int u2) {
        return x - x / d1 - x / d2 + x / l >= Math.max(u1 + x / l - x / d2, 0) + Math.max(u2 + x / l - x / d1, 0);
    }

}
