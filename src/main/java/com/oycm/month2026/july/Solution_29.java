package com.oycm.month2026.july;

public class Solution_29 {

    /**
     * 3518. <a href="https://leetcode.cn/problems/smallest-palindromic-rearrangement-ii/description/">最小回文排列 II</a> 2375
     *
     * @param s
     * @param k
     * @return
     */
    public String smallestPalindrome(String s, int k) {
        /*
        给你一个 回文 字符串 s 和一个整数 k。
        返回 s 的按字典序排列的 第 k 小 回文排列。如果不存在 k 个不同的回文排列，则返回空字符串。
        注意： 产生相同回文字符串的不同重排视为相同，仅计为一次。
        如果一个字符串从前往后和从后往前读都相同，那么这个字符串是一个 回文 字符串。
        排列 是字符串中所有字符的重排。
        如果字符串 a 按字典序小于字符串 b，则表示在第一个不同的位置，a 中的字符比 b 中的对应字符在字母表中更靠前。
        如果在前 min(a.length, b.length) 个字符中没有区别，则较短的字符串按字典序更小。
        */
        /*
        由于是回文串，左半固定，右半就确定是什么了。
        n 是奇数，中间的字符是固定的，相当去掉中间字符，考虑 n 是偶数的情况。也就只填 n/2 左半情况。
         */
        int n = s.length();
        int m = n / 2;

        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        if (perm(cnt, m, k) < k) {
            return "";
        }

        char[] ls = new char[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 26; j++) {
                // 字符不存在
                if (cnt[j] == 0) {
                    continue;
                }
                // 假设填字母 j，看是否有足够的排列
                cnt[j]--;
                int p = perm(cnt, m - i - 1, k); // 剩余位置的排列个数
                if (p >= k) {
                    // 有足够的排列，开始填下一个字符
                    ls[i] = (char) ('a' + j);
                    break;
                }
                // ls[i] 填字符 j，最大的方案数是 p，当前位置不能填 j，减去 ls[i] 填 j 的方案数（一定不符合要求）后，排除后的第 k 个字符
                k -= p;
                cnt[j]++;
            }
        }

        StringBuilder ans = new StringBuilder(n);
        ans.append(ls);
        // 奇数固定
        if (n % 2 > 0) {
            ans.append(s.charAt(n / 2));
        }
        for (int i = m - 1; i >= 0; i--) {
            ans.append(ls[i]);
        }
        return ans.toString();
    }

    public int comb(int n, int m, int k) {
        // 这里必须 (m, n-m) 做最小值判断，当 m > n/2 时，恰好 m = n/2，且组合数大于 k 时，会出现误判 res >= k 的情况。
        m = Math.min(m, n - m);
        // C(n, 0)
        long res = 1;
        for (int i = 1; i <= m; i++) {
            // C(n, i) = C(n, i-1) * (n-i+1)/i
            res = res * (n - i + 1) / i;
            if (res >= k) {
                return k;
            }
        }
        return (int) res;
    }

    public int perm(int[] cnt, int n, int k) {
        long res = 1;
        for (int c : cnt) {
            if (c == 0) continue;
            res *= comb(n, c, k);
            if (res >= k) {
                return k;
            }
            n -= c;
        }

        return (int) res;
    }

}

class Solution_62 {
    /**
     * 62. <a href="https://leetcode.cn/problems/unique-paths/description/">不同路径</a>
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        /*
        一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
        问总共有多少条不同的路径？
         */
        /*
        组合数计算：n+m-2 个位置中，要填 m-1 个下，n-1 个右。
        相当于从 (n+m-2) 位置中，选出 m-1 个位置填下，剩余位置必然填右，求这样的方案数。
        变成求 C(n+m-2)(m-1) 的组合数
        C(n, i) = n! / (i! (n-i)! )
        C(n, i-1) = n! / ( (i-1)! (n-i+1)! )
        C(n, i) = C(n, i-1) * (n-i+1)/i
        由于 C(n, i) 一定是整数，所以右边的结果一定是整数
         */
        return (int) comb(n + m - 2, m - 1);
    }

    public long comb(int n, int k) {
        k = Math.min(k, n - k);
        // C(n, 0)
        long res = 1;
        for (int i = 1; i <= k; i++) {
            // C(n, i) = C(n, i-1) * (n-i+1)/i
            res = res * (n - i + 1) / i;
        }
        return res;
    }
}
