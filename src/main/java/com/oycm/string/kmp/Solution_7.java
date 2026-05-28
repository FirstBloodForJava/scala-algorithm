package com.oycm.string.kmp;

public class Solution_7 {

    /**
     * 459. <a href="https://leetcode.cn/problems/repeated-substring-pattern/description/">重复的子字符串</a>
     *
     * @param s 1 <= s.length <= 1e4
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        /*
        给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
         */
        /*
        重复的子串长为 m，枚举 n % m == 0; 所有子串是否和 s 匹配
         */
        /*
        s + s 得到一个 t 串，如果 s 是重复串，重复串长度为 i，则 t.indexOf(s) == i，n % i == 0
        s next[n-1] 也一定满足 = n - i，因为都是重复串，后缀中去掉第一个的重复串不匹配。
        s 存在重复串，去掉 t 的第一个和最后一个字符，如果能找到，就匹配。 p => q 充分性。
        t 去掉第一个和最后一个字符，s 是重复串。要证明其必要性
        假设找到的下标为 i，则 i (0, n)，s[0, n-1] = t[i, n+i-1]
        t 是由两个 s 组成，可以将 t 分成 n-1 左侧和右侧两部分
            t[i, n-1] = s[0, n-i-1] 右端点左移 i；
            t[n, n+i-1] = s[n-i, n-1] 左端点右移 n-i；t[n, n+i-1] 长为 i，对应 t 的 [0, i-1] 部分
            s[0, n-1] = t[0, n-1] = t[n, 2n-1]
            上面的等式转换到 s，则为
            s[i, n-1] = s[0, n-i-1]
            s[0, i-1] = s[n-i, n-1]
            这里说 s 是一个可旋转的字符串，旋转值固定为 i，s[j] = s[j+i]
         */
        int n = s.length();
        int[] next = new int[n];
        for (int i = 1, cnt = 0; i < n; i++) {
            while (cnt > 0 && s.charAt(i) != s.charAt(cnt)) {
                cnt = next[cnt - 1];
            }
            if (s.charAt(i) == s.charAt(cnt)) {
                cnt++;
            }
            next[i] = cnt;
        }

        return next[n - 1] != 0 && n % (n - next[n - 1]) == 0;
    }

}
