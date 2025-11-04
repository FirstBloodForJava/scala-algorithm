package com.oycm.algorithm.a.advance

object Solution_12 {

  /**
   * 438.找到字符串中所有字母异位词
   * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
   *
   * 异位词：是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。
   *
   * @param s 长度为 n
   * @param p 长度为 k
   * @return 返回这些子串的起始索引
   */
  def findAnagrams(s: String, p: String): List[Int] = {
    /*
    时间复杂度：O(n * log 26)
    空间复杂度：O(26)
    */
    val ans = scala.collection.mutable.ArrayBuffer[Int]()

    if (p.length > s.length) {
      return ans.toList
    }
    val pMap = scala.collection.mutable.Map[Char, Int]()
    for (i <- p.indices) {
      pMap(p(i)) = pMap.getOrElse(p(i), 0) + 1
    }
    val cntMap = scala.collection.mutable.Map[Char, Int]()
    for (r <- s.indices) {
      val rc = s(r)
      cntMap(rc) = cntMap.getOrElse(rc, 0) + 1
      val l = r - p.length + 1
      if (l >= 0) {
        // 次数自减无法会出现负数的情况，如果之间删除会导致后续计算出现问题
        val lc = s(l)
        if (pMap == cntMap) {
          ans.append(l)
        }
        cntMap(lc) -= 1
        if (cntMap(lc) == 0) {
          cntMap.remove(lc)
        }
      }
    }

    ans.toList
  }

  def main(args: Array[String]): Unit = {
    println(findAnagrams("cbaebabacd", "abc"))
    println(findAnagrams("abab", "ab"))
  }
}
