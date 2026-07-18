package com.oycm.month2026.july;

public class Solution_18 {

    /**
     * 1979. <a href="https://leetcode.cn/problems/find-greatest-common-divisor-of-array/description/">找出数组的最大公约数</a> 1184
     *
     * @param nums
     * @return
     */
    public int findGCD(int[] nums) {
        /*
        题解：减少比较次数
        两个一组比较：
            如果 x < y，x 不可能是最大值，y 不可能是最小值，x 只需要和最小值比较，y 只需要和最大值比较；
            如果 x > y，x 不可能是最小值，y 不可能是最大值，x 只需要和最小值比较，y 只需要和最小值比较；
            如果 x = y，可以并入上述任意一种情况比较
        这种方式比较，两两一组，只需要比较 3 次，直接比较，需要比较 4 次
         */
        int n = nums.length;
        int mn = nums[0];
        int mx = mn;
        if (n % 2 == 0) {
            // 偶数长度
            if (nums[0] < nums[1]) {
                mx = nums[1];
            } else {
                mn = nums[1];
            }
        }
        for (int i = 2 - n % 2; i < n; i += 2) {
            int x = nums[i];
            int y = nums[i + 1];
            if (x > y) {
                x = nums[i + 1];
                y = nums[i];
            }
            // 规定 x <= y
            if (x < mn) {
                mn = x;
            }
            if (y > mx) {
                mx = y;
            }
        }

        return gcd(mn, mx);
    }

    public int gcd(int a, int b) {
        while (a != 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

}
