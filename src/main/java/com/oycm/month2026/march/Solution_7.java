package com.oycm.month2026.march;

public class Solution_7 {

    /**
     * 1888. <a href="https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/">使二进制字符串字符交替的最少反转次数</a> 2006
     * 按任意顺序执行以下两种操作任意次:
     *  类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
     *  类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
     * @param s
     * @return 使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数
     */
    public int minFlips(String s) {
        /*
        题解思路: 字符串不断进行 类型 1 操作,
        111000
         110001
          100011
           000111
            001110
             011100
              111000
        不断进行 类型 1, 得到的字符串就是 2s - 1(111000 11100) 字符串的子串
        下标从 0 开始
        11100011100
        X.XX.XX.XX.
        int(s[i]) == i % 2, 记为 ., 表示 0101 模式符合要求的字符
        int(s[i]) != i % 2, 记为 X, 表示 1010 模式符合要求的字符
        字符串左端点下标是偶数时:
            0101 模式, X 的数量就是 类型 2 的操作次数
            1010 模式, . 的数量就是 类型 2 的操作次数
        字符串左端点下标时奇数时:
            0101 模式, . 的数量就是 类型 2 的操作次数
            1010 模式, X 的数量就是 类型 2 的操作次数
        . 的数量 +　X 的数量 = n
         */
        int cnt = 0, n = s.length();
        int ans = n;
        char[] cs = s.toCharArray();
        for (int i = 0; i < 2 * n - 1; i++) {
            if (cs[i % n] % 2 == i % 2 ){
                cnt ++;
            }
            int left = i - n + 1;
            if (left >= 0) {
                ans = Math.min(ans, Math.min(cnt, n - cnt));
                if (cs[left] % 2 == left % 2) {
                    cnt--;
                }
            }
        }
        return ans;

    }

}
