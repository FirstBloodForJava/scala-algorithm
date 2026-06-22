package com.oycm.dualweek.lc2024.No140;

public class Solution_3 {

    /**
     * 3302. <a href="https://leetcode.cn/problems/find-the-lexicographically-smallest-valid-sequence/description/">字典序最小的合法序列</a> 2474
     *
     * @param word1
     * @param word2
     * @return
     */
    public int[] validSequence(String word1, String word2) {
        /*
        给你两个字符串 word1 和 word2。
        如果一个字符串 x 修改 至多 一个字符会变成 y ，那么我们称它与 y 几乎相等。
        如果一个下标序列 seq 满足以下条件，我们称它是 合法的：
            下标序列是 升序 的。
            将 word1 中这些下标对应的字符 按顺序 连接，得到一个与 word2 几乎相等 的字符串。
            请你返回一个长度为 word2.length 的数组，表示一个 字典序最小 的 合法 下标序列。如果不存在这样的序列，请你返回一个 空 数组。
        注意 ，答案数组必须是字典序最小的下标数组，而 不是 由这些下标连接形成的字符串。
        1 <= word2.length < word1.length <= 3 * 1e5
         */
        /*
        问题可以先转换成，word2 删除一个字符，剩下的字符串是否为 word1 的子序列。
        word1 记为 s，word2 记为 t，暴力的做法，可以先枚举 t 中去掉一个字符，是否为 s 的子序列，这样做，光是子序列判断就是 nm。
        有什么优化呢？
            s[0 : i] 去匹配 t 的前缀，pre[i] 表示能匹配 t 前缀的结束下标再 +1；
            s[i+1 : n-1] 去匹配 t 的后缀，suf[i+1] 表示能匹配 t 后缀开始下标再 -1；
            [pre[i], suf[i+1]] 表示删除 t 的子串，t 是 s 的子序列，suf[i+1] - pre[i] + 1 <= 1;
            即 pre[i] >= suf[i+1] 则肯定能去掉一个字符，使得 t 是 s 的子序列。
            但是如果是修改，则不能这么判断，例如 s = "acbbb", t = "abc"
        这里是找修改，找字典序最小的下标序列，可以用到预处理后 suf 数组
        suf[i] 表示 s[i :] 对应 t 最长后缀开始下标 j，则 t[j :] 是 s[i :] 的子序列。
        从左到右遍历 s，分类讨论：
            如果 s[i] = t[j]，前面能匹配上，则立刻把 i 加入答案。如果后面有序列匹配了，这里没加上，则说明不是最小子序列。
            如果 s[i] != t[j]，判断是否修改 s[i] 使得 t 是 s 的子序列。这里越早修改，子序列越小。
            如果修改，则 t[0 : j] 是 s[0 : i] 的子序列，要判断 t[j+1 :] 是否为 s[i+1 :] 的子序列，根据前面 suf 数组定义，
            要满足 t[j+1 :] 是 s[i+1 :] 的子序列， 则 suf[i+1] <= j+1。修改后，进行标记。
        当 j == m 时，提取返回，如果最终没有提前返回，则说明没有符合条件的子序列
         */
        char[] cs = word1.toCharArray();
        char[] ts = word2.toCharArray();
        int n = cs.length;
        int m = ts.length;
        int[] suf = new int[n + 1];
        suf[n] = m;
        for (int i = n - 1, j = m - 1; i >= 0; i--) {
            if (j >= 0 && cs[i] == ts[j]) {
                j--;
            }
            suf[i] = j + 1;
        }
        int[] ans = new int[m];
        boolean changed = false;
        for (int i = 0, j = 0; i < n; i++) {
            if (cs[i] == ts[j] || !changed && suf[i + 1] <= j + 1) {
                if (cs[i] != ts[j]) {
                    changed = true;
                }
                ans[j++] = i;
                if (j == m) return ans;
            }
        }

        return new int[0];
    }

}
