package com.oycm.week.lc2020.No183;

public class Solution_2 {

    /**
     * 1404. <a href="https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/description/">将二进制表示减到 1 的步骤数</a> 1397
     *
     * @param s
     * @return
     */
    public int numSteps(String s) {
        /*
        给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
            如果当前数字为偶数，则将其除以 2 。
            如果当前数字为奇数，则将其加上 1 。
        题目保证你总是可以按上述规则将测试用例变为 1 。
         */
        /*
        设 s 最右边的 '1' 下标为 i，初始化答案为 n-1
            下标 [i+1, n-1] 都是 0，只进行 除法 操作；
            s[i] 处执行加法，答案加 1，进位 1；
            如果产生了进位，中间的 0 先执行加法才能删除，中间的 1 不需要执行加法，始终会进位，所以 s[0] 处肯定也要执行一次加法;
            需要找 [1, i] 中间 0 的个数;
            最终再加上 2，s[i] 和 s[0] 处的操作
         */
        int ans = s.length() - 1;
        int i = s.lastIndexOf('1');
        if (i > 0) {
            for (int j = i; j > 0; j--) {
                ans += '1' - s.charAt(j);
            }
            ans += 2;
        }
        return ans;
    }

}
