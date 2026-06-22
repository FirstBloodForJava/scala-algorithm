package com.oycm.week.lc2026.No504;

public class Solution_1 {
    public int digitFrequencyScore(int n) {
        /*
        给你一个整数 n。
        n 的得分定义为：对所有不同数字 d，计算 d * freq(d) 的总和，其中 freq(d) 表示数字 d 在 n 中出现的次数。
        返回一个整数，表示 n 的得分。
         */
        int ans = 0;
        while (n > 0) {
            ans += n % 10;
            n /= 10;
        }

        return ans;
    }
}
