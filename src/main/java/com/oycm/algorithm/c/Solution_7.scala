package com.oycm.algorithm.c

object Solution_7 {

  /**
   * 1513. 仅含 1 的子串数 1351
   * https://leetcode.cn/problems/number-of-substrings-with-only-1s/description/
   *
   * 字符都为 1 的子字符串，同 2348
   *
   * @param s 二进制字符串
   * @return
   */
  def numSub(s: String): Int = {

    val n = s.length
    var ans = 0L
    var l = 0
    while (l < n) {
      if (s(l) == '1') {
        var r = l + 1
        while (r < n && s(r) == '1') {
          r += 1
        }
        ans += (r - l).toLong * (r - l + 1) / 2
        l = r
      }
      l += 1
    }

    (ans % 1000000007L).toInt
  }
}
