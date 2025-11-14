package com.oycm.algorithm.bb.subsequence

object Solution_2 {

  /**
   * 524. 通过删除字母匹配到字典里最长单词
   * https://leetcode.cn/problems/longest-word-in-dictionary-through-deleting/description/
   *
   * 找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
   *
   * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
   *
   * @param s          字符串, n 表示字符串长度
   * @param dictionary 字符串数组, m 表示字符串平均长度, k 表示数组长度
   * @return
   */
  def findLongestWord(s: String, dictionary: List[String]): String = {
    /*
    求 dictionary 中 s 的最长子序列，如果相同，则返回 字母序最小的字符串


    时间复杂度 O(k * (n + m))

    动态规划判断是否为子序列
    时间复杂度 k * m + 26 * n
     */
    var ans = ""

    // t 是否为 s 的子序列
    def isSubsequence(t: String, s: String): Boolean = {
      var cnt = 0
      for (c <- s if cnt < t.length) {
        if (t(cnt) == c) {
          cnt += 1
        }
      }
      cnt == t.length
    }

    for (t <- dictionary if t.length > ans.length || (t.length == ans.length && t.compareTo(ans) < 0)) {
      if (isSubsequence(t, s)) {
        if (t.length == ans.length) {
          // 选字母序最小
          if (t.compareTo(ans) < 0) {
            ans = t
          }
        } else {
          ans = t
        }
      }
    }
    if (ans.isEmpty) {
      ans = null
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(findLongestWord("abpcplea", Array("ale", "apple", "monkey", "plea").toList))
    println(findLongestWord("abpcplea", Array("a", "b", "c").toList))
  }
}
