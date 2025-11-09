package com.oycm.algorithm.c

object Solution_4 {

  /**
   * 3456. 找出长度为 K 的特殊子字符串
   * https://leetcode.cn/problems/find-special-substring-of-length-k/description/
   *
   * 判断是否存在一个长度 恰好 为 k 的子字符串，该子字符串需要满足以下条件
   *  - 该子字符串 只包含一个唯一字符
   *  - 如果该子字符串的 前面 有字符，则该字符必须与子字符串中的字符不同。
   *  - 如果该子字符串的 后面 有字符，则该字符也必须与子字符串中的字符不同。
   *
   * @param s
   * @param k
   * @return
   */
  def hasSpecialSubstring(s: String, k: Int): Boolean = {
    /*
    是否存在 连续子字符串 全是同一个字符，且长度为 k
    统计相同字符的数量，当后面字符不相同时，计算长度，等于 k 则为 true，否则继续循环

    时间复杂度 O(n)
    */
    var ans = false
    val n = s.length
    var j = 0
    for (i <- 1 until n if !ans) {
      if (s(j) != s(i)) {
        if (k == i - j) {
          ans = true
        }
        j = i
      }
    }
    // 长度为 1，或最后一段字符都相同的时判断是否相等
    if (k == n - j) {
      ans = true
    }
    ans
  }

  def groupedLoop(s: String, k: Int): Boolean = {
    var ans = false
    var cnt = 0
    val n = s.length
    for (i <- s.indices) {
      cnt += 1
      // 组结束 条件
      if (i == n - 1 || s(i) != s(i + 1)) {
        // 更新答案
        if (cnt == k) {
          ans = true
        }
        cnt = 0
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(hasSpecialSubstring("aaabaaa", 3))
    println(hasSpecialSubstring("abc", 2))
    println(hasSpecialSubstring("abc", 1))
    println(hasSpecialSubstring("dii", 1))

    println(groupedLoop("aaabaaa", 3))
    println(groupedLoop("abc", 2))
    println(groupedLoop("abc", 1))
    println(groupedLoop("dii", 1))
  }
}
