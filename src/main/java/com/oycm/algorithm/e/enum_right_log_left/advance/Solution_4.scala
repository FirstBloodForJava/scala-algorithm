package com.oycm.algorithm.e.enum_right_log_left.advance

object Solution_4 {

  /**
   * 2506. 统计相似字符串对的数目 1335
   * https://leetcode.cn/problems/count-pairs-of-similar-strings/description/
   *
   * 两个字符串由相同的字符组成，则认为这两个字符串 相似 。
   *
   * @param words 字符串数组, words[i] 仅由小写英文字母组成
   * @return 求 相似 的下标对数
   */
  def similarPairs(words: Array[String]): Int = {
    /*
    位运算记录不同字符的数量 c |= 1 << (word - 'a')
    */
    var ans = 0
    val map = scala.collection.mutable.Map[Int, Int]()
    for (word <- words) {
      var bit = 0
      for (c <- word) {
        bit |= 1 << (c - 'a')
      }
      if (map.contains(bit)) {
        ans += map(bit)
        map(bit) += 1
      } else {
        map(bit) = 1
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(similarPairs(Array("aba", "aabb", "abcd", "bac", "aabc")))
    println(similarPairs(Array("aabb", "ab", "ba")))
  }
}
