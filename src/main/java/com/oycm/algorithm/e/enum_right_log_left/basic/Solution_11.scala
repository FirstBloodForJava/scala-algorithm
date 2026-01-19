package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_11 {

  /**
   * 2342. 数位和相等数对的最大和 1309
   * https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits/description/
   *
   * 找出 i != j, nums(i) 数位和 与 nums(j) 数位和 相等的 最大值，没有则返回 -1
   *
   * @param nums 正整数数组
   * @return
   */
  def maximumSum(nums: Array[Int]): Int = {
    /*
    枚举 nums(i) 先查询是否存在数位和，存在则更新 ans 的最大值，并更新当前 数位和 对应数值的最大值
    枚举 nums(i) 的数位和，记录当前值
     */
    val sums = Array.fill(82)(0)
    var ans = -1
    // 找数位的过程
    for (i <- nums.indices) {
      var k = nums(i)
      var sum = 0
      while (k > 0) {
        sum += k % 10
        k /= 10
      }
      if (sums(sum) > 0) {
        ans = Math.max(ans, sums(sum) + nums(i))
        sums(sum) = Math.max(sums(sum), nums(i))
      } else {
        sums(sum) = nums(i)
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumSum(Array(18, 43, 36, 13, 7)))
    println(maximumSum(Array(10, 12, 19, 14)))
  }

}
