package com.oycm.algorithm.ba.opposite

object Solution_24 {

  /**
   * 1616. 分割两个字符串得到回文串
   * https://leetcode.cn/problems/split-two-strings-to-make-palindrome/description/
   *
   * 字符串 a b 长度相同，选择一个下标，将两个字符串都在相同的下标分割开
   *  - a = a(pre) + a(suf)
   *  - b = b(pre) + b(suf)
   *
   * 判断 a(pre) + b(suf) 或 b(pre) + a(suf) 是否能构成回文串
   *
   * @param a 字符串
   * @param b 字符串
   * @return
   */
  def checkPalindromeFormation(a: String, b: String): Boolean = {
    /*
    分开处理：
    a 的前缀 和 a 或 b 的后缀
    b 的前缀 和 a 或 b 的后缀

    a 的前缀 和 b 和 b 的后缀
    b 的前缀 和 a 和 a 的后缀
    上面的字符串组合能否构成回文串

    a(pre) 和 a|b(suf) => 中间的 a 或 b 是不是回文串
    b(pre) 和 a|b(suf) => 中间的 a 或 b 是不是回文串

    转换

    a前缀 和 b后缀 回文匹配的最大值, [pre, suf] 只需要在判断这个区间里面 a 或 b 是不是回文串，如果 a 和 b 不匹配，则 判断整个字符串是不是回文串
    b前缀 和 a后缀 同上

    时间复杂度 O(n)
     */

    def isPalindrome(s: String, i: Int, j: Int): Boolean = {
      var ans = true
      var l = i
      var r = j
      while (l < r && ans) {
        if (s(l) != s(r)) {
          ans = false
        }
        l += 1
        r -= 1
      }
      ans
    }

    def check(a: String, b: String): Boolean = {
      var l = 0
      var r = a.length - 1
      while (l < r && a(l) == b(r)) {
        l += 1
        r -= 1
      }

      isPalindrome(a, l, r) || isPalindrome(b, l, r)
    }
    check(a, b) || check(b, a)
  }

  def main(args: Array[String]): Unit = {
    println(checkPalindromeFormation("a", "b"))
    println(checkPalindromeFormation("xbdef", "xecab"))
    println(checkPalindromeFormation("ulacfd", "jizalu"))
    println(checkPalindromeFormation("pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp"))
  }
}
