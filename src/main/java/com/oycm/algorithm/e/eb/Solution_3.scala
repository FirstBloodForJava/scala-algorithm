package com.oycm.algorithm.e.eb

object Solution_3 {

  /**
   * 1930. 长度为 3 的不同回文子序列 1533
   * https://leetcode.cn/problems/unique-length-3-palindromic-subsequences/
   *
   * 相同子序列计数一次
   *
   * @param s 字符串
   * @return 求 s 中长度为 3 的不同回文子序列 的个数
   */
  def countPalindromicSubsequence(s: String): Int = {
    /*
    i < j < k
    s(i) == s(k)
    怎么找到两边相同字符，都是小写字母，使用数组记录，遍历前后字符数，如果都大于 0 记录答案
    时间复杂度 O(26n)
    空间负载的 O(26*26)
     */
    val n = s.length
    var ans = 0
    var cnt = Array.fill(26)(Array.fill(26)(0))

    val sufCnt = Array.fill(26)(0)
    for (i <- n - 1 to 2 by -1) {
      sufCnt(s(i) - 'a') += 1
    }
    val preCnt = Array.fill(26)(0)
    preCnt(s(0) - 'a') = 1
    for (j <- 1 to n - 2) {
      for (i <- 0 to 25) {
        if (preCnt(i) > 0 && sufCnt(i) > 0 && cnt(s(j) - 'a')(i) == 0) {
          cnt(s(j) - 'a')(i) = 1
          ans += 1
        }
      }
      preCnt(s(j) - 'a') += 1
      sufCnt(s(j + 1) - 'a') -= 1
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(countPalindromicSubsequence("aabca"))
    println(countPalindromicSubsequence("adc"))
    println(countPalindromicSubsequence("bbcbaba"))
  }
}
