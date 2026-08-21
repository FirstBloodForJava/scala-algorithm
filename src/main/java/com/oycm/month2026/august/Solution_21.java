package com.oycm.month2026.august;

public class Solution_21 {

    /**
     * 3116. <a href="https://leetcode.cn/problems/kth-smallest-amount-with-single-denomination-combination/description/">单面值组合的第 K 小金额</a> 2388
     *
     * @param coins
     * @param k
     * @return
     */
    public long findKthSmallest(int[] coins, int k) {
        /*
        给你一个整数数组 coins 表示不同面额的硬币，另给你一个整数 k 。
        你有无限量的每种面额的硬币。但是，你 不能 组合使用不同面额的硬币。
        返回使用这些硬币能制造的 第 k 小 金额。
         */
        /*
        如果面额 x % y = 0，那么 x 可以不用考虑。
        只有 x 和 y 互质时，金额为 x 和 y 的最小公倍数倍数时，构造的金额值，计算排名时，会重复计算。
        例如 2 3，
            2, 4, 6
            3, 6
            6 在里面排第 4。
        如果能解决这个问题，就能通过二分查找猜答案
         */
        /*
        一个硬币时，对金额 m 的贡献数量为 m/x
        两个硬币时，对金额 m 的贡献数量为 m/x + x/y - m/lcm(x, y)，转换成集合就是 A ∪ B = A + B - (A ∩ B)
        三个硬币时，对金额 m 的贡献数量为 m/x + x/y + x/z - m/lcm(x, y) - m/lcm(x, z) - m/lcm(y, z) + m/lcm(x, y, z);
         */
        int mn = Integer.MAX_VALUE;
        for (int x : coins) {
            mn = Math.min(mn, x);
        }
        long left = k-1;
        long right = (long) mn * k;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (check(mid, coins, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {
        while (a != 0) {
            long tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

    private boolean check(long m, int[] coins, int k) {
        long cnt = 0;
        next:
        for (int i = 1; i < (1 << coins.length); i++) {
            // 枚举所有非空子集
            long lcmRes = 1;
            for (int j = 0; j < coins.length; j++) {
                if ((i >> j & 1) == 1) {
                    // j 在集合 i 中
                    lcmRes = lcm(lcmRes, coins[j]);
                    if (lcmRes > m) {
                        continue next;
                    }
                }
            }
            /*
            所有只有一个元素的子集，先当与所有的初始集合 A + B + ...
            两个元素的集合 减去 两个元素 交集贡献
            三个元素的集合 加上 三个元素 交集贡献
             */
            cnt += Integer.bitCount(i) % 2 == 1 ? m / lcmRes : -m / lcmRes;
        }
        return cnt >= k;
    }



}
