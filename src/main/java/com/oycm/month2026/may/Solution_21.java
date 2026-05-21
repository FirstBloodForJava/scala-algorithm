package com.oycm.month2026.may;

import java.util.HashSet;
import java.util.Set;

public class Solution_21 {

    /**
     * 3043. <a href="https://leetcode.cn/problems/find-the-length-of-the-longest-common-prefix/description/">最长公共前缀的长度</a> 1689
     *
     * @param arr1 length, [1, 5e4]; arr1[i] [1, 1e8]
     * @param arr2 length, [1, 5e4]; arr1[i] [1, 1e8]
     * @return
     */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        /*
        给你两个 正整数 数组 arr1 和 arr2。
        正整数的 前缀 是其 最左边 的一位或多位数字组成的整数。例如，123 是整数 12345 的前缀，而 234 不是。
        设若整数 c 是整数 a 和 b 的 公共前缀 ，那么 c 需要同时是 a 和 b 的前缀。例如，5655359 和 56554 有公共前缀 565 和 5655，而 1223 和 43456 没有 公共前缀。
        你需要找出属于 arr1 的整数 x 和属于 arr2 的整数 y 组成的所有数对 (x, y) 之中最长的公共前缀的长度。
        返回所有数对之中最长公共前缀的长度。如果它们之间不存在公共前缀，则返回 0。
         */
        /*
        题解思路：把一个数组的前缀用 hash 表记录，枚举另一个数组的所有前缀，去 hash 表中查找
        枚举前缀的方式：
            字符串，枚举所有前缀 [0, 1), [0, 2), ... [0, n)
            数字，不断除以 10 下取整，直到数为 0，下取整的结果就是前缀
         */
        Set<Integer> set = new HashSet<>();
        for (int x : arr1) {
            for (; x > 0; x /= 10) {
                set.add(x);
            }
        }
        /*
        数组枚举，前缀是从长到短，找到第一个后，就不用继续查找了
         */
        int mx = 0;
        for (int x : arr2) {
            for (; x > 0 && !set.contains(x); x /= 10) {
                ;
            }
            mx = Math.max(mx, x);
        }
        return mx == 0 ? 0 : String.valueOf(mx).length();
    }

}
