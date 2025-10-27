package com.oycm.algorithm.aba.basic

import scala.collection.mutable

object Solution_1 {


  /**
   * 3. 无重复字符的最长子串
   * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
   *
   * @param s 给定的字符串
   * @return 求不含有重复字符的最长 子字符串长度
   */
  def lengthOfLongestSubstring(s: String): Int = {
    var ans = 0
    // 记录窗口的左
    var l = 0
    val map = mutable.Map[Char, Int]()
    for (r <- s.indices) {
      // 记录字符出现的次数
      map(s(r)) = map.getOrElse(s(r), 0) + 1
      // 字符重复出现，删除左边直到重复出现的字符
      while (map(s(r)) > 1) {
        map(s(l)) = map(s(l)) - 1
        l += 1
      }
      ans = Math.max(ans, r - l + 1)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(lengthOfLongestSubstring("abcabcbb") == 3)
    println(lengthOfLongestSubstring("bbbbb") == 1)
    println(lengthOfLongestSubstring("pwwkew") == 3)
  }
}
