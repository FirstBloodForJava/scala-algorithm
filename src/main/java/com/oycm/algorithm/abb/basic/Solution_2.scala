package com.oycm.algorithm.abb.basic

import scala.collection.mutable

object Solution_2 {

  /**
   * 2904.最短且字典序最小的美丽子字符串 做到 O(n平方)
   * https://leetcode.cn/problems/shortest-and-lexicographically-smallest-beautiful-string/description/
   * 美丽字符串：s 中某个字符串中的 1 的个数恰好等于 k
   * 字典序比较：对于相同长度的两个字符串 a 和 b ，如果在 a 和 b 出现不同的 第一个位置上，a 中该位置上的字符严格大于 b 中的对应字符，则认为字符串 a 字典序 大于 字符串 b 。
   * 另 len 等于最短美丽字符串的长度
   * 求返回长度等于 len 且字典序最小的美丽字符串，若没有则返回 null
   *
   * @param s 二进制字符串，只包含 0 或 1
   * @param k 正整数
   * @return 求字典序列最小且最短的美丽字符串
   */
  def shortestBeautifulSubstring(s: String, k: Int): String = {
    var ans: String = ""

    // k > 1，最短美丽字符串存在，则头尾必是 1，求字典序最小的，
    var temp: String = null
    // 假设 l 是美丽字符串的左边，如果美丽字符串存在，则会有唯一的 r 构成最短的美丽字符串
    for (l <- 0 to s.length - k) {
      var counts = 0
      if (s(l) == '1') {
        counts += 1
        var r = l + 1
        while (counts < k && r < s.length) {
          if (s(r) == '1') {
            counts += 1
          }
          r += 1
        }
        // 是美丽字符串
        if (counts == k) {
          temp = s.substring(l, r)
          // 第一次
          if (ans == "") {
            ans = temp
          } else if (temp.length < ans.length) {
            // 长度更短
            ans = temp
          } else if (temp.length == ans.length) {
            // 长度相同 更新序列最小
            // 怎么算出哪个 序列最小
            var i = 1
            var swap = true
            while (i < temp.length) {
              // 一次大于/小于就退出
              if (temp(i) < ans(i)) {
                // temp 第一次 小于 表示小于 ans
                i = temp.length
              } else if (temp(i) > ans(i)) {
                // temp 第一次 大于 表示 大于 ans
                i = temp.length
                swap = false
              }
              i += 1
            }
            if (swap) {
              ans = temp
            }
          }
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(shortestBeautifulSubstring("100011001", 3) == "11001")
    println(shortestBeautifulSubstring("1011", 2) == "11")
    println(shortestBeautifulSubstring("000", 1) == "")
    println(shortestBeautifulSubstring("111", 1) == "1")
    println(shortestBeautifulSubstring("111111110010001010", 11) == "11111111001000101")
    println(shortestBeautifulSubstring("110101000010110101", 3) == "1011")
  }
}
