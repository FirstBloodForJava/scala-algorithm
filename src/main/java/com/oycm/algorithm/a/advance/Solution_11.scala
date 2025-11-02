package com.oycm.algorithm.a.advance

object Solution_11 {

  /**
   * 567.字符串的排列
   * https://leetcode.cn/problems/permutation-in-string/
   *
   * 排列：是字符串中所有字符的重新排序。
   *
   * 判断 s2 是否包含 s1 的排列
   *  - s1 = ab，s2 = eidbaooo 结果 true
   *  - s1 = ab，s2 = eidboaoo 结果 false
   *    s2 存在一个字串，其中所有字母的数量和 s1 中所有字母数量一致
   *
   * @param s1
   * @param s2
   * @return
   */
  def checkInclusion(s1: String, s2: String): Boolean = {
    /*
    记录 s1 中不同字符的数量
    遍历 s2 长为 s1 的窗口，记录子串中字符的数量，和 s1 比较
    */
    var ans = false
    if (s1.length > s2.length) {
      return ans
    }
    val s1Map = scala.collection.mutable.Map[Char, Int]()
    for (i <- s1.indices) {
      s1Map(s1(i)) = s1Map.getOrElse(s1(i), 0) + 1
    }

    val s2Map = scala.collection.mutable.Map[Char, Int]()
    for (r <- s2.indices if !ans) {
      // 入
      s2Map(s2(r)) = s2Map.getOrElse(s2(r), 0) + 1

      val l = r - s1.length + 1
      if (l >= 0) {
        // 比较
        ans = s1Map == s2Map
        // 出
        if (s2Map(s2(l)) == 1) {
          s2Map.remove(s2(l))
        } else {
          s2Map(s2(l)) -= 1
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(checkInclusion("ab", "eidbaooo"))
    println(checkInclusion("ab", "eidboaoo"))
  }
}
