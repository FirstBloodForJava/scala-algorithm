package com.oycm.algorithm.ba.opposite

object Solution_4 {

  /**
   * 125.验证回文串
   * https://leetcode.cn/problems/valid-palindrome?envType=problem-list-v2&envId=K0Hriq4a
   *
   * 回文串：字符正着读和反着读一样
   *
   * 将 s 字符串所有大写字符转换为小写字符、并移除所有非字母数字字符之后，字符是否还是回文串
   *
   * 注意：剩余字符串可以是 数字或字母
   * @param s
   * @return
   */
  def isPalindrome(s: String): Boolean = {
    /*
    s 转换成 全小写
    当 l < r 时
    s(l) == s(r) l++, r--
    s(l+1) == s(r-1) l++, r--
    ...
    特殊情况处理，字符都不是 字母字符，空字符串也是 回文串
     */
    val n = s.length
    val s1 = s.toLowerCase
    var flag = true
    var l = 0
    var r = n - 1

    while (l < r && flag) {

      def isLetterOrNumber(c: Char): Boolean = {
        (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')
      }

      // 找到 非 字母、数组字符跳过
      while (l < n && !isLetterOrNumber(s1(l))) {
        l += 1
      }

      while (r > 0 && !isLetterOrNumber(s1(r))) {
        r -= 1
      }

      // l 和 r 比较
      if (l < n || r > 0) {
        flag = s1(r) == s1(l)
      }
      l += 1
      r -= 1

    }
    flag
  }

  def main(args: Array[String]): Unit = {
    println(isPalindrome("A man, a plan, a canal: Panama"))
    println(!isPalindrome("race a car"))
    println(isPalindrome(" "))
    println(isPalindrome(".,"))
    println(!isPalindrome("0P"))
  }

}
