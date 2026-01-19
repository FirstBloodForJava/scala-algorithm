package com.oycm.algorithm.e.enum_right_log_left.advance

object Solution_5 {
  /**
   * 2874. 有序三元组中的最大值 II 1583
   * https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii/description/
   *
   * 下标三元组值定义： i < j < k，(nums[i] - nums[j]) * nums[k]
   *
   * @param nums 整数数组
   * @return
   */
  def maximumTripletValue(nums: Array[Int]): Long = {
    /*
    题解：枚举 k
    维护 nums(i) - nums(j) 的最大值，遍历 k
    同时维护 nums(i) 的最大值

    */
    var ans = 0L

    var maxDiff = 0
    var preI = 0
    for (num <- nums) {
      ans = Math.max(ans, maxDiff.toLong * num)
      maxDiff = Math.max(maxDiff, preI - num)
      preI = Math.max(preI, num)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumTripletValue(Array(12, 6, 1, 2, 7)))
    println(maximumTripletValue(Array(1, 10, 3, 4, 19)))
    println(maximumTripletValue(Array(1, 2, 3)))
    println(maximumTripletValue(Array(8, 6, 3, 13, 2, 12, 19, 5, 19, 6, 10, 11, 9)))
  }
}
