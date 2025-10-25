package com.oycm.algorithm.a.basic

import scala.collection.mutable

object Solution_7 {

  /**
   * 2461.长度为 K 子数组中的最大和 1553
   * https://leetcode.cn/problems/maximum-sum-of-distinct-subarrays-with-length-k/
   * 求 连续子数组长度为 k，且子数组中元素各不相同的最大子数组和，若没有则返回 0
   *
   * 2841 的变体，相当于 m = k
   *
   * @param nums 整数数组
   * @param k    子数组长度
   * @return
   */
  def maximumSubarraySum(nums: Array[Int], k: Int): Long = {
    var ans: Long = 0
    var temp: Long = 0
    val map = mutable.Map[Long, Int]()

    for (i <- nums.indices) {
      // 入
      val num = nums(i)
      temp += num
      map(num) = map.getOrElse(num, 0) + 1

      if (i - k + 1 >= 0) {
        // 更新
        if (map.size == k) {
          ans = Math.max(temp, ans)
        }
        // 出
        val leftNum = nums(i - k + 1)
        temp -= leftNum
        map(leftNum) = map(leftNum) - 1
        if (map(leftNum) == 0) {
          map.remove(leftNum)
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumSubarraySum(Array(1, 5, 4, 2, 9, 9, 9), 3) == 15)
    println(maximumSubarraySum(Array(4, 4, 4), 3) == 0)
  }
}
