package com.oycm.algorithm.h.basic;

public class Solution_22 {

    /**
     * 169. <a href="https://leetcode.cn/problems/majority-element/description/">多数元素</a>
     *
     * @param nums
     * @return 在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
     */
    public int majorityElement(int[] nums) {
        /*
        32 * n, 记录 nums 中 为 1 的 bit 大于 n/2 的 次数
         */
        int ans = 0;
        int n = nums.length;

        for (int i = 0, mask = 1; i < 32; i++, mask <<= 1) {
            int bitCnt = 0;
            for (int num : nums) {
                // 1 << 31 时 是负数, 不能用 大于 0 来判断
                if ((mask & num) == mask) {
                    bitCnt++;
                }
                if (bitCnt > n / 2) {
                    ans |= mask;
                    break;
                }
            }
        }

        return ans;
    }

    static class Solution_22_1 {
        public int majorityElement(int[] nums) {
            /*
            擂台赛:
                初始化擂主为 nums[0], 擂主血量为 1；
                挑战者上场，如果挑战者和擂主相同，则擂主血量 +1，否则擂主血量减少 1，如果血量减为 0，则开启一个新的擂主挑战赛
                最后的擂主就是众数
            证明这个过程是对的：众数出现次数记为 a, 其它数出现次数记为 b, a = n - b, 且 a > b
                这个挑战赛的过程，会把数组 nums[i] 和 nums[i+1] 之间分隔成多段，
                除了最后一段的每一段，设 众数出现的次数为 x 次，其它数出现的次数为 y 次，必然有 x <= y，假设 x > y，则不会开启一个新段，因为他还是擂主
                    所以可得 a - x > b - y，设 a' 表示前一段擂台赛后剩余众数数量，b' 表示前一段擂台赛后剩余其它数数量，总会有 a' > b'
                    对于最后一段，由于 a' > b'，众数血量更高，不可能下场
             */
            int ans = nums[0];
            int heap = 0;
            for (int num : nums) {
                if (heap == 0) {
                    ans = num;
                    heap = 1;
                } else {
                    heap += num == ans ? 1 : -1;
                }
            }
            return ans;
        }
    }

}
