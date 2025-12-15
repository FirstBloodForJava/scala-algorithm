package com.oycm.algorithm.h.basic;


public class Solution_12 {

    /**
     * 2657. <a href="https://leetcode.cn/problems/find-the-prefix-common-array-of-two-arrays/description/">找到两个数组的前缀公共数组</a> 1304
     *
     * @param A
     * @param B
     * @return
     */
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        /*
        用一个 long 记录存在
        先判断 A[i] ^ B[i] == 0 是否相等，不相等，再判断
         */
        long s = (1L << A[0]) ^ (1L << B[0]);
        int n = A.length;
        int[] ans = new int[n];
        ans[0] = s == 0 ? 1 : 0;
        for (int i = 1; i < n; i++) {
            if ((A[i] ^ B[i]) == 0) {
                ans[i] = ans[i-1] + 1;
            } else {
                int cnt = 0;
                long a = 1L << A[i];
                long b = 1L << B[i];
                if ((s & a) > 0) {
                    // 删除 A[i]
                    s = s ^ a;
                    cnt++;
                } else {
                    s |= a;
                }

                if ((s & b) > 0) {
                    // 删除 B[i]
                    s = s ^ b;
                    cnt++;
                } else {
                    s |= b;
                }
                ans[i] = ans[i-1] + cnt;

            }
        }

        return ans;
    }
    public int[] answer(int[] A, int[] B) {
        /*
        A B 分别往 a b 集合中添加元素，求其中交集的数量，就是前 i 个相同元素的数量
         */
        long a = 0;
        long b = 0;
        for (int i = 0; i < A.length; i++) {
            a |= 1L << A[i];
            b |= 1L << B[i];
            A[i] = Long.bitCount(a & b);
        }
        return A;
    }

}
