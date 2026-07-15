package com.oycm.week.lc2018.No98;

import java.util.HashSet;
import java.util.Set;

public class Solution_888 {

    /**
     * 888. 公平的糖果交换
     * <br>
     * 888. <a href="https://leetcode.cn/problems/fair-candy-swap/description/">公平的糖果交换</a> 1334
     *
     * @param aliceSizes
     * @param bobSizes
     * @return
     */
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        /*
        爱丽丝和鲍勃拥有不同总数量的糖果。
        给你两个数组 aliceSizes 和 bobSizes ，aliceSizes[i] 是爱丽丝拥有的第 i 盒糖果中的糖果数量，bobSizes[j] 是鲍勃拥有的第 j 盒糖果中的糖果数量。
        两人想要互相交换一盒糖果，这样在交换之后，他们就可以拥有相同总数量的糖果。一个人拥有的糖果总数量是他们每盒糖果数量的总和。
        返回一个整数数组 answer，其中 answer[0] 是爱丽丝必须交换的糖果盒中的糖果的数目，answer[1] 是鲍勃必须交换的糖果盒中的糖果的数目。
        如果存在多个答案，你可以返回其中 任何一个 。题目测试用例保证存在与输入对应的答案。
         */
        /*
        aliceSizes 数组和记为 s1
        bobSizes 数组和记为 s2
        answer[0] 记为 a，answer[1] 记为 b
        则 交换后必有：
            s1 - a + b = s
            s2 + a - b = s
            s1 + s2 = 2s
            交换后的各自糖果总数量为 (s1+s2)/2
        如果 s1 > s，枚举交换 aliceSizes[i]，则看 bobSizes 中是否存在 (s - s1 + a) 的数
        如果 s1 < s，枚举交换 aliceSizes[i]，则看 bobSizes 中是否存在 (s - s1 + a) 的数
         */
        int s1 = 0, s2 = 0;
        for (int x : aliceSizes) {
            s1 += x;
        }
        Set<Integer> set = new HashSet<>();
        for (int x : bobSizes) {
            s2 += x;
            set.add(x);
        }
        int s = (s1 + s2) / 2;

        for (int a : aliceSizes) {
            int b = s - s1 + a;
            if (set.contains(b)) {
                return new int[]{a, b};
            }
        }

        return new int[0];
    }

}
