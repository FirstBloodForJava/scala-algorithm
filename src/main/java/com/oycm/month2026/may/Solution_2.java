package com.oycm.month2026.may;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_2 {

    /**
     * 788. <a href="https://leetcode.cn/problems/rotated-digits/description/">旋转数字</a> 1397
     *
     * @param n
     * @return
     */
    public int rotatedDigits(int n) {
        /*
        好数：如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。
        如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。
            0, 1, 和 8 被旋转后仍然是它们自己；
            2 和 5 可以互相旋转成对方，6 和 9 同理；
            除了这些以外其他的数字旋转以后都不再是有效的数字。
         */
        /*
        预处理有哪些好数，进行二分查找
         */
        init();
        int idx = Collections.binarySearch(goods, n);
        if (idx < 0) {
            idx = ~idx - 1;
        }
        return idx + 1;
    }

    private static boolean initialized = false;
    public static List<Integer> goods = new ArrayList<>();

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int i = 1; i <= 10000; i++) {
            if (isGood(i)) {
                goods.add(i);
            }
        }
    }
    private boolean isGood(int x) {
        int temp = x;
        int spin = 0;
        int k = 1;
        while (temp > 0) {
            int g = temp % 10;
            if (g == 2) {
                g = 5;
            } else if (g == 5) {
                g = 2;
            } else if (g == 6) {
                g = 9;
            } else if (g == 9) {
                g = 6;
            } else if (g == 3 || g == 4 || g == 7) {
                return false;
            }
            spin += k * g;
            k *= 10;
            temp /= 10;
        }
        return x != spin;
    }

}
