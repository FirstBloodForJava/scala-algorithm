package com.oycm.algorithm.bb

object Solution_6 {

  /**
   * LCP 18. 早餐组合
   * https://leetcode.cn/problems/2vYnGI/description/
   *
   * 选购 一份主食 和 一份饮料 不超过 x 元的方案数
   *
   * @param staple 整型数组, 表示 每种主食的价格
   * @param drinks 整型数组, 表示 每种饮料的价格
   * @param x      花费
   * @return
   */
  def breakfastNumber(staple: Array[Int], drinks: Array[Int], x: Int): Int = {
    /*
    排序后，可使用双指针 或 二分来做

    staple n -> 0 和 drinks 0 -> m

    时间复杂度 O(n log n) 在排序

    */
    var ans = 0L
    val s1 = staple.sorted
    val d1 = drinks.sorted
    val m = drinks.length
    var l = 0
    for (i <- staple.length - 1 to 0 by -1) {
      while (l < m && d1(l) + s1(i) <= x) {
        l += 1
      }
      ans += l
    }

    (ans % 1000000007).toInt
  }

  def main(args: Array[String]): Unit = {
    println(breakfastNumber(Array(10, 20, 5), Array(5, 5, 2), 15))
    println(breakfastNumber(Array(2, 1, 1), Array(8, 9, 5, 1), 9))
  }

}
