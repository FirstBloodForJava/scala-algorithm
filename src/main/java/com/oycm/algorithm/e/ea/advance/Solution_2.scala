package com.oycm.algorithm.e.ea.advance

object Solution_2 {

  /**
   * 3185. 构成整天的下标对数目 II 1385
   * https://leetcode.cn/problems/count-pairs-that-form-a-complete-day-ii/description/
   *
   * @param hours 整数数组 表示以 小时 为单位的时间
   * @return i < j hours[i] + hours[j] 是 24 的整数被
   */
  def countCompleteDayPairs(hours: Array[Int]): Long = {
    /*
    可以对 hours(i) % 24 的结果进行计数 hours(j) % 24 + hours(i) % 24 == 24 这个 i 和 j 就符合要求

    时间复杂度 O(n)
    空间复杂度 O(24)
    */
    var ans = 0L
    val cnt = Array.fill(24)(0)
    for (num <- hours) {
      val mod = num % 24
      // 先查找历史，更新答案，在记录次数
      ans += cnt((24 - mod) % 24)
      cnt(mod) += 1
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(countCompleteDayPairs(Array(12, 12, 30, 24, 24)))
    println(countCompleteDayPairs(Array(72, 48, 24, 3)))
  }
}
