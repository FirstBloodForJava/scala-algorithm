package com.oycm.algorithm.abb.basic

object Solution_5 {

  /**
   * 76. 最小覆盖子串
   * https://leetcode.cn/problems/minimum-window-substring/description/
   *
   * @param s 字符串
   * @param t 字符串
   * @return s 中涵盖 t 所有字符的最小子串, s 中不存在涵盖 t 所有字符的最小子串, 则返回 空字符粗 ""
   */
  def minWindow(s: String, t: String): String = {
    /*
    求 s 中最短子串，包含所有 t 的字符
    [l, r] 包含所有 t 的字符, 如果 [l+1, r] 还包含, 则继续循环

    怎么判断 子串 是否 全部包含 t 的所有字符

    用 hash 表记录 t 的字符情况, 遍历 s 的过程中不断减少 hash 表中的字符, 当为 空时，则符合要求
    固定 r, 匹配时，找 l 的最小值

    k 表示 t 的字符数, m 表示 s 的长度, n 表示 t 的长度
    时间复杂度 O(k * m + t)

    */
    def isCover(s: scala.collection.mutable.Map[Char, Int], t: scala.collection.mutable.Map[Char, Int]): Boolean = {
      if (s.size != t.size) {
        return false
      }
      var ans = true
      for ((k, v) <- s if ans) {
        if (v < t(k)) {
          ans = false
        }
      }
      ans
    }

    var mixL = -1
    var mixR = s.length
    // 记录
    val map = scala.collection.mutable.Map[Char, Int]()
    for (c <- t) {
      map(c) = map.getOrElse(c, 0) + 1
    }
    val cnt = scala.collection.mutable.Map[Char, Int]()
    var l = 0
    for (r <- s.indices) {
      if (map.contains(s(r))) {
        cnt(s(r)) = cnt.getOrElse(s(r), 0) + 1
      }
      // 个数和数量都一致才可以
      while (isCover(cnt, map)) {
        if (r - l < mixR - mixL) {
          mixL = l
          mixR = r
        }
        if (map.contains(s(l))) {
          cnt(s(l)) -= 1
          if (cnt(s(l)) == 0) {
            cnt.remove(s(l))
          }
        }
        l += 1
      }

    }
    if (mixL < 0) {
      ""
    } else {
      s.substring(mixL, mixR + 1)
    }
  }

  def main(args: Array[String]): Unit = {
    println(minWindow("ADOBECODEBANC", "ABC"))
    println(minWindow("a", "a"))
    println(minWindow("a", "aa"))
  }
}
