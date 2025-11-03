package com.oycm.algorithm.ba.opposite

object Solution_5 {

  /**
   * 1750.删除字符串两端相同字符后的最短长度 1502
   * https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/
   *
   * s 字符串只包含 a, b, c 三种字符，进行以下操作后（5个步骤）任意次，能得到的最短子串长度
   *  - 选择字符串 s 一个 非空 的前缀，这个前缀的所有字符都相同
   *  - 选择字符串 s 一个 非空 的后缀，这个后缀的所有字符都相同
   *  - 前缀和后缀在字符串中任意位置都不能有交集
   *  - 前缀和后缀包含的所有字符都要相同
   *  - 同时删除前缀和后缀
   *
   *  第 3 点的描述有点怪
   *
   * @param s
   * @return 求进行上面操作后，能得到的最短字符串
   */
  def minimumLength(s: String): Int = {
    var ans = 0
    val n = s.length
    var l = 0
    var r = n - 1
    //
    if (s(l) != s(r) || s.length == 1) {
      return s.length
    }

    // 记录字符出现的次数
    var currCnt = 0
    var curr = s(0)

    var flag = true
    while (l < r && flag) {

      while (l < r && curr == s(l)) {
        l += 1
        currCnt += 1
      }
      while (l <= r && curr == s(r)) {
        r -= 1
      }
      // 能进入这里说明 [l, r] 区间还有字符
      if (l <= r) {
        // 和上一个相对 都可以删除
        if (s(l - 1) == s(r + 1)) {
          ans = r - l + 1
          curr = s(l)
          currCnt = 0
        } else {
          flag = false
          ans = r - l + 1 + currCnt
        }
      } else {
        ans = 0
      }

    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(minimumLength("ca") == 2)
    println(minimumLength("cabaabac") == 0)
    println(minimumLength("aabccabba") == 3)
    println(minimumLength("c") == 1)
  }
}
