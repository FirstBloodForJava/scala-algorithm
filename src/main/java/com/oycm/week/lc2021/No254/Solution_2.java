package com.oycm.week.lc2021.No254;

public class Solution_2 {

    /**
     * 1968. 构造元素不等于两相邻元素平均值的数组
     * 1968. <a href="https://leetcode.cn/problems/array-with-elements-not-equal-to-average-of-neighbors/description/">构造元素不等于两相邻元素平均值的数组</a> 1499
     *
     * @param nums
     * @return
     */
    public int[] rearrangeArray(int[] nums) {
        /*
        给你一个 下标从 0 开始 的数组 nums ，数组由若干 互不相同的 整数组成。
        你打算重新排列数组中的元素以满足：重排后，数组中的每个元素都 不等于 其两侧相邻元素的 平均值。
        更公式化的说法是，重新排列的数组应当满足这一属性：对于范围 1 <= i < nums.length - 1 中的每个 i ，(nums[i-1] + nums[i+1]) / 2 不等于 nums[i] 均成立。
        返回满足题意的任一重排结果。
         */
        /*
        nums[i] - nums[i-1] = nums[i+1] - nums[i] 前后两个数差相等时，nums[i] 等于前后两个元素的平均值。
        [1, 3, 4, 5] 如果 nums[i] = (nums[i-1] + nums[i+1]) / 2, 分类讨论交换情况：
            如果 i = 1，只要不是 nums[i-1] 和 nums[i+1] 交换即可；i 和 i+1 交换值；
            如果 i = 2，3，nums[i] 和 nums[i-2] 交换，由于交换后 (nums[i-2] + nums[i]) / 2 不变，所以不会影响 nums[i-1] 左右情况，
                当 i = 2，由于元素各不相同，交换后 nums[i] 也符合要求。
                当 i = 3，会对 nums[i-2] 造成影响吗？不会
                    nums[i-2] != (nums[i-3] + nums[i-1]) / 2
                    nums[i] = nums[i-1] + nums[i+1] / 2
                    nums[i-2] 变成 nums[i]，由于没有相同元素，所以 (nums[i-3] + nums[i-1]) / 2 不会等于 nums[i]，如果相等了，则 说明 nums[i-3] == nums[i+1]
                    nums[i] 变成 nums[i-2]，由于没有相同元素，所以 nums[i] != nums[i-1] + nums[i+1] / 2 也成立。
                当 i > 3 时，nums[i] 和 nums[i-2] 交换，虽然不会影响 nums[i-1] 左右情况，但是 nums[i-2] 修改后，影响 nums[i-3] 右边的情况，需要判断
                    nums[i] - nums[i-3] 和 nums[i-3] - nums[i-4] 是否相等，如果不相等，则可以安装第二种情况交换；
                    否则将 nums[i+1] 和 nums[i-2] 交换，交换后，nums[i-3] 不影响，
                    需要证明交换后 nums[i-2]，nums[i-1] 不会等于左右平均值：
                    已知：
                        nums[i-2] != (nums[i-3] + nums[i-1]) / 2
                        nums[i-1] != (nums[i-2] + nums[i]) / 2
                        nums[i] = (nums[i-1] + nums[i+1]) / 2，nums[i] 在 (nums[i-1], nums[i+1]) 中间
                        nums[i-3] = (nums[i-4] + nums[i]) / 2，两种情况，三者是升序或倒序关系
                    如果 nums[i-3] < nums[i]，那么 nums[i-3] + nums[i-1] 的范围是 (nums[i]-1, nums[i-1]) 和 nums[i+1] 完全没有交集
                        nums[i-1] < nums[i+1]，平均值小于 nums[i]-1，恒小于 nums[i+1]；
                        nums[i-1] > nums[i+1]，平均值大于 nums[i]-1，恒大于 nums[i+1]；
                    所以 nums[i-2] 和 nums[i+1] 交换后， nums[i-2] != (nums[i-3] + nums[i-1]) / 2 成立。
                    如果 nums[i-1] < nums[i+1]，交换后 nums[i-1] 平均值为 nums[i]，一直大于 nums[i-1]
                    如果 nums[i-1] > nums[i+1]，交换后 nums[i-1] 平均值为 nums[i]，一直小于 nums[i-1]
                    综述，按这种逻辑交换，不会影响前面正确的平均值
        注意，还需要看 nums[i-2] 交换为 nums[i] 时，是否对 nums[n-3] 有影响
         */
        int n = nums.length;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] == nums[i] - nums[i - 1]) {
                if (i > 3) {
                    /*
                    nums[i] 和 nums[i-2] 交换，要判断是否会对 nums[i-3] 影响（不会对 nums[i-1] 有影响）；
                    nums[i+1] nums[i-2] 交换，要判断是否会对 nums[i-1] 有
                     */
                    if (nums[i - 3] - nums[i - 4] == nums[i] - nums[i - 3]) {
                        swap(nums, i + 1, i - 2);
                    } else {
                        swap(nums, i, i - 2);
                    }
                } else if (i == 1) {
                    swap(nums, i, i + 1);
                } else {
                    swap(nums, i, i - 2);
                }
            }

        }

        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
