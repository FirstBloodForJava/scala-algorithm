package com.oycm.algorithm.a.basic

import scala.collection.mutable

object Solution_6 {

  /**
   * 2841.几乎唯一子数组的最大和 1546
   * https://leetcode.cn/problems/maximum-sum-of-almost-unique-subarray/description/
   *
   * “几乎唯一” 子数组： nums 的一个子数组有至少 m 个互不相同的元素
   *
   * @param lists 整数数组
   * @param m    m 个不同的元素
   * @param k    子数组长度
   * @return 返回 nums 中长度为 k 的 “几乎唯一” 子数组的 最大和，如果没用，则返回 0
   */
  def maxSum(lists: List[Int], m: Int, k: Int): Long = {
    val nums = lists.toArray
    // 由于 List 是链表结构，在 list 过大时，超过一次获取 nums(i) 会导致时间超出
    var ans: Long = 0
    // 没有互不相同元素要求，则求的就是连续子数组的最大值，更新答案的时候，判断子数组是否符合几乎唯一条件
    var temp: Long = 0

    // 标记是否符合条件
    val map = mutable.Map[Int, Int]()
    for (i <- nums.indices) {
      val num = nums(i)
      // 入
      temp += num
      // 更新重复元素出现次数
      map(num) = map.getOrElse(num, 0) + 1
      if (i - k + 1 >= 0) {
        // 更新
        if (map.size >= m) {
          ans = Math.max(ans, temp)
        }
        // 出
        val left = nums(i - k + 1)
        temp -= left
        map(left) = map(left) - 1
        if (map(left) == 0) {
          map.remove(left)
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxSum(List(2, 6, 7, 3, 1, 7), 3, 4) == 18)
    println(maxSum(List(5, 9, 9, 2, 4, 5, 4), 1, 3) == 23)
    println(maxSum(List(1, 2, 1, 2, 1, 2, 1), 3, 3) == 0)
  }

}
