package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_10 {

  /**
   * 2815. 数组中的最大数对和 1295
   * https://leetcode.cn/problems/max-pair-sum-in-an-array/description/
   *
   * 数 数位上的最大值：123 -> 3, 192 ->9
   *
   * @param nums 整数数组
   * @return 找出 和 最大 的一对，且这两个数 数位上最大的数字 相等
   */
  def maxSum(nums: Array[Int]): Int = {
    /*
    题解：取模找出数位的最大值
    */
    var ans = -1
    val cnt = Array.fill(10)(Int.MinValue)
    for (num <- nums) {
      var maxD = 0
      var x = num
      while (x > 0) {
        maxD = Math.max(maxD, x % 10)
        x /= 10
      }
      // 更新最大值 最小值和 max(nums(i)) 小于 -1
      ans = Math.max(ans, num + cnt(maxD))
      // 更新相同数位的最大值
      cnt(maxD) = Math.max(cnt(maxD), num)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxSum(Array(51, 71, 17, 24, 42)))
    println(maxSum(Array(1, 2, 3, 4)))
  }

}
