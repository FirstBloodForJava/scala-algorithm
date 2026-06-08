package com.oycm.dualweek2022.No71;

public class Solution_3 {

    /**
     * 2162. <a href="https://leetcode.cn/problems/minimum-cost-to-set-cooking-time/description/">设置时间的最少代价</a> 1852
     *
     * @param startAt
     * @param moveCost
     * @param pushCost
     * @param targetSeconds
     * @return
     */
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        /*
        常见的微波炉可以设置加热时间，且加热时间满足以下条件：
            至少为 1 秒钟。
            至多为 99 分 99 秒。
        你可以 最多 输入 4 个数字 来设置加热时间。如果你输入的位数不足 4 位，微波炉会自动加 前缀 0 来补足 4 位。
        微波炉会将设置好的四位数中，前 两位当作分钟数，后 两位当作秒数。它们所表示的总时间就是加热时间。
        例如：
            你输入 9 5 4 （三个数字），被自动补足为 0954 ，并表示 9 分 54 秒。
            你输入 0 0 0 8 （四个数字），表示 0 分 8 秒。
            你输入 8 0 9 0 ，表示 80 分 90 秒。
        给你整数 startAt ，moveCost ，pushCost 和 targetSeconds。
        一开始，你的手指在数字 startAt 处。将手指移到 任何其他数字 ，需要花费 moveCost 的单位代价。每 输入你手指所在位置的数字一次，需要花费 pushCost 的单位代价。
        要设置 targetSeconds 秒的加热时间，可能会有多种设置方法。你想要知道这些方法中，总代价最小为多少。
        请你能返回设置 targetSeconds 秒钟加热时间需要花费的最少代价。
        请记住，虽然微波炉的秒数最多可以设置到 99 秒，但一分钟等于 60 秒。
         */
        /*
        输入 targetSeconds 秒的方案固定就几种
            分钟输入最大，剩余输秒：targetSeconds/60 输入分钟，targetSeconds % 60 输入秒
            当 targetSeconds % 60 <= 39 秒时，分钟少输一分钟，秒增加 60，targetSeconds/60, targetSeconds % 60 + 60
        如果 startAt 初始停留在 0 位置，要想代价最小，不输入是最优解，因为最终 targetSeconds 大于 0，需要移动，不输入情况下，可以自动补 0
         */
        int ans = Integer.MAX_VALUE;
        int mod = targetSeconds % 60;
        int minutes = targetSeconds / 60;
        if (mod <= 39 && minutes > 0) {
            // 分钟肯定大于大于 10
            int ms = minutes - 1;
            int ss = mod + 60;
            String s = ms > 0 ? String.valueOf(ms) : "";
            s += ss;
            ans = Math.min(ans, calCost(startAt, moveCost, pushCost, s));
        }
        // 分钟大于 99
        if (minutes > 99) {
            return ans;
        }
        String s = minutes > 0 ? String.valueOf(minutes) : "";
        s += mod < 10 && minutes > 0 ? "0" + mod : mod;
        ans = Math.min(ans, calCost(startAt, moveCost, pushCost, s));

        return ans;
    }

    public int calCost(int pre, int moveCost, int pushCost, String s) {
        int cost = 0;

        for (char c : s.toCharArray()) {
            c -= '0';
            if (pre == c) {
                cost += pushCost;
            } else {
                cost += pushCost + moveCost;
                pre = c;
            }
        }
        return cost;
    }
}
