package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_17 {

  /**
   * 2364. 统计坏数对的数目 1622
   * https://leetcode.cn/problems/count-number-of-bad-pairs
   *
   * 如果 i < j 且 j - i != nums[j] - nums[i], 称 (i, j) 为 坏数对
   *
   * @param nums 整数数组
   * @return 求 nums 中 坏数对 的数
   */
  def countBadPairs(nums: Array[Int]): Long = {
    /*
    怎么找 j - i = nums(j) - nums(i) 的数量？
    nums(j) - j = nums(i) - i 的数量
    n * (n - 1) / 2 - 好数对 的数量就是答案

     */
    val n = nums.length
    val cnt = n.toLong * (n - 1) / 2
    // 好数对
    var ans = 0L
    val map = scala.collection.mutable.Map[Int, Int]()
    for (j <- nums.indices) {
      // 枚举 j，查找 i
      ans += map.getOrElse(nums(j) - j, 0)
      map(nums(j) - j) = map.getOrElse(nums(j) - j, 0) + 1
    }

    cnt - ans
  }

  def main(args: Array[String]): Unit = {
    println(countBadPairs(Array(4, 1, 3, 3)))
    println(countBadPairs(Array(1, 2, 3, 4, 5)))
    println(countBadPairs(Array(1)))
  }

}
