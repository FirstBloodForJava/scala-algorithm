package com.oycm.algorithm.bb.subsequence

object Solution_3 {

  /**
   * 2486. 追加字符以获得子序列 1363
   * https://leetcode.cn/problems/append-characters-to-string-to-make-subsequence/
   *
   * 通过向 s 末尾追加字符的方式使 t 变成 s 的一个 子序列 ，返回需要追加的最少字符数。
   *
   * @param s
   * @param t
   * @return
   */
  def appendCharacters(s: String, t: String): Int = {
    // 找最长的公共子序列长度
    var m = t.length
    var i = 0
    for (c <- s if i < m) {
      if (c == t(i)) {
        i += 1
      }
    }
    m - i
  }

}
