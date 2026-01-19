package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_7 {

  /**
   * 2016. 增量元素之间的最大差值 1246
   * https://leetcode.cn/problems/maximum-difference-between-increasing-elements/description/
   *
   * 满足 0 <= i < j < n 且 nums[i] < nums[j], nums[j] - nums[i] 的 最大差值
   *
   * 不存在，则返回 -1 。数组满足 nums(i) > nums(i+1)
   *
   * @param nums
   * @return
   */
  def maximumDifference(nums: Array[Int]): Int = {
    /*
    题解思路：从左到右枚举卖出价格，卖出的过程中，不断维护 preMin 不断计算 x - preMin 的最大值
    */
    var ans = 0
    var preMin = Int.MaxValue
    for (num <- nums) {
      ans = Math.max(ans, num - preMin)
      preMin = Math.min(preMin, num)
    }
    // [1, 1] 这种情况
    if (ans == 0) {
      ans = -1
    }
    ans
  }

}
