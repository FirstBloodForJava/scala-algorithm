package com.oycm.dp.a.climb_stairs;

public class Solution_6 {

    /**
     * 2266. <a href="https://leetcode.cn/problems/count-number-of-texts/description/">统计打字方案数</a> 1857
     *
     * @param pressedKeys
     * @return
     */
    public int countTexts(String pressedKeys) {
        /*
        为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
            比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。类似的， Alice 需要按 '5' 两次得到字母  'k' 。
            注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
        但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
            比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
        给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
        由于答案可能很大，将它对 1e9 + 7 取余 后返回。
         */
        /*
        abc, def, ghi, jkl, mno, pqrs, tuv, wxyz
        以 2266622 为例，可拆分为 22, 666, 22 三个解析后的乘积
        22 的过程可以转换成爬楼梯问题，步长为 1, 2, 3 求走两步的方案数
        可以先初始化 3 步及 4 步 的 dp 数组
         */
        int n = pressedKeys.length();
        init();
        long ans = 1;
        char[] cs = pressedKeys.toCharArray();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            cnt++;
            // 分组循环
            if (i == n - 1 || c != cs[i + 1]) {
                ans = ans * (c == '7' || c == '9' ? f4[cnt] : f3[cnt]) % mod;
                cnt = 0;
            }
        }

        return (int) (ans % mod);
    }

    public static long[] f3 = new long[100000 + 1];
    public static long[] f4 = new long[100000 + 1];
    public static int mod = 1000000007;
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        f3[0] = f4[0] = 1;
        f3[1] = f4[1] = 1;
        f3[2] = f4[2] = 2;
        f3[3] = f4[3] = 4;

        for (int i = 4; i < f3.length; i++) {
            // 一起计算，需要调整数组类型
            f3[i] = (f3[i - 1] + f3[i - 2] + f3[i - 3]) % mod;
            f4[i] = (f4[i - 1] + f4[i - 2] + f4[i - 3] + f4[i - 4]) % mod;
        }
    }

    public static void main(String[] args) {
        Solution_6 solution = new Solution_6();
        System.out.println(solution.countTexts("222222222222222222222222222222222222"));

    }


}
