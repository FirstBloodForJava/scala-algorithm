package com.oycm.algorithm.abd


object Solution_3 {

  /**
   * 1156. 单字符重复子串的最大长度
   * https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/description/
   *
   * 如果字符串中的所有字符都相同，那么这个字符串是 单字符重复的字符串。
   *
   * 只能交换其中两个字符一次或者什么都不做，然后得到 单字符重复的子串。求最长的 子串长度
   *
   * @param text 字符串
   * @return
   */
  def maxRepOpt1(text: String): Int = {
    /*
    先记录 字符出现的次数 cnt
    循环 i = 0, j = i, text(i) == text(j) j++,
    当 text(i) != text(j), 此时[i, j) 是左边相同字符 记录 k = j,
    循环 text(i) == text(k) k++,
    当 text(i) != text(k) [j+1, k)
    记录答案 min((j - i) + (k -j - 1) + 1, cnt(text(i) - 'a'))

    上面的循环是不是可以转换成：r - l + 1 - cur(text(l) - 'a') < 1

    时间复杂度 O(n)
    空间复杂度 O(26)

     */
    val cnt = Array.fill(26)(0)
    for (i <- text.indices) {
      cnt(text(i) - 'a') += 1
    }
    var ans = 0
    var l = 0
    val cur = Array.fill(26)(0)
    for (r <- text.indices) {
      cur(text(r) - 'a') += 1
      while (r - l + 1 - cur(text(l) - 'a') > 1) {
        cur(text(l) - 'a') -= 1
        l += 1
      }
      ans = Math.max(ans, Math.min(cur(text(l) - 'a') + 1, cnt(text(l) - 'a')))
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxRepOpt1("baaabaaaaaaabaab") == 11)

    println(maxRepOpt1("ababa") == 3)
    println(maxRepOpt1("aaabaaa") == 6)
    println(maxRepOpt1("aaabbaaa") == 4)
    println(maxRepOpt1("aaaaa") == 5)
    println(maxRepOpt1("abcdef") == 1)
  }

}
