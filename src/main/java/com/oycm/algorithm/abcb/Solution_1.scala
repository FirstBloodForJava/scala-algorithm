package com.oycm.algorithm.abcb

object Solution_1 {

  /**
   * 1358.包含所有三种字符的子字符串数目 1646
   * https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters/
   * s 字符串，只包含 a, b, c 三种字符
   * 求 a, b, c 至少出现一次的 子字符串数目
   *
   * @param s 字符串，只包含 a, b, c 三种字符
   * @return a, b, c 至少出现一次的 子字符串数目
   */
  def numberOfSubstrings(s: String): Int = {
    /**
     * [l, r(min)] 满足条件 则 r 在 [r(min), n-1] 后追加字符的子串都符合条件 n - r
     * 固定 l，找到 r(min) 记录答案，l 向右移动，继续找 r(min) 知道 r 到 n-1 还未满足条件，则可以退出查找
     */
    var ans = 0
    val n = s.length
    val count = Array.fill('d')(0)
    var l = 0
    var r = 0
    var flag = true
    while (l < n - 2 && flag) {

      while (r < n && (count('a') == 0 || count('b') == 0 || count('c') == 0)) {
        count(s(r)) += 1
        r += 1
      }
      if (count('a') >= 1 && count('b') >= 1 && count('c') >= 1) {
        // 符合答案时 r + 1 了
        ans += n - r + 1
      } else if (r == n) {
        flag = false
      }
      // 继续下一轮
      count(s(l)) -= 1
      l += 1
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(numberOfSubstrings("abcabc"))
    println(numberOfSubstrings("aaacb"))
    println(numberOfSubstrings("abc"))
    println(numberOfSubstrings("abaaaa"))
  }

}
