package com.oycm.algorithm.aba.advance;

public class Solution_4 {

    /**
     * 2516. <a href="https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/description/">每种字符至少取 K 个</a> 1948
     *
     * @param s 只包含 a, b, c 三种字符的字符串
     * @param k
     * @return 求从 s 的最左侧或最右侧 取走每种字符 至少 k 个的次数
     */
    public int takeCharacters(String s, int k) {
        if (k == 0) {
            return 0;
        }
        if (2 * k > s.length()) {
            return -1;
        }
        /*
        先统计每个 字符的数量
        题解思路，记录 字符串中 a, b, c 字符的数量分别为 x, y, z 个，左右两侧移除至少 k 个 a, b, c 后，剩下的子串 a, b, c 字符的数量至多不会超过 x-k, y-k, z-k 个
        问题转换成求 满足上面条件的最长子串，由于 子串越短越符合条件，越长越不能满足要求，具有单调性
        窗口移动过程中，可以维护窗口外 的字符串数, r 移动时，减少窗口中字符串的数量，当字符数 < k 时，移动 l
         */
        int ans = 0;
        int[] cnt = new int[3];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) {
            return -1;
        }
        int l = 0, r = 0, n = s.length();
        while (r < n) {
            cnt[chars[r] - 'a']--;
            while (cnt[chars[r] - 'a'] < k) {
                cnt[chars[l] - 'a']++;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }


        return n - ans;
    }
}
