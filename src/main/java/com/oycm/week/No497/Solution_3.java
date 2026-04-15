package com.oycm.week.No497;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_3 {

    /**
     * 3900. <a href="https://leetcode.cn/problems/longest-balanced-substring-after-one-swap/description/">一次交换后的最长平衡子串</a>
     *
     * @param s '0' 和 '1' 组成的二进制字符串
     * @return 能够选取的 平衡 子串的 最大 长度
     */
    public int longestBalanced(String s) {
        /*
        一个字符串中 0 和 1 的数量 相等，称该字符串是 平衡 字符串。
        最多可以让 s 中任意两个字符进行 一次 交换。之后，从 s 中选出一个 平衡 子串。
         */
        /*
        把 '1' 看作 数值 1, '0' 看作数值 -1, 用 map 记录 相同前缀和的第一次出现的 下标，-sum[i+1] 存在的下标为 j，则 i - j + 1 为不交换时，平衡字符串的长度
        怎么判断是否能交换，当 sums[i+1] - pre == 0, [0, pre)，的前缀和绝对值 != pre ，则前面通过交换，还可以增加 2

        先计算前缀和数组，并记录相同值第一次出现下标，Math.abs(sums[j]) != j || Math.asb(sums[n] - sum[i+1]) != n - i
        11000001 把中间的 0 换成 1，+2 -2 的 j 交换中间
         */
        /*
        sums[r] - sums[l] == 0 时，只需要直到最左边的 l
        当子串中多出现 2 个 1 时，可以使用里面的 1 交换外面的 0，得到平衡字符串
            sums[r] - sums[l] == 2 时，里面使用的 0 数量为 (r - l - 2) / 2,
                如果数量 小于 所有 0，则可以交换成功；
                如果数量 等于 所有 0，则不可以交换成功；需要看第二次 sum[r] - sum[l'] == 2 是否存在，如果存在
                sums[r] - sums[l] == sums[r] - sums[l'], [l, l'] 之间是否存在可用的 0？sums[l] == sums[l'] l != l', 中间至少存在一个 1/0
         */
        char[] cs = s.toCharArray();
        int total0 = 0;
        for (char c : cs) {
            if (c == '0') {
                total0++;
            }
        }
        int total1 = cs.length - total0;

        Map<Integer, List<Integer>> pos = new HashMap<>();
        // 前缀和 0 的下标 0, i 数量是 i - 0 + 1
        pos.computeIfAbsent(0, l -> new ArrayList<>()).add(-1);

        int ans = 0;
        int sum = 0;

        for (int i = 0; i < cs.length; i++) {
            sum += (cs[i] - '0') * 2 - 1;

            List<Integer> p = pos.computeIfAbsent(sum, l -> new ArrayList<>());
            if (p.size() < 2) {
                p.add(i);
            }

            // 先添加避免了判断，当前是平衡子串
            ans = Math.max(ans, i - p.get(0));

            // 当前子串多两个 1，需要交换外面的 0
            p = pos.get(sum - 2);
            if (p != null) {
                if ((i - p.get(0) - 2) / 2 < total0) {
                    ans = Math.max(ans, i - p.get(0));
                } else if (p.size() > 1) {
                    ans = Math.max(ans, i - p.get(1));
                }
            }

            // 当前子串多两个 0，需要交换外面的 1
            p = pos.get(sum + 2);
            if (p != null) {
                if ((i - p.get(0) - 2) / 2 < total1) {
                    ans = Math.max(ans, i - p.get(0));
                } else if (p.size() > 1) {
                    ans = Math.max(ans, i - p.get(1));
                }
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        Solution_3 solution = new Solution_3();
        System.out.println(solution.longestBalanced("0010111100000000110"));
    }
}
