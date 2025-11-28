package com.oycm.algorithm.a.advance

object Solution_16 {

  /**
   * 1016. 子串能表示从 1 到 N 数字的二进制串 1779
   * https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/description/
   *
   * [1, n] 范围内的每个整数，其对应的二进制都是 s 的子字符串
   *
   * @param s 二进制字符串
   * @param n 正整数, [1, 10 pow 9]
   * @return
   */
  def queryString(s: String, n: Int): Boolean = {
    /*
    由于 n <= 10 pow 9, 所以 [1, n] 中所有元素的 子串不会超过 30
    时间复杂度 O(n)
     */
    var ans = true
    for (i <- n / 2 + 1 to n if ans) {
      if (s.indexOf(Integer.toBinaryString(i)) < 0) {
        ans = false
      }
    }

    ans
  }

  def slideWindow(s: String, n: Int): Boolean = {
    /*
    s 的子串如果都包含 [n/2 + 1, n] 的子串，则都包含
    题解思路：
    假设 n 是长为 k+1 的二进制字符串,
    只要都包含
      长为 k+1 [2^k, n] 范围的二进制字符串
      长为 k [2^(k-1), 2^k -1] 范围的二进制字符串
    长为 k 的字符串，如果需要有 n 个不同的子串，字符串长度至少是 k + n - 1
    k+1 长度要求 s 字符串长度：k+1 + n - 2^k => n - 2^k + k + 1
    k 长度要求 s 字符串长度：k + 2^k - 1 - 2^(k-1) => 2^(k-1) + k - 1

    s 长度至少 >= max(n - 2^k + k + 1, 2^(k-1) + k - 1)
    当 n = 1, 时，k = 0, [2^(k-1), 2^k-1] 区间不存在, 子要判断 s 是否包含 '1'

    [2^(k-1), n/2] 的区间可以从 [2^k, n] 右移一位得到

    所以只需要考虑
      长为 k+1 [2^k, n] 范围的二进制字符串
      长为 k [n/2 + 1, 2^k -1] 范围的二进制字符串

    n = 2, k = 1, [2,1] [2,2]
    n = 4, k = 2, [3,3] [4,4]
    当 lower > upper 时，不需要判断
     */
    if (n == 1) {
      return s.contains("1")
    }
    val k = Integer.toBinaryString(n).length - 1
    // n - 2^k + k + 1
    // 2^(k-1) + k - 1
    if (s.length < Math.max(n - (1 << k) + k + 1, (1 << (k - 1)) + k - 1)) {
      return false
    }

    def check(s: String, k: Int, lower: Int, upper: Int): Boolean = {
      if (lower > upper) {
        true
      } else {
        val set = scala.collection.mutable.Set[Int]()
        // 用于滑动窗口的移除左端点
        val mark = (1 << (k - 1)) - 1
        var x = Integer.parseInt(s.substring(0, k - 1), 2)
        for (i <- k - 1 until s.length) {
          // 位运算移除左边高位
          x = ((x & mark) << 1) | (s(i) - '0')
          if (x >= lower && x <= upper) {
            set.add(x)
          }
        }
        set.size == upper - lower + 1
      }

    }

    check(s, k, n / 2 + 1, (1 << k) - 1) && check(s, k + 1, 1 << k, n)
  }

  def main(args: Array[String]): Unit = {
//    println(slideWindow("0110", 3))
//    println(slideWindow("0110", 4))
    println(slideWindow("111100", 4))

  }

}
