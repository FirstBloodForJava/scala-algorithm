package com.oycm.week.lc2024.No393;

public class Solution_1 {

    /**
     * 3114. <a href="https://leetcode.cn/problems/latest-time-you-can-obtain-after-replacing-characters/description/">替换字符可以得到的最晚时间</a> 1291
     *
     * @param s
     * @return
     */
    public String findLatestTime(String s) {
        /*
        给你一个字符串 s，表示一个 12 小时制的时间格式，其中一些数字（可能没有）被 "?" 替换。
        12 小时制时间格式为 "HH:MM" ，其中 HH 的取值范围为 00 至 11，MM 的取值范围为 00 至 59。
        最早的时间为 00:00，最晚的时间为 11:59。
        你需要将 s 中的 所有 "?" 字符替换为数字，使得结果字符串代表的时间是一个 有效 的 12 小时制时间，并且是可能的 最晚 时间。
        返回结果字符串。
         */
        char[] cs = s.toCharArray();
        if (cs[0] == '?') {
            cs[0] = cs[1] <= '1' || cs[1] == '?' ? '1' : '0';
        }
        if (cs[1] == '?') {
            cs[1] = cs[0] == '0' ? '9' : '1';
        }
        if (cs[3] == '?') {
            cs[3] = '5';
        }
        if (cs[4] == '?') {
            cs[4] = '9';
        }

        return new String(cs);
    }

}
