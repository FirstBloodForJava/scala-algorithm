package com.oycm.algorithm.c

object Solution_1 {

  /**
   * 1446. 连续字符
   * https://leetcode.cn/problems/consecutive-characters/description/
   *
   * 字符串能量：只包含一种字符的最长非空子字符串的长度
   *
   * @param s 只包含小写英文字母
   * @return 字符串能量
   */
  def maxPower(s: String): Int = {
    var ans = 0
    var cnt = 0
    for (i <- s.indices) {
      cnt += 1
      // 相当于内循环结束 记录答案
      if (i == s.length - 1 || s(i) != s(i + 1)) {
        ans = Math.max(cnt, ans)
        cnt = 0
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxPower("leetcode"))
    println(maxPower("abbcccddddeeeeedcba"))
  }
}
