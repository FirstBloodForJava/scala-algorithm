package com.oycm.week.No493;

public class Solution_1 {

    public int countCommas(int n) {
        /*
        [1, n] 数字格式中书写时用到的 ,
         */
        int ans = 0;

        for (int i = 1000; i < n; i++) {
            int temp = i;
            while (temp >= 1000) {
                temp /= 1000;
                ans++;
            }
        }

        return ans;
    }
}
