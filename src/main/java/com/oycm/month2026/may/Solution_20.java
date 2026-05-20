package com.oycm.month2026.may;

public class Solution_20 {

    /**
     * 2657. <a href="https://leetcode.cn/problems/find-the-prefix-common-array-of-two-arrays/description/">找到两个数组的前缀公共数组</a> 1304
     *
     * @param A
     * @param B
     * @return
     */
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        /*
        给你两个下标从 0 开始长度为 n 的整数排列 A 和 B。
        A 和 B 的 前缀公共数组 定义为数组 C ，其中 C[i] 是数组 A 和 B 到下标为 i 之前公共元素的数目。
        请你返回 A 和 B 的 前缀公共数组 。
        如果一个长度为 n 的数组包含 1 到 n 的元素恰好一次，我们称这个数组是一个长度为 n 的 排列。
        A.length == B.length [1, 50]
         */
        /*
        题目保证 A 和 B 两个数组都是 n 个元素的排列。说明数组元素都恰好出现一个
        set1 记录 A 中元素出现情况
        set2 记录 B 中元素出现情况
         */
        /*int n = A.length;
        int[] C = new int[n];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        if (A[0] == B[0]) {
            C[0] = 1;
        } else {
            set1.add(A[0]);
            set2.add(B[0]);
        }
        for (int i = 1; i < n; i++) {
            C[i] += C[i - 1];
            if (A[i] == B[i]) {
                C[i]++;
                continue;
            }
            if (set2.remove(A[i])) {
                C[i]++;
            } else {
                set1.add(A[i]);
            }

            if (set1.remove(B[i])) {
                C[i]++;
            } else {
                set2.add(B[i]);
            }

        }*/
        /*
        元素都恰好出现移除，且长度比较，可以使用集合+二进制来求公共元素数目
        用两个 long 表示 A, B 的集合，a & b 中 1 的数量，就是前面公共元素的数量
         */
        long a = 0, b = 0;
        for (int i = 0; i < A.length; i++) {
            a |= 1L << A[i];
            b |= 1L << B[i];
            A[i] = Long.bitCount(a & b);
        }

        return A;
    }

}
