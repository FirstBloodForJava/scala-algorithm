package com.oycm.algorithm.ba.opposite

object Solution_11 {

  /**
   * 633. 平方数之和
   * https://leetcode.cn/problems/sum-of-square-numbers/?envType=problem-list-v2&envId=gPEYJATt
   *
   * @param c 非负整数
   * @return 判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
   */
  def judgeSquareSum(c: Int): Boolean = {
    var ans = false

    var r = Math.sqrt(c).toInt
    var l = 0
    // 注意 1 + 1 = 2
    while (l <= r && !ans) {
      val sum = Math.pow(l, 2) + Math.pow(r, 2)
      if (sum > c) {
        r -= 1
      } else if (sum < c) {
        l += 1
      } else {
        ans = true
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(judgeSquareSum(5))
    println(judgeSquareSum(3))
  }

}
