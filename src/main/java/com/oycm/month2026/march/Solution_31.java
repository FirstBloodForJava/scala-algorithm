package com.oycm.month2026.march;

public class Solution_31 {

    /**
     * 3474. <a href="https://leetcode.cn/problems/lexicographically-smallest-generated-string/description/">字典序最小的生成字符串</a> 2605
     *
     * @param str1 长度为 n, 由 T 和 F 组成
     * @param str2 长度为 m, 由小写字母组成
     * @return 由 str1 和 str2 生成 的 字典序最小 的字符串。如果不存在满足条件的字符串，返回空字符串 ""
     */
    public String generateString(String str1, String str2) {
        /*
        长度为 n + m - 1 的字符串 word 的每个下标 0 <= i <= n - 1 都满足以下条件，则称其由 str1 和 str2 生成
            如果 str1[i] == 'T'，则长度为 m 的 子字符串（从下标 i 开始）与 str2 相等，即 word[i..(i + m - 1)] == str2
            如果 str1[i] == 'F'，则长度为 m 的 子字符串（从下标 i 开始）与 str2 不相等，即 word[i..(i + m - 1)] != str2, 子串中任意一个字符不相等即可
         */
        /*
        str1[i] == 'T', 可以确定 ans[i, i + m -1] 填的字符为 str2[i], 如果前面填的值和现在要填的值不一样，则一定无法构建该字符串
        str2[i] == 'F', ans[i, i + m - 1] 填的字符任意一个与 str2[i] 不一致即可, 判断这里是否能填一个更小的字母 'a', 'b', ...
            ans[i] 填 'a' 肯定优于后面的位置填 'a', 这里不能填 'a' 的情况，只能是 ans[i+1, i + m -1] 与 str2[1, m-1] 都相等, 且 str2[0] == 'a'

        先把固定的都填上，不符合要求直接返回。
        未填的位置，从左到右依次由最小的字符
        [0, n-1] 下标 i 未填字符只能有一下几种情况
            cs1[0] = 'F', ans[0] 后面连续为 'F' 的下标
            cs1[j] = 'F', i = j - m + 1, [i, j] 全是 'F'
        ans [0, n + m - 1], ans[i] == 0
            0 <= i <= m - 1, 则 i [0, min(n, m) - 1] str1[i] == 'F'
            i >= m, [(i - m + 1), i] str1[j] == 'F'
         */

        int n = str1.length(), m = str2.length();
        char[] cs1 = str1.toCharArray(), cs2 = str2.toCharArray();
        char[] ans = new char[n + m - 1];
        int[] same = new int[n];
        int[] notV = new int[n];
        for (int i = 0; i < n; i++) {
            if (cs1[i] == 'T') {
                for (int j = 0; j < m; j++) {
                    int k = i + j;
                    if (ans[k] == 0) {
                        ans[k] = cs2[j];
                    } else if (ans[k] != cs2[j]) {
                        return "";
                    }

                }
            }

        }
        // ans[] 下标 i 开始, [i, i + m - 1] 相同字符数量

        // 未填的字符串，是否能填 'a'
        for (int k = 0; k < ans.length; k++) {
            if (k < n && cs1[k] == 'F') {
                for (int i = k; i < k + m; i++) {
                    if (ans[i] == 0) {
                        notV[k]++;
                    } else if (ans[i] == cs2[i - k]) {
                        same[k]++;
                    } else {
                        same[k] = -1;
                        break;
                    }
                }
                // F 标记字符串都相等
                if (same[k] == m) return "";

                // 未填字符, 这里只 根据 k 向后看，如果 k 前面有 'F', 需要校验 [k - m + 1, k] 的情况是否符合要求
                if (ans[k] == 0) {
                    if (same[k] < 0 || notV[k] > 1) {
                        ans[k] = 'a';
                        if (cs2[0] != 'a') {
                            same[k] = -1;
                        }
                    } else {
                        // 后面字符串都相等,
                        ans[k] = cs2[0] == 'a' ? 'b' : 'a';
                    }
                    notV[k]--;
                }

            }

            if (ans[k] == 0) {
                // 填 a 不相等标记 -1
                if (same[n - 1] < 0 || notV[n - 1] > 1) {
                    ans[k] = 'a';
                    if (cs2[k - n + 1] != ans[k]) {
                        same[n - 1] = -1;
                    }
                } else {
                    // 后面字符串都相等,
                    ans[k] = cs2[k - n + 1] == 'a' ? 'b' : 'a';
                }
                notV[n - 1]--;
            }


        }

        return new String(ans);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_31().generateString("",
                ""));
    }

}
