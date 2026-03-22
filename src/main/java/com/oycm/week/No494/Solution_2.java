package com.oycm.week.No494;

public class Solution_2 {

    /**
     * <a href="https://leetcode.cn/problems/construct-uniform-parity-array-ii/">构造奇偶一致的数组 II</a>
     *
     * @param nums1
     * @return
     */
    public boolean uniformArray(int[] nums1) {
        /*
        按一下规则构建数组 nums2, 每个下标 i, 需要以下两种选择中任意一种
            nums2[i] = nums1[i]
            nums2[i] = nums1[i] - nums1[j], j != i; 且满足 nums1[i] - nums1[j] >= 1
        是否存在 nums2 全部为 奇数，要么全部为 偶数
         */
        /*
        由于 第二种限制, 最小值 nums1[i] 决定了 nums2 的奇偶性
        确定 nums1 的最小值, 由于 数组中元素  互不相同
            min 是奇数, nums1[i] 为偶数, nums1[i] - min 即可构建 全是奇数数组
            min 是偶数, nums1[i] 为奇数, 奇数 - 较小的奇数, 较小的奇数无法找人, 除非 nums1 全是偶数
        min 是奇数, 肯定能构建
        min 是偶数, 则需要全部都是偶数
         */
        int min = Integer.MAX_VALUE;
        boolean even = true;
        for (int x : nums1) {
            min = Math.min(min, x);
            if (even && x % 2 == 1) {
                even = false;
            }
        }

        return even || min % 2 == 1;
    }

    static class Solution_2_1 {
        public boolean uniformArray(int[] nums1) {
            /*
            按一下规则构建数组 nums2, 每个下标 i, 需要以下两种选择中任意一种
                nums2[i] = nums1[i]
                nums2[i] = nums1[i] - nums1[j], j != i; 且满足 nums1[i] - nums1[j] >= 1
            是否存在 nums2 全部为 奇数，要么全部为 偶数
             */
            /*
            由于 第二种限制, 最小值 nums1[i] 决定了 nums2 的奇偶性
            确定 nums1 的最小值, 由于 数组中元素  互不相同
                min 是奇数, nums1[i] 为偶数, nums1[i] - min 即可构建 全是奇数数组
                min 是偶数, nums1[i] 为奇数, 奇数 - 较小的奇数, 较小的奇数无法找人, 除非 nums1 全是偶数

            题解计算优化, 找最小的偶数, 最小的奇数
            前面的计算等价: 没有奇数, 或 最小偶数 > 最小奇数
             */
            int[] ints = {Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int x : nums1) {
                ints[x & 1] = Math.min(ints[x & 1], x);
            }

            return ints[1] == Integer.MAX_VALUE || ints[0] > ints[1];
        }
    }

}
