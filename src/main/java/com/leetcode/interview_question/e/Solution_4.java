package com.leetcode.interview_question.e;

public class Solution_4 {

    /**
     * 面试题 05.04. <a href="https://leetcode.cn/problems/closed-number-lcci/description/">下一个数</a>
     *
     * @param num
     * @return
     */
    public int[] findClosedNumbers(int num) {
        /*
        下一个数。给定一个正整数，找出与其二进制表达式中 1 的个数相同且大小最接近的那两个数（一个略大，一个略小）。
         */
        /*
        略大的数：找到二进制中第一个 01(从低位到高位)，将其变成 10，其余位不变；
        略小的数：找到二进制中第一个 10(从低位到高位)，将其变成 01，其余位不变；
            如果 10 是最高位，那么最高位右移 1 位，剩余高位补齐剩余 1 数量
        不能使用 lowbit，因为这种情况 1011 计算都是错误的。
         */
        return new int[]{max(num), min(num)};
    }

    public int max(int num) {
        if (num == Integer.MAX_VALUE) {
            return -1;
        }
        /*
        1011 第一个 01 可以通过 1011 + lowbit(1011) 得到
        只需要在后面拼接 num 第一个 01 右边的 1
        补齐剩余的 0
         */
        int x = num + (num & -num);
        // 0111 加上 lowbit 低位的连续都被去掉了，需要补上
        int dif = Integer.bitCount(num) - Integer.bitCount(x);
        // 1 << dif 再减少 1，补齐低位 0
        return x | ((1 << dif) - 1);
    }

    public int min(int num) {
        // 全是连续 1，没有 10 存在
        if ((num & (num + 1)) == 0) {
            return -1;
        }
        /*
        1001101
        0110010 求取反后的 lowbit，恰好就是原 num 第一个 10 向右移一位
        如果 10 不是最高位那么，就求出答案。
        1000111 => 0111000
        0001000
        0111111 => 0100111
        x 的 1 会比 num 1 多，比 num 多的低位 1 要去掉
         */
        int x = ~num;
        x = num - (x & -x);
        int dif = Integer.bitCount(x) - Integer.bitCount(num);
        // x >> dif 去掉低位 1，再 << dif 恢复高位
        return x >> dif << dif;
    }

}
