package com.oycm.hot100.trick;

public class Solution_97 {

    /**
     * 169. <a href="https://leetcode.cn/problems/majority-element/description/">多数元素</a>
     *
     * @param nums
     * @return 在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
     */
    public int majorityElement(int[] nums) {
        /*
        打擂台，初始化 ans = nums[0]，擂主血量为 0；
        遍历数组：
            如果擂主血量为 0，则下一位上台者为擂主，且血量 +1；
            否则，判断当前选水是否就是擂主，不是则血量 -1；是则血量 +1；
            最后 擂主就是出现次数大于 n/2 次数的元素
         */
        int ans = nums[0];
        int heap = 0;
        for (int x : nums) {
            if (heap == 0) {
                ans = x;
                heap = 1;
            } else {
                heap += ans == x ? 1 : -1;
            }
        }
        return ans;
    }

}
