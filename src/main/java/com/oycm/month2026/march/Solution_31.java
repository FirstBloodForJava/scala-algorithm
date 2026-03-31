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
        题解思路：把未填的字符先都填 'a', 如果 'F' 开始的子串是相等, 则把最后可改位置改为 b
         */
        int n = str1.length(), m = str2.length();
        char[] cs1 = str1.toCharArray(), cs2 = str2.toCharArray();
        char[] ans = new char[n + m - 1];
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
        char[] oldAns = ans.clone();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) ans[i] = 'a';
        }
        for (int i = 0; i < n; i++) {
            if (cs1[i] == 'F' && new String(ans, i, m).equals(str2)) {
                boolean flag = false;
                for (int j = i + m - 1; j >= i; j--) {
                    if (oldAns[j] == 0) {
                        ans[j] = 'b';
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    return "";
                }
            }
        }


        return new String(ans);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_31().generateString("",
                ""));
    }

}
