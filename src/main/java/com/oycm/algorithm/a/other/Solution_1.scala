package com.oycm.algorithm.a.other

object Solution_1 {

  /**
   * 2269. 找到一个数字的 K 美丽值
   * https://leetcode.cn/problems/find-the-k-beauty-of-a-number/description/
   *
   * 一个整数 num 的 k 美丽值：num 中符合以下条件的 子字符串 数目
   *  - 子字符串长度为 k （定长）
   *  - 子字符串能整除 num
   *
   * 注意：
   *  - 子字符串 允许有 前缀 0
   *  - 0 不能整除任何值。
   *
   * @param num
   * @param k
   * @return
   */
  def divisorSubstrings(num: Int, k: Int): Int = {
    /*
    定长滑动窗口
    时间复杂度 O(n)
     */
    var ans = 0
    val n = num.toString.length
    val nums = num.toString.toCharArray
    for (i <- 0 to n - k) {
      val sub = new String(nums.slice(i, i + k)).toInt
      if (sub > 0 && num % sub == 0) {
        ans += 1
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(divisorSubstrings(240, 2) == 2)
    println(divisorSubstrings(430043, 2) == 2)
  }


}
