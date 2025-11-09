package com.oycm.algorithm.c

object Solution_3 {

  /**
   * 2414. 最长的字母序连续子字符串的长度
   * https://leetcode.cn/problems/length-of-the-longest-alphabetical-continuous-substring/description/
   *
   * 字母序连续字符串：字母表中连续字母组成的字符串。"abcdefghijklmnopqrstuvwxyz" 任意子字符串都是 字母序连续字符串
   *
   * @param s 仅由小写英文字母组成的字符串
   * @return 求 s 最长 的 字母序连续子字符串 的长度
   */
  def longestContinuousSubstring(s: String): Int = {
    var ans = 0
    val n = s.length

    var i = 0
    while (i < n) {
      val start = i
      i += 1
      // abc
      while (i < n && s(i) - s(i - 1) == 1) {
        i += 1
      }
      // i 已经不符合要求
      ans = Math.max(ans, i - start)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(longestContinuousSubstring("abacaba"))
    println(longestContinuousSubstring("abcde"))
  }
}
