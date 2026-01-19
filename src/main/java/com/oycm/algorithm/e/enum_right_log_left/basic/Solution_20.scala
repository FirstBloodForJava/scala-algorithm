package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_20 {

  /**
   * 3584. 子序列首尾元素的最大乘积 1763
   * https://leetcode.cn/problems/maximum-product-of-first-and-last-elements-of-a-subsequence/description/
   *
   * @param nums 整数数组
   * @param m    子序列长度
   * @return 求 长为 m 的子序列 首尾元素 乘积的 最大值
   */
  def maximumProduct(nums: Array[Int], m: Int): Long = {
    /*
    枚举 nums(j) 的 过程中，假设 j 是尾部元素，j 之前的中间元素都不丢弃，头部元素为 nums(j-m+1)
    我只要知道 nums [0, j-m+1] 的最大值和最小值 不断和 nums(j) 相乘，然后求最大
    min = nums(0), max = nums(0)
    当 i = j - m + 1 >= 0 时，更新答案, 同时 和 nums(j+1) 相比 更新 min 和 max

    暴力过程也是
      [0,m-1], 不丢弃
      [0, m] 丢弃从 [0,1] 中选
      [0, m+1] 丢弃从 [0,2] 中选
      ...
    时间复杂度 O(n - m)
    空间复杂度 O(1)
     */
    var ans = Long.MinValue
    var min = Long.MaxValue
    var max = Long.MinValue
    for (j <- m - 1 until nums.length) {
      val i = j - m + 1
      // 更新头
      min = Math.min(min, nums(i))
      max = Math.max(max, nums(i))
      // 枚举尾，记录答案
      if (nums(j) >= 0) {
        ans = Math.max(ans, nums(j) * max)
      } else {
        ans = Math.max(ans, nums(j) * min)
      }

    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumProduct(Array(-1, -9, 2, 3, -2, -3, 1), 1))
    println(maximumProduct(Array(1, 3, -5, 5, 6, -4), 3))
    println(maximumProduct(Array(2, -1, 2, -6, 5, 2, -5, 7), 2))
  }

}
