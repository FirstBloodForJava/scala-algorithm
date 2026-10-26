package com.oycm.month2026.october;

public class Solution_24 {

    /**
     * 2409. <a href="https://leetcode.cn/problems/count-days-spent-together/description/">统计共同度过的日子数</a> 1562
     *
     * @param arriveAlice
     * @param leaveAlice
     * @param arriveBob
     * @param leaveBob
     * @return
     */
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        /*
        Alice 和 Bob 计划分别去罗马开会。
        给你四个字符串 arriveAlice ，leaveAlice ，arriveBob 和 leaveBob。
        Alice 会在日期 arriveAlice 到 leaveAlice 之间在城市里（日期为闭区间），而 Bob 在日期 arriveBob 到 leaveBob 之间在城市里（日期为闭区间）。
        每个字符串都包含 5 个字符，格式为 "MM-DD" ，对应着一个日期的月和日。
        请你返回 Alice和 Bob 同时在罗马的天数。
        你可以假设所有日期都在 同一个 自然年，而且 不是 闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] 。
         */
        /*
        怎么让日期开始和结束，落在一个一维的区间？
         */
        int left = calDays(arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob);
        int right = calDays(leaveAlice.compareTo(leaveAlice) > 0 ? leaveAlice : leaveBob);
        return Math.max(right - left + 1, 0);
    }

    private static final int[] daysPreSum = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    private int calDays(String s) {
        int month = (s.charAt(0) - '0') * 10 + s.charAt(1) - '0';
        int day = (s.charAt(3) - '0') * 10 + s.charAt(4) - '0';
        return daysPreSum[month - 1] + day;
    }
}
