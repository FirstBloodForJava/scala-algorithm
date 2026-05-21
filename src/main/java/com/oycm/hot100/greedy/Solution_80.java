package com.oycm.hot100.greedy;

import java.util.ArrayList;
import java.util.List;

public class Solution_80 {

    /**
     * 763. <a href="https://leetcode.cn/problems/partition-labels/description/">划分字母区间</a> 1443
     *
     * @param s s.length [1, 500] s 仅由小写英文字母组成
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        /*
        给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
        例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
        注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
        返回一个表示每个字符串片段的长度的列表。
         */
        /*
        把他看成一个传送，一个点连通另一个最远的点，中间的点也能连接更远的点，然后求尽可能多的传送次数。
        遍历 s[i] 看 i 能传送的最远距离，更新子u大致，如果 i == mx，直接传送，记录上次传送点。
        优化，由于只有字母，所以不用每次都去查找字母的最后下标，用一个数组记录每个字母的最后下标
         */
        char[] cs = s.toCharArray();
        int[] last = new int[26];
        for (int i = 0; i < cs.length; i++) {
            last[cs[i] - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int mx = 0;
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            mx = Math.max(mx, last[cs[i] - 'a']);
            if (mx == i) {
                ans.add(i - pre);
                pre = i;
            }
        }

        return ans;
    }

}
