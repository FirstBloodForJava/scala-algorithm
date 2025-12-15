package com.oycm.algorithm.h.basic;


import java.util.stream.IntStream;

public class Solution_3 {

    /**
     * 1356. <a href="https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits/description/">根据数字二进制下 1 的数目排序</a> 1258
     *
     * @param arr
     * @return
     */
    public int[] sortByBits(int[] arr) {

        return IntStream.of(arr).boxed().sorted((a, b) -> {
            int ba = Integer.bitCount(a);
            int bb = Integer.bitCount(b);
            return ba == bb ? a - b : ba - bb;
        }).mapToInt(o -> o).toArray();
    }
}
