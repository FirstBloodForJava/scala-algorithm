package com.oycm.algorithm.d.binary_search_first_k;


public class Solution_6 {

    /**
     * 793. <a href="https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/description/">阶乘函数后 K 个零</a>
     * <p>
     * f(x) 表示 x! 末尾是 0 的数量
     *
     * @param k
     * @return f(x) == k 的非负整数数量
     */
    public int preimageSizeFZF(int k) {
        /*
        10! = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10
              1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800
        11! = 39916800
        14! = 87178291200
        15! = 1307674368000
        16!, 17! 18!, 19!
        如果存在 f(x) == k, [x, x+4] 区间数的阶乘, 末尾会是 k
        题解思路: [1, x] 中能拆分出 5 的数量决定了末尾是 0 的数量, 随着 x 的不断变大, 5 的数量会越来越多, 具有单调性, 可以二分答案
        判断是否存在一个数满足 [1, x] 5 的数量是 k, 存在 答案那就是 5, 不存在 答案就是 0
        问题转换成, 怎么计算 x 中有多少个 5 ?
         */
        long left = 0;
        long right = k * 5L;
        // 闭区间写法
        while (left <= right) {
            long mid = (right + left) / 2;
            int cnt = 0;
            int n = 5;
            while (n <= mid) {
                cnt += mid / 5;
                n *= 5;
            }
            if (cnt == k) {
                return 5;
            } else if (cnt < k){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        long x = 1;
        int k = 20;
        for (int i = 19; i >= 1; i--) {
            x *= k;
        }
        System.out.println(x);
    }
}
