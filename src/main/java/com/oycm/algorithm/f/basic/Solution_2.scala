package com.oycm.algorithm.f.basic

object Solution_2 {

  /**
   * 3427. 变长子数组求和 1216
   * https://leetcode.cn/problems/sum-of-variable-length-subarrays/description/
   *
   * nums(i) 有以下定义：
   *  - start = max(0, i - nums(i))
   *  - 求每个 nums(i) nums [start, i] 的和
   *
   * @param nums 整数数组
   * @return
   */
  def subarraySum(nums: Array[Int]): Int = {
    /*
    计算 sums 时的同时计算 连续子数组和
    */
    val n = nums.length
    val sums = Array.fill(n + 1)(0)
    var sum = 0

    for (i <- nums.indices) {
      sums(i + 1) = sums(i) + nums(i)
      val start = Math.max(0 , i - nums(i))
      sum += sums(i + 1) - sums(start)
    }

    sum
  }

  /**
   * todo: 差分数组解法
   */

}
