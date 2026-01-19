package com.oycm.algorithm.f.prefix_sum_basic

object Solution_3 {

  /**
   * 2559. 统计范围内的元音字符串数 1435
   * https://leetcode.cn/problems/count-vowel-strings-in-ranges/description/
   *
   * l = queries(i)(0), r = queries(i)(1)
   * 表示查询 words 中 [l, r] 单词以元音开头和结尾的字符串的数目
   *
   * @param words 字符串数组
   * @param queries 二维整数数组
   * @return
   */
  def vowelStrings(words: Array[String], queries: Array[Array[Int]]): Array[Int] = {
    val ans = Array.fill(queries.length)(0)
    // 表示 words 前 i 个 以元音开头和结尾的字符串的数目
    val sums = Array.fill(words.length + 1)(0)
    for (i <- words.indices) {
      if ("aeiou".contains(words(i)(0)) && "aeiou".contains(words(i).last)) {
        sums(i + 1) = sums(i) + 1
      } else {
        sums(i + 1) = sums(i)
      }
    }
    for (i <- queries.indices) {
      ans(i) = sums(queries(i)(1) + 1) - sums(queries(i)(0))
    }
    ans
  }
}
