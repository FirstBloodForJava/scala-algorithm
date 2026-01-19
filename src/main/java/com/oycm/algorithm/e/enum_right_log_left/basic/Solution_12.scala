package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_12 {

  /**
   * 1679. K 和数对的最大数目 1346
   * https://leetcode.cn/problems/max-number-of-k-sum-pairs/description/
   *
   * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
   *
   * @param nums 正整数数组
   * @param k    正整数
   * @return 移除和为 k 的两个整数，求可对数组执行的最大操作数
   */
  def maxOperations(nums: Array[Int], k: Int): Int = {
    /*
    用 map 记录 nums(i) 值和出现次数
    枚举 nums(i) 先查看 set 中 是否存在 k - nums(i) 元素，存在则次数减1
     */
    var ans = 0
    val map = scala.collection.mutable.Map[Int, Int]()
    for (num <- nums) {
      val c = map.getOrElse(k - num, 0)
      if (c > 0) {
        map(k - num) -= 1
        ans += 1
      } else {
        map(num) = map.getOrElse(num, 0) + 1
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxOperations(Array(1, 2, 3, 4), 5))
    println(maxOperations(Array(3, 1, 3, 4, 3), 6))
    println(maxOperations(Array(4, 4, 1, 3, 1, 3, 2, 2, 5, 5, 1, 5, 2, 1, 2, 3, 5, 4), 2))
  }

}
