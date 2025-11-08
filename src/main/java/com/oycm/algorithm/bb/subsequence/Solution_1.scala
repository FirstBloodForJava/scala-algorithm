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

  def moreSIsSubsequence(s: String, t: String): Boolean = {
    /*
    加入有多个 s 需要判断 是否 为 t 的子序列，时间复杂度是 s(time) * t.length
    动态规划优化：
    初始化一个二维数组 ci[n][26] 记录字符 出现的下标 情况，n 表示 t.length
    通过遍历 s 是否能查询到 字符的下标
    ci[i][c] = i 表示 t 字符串，第 i 个 字符的值是 i
    ci[n] = Array.fill(26)(n)

    时间复杂度 26 n
    空间复杂度 26 n
    */
    val n = t.length
    val ci = Array.ofDim[Int](n + 1, 26)
    java.util.Arrays.fill(ci(n), n)
    // 初始化 ci 数组
    for (i <- n - 1 to 0 by -1) {
      ci(i) = ci(i + 1).clone
      ci(i)(t(i) - 'a') = i
    }
    var ans = true
    var i = -1
    for (j <- s.indices if ans) {
      // i 之后查找下个字符
      i = ci(i + 1)(s(j) - 'a')
      if (i == n) {
        ans = false
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(isSubsequence("abc", "ahbgdc"))
    println(isSubsequence("axc", "ahbgdc"))

    println(moreSIsSubsequence("abc", "ahbgdc"))
    println(moreSIsSubsequence("axc", "ahbgdc"))
    println(moreSIsSubsequence("abc", ""))
    println(moreSIsSubsequence("acb", "ahbgdc"))
  }
}
