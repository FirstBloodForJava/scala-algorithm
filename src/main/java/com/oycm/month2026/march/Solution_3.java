package com.oycm.month2026.march;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {


    /**
     * 1545. <a href="https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string/description/">找出第 N 个二进制字符串中的第 K 位</a> 1479
     *
     * @param n
     * @param k
     * @return
     */
    public char findKthBit(int n, int k) {
        /*
        Sn 生成规则如下
            i = 1, S1 = "0"
            i > 1, Si = Si-1 + "1" + reverse(invert(Si-1))
        reverse 表示 反转
        invert 表示 翻转二进制
        返回 Sn 中 第 k 位字符
         */
        List<Integer> pre = new ArrayList<>();
        pre.add(0);
        List<Integer> ans = dfs(1, n, pre);
        return ans.get(k - 1).toString().charAt(0);
    }

    private List<Integer> dfs(int i, int n, List<Integer> pre) {

        if (i == n) {
            return pre;
        } else {
            List<Integer> cur = new ArrayList<>(pre);
            cur.add(1);
            for (int j = pre.size() - 1; j >= 0; j--) {
                cur.add(pre.get(j) ^ 1);
            }
            return dfs(++i, n, cur);
        }
    }

    public char findKthBit1(int n, int k) {
        /*
        题解思路: s1 = 1, sn = 2 * Sn-1 + 1
        Sn + 1 = 2 * (Sn-1 + 1)
        Sn + 1 是 首项为 2, 公比为 2 的等比数列
            得 Sn = 2^n - 1
            Sn-1 = 2^(n-1) - 1, 说明 Sn 的左半是 第 1 个字符到第 2^(n-1) -1 个字符; 正中间是第 2^(n-1) 个字符, 右半是第 2^(n-1) + 1 个 到 2^n - 1 个
        分类讨论:
            如果 k < 2^(n-1), 那么第 k 个字符位于 Sn 的 左半, 问题转换成 Sn-1 的第 k 个字符; 可以使用递归解决
            如果 k > 2^(n-1), 那么第 k 个字符位于 Sn 的 右半, 问题转换成 Sn-1 反转后第 k - 2^(n-1) 个字符, 反转前第 2^(n-1) - (k - 2^(n-1)) (总数 + 1 - 第 k 个) = 2^n - k, 然后字符再翻转
        递归边界:
            如果 n == 1, 返回唯一字符 0;
            如果 n == 2^(n-1), 返回 Sn 正中间字符 1;
         */
        if (n == 1) {
            return '0';
        }
        if (k == 1 << (n - 1)) {
            return '1';
        }
        if (k < 1 << (n - 1)) {
            return findKthBit1(n - 1, k);
        }
        char res = findKthBit1(n - 1, (1 << n) - k);
        return (char) (res ^ 1);
    }

    public char findKthBit2(int n, int k) {
        /*
        题解思路: 数学公式
        只看奇数位（下标从 1 开始）
        0
        0 1 1
        011 1 001
        011 1 001 1 011 0 001
        0
        01
        0101, 01 变换过程 01 -> 10 -> 01
        01010101 0101 变换过程 0101 -> 1010 -> 0101
        技术位 从 010101 交替显示 0 1
        1, 3, 5, 7
        0, 1, 0, 1
        k = 1, 3, 5, 7 ..., k' = k/2 = 0, 1, 2, 3 ...
        k 是奇数 '0' + (k/2) % 2

        只看偶数位, i >= 2, Si 偶数位字符都发源于 中间的 1, 即 2, 4, 8, 16 ... 的字符 1
        S1 = 0
        S2 = 0 1 1
        S3 = 011 1 001
        S4 = 011 1 001 1 011 0 001

        以 S2 中间字符为例, 2
        S2 = 1 2
        S3 = 10 2, 6 => 8 - 2
        S4 = 1010 2,6,10,14 = 1,3, 5,7 => 16 - 2,6
        S5 = 10101010 2,6,10,14, 18,22,26,30 => 32 - 2,6,10,14
        ...
        以 S3 中间字符为例, 4
        S3 = 1
        S4 = 10, 4, 12  => 16 - 4
        S5 = 1010, 4,12, 20,28  => 32 - 4,12
        ...
        Sn-1 第 k 个字符，就是 Sn 的第 2^n - k 个字符
        2^n - k 字符就是 第 k 个字符翻转得到的
        2^n - k 和 k 的尾零个数一样 10000 - 100 = 1100, 可以根据 k 的尾零个数，找到 k 发源于那个 Si, 这个 Si 遵循一下规律
        偶数下标有一个规律 t * lowbit = k, t = 1, 3, 5, 7, t/2 = 0, 1, 2, 3 偶数是 1, 奇数是 0

         */

        if (k % 2 != 0) {
            return (char) ('0' + k / 2 % 2);
        }
        // lowbit = k * -k
        k /= k & -k;
        return (char) ('1' - k / 2 % 2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_3().findKthBit(3, 1));


        int base = 2;
        for (int i = 1; i < 10; i += 2) {
            System.out.println(Integer.toBinaryString(base * i));
        }
        System.out.println(32 & -32);
    }


}
