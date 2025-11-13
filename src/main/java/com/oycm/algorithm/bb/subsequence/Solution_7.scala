package com.oycm.algorithm.bb.subsequence

object Solution_7 {

  /**
   * 522. 最长特殊序列 II 1700
   * https://leetcode.cn/problems/longest-uncommon-subsequence-ii/description/
   *
   * 特殊序列：该序列为某字符串 独有的子序列
   *
   * 返回最长的 特殊序列，没有则返回 -1
   *
   * @param strs 字符串数组
   * @return
   */
  def findLUSlength(strs: Array[String]): Int = {
    /*
    找一个字符串的最长子序列，和其它字符串的子序列都没有交集
    ["aba","cdc","eae"]
    ["aaa","aaa","aa"]
    假设有一个 字符串 都长于其它字符串，它自己就是 特殊序列

    题解-思路：需要枚举每个字符串的所有子序列吗？
    如果一个 短子序列 是 特殊序列，那么更长的 子序列 也会是特殊序列。
    abc, abcd, abd 是独有子序列，则 adcd 会是更长的子序列
    子序列越长，越不可能是其它字符串的子序列，只要枚举 字符串 s=strs(i) 判断 s 是否为 其它字符串的子序列，如果都不是，则用 s 的长度更新答案的最大值
     */
    var ans = -1

    // 判断 s 是否为 t 的子序列
    def isSubsequence(s: String, t: String): Boolean = {
      var i = 0
      for (c <- t if i < s.length) {
        if (c == s(i)) {
          i += 1
        }
      }
      i == s.length
    }

    for (i <- strs.indices if strs(i).length > ans) {
      var isSub = false
      for (j <- strs.indices if i != j && !isSub) {
        if (isSubsequence(strs(i), strs(j))) {
          isSub = true
        }
      }
      if (!isSub) {
        ans = Math.max(ans, strs(i).length)
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(findLUSlength(Array("aba", "cdc", "eae")))
    println(findLUSlength(Array("aaa", "aaa", "aa")))
  }

}
