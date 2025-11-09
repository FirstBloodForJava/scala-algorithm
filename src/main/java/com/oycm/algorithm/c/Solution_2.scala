package com.oycm.algorithm.c

object Solution_2 {

  /**
   * 1869. 哪种连续子字符串更长
   * https://leetcode.cn/problems/longer-contiguous-segments-of-ones-than-zeros/description/
   *
   * 1 组成的 最长 连续子字符串 大于 由 0 组成的 最长 连续子字符串，则返回 true，否则 false
   *
   * @param s 二进制字符串
   * @return
   */
  def checkZeroOnes(s: String): Boolean = {
    val n = s.length
    var zero = 0
    var one = 0
    var i = 0
    while (i < n) {
      if (s(i) == '1') {
        val start = i
        i += 1
        while (i < n && s(i) == s(i - 1)) {
          i += 1
        }
        one = Math.max(one, i - start)
      }
      if (i < n && s(i) == '0') {
        val start = i
        i += 1
        while (i < n && s(i) == s(i - 1)) {
          i += 1
        }
        zero = Math.max(zero, i - start)
      }
    }

    one > zero
  }

  def main(args: Array[String]): Unit = {
    println(checkZeroOnes("1101"))
    println(checkZeroOnes("111000"))
  }
}
