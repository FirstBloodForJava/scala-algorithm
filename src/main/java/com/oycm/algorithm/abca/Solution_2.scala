package com.oycm.algorithm.abca

object Solution_2 {

  /**
   * 3258.统计满足 K 约束的子字符串数量 I 做到 O(n)
   * https://leetcode.cn/problems/count-substrings-that-satisfy-k-constraint-i/description/
   * s 二级制字符串，只包含 0 或 1
   * 二级制字符串满足以下任一条件，认为该字符串满足 K 约束
   *  - 字符串中 0 的数量最多为 k
   *  - 字符串中 1 的数量最多为 k
   *    假设 k = 1，则 101 符合要求，1010 不符合
   *    求 s 的所有满足 k 约束 的子字符串的数量
   *
   * @param s 二级制字符串，只包含 0 或 1
   * @param k 整数
   * @return 求 s 的所有满足 k 约束 的子字符串的数量。
   */
  def countKConstraintSubstrings(s: String, k: Int): Int = {
    var ans = 0

    for (l <- s.indices) {
      val count = Array.fill('2')(0)
      var r = l
      var flag = true
      while (r < s.length && flag) {
        count(s(r)) += 1
        if (count('0') <= k || count('1') <= k) {
          ans += 1
        } else {
          flag = false
        }
        r += 1
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(countKConstraintSubstrings("10101", 1) == 12)
    println(countKConstraintSubstrings("1010101",2) == 25)
    println(countKConstraintSubstrings("11111", 1) == 15)
  }
}
