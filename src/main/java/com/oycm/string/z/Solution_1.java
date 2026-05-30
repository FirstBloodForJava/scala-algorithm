package com.oycm.string.z;

public class Solution_1 {

    /**
     * 2223. <a href="https://leetcode.cn/problems/sum-of-scores-of-built-strings/description/">构造字符串的总得分和</a> 2220
     *
     * @param s
     * @return
     */
    public long sumScores(String s) {
        /*
        你需要从空字符串开始 构造 一个长度为 n 的字符串 s ，构造的过程为每次给当前字符串 前面 添加 一个 字符。
        构造过程中得到的所有字符串编号为 1 到 n ，其中长度为 i 的字符串编号为 si。
        比方说，s = "abaca" ，s1 == "a" ，s2 == "ca" ，s3 == "aca" 依次类推。
        si 的 得分 为 si 和 sn 的 最长公共前缀 的长度（注意 s == sn ）。
        给你最终的字符串 s ，请你返回每一个 si 的 得分之和 。
         */
        char[] cs = s.toCharArray();
        int n = cs.length;
        long sum = n;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                /*
                z[i] 对应的区间 [i, i+z[i]-1] 称为匹配段，称为 z-box，维护右端点最靠右的匹配段
                在 z-box 中, s[l : r] = s[0 : r-l] 左端点右移 i-l 得 s[i : r] = s[i-l : r-l]
                说明 z[i] 受到 z[i-l] 的约束：
                如果 z[i-l] < r-i+1，设 z[i-l] = j + 1，j < r-i，则 s[0 : j] = s[i-l : i-l+j]
                    由于 s[i-l : r-l] = s[i : r]，i+j < r，则 i-l+j < r-l，在 r-l 的左边，
                    s[i : r] = s[i-l : r-l] 右端点向左移动 r-(i+j)，可得
                    s[i : i+j] = s[i-l : i+j-l] = s[0, j] s[i+j+1] != s[j+1]，根据定义 z[i] = j+1 = z[i-j]
                如果 z[i-l] >= r-i+1，s[i : r] = s[i-l : r-l] = s[0 : r-i+1]，则 z[i] = r-i+1，s[i+z[i]] 比较 s[z[i]] 继续扩展
                 */
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && cs[z[i]] == cs[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                r = i + z[i] - 1;
                l = i;
            }
            sum += z[i];
        }

        return sum;
    }

}
