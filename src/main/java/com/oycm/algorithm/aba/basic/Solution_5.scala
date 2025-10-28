package com.oycm.algorithm.aba.basic

object Solution_5 {

  /**
   * 1208.尽可能使字符串相等 1497
   * https://leetcode.cn/problems/get-equal-substrings-within-budget/
   * 长度相同的字符串 s 和 t
   * |s[i] - t[i]| 表示 将 s 中的 第 i 个字符转换成 t 中的第 i 个字符的开销(>= 0)，转换后 s 的部分相当于 t 的子字符串
   * 计算 使用最大预算 maxCost s 转换成 t 的最长字串长度
   *
   * @param s       字符串
   * @param t       字符串
   * @param maxCost 变更字符串的最大开销
   * @return
   */
  def equalSubstring(s: String, t: String, maxCost: Int): Int = {
    var ans = 0
    // 记录两种的绝对值
    val dif = Array.fill(s.length)(0)
    for (i <- s.indices) {
      dif(i) = Math.abs(s(i) - t(i))
    }
    // dif [l,r] 中的和未超过 maxCost 表示其就是符合要求的子字符串，只需要在其中求最大值
    var l = 0
    // 计算开销
    var temp = 0
    for (r <- dif.indices) {
      temp += dif(r)
      while (temp > maxCost) {
        temp -= dif(l)
        l += 1
      }
      ans = Math.max(ans, r - l + 1)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(equalSubstring("abcd", "bcdf", 3) == 3)
    println(equalSubstring("abcd", "cdef", 3) == 1)
    println(equalSubstring("abcd", "acde", 0) == 1)
  }
}
