package com.oycm.algorithm.a.advance

import scala.collection.mutable

object Solution_8 {

  /**
   * 1297.子串的最大出现次数 1748
   * https://leetcode.cn/problems/maximum-number-of-occurrences-of-a-substring/
   * 求 满足以下条件，且出现次数最大的任意子串次数：
   *  - 字串中不同字母数不超过 maxLetters
   *  - 字串长度必须 大于等于 minSize 且 小于等于 maxSize
   *
   * @param s          只包含小写英文字母的字符串
   * @param maxLetters 字串中不同字母的数目的最大值
   * @param minSize    字串的最小值
   * @param maxSize    字串的最大值
   * @return
   */
  def maxFreq(s: String, maxLetters: Int, minSize: Int, maxSize: Int): Int = {
    /*
    题解：脑筋急转弯
    要求次数出现最多的字串次数，则字串就需要越短，答案才能越多
    字串越短，越能匹配第一个条件
    问题转换成了只需要考虑长度恰好为 minSize 的子串，且不同字符数小于等于 maxLetters 的字串出现最多次数
    */
    var ans = 0
    // 记录字符数
    val ansMap = mutable.Map[String, Int]()
    // 记录字符出现次数
    val charCnt = Array.fill(26)(0)
    // 不同字符数
    var kinds = 0
    for (r <- s.indices) {
      // 1. 入
      charCnt(s(r) - 'a') += 1
      if (charCnt(s(r) - 'a') == 1) {
        kinds += 1
      }
      // [l, r] minSize = r - l + 1
      val left = r - minSize + 1
      if (left >= 0) {
        // 2. 更新答案
        if (kinds <= maxLetters) {
          val subStr = s.substring(left, left + minSize)
          ansMap(subStr) = ansMap.getOrElse(subStr, 0) + 1
          ans = Math.max(ans, ansMap(subStr))
        }
        // 3. 出
        charCnt(s(left) - 'a') -= 1
        if (charCnt(s(left) - 'a') == 0) {
          kinds -= 1
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxFreq("aababcaab", 2, 3, 4))
    println(maxFreq("aaaa", 1, 3, 3))
    println(maxFreq("aabcabcab", 2, 2, 3))
    println(maxFreq("abcde", 2, 3, 3))
  }
}
