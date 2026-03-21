package com.oycm.algorithm.ab.min;

public class Solution_3 {

    /**
     * 1234. <a href="https://leetcode.cn/problems/replace-the-substring-for-balanced-string/description/">替换子串得到平衡字符串</a> 1878
     *
     * @param s 只含有 'Q', 'W', 'E', 'R' 四种字符, 四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」
     * @return 求最短待替换字符串, 将 s 变成平衡字符串
     */
    public int balancedString(String s) {
        /*
        滑动窗口 [left, right] 表示待替换的字符串, 当所有字母都小于 等于 m 时, 就可以替换当前字符串变成平衡字符串
         */
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int['X'];
        for (char c : cs) {
            cnt[c]++;
        }
        int m = n / 4;
        if (cnt['Q'] == m && cnt['W'] == m && cnt['E'] == m && cnt['R'] == m) {
            return 0;
        }

        int ans = n;
        int left = 0;
        for (int right = 0; right < n; right++) {
            cnt[cs[right]]--;
            // [left, right] 表示待替换的字符串
            while (cnt['Q'] <= m && cnt['W'] <= m && cnt['E'] <= m && cnt['R'] <= m) {
                ans = Math.min(ans, right - left + 1);
                cnt[cs[left]]++;
                left++;
            }
        }

        return ans;
    }

}
