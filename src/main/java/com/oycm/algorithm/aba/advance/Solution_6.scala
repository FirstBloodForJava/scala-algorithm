package com.oycm.algorithm.aba.advance

object Solution_6 {


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
    aabaa 0134
    abaaba 0235
    记录相同字符串的下标，相同字符串的区间 [l, r] 如果 r - l + 1 - cnt[text(l)](次数) <= 1 删除中间不同的字符串，就能得到相同字符串串
    现在这里需要替换，怎么知道外面是否有可替换的字符

    分组滑动 pos 数组表示一组相同字符的下标集合

    pos(r) - pos(l) + 1 - (r - l + 1) <= 1 时，如果 r < pos.length - 1 || l > 0 答案等于 pos 子数组长度 + 1

    时间复杂度 O(n)
    空间复杂度 O(n)

     */
    var ans = 0
    // 记录字符 出现的下标
    val cnt = Array.fill(26)(scala.collection.mutable.ArrayBuffer[Int]())
    for (i <- text.indices) {
      cnt(text(i) - 'a').append(i)
    }

    for (pos <- cnt if pos.size > ans) {
      var l = 0
      val n = pos.length
      for (r <- pos.indices) {
        while (pos(r) - pos(l) - (r - l) > 1) {
          l += 1
        }
        if (r < n - 1 || l > 0) {
          ans = Math.max(ans, r - l + 2)
        } else {
          ans = Math.max(ans, r - l + 1)
        }
      }
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
