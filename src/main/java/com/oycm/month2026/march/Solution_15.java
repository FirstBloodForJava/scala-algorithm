package com.oycm.month2026.march;

import java.util.ArrayList;
import java.util.List;

public class Solution_15 {

    /**
     * 1622. <a href="https://leetcode.cn/problems/fancy-sequence/description/">奇妙序列</a> 2476
     */
    class Fancy {

        private static final int MOD = 1_000_000_007;

        private final List<Integer> values = new ArrayList<>();
        private long add = 0;
        private long mul = 1;

        public Fancy() {
            /*
            题解思路: 懒加载 + 等价转换
            在用到这个数的时候计算答案

            只有加法: 用一个 add 记录 addAll 加的值, 添加之前, val = val - add, 这样返回的 val + add 就是只进行加法后的结果
            只有乘法: 用一个 mul 记录 multAll 乘的数, 添加之前, val = val / mul, 返回的时候 val * mul 就是只进行乘法的结果
            加法乘法结合
                初始化 add = 0, nul = 1
                执行 addAll, add += inc
                执行 multAll, (val * mul + add) * m = val * mul * m + add * m; mul = mul * m, add = add * m
                    getIndex = values[i] * mul + add;
                append 操作 val - add / mul
             */
        }

        /**
         * 添加在序列末尾
         *
         * @param val
         */
        public void append(int val) {
            values.add((int) ((val - add + MOD) * pow(mul, MOD - 2) % MOD));
        }

        /**
         * 将所有序列中的现有数值都增加 inc
         *
         * @param inc
         */
        public void addAll(int inc) {
            add = (add + inc) % MOD;
        }

        /**
         * 将序列中的所有现有数值都乘以整数 m
         *
         * @param m
         */
        public void multAll(int m) {
            mul = mul * m % MOD;
            add = add * m % MOD;
        }

        /**
         * @param idx
         * @return 下标为 idx 处的数值（下标从 0 开始）
         */
        public int getIndex(int idx) {
            if (idx >= values.size()) {
                return -1;
            }
            return (int) ((values.get(idx) * mul + add) % MOD);
        }

        private long pow(long x, int n) {
            long res = 1;
            for (; n > 0; n /= 2) {
                if (n % 2 > 0) {
                    res = res * x % MOD;
                }
                x = x * x % MOD;
            }
            return res;
        }
    }


}
