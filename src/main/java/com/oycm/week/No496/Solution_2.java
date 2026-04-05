package com.oycm.week.No496;

import java.util.*;

public class Solution_2 {

    /**
     * 3890. <a href="https://leetcode.cn/problems/integers-with-multiple-sum-of-two-cubes/description/">可由多种立方和构造的整数</a>
     *
     * @param n
     * @return
     */
    public List<Integer> findGoodIntegers(int n) {
        /*
        当存在 至少 两组不同的整数对 (a, b) 满足以下条件时，整数 x 被称为 好整数
            a 和 b 是正整数;
            a <= b
            x = a^3 + b^3
         */
        /*
        a <= b, b 最大可取 x 的开立方
        [0, cbrt] 两重循环，用 hash 表记录 和 的次数
        另外思路: 也可以初始化一个符合条件的数组，然后对结果进行二分查找
        ~n = -(n+1) 一个整数的相反数等于其按位取反再加一
        -n = ~n + 1 => -(n + 1) = ~n
         */
        int max = (int) Math.cbrt(n);
        Map<Integer, Integer> map = new HashMap<>();
        for (int a = 0; a <= max; a++) {
            int aCube = a * a * a;
            for (int b = a; b <= max; b++) {
                int bCube = b * b * b;
                if (aCube + bCube > n) {
                    break;
                }
                map.merge(aCube + bCube, 1, Integer::sum);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
            if (kv.getValue() >= 2) {
                ans.add(kv.getKey());
            }
        }
        Collections.sort(ans);

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_2().findGoodIntegers(4104));
    }

}
