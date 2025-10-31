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
     题解：解决记录一个最左边出现 1 的问题
     [l, r] 子数组的和，就是子数组中的 1 的个数
     假设以 r 为右端点，[l, r] 子数组的和等于 goal，以 r 为右端点符合要求的子数组 l - l(last_1)(l 左边最后出现的 1 index)
     怎么记录这个值？
     [l1, r1] 第一次符合要求，则 ans = l1 - 0 + 1
     [l2, r2] 第一个符合要求，则 ans = l2 - l1（l1不符合要求）
     sum1 l1 记录慢的 1 的 index
     sum2 l2 记录快的 1 的 index l2 - l1 就是每次的答案
     */
    var ans = 0
    var sum1 = 0
    var l1 = 0
    var sum2 = 0
    var l2 = 0
    for (r <- nums.indices) {
      sum1 += nums(r)
      // 第一次符合时开始查找：以 r 为右端点第一个 = 1 的 l1 值
      while (l1 <= r && sum1 > goal) {
        sum1 -= nums(l1)
        l1 += 1
      }

      sum2 += nums(r)
      while (l2 <= r && sum2 >= goal) {
        sum2 -= nums(l2)
        l2 += 1
      }
      // k = 2， [0, 0, 1, 1, 0, 1]
      ans += l2 - l1
    }
    ans
  }

  def transform(nums: Array[Int], goal: Int): Int = {
    /*
    题解：问题转换
    问题转换成求：连续子数组和小于等于 k 的个数 - 连续子数组和小于等于 (k-1) 的个数
    固定 r，向右移动 r 的过程中不断移动 l
    */
    def count(nums: Array[Int], k: Int): Int = {
      var ans = 0
      // [l, r] 子数组和小于等于 k，以 r 为右端，则 ans += r - l + 1
      // 继续下一轮 [l, r+1] 如果大于 k，则 l 向右移动
      var l = 0
      var sum = 0
      for (r <- nums.indices) {
        sum += nums(r)
        while (sum > k && l <= r) {
          sum -= nums(l)
          l += 1
        }
        ans += r - l + 1
      }
      ans
    }

    count(nums, goal) - count(nums, goal - 1)
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
    println(prefixSum(Array(1, 0, 1, 0, 1), 2) == 4)
    println(prefixSum(Array(0, 0, 0, 0, 0), 0) == 15)
    println(prefixSum(Array(0, 0, 0, 0, 0), 1) == 0)
    println(prefixSum(Array(0, 0, 1, 1, 0, 1), 2) == 7)

    println(numSubarraysWithSum(Array(0, 0, 1, 1, 0, 1), 2) == 7)
    println(numSubarraysWithSum(Array(1, 0, 1, 0, 1), 2) == 4)
    println(numSubarraysWithSum(Array(0, 0, 0, 0, 0), 0) == 15)
    println(numSubarraysWithSum(Array(0, 0, 0, 0, 0), 1) == 0)

//    println(transform(Array(0, 0, 1, 1, 0, 1), 2) == 7)
//    println(transform(Array(1, 0, 1, 0, 1), 2) == 4)
//    println(transform(Array(0, 0, 0, 0, 0), 1) == 0)
    println(transform(Array(0, 0, 0, 0, 0), 0) == 15)
  }

}
