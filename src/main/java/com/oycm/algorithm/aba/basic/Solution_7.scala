package com.oycm.algorithm.aba.basic

import scala.collection.mutable

object Solution_7 {


  /**
   * 1695.删除子数组的最大得分 1529
   * https://leetcode.cn/problems/maximum-erasure-value/
   * 得分：删除一个含有 若干不同元素 的子数组，删除子数组的元素之和就是得分。
   * 求只删除一个 子数组 获取的最大得分
   *
   * @param nums 正整数数组
   * @return 求连续不同元素子数组的最大值
   */
  def maximumUniqueSubarray(nums: Array[Int]): Int = {
    var ans = 0
    var l = 0
    var temp = 0
    // 记录元素出现的次数
    val map = mutable.Map[Int,Int]()
    for (r <- nums.indices) {
      val curr = nums(r)
      temp += curr
      map(curr) = map.getOrElse(curr, 0) + 1
      while (map(curr) > 1) {
        map(nums(l)) = map(nums(l)) - 1
        temp -= nums(l)
        l += 1
      }
      ans = Math.max(ans, temp)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumUniqueSubarray(Array(4, 2, 4, 5, 6)) == 17)
    println(maximumUniqueSubarray(Array(1, 2, 4, 5, 6)) == 18)
    println(maximumUniqueSubarray(Array(5, 2, 1, 2, 5, 2, 1, 2, 5)) == 8)
  }
}
