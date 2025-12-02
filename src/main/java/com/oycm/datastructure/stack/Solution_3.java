package com.oycm.datastructure.stack;


public class Solution_3 {

    /**
     * 682. <a href="https://leetcode.cn/problems/baseball-game/description/">棒球比赛</a>
     * <p>
     * operations 字符串列表，表示第 i 项的操作，操作遵循以下规则：
     * <p>
     * - 整数 x - 表示本回合新获得分数 x
     * <p>
     * - "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
     * <p>
     * - "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
     * <p>
     * - "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
     *
     * @param operations 字符串列表
     * @return 求总得分
     */
    public static int calPoints(String[] operations) {
        int[] opt = new int[operations.length];
        int cnt = 0;
        int ans = 0;
        for (String o : operations) {
            if (o.equals("+")) {
                opt[cnt] = opt[cnt - 1] + opt[cnt - 2];
                ans += opt[cnt];
                cnt++;
            } else if (o.equals("D")) {
                opt[cnt] = opt[cnt - 1] * 2;
                ans += opt[cnt];
                cnt++;
            } else if (o.equals("C")) {
                ans -= opt[--cnt];
            } else {
                opt[cnt] = Integer.parseInt(o);
                ans += opt[cnt];
                cnt++;
            }
        }

        return ans;
    }

}
