package com.oycm.dualweek.lc2026.No191;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_3 {

    /**
     * 得到恰好 N 分的最少天数
     *
     * @param n
     * @return
     */
    public int minDays(int n) {
        /*
        给你一个整数 n，表示目标分数。
        你的分数初始为 0，每天你既可以获得分数，也可以跳过。
        分数是在连胜期间获得的。在连胜的第一天，你获得 1 分，第二天获得 2 分，第三天获得 3 分，依此类推。跳过一天将获得零分并重置连胜，因此下一次你获得分数时，将再次从 1 开始。
        返回达到恰好为 n 的分数所需的最少天数（包括所有跳过的天数）。
         */
        /*
        可以预处理
         */
        init();
        return f[n];
    }

    public static boolean initialed = false;
    public static int[] f = new int[100001];

    public void init() {
        if (initialed) return;
        initialed = true;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * (i + 1) / 2 < f.length; i++) {
            list.add(i * (i + 1) / 2);
        }
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = -1;
        for (int i = 1; i < f.length; i++) {
            for (int k = 0; k < list.size() && list.get(k) <= i; k++) {
                int t = list.get(k);
                // k + 2，用时 k+1，再加跳过的一天
                f[i] = Math.min(f[i], f[i - t] + k + 2);
            }
        }
    }


}
