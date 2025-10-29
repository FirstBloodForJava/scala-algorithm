package com.oycm.algorithm.abb.basic

object Solution_1 {

  /**
   * 209.长度最小的子数组
   * https://leetcode.cn/problems/minimum-size-subarray-sum/
   *
   * @param target 目标值
   * @param nums   正整数数组
   * @return 返回 大于等于 target 的最小子数组长度
   */
  def minSubArrayLen(target: Int, nums: Array[Int]): Int = {
    var ans = Int.MaxValue

    var temp = 0
    var l = 0
    for (r <- nums.indices) {
      temp += nums(r)
      while (temp >= target) {
        ans = Math.min(ans, r - l + 1)
        temp -= nums(l)
        l += 1
      }
    }
    if (ans == Int.MaxValue) {
      ans = 0
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minSubArrayLen(7, Array(2, 3, 1, 2, 4, 3)) == 2)
    println(minSubArrayLen(4, Array(1, 4, 4)) == 1)
    println(minSubArrayLen(10, Array(1, 1, 1, 1, 1, 1, 1, 1)) == 0)
  }
}
