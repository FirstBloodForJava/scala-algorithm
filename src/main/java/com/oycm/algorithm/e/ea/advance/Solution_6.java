package com.oycm.algorithm.e.ea.advance;


import java.util.HashMap;
import java.util.Map;

public class Solution_6 {

    /**
     * 1497. <a href="https://leetcode.cn/problems/check-if-array-pairs-are-divisible-by-k/description/">检查数组对是否可以被 k 整除</a> 1787
     *
     * @param arr
     * @param k
     * @return arr 长度 n 是偶数，将数组恰好分成 n/2 对，每对数字和能够被 k 整除，存在返回 true，否则返回 false
     */
    public boolean canArrange(int[] arr, int k) {
        /*
        (arr[i] + arr[j]) % k == 0
        (arr[i] % k + arr[j] % k) % k == 0, 两种情况
            余数 > 0, 则 arr[i] % k + arr[j] % k == k
            余数 == 0, 则 arr[i] % k == arr[j] % k == 0
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            // num 可能小于 0, 余数取正
            int mod = (num % k + k) % k;
            int left = (k - mod) % k;
            if (map.containsKey(left)) {
                map.merge(left, -1, Integer::sum);
                if (map.get(left) == 0) {
                    map.remove(left);
                }
            } else {
                map.merge(mod, 1, Integer::sum);
            }
        }

        return map.isEmpty();
    }

    public boolean arrayHandle(int[] arr, int k) {
        /*
        (arr[i] + arr[j]) % k == 0
        (arr[i] % k + arr[j] % k) % k == 0, 两种情况
            余数 > 0, 则 arr[i] % k + arr[j] % k == k
            余数 == 0, 则 arr[i] % k == arr[j] % k == 0
         */
        int[] cnt = new int[k];
        for (int num : arr) {
            cnt[(num % k + k) % k]++;
        }
        // [1, k-1] cnt[l] == cnt[r] 且 l + r == k
        for (int i = 1; i < k; i++) {
            if (cnt[i] != cnt[k - i]) {
                return false;
            }
        }

        return cnt[0] % 2 == 0;
    }


}
