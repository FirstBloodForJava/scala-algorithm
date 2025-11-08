package com.oycm.algorithm.bb.subsequence

object Solution_1 {

  /**
   * 392. 判断子序列
   * https://leetcode.cn/problems/is-subsequence/description/
   *
   * 字符串子序列：是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串
   *
   * 判断 s 是否为 t 的子序列。
   *
   * @param s 字符串
   * @param t 字符串
   * @return
   */
  def isSubsequence(s: String, t: String): Boolean = {
    /*
    虽然有嵌套循环，里面的循环不会超过 n 次
    时间复杂度 O(n)
    */
    var ans = false
    val n = t.length
    val m = s.length
    var j = 0
    // 记录相同字符次数
    var time = 0
    for (i <- s.indices if j < n) {
      while (j < n && s(i) != t(j)) {
        j += 1
      }
      if (j < n) {
        time += 1
        j += 1
      }
    }
    ans = m == time
    ans
  }

  def main(args: Array[String]): Unit = {
    println(isSubsequence("abc", "ahbgdc"))
    println(isSubsequence("axc", "ahbgdc"))
  }
}
