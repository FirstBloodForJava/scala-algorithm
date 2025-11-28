package com.oycm.algorithm.f.basic

object Solution_8 {

  /**
   * 3361. 两个字符串的切换距离 1533
   * https://leetcode.cn/problems/shift-distance-between-two-strings/description/
   *
   * 一次操作中，可以选择 s 中的一个下标执行以下操作之一：
   *  - 将 s[i] 切换为字母表中的下一个字母, s[i] = 'z' 切换 成 s[i] = 'a' 切换的代价 nextCost[25]
   *  - 将 s[i] 切换为字母表中的上一个字母, s[i] = 'a' 切换 成 s[i] = 'z' 切换的代价 previousCost[0]
   *
   * 切换距离：s 切换成 t 的最少操作代价之和
   *
   * @param s            长为 n 的字符串
   * @param t            长为 n 的字符串
   * @param nextCost     长 26 的数组，切换成下一个字符的花费
   * @param previousCost 长 26 的数组，切换成上一个字符的花费
   * @return
   */
  def shiftDistance(s: String, t: String, nextCost: Array[Int], previousCost: Array[Int]): Long = {
    /*
    切换的过程中，只能沿一个方向，要么是顺切换，要么是逆切换，才能最小操作代价。除非都是 0 的情况下，可以随便来回切
    当字符 i < j
      nextCost: nextCost(i) + nextCost(i+1) + nextCost(j-1)
        nextSums(j) - nextSums(i)
      previousCost: previousCost(i) + previousCost(i-1) + previousCost(0) + previousCost(25) + .. + previousCost(j+1)
        preSums(26) - (preSums(j+1) - preSums(i+1))
     当字符 i > j
      nextCost: nextCost(i) + nextCost(i+1) + ... + nextCost(25) + nextCost(0) + nextCost(j-1)
        nextSums(26) - (nextSums(i) - nextSums(j))
      previousCost: previousCost(i) + previousCost(i-1) + ... + previousCost(j+1)
        preSums(i+1) - preSums(j+1)
    */
    var ans = 0L
    // 注意前缀和溢出问题
    val nextSums = Array.fill(27)(0L)
    val preSums = Array.fill(27)(0L)
    for(i <- nextCost.indices) {
      nextSums(i + 1) = nextSums(i) + nextCost(i)
      preSums(i + 1) = preSums(i) + previousCost(i)
    }
    for (k <- s.indices) {
      val i = s(k) - 'a'
      val j = t(k) - 'a'
      if (i < j) {
        ans += Math.min(nextSums(j) - nextSums(i), preSums(26) - (preSums(j+1) - preSums(i+1)))
      } else if (i > j) {
        ans += Math.min(nextSums(26) - (nextSums(i) - nextSums(j)), preSums(i+1) - preSums(j+1))
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {

  }

}
