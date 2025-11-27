package com.oycm.algorithm.a.advance

object Solution_16 {

  /**
   * 1016. 子串能表示从 1 到 N 数字的二进制串 1779
   * https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/description/
   *
   * [1, n] 范围内的每个整数，其对应的二进制都是 s 的子字符串
   *
   * @param s 二进制字符串
   * @param n 正整数, [1, 10 pow 9]
   * @return
   */
  def queryString(s: String, n: Int): Boolean = {
    /*
    由于 n <= 10 pow 9, 所以 [1, n] 中所有元素的 子串不会超过 30
    时间复杂度 O(n)
     */
    var ans = true
    for (i <- n / 2 + 1 to n if ans) {
      if (s.indexOf(Integer.toBinaryString(i)) < 0) {
        ans = false
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(Integer.numberOfLeadingZeros(20))
  }

}
