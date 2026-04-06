package com.oycm.week.No496;

public class Solution_1 {

    /**
     * 3889. <a href="https://leetcode.cn/problems/mirror-frequency-distance/">镜像频次距离</a>
     *
     * @param s 由小写英文字母和数字组成的字符串
     * @return 这些 不同的镜像对 的绝对差之和
     */
    public int mirrorFrequency(String s) {
        /*
        镜像字符:
            对于字母，某字符的镜像字符是字母表中从末尾与其位置相同的字母, 'a' -> 'z'; 'b' -> 'y'
            对于数字，某字符的镜像字符是范围 '0' 到 '9' 中从末尾与其位置相同的数字, '0' -> '9'
        对于字符串中每个 唯一 字符 c:
            设 m 为其 镜像字符
            设 freq(x) 表示字符 x 在字符串中出现的次数
            计算其与镜像字符出现次数之间的 绝对差，定义为：|freq(c) - freq(m)|
            镜像对 (c, m) 和 (m, c) 被视为相同，只能被计算 一次
        用 map 或 数组先记录不同字符的数量

         */
        int ans = 0;
        int[] cnt = new int[128];
        for (char c : s.toCharArray()) {
            cnt[c]++;
        }
        for (int start = '0', end = '9'; start < end; start++, end--) {
            ans += Math.abs(cnt[start] - cnt[end]);
        }
        for (int start = 'a', end = 'z'; start < end; start++, end--) {
            ans += Math.abs(cnt[start] - cnt[end]);
        }

        return ans;
    }

}
