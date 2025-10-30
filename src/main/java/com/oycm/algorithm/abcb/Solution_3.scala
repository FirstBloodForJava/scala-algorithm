package com.oycm.algorithm.abcb

object Solution_3 {

  /**
   * 3325.字符至少出现 K 次的子字符串 I 做到 O(n)
   * https://leetcode.cn/problems/count-substrings-with-k-frequency-characters-i/
   * s 只含有小写字母的字符串
   * k 正整数
   * 求至少出现 k 次字符连续子串的总数
   *
   * @param s 只含有小写字母的字符串
   * @param k 正整数
   * @return
   */
  def numberOfSubstrings(s: String, k: Int): Int = {
    /*
    z = 122
    [l, r]
    count(?) >= k 代表这个字符串符合要求
    通过记录上一次 符合要求字符 index，判断进入下一个左端点时，移除的字符是否和 target 一致，
    如果一致，则右端点需要循环找下一个目标字符，不一致，则右端点不需要进入循环，count(target) >= k 记录答案
    */
    var ans = 0
    val n = s.length
    val count = Array.fill(123)(0)
    var l = 0
    var r = 0
    // 表示上一轮字符数 大于等于 k 的 index
    var target = 0
    // r < n 没有考虑到 在最后面出现 字符数超过 k
    while (l < n - k + 1) {
      // 这里 flag 可以 换成 count(s(target)) < k
      while (r < n && count(s(target)) < k) {
        count(s(r)) += 1
        if (count(s(r)) >= k) {
          target = r
        }
        r += 1
      }
      // target 字符是否符合要求
      if (count(s(target)) >= k) {
        ans += n - r + 1
      }
      // 上一轮左端点字符和 当前符合要求字符相同，则需要进行循环查早符合要求的k，如果不是，则不用循环
      count(s(l)) -= 1
      l += 1
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(numberOfSubstrings("abacb", 2) == 4)
    println(numberOfSubstrings("abcde", 1) == 15)
    println(numberOfSubstrings("ajsrhoebe", 2) == 7)
  }
}
