package com.oycm.week.No495;

public class Solution_1 {

    public int firstMatchingIndex(String s) {
        /*
        6 = 12 / 2
        5 = 11 / 2
         */
        int ans = -1;
        int n = s.length();
        for (int i = n / 2; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(n - i - 1)) {
                ans = i;
            }
        }

        return ans;
    }
}
