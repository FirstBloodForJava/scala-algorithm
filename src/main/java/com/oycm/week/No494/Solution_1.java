package com.oycm.week.No494;

public class Solution_1 {

    /**
     * <a href="https://leetcode.cn/problems/construct-uniform-parity-array-i/description/">构造奇偶一致的数组 I</a>
     *
     * @param nums1 包含 互不相同 的整数的数组
     * @return
     */
    public boolean uniformArray(int[] nums1) {
        /*
        按一下规则构建数组 nums2, 每个下标 i, 需要以下两种选择中任意一种
            nums2[i] = nums1[i]
            nums2[i] = nums1[i] - nums1[j], j != i
        是否存在 nums2 全部为 奇数，要么全部为 偶数
         */
        /*
        构建偶数数组, nums1[i] 为奇数, 则需要去早另外一个奇数去匹配, 奇数数量 大于 1 即可
        构建奇数数组, nums1[i] 为偶数, 则需要去找另外一个奇数去匹配, 奇数数量 大于等于 1 即可
        为 0 时 构建偶数即可
        综上所诉, 不管奇数多少数量都可满足条件
         */

        return true;
    }

}
