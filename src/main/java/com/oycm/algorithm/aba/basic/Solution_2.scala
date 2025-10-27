package com.oycm.algorithm.aba.basic

import scala.collection.mutable

object Solution_2 {

  /**
   * 3090.每个字符最多出现两次的最长子字符串 1329
   * https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/
   *
   * @param s 给定的字符串
   * @return 找出满足每个字符最多出现两次的最长子字符串，返回最大的子字符串长度
   */
  def maximumLengthSubstring(s: String): Int = {
    var ans = 0
    val map = mutable.Map[Char, Int]()
    var l = 0
    for (r <- s.indices) {
      val curr = s(r)
      map(curr) = map.getOrElse(curr, 0) + 1
      while (map(curr) > 2) {
        map(s(l)) = map(s(l)) - 1
        l += 1
      }
      ans = Math.max(ans, r - l + 1)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumLengthSubstring("bcbbbcba") == 4)
    println(maximumLengthSubstring("aaaa") == 2)
  }

}
