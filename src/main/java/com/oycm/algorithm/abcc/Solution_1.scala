package com.oycm.algorithm.abcc

import scala.collection.mutable

object Solution_1 {

  /**
   * 930.和相同的二元子数组 1592
   * https://leetcode.cn/problems/binary-subarrays-with-sum/
   * nums 长度为 n 的二元数组，nums(i) 值为 0 或 1
   * goal 目标值
   * 求 nums 连续子数组和为 goal 的 个数
   *
   * @param nums 二元数组，nums(i) 值为 0 或 1
   * @param goal 目标值 [0, n]
   * @return
   */
  def numSubarraysWithSum(nums: Array[Int], goal: Int): Int = {
    /*
     [l, r] 子数组的和，就是子数组中的 1 的个数
     假设以 r 为右端点，[l, r] 子数组的和等于 goal，以 r 为右端点符合要求的子数组 l - l(last_1)(l 左边最后出现的 1 index)
     怎么记录这个值？
     */
    var ans = 0
    val count = Array.fill(2)(0)

    ans
  }

  def prefixSum(nums: Array[Int], goal: Int): Int = {
    /*
    题解：
    前缀和思路：sums (n+1) 表示 nums 数组的前缀和
    [l, r] 数组的和 = sums(r) - sums(l)
    当 goal = sums(r) - sums(l) => sum(l) = sum(r) - goal 历史有多个 sum(l)，则答案就是该次数
    循环中记录历史 sum 的次数
     */
    var ans = 0
    var sum = 0
    val map = mutable.Map[Int, Int]()
    for (num <- nums) {
      // 默认前缀和初始值 0
      map(sum) = map.getOrElse(sum, 0) + 1
      sum += num
      ans += map.getOrElse(sum - goal, 0)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(prefixSum(Array(1, 0, 1, 0, 1), 2))
    println(prefixSum(Array(0, 0, 0, 0, 0), 0))
    println(prefixSum(Array(0, 0, 0, 0, 0), 1))
  }

}
