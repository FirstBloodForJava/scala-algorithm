package com.oycm.dp.e.best_divide;

public class Solution_7 {

    /**
     * LCR 165. <a href="https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/description/">解密数字</a>
     *
     * @param ciphertext [0, 1<<31]
     * @return
     */
    public int crackNumber(int ciphertext) {
        /*
        现有一串神秘的密文 ciphertext，经调查，密文的特点和规则如下：
            密文由非负整数组成
            数字 0-25 分别对应字母 a-z
        请根据上述规则将密文 ciphertext 解密为字母，并返回共有多少种解密结果。
         */
        /*
        ciphertext 转成字符串 s
        f[i] 表示字符串 s[0 : i] 字符解密方式，可根据已经计算的 f[]，来计算 f[i+1] 的解密方法，分类讨论：
            s[i] == '1', f[i+1] = f[i-1]（s[i] 和 s[i+1] 组合解密） + f[i]（单独作为字符解密）
            s[i] == '2' && s[i+1] <= '5', f[i+1] = f[i-1] + f[i]
            f[i] == '2' && s[i+1] > 6, f[i+1] = f[i]
            其它情况 f[i+1] = f[i]
        */
        // 数字倒序处理
        int f0 = 1, f1 = 1;
        int pre = ciphertext % 10;
        int x = ciphertext / 10;
        while (x > 0) {
            int d = x % 10;
            int f = 0;
            if (d == 1 || d == 2 && pre <= 5) {
                f = f0 + f1;
            } else {
                f = f1;
            }
            x /= 10;
            pre = d;
            f0 = f1;
            f1 = f;
        }

        return f1;
    }

}
