package com.oycm.algorithm.abb.basic

object Solution_3 {

  /**
   * 1234.替换子串得到平衡字符串 1878
   * https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
   * s 只有 Q, W, E, R 四种字符，长度为 n，n 是 4 的倍数
   * 平衡字符串：四种字符串恰好出现 n/4 次
   *
   * 求替换一个子串，使 s 变成平衡字符串
   *
   * @param s 只有 Q, W, E, R 四种字符
   * @return 求 替换 s 的字串，将 s 变成平衡字符串的最小子串长度，不需要替换返回 0
   */
  def balancedString(s: String): Int = {
    val n = s.length
    val k = n / 4
    // 先统计每个字符出现的次数，都为 k 则不需要替换
    val count = Array.fill('X')(0)
    for (i <- s.indices) {
      count(s(i)) += 1
    }
    if (count('Q') == k && count('W') == k && count('E') == k && count('R') == k) {
      return 0
    }
    // 字符次数 > k，替换子串需要满足条件，则 子串 外的字符数不可超过 k
    // 维持一个窗口 [l, r] 表示要替换的子串，count 现在记录就是窗口外的字符数量
    // 当窗口外的字符数未超过 k 时，说明替换当前窗口可以变成平衡字符串，可以继续缩小窗口，更新最小值
    // 当窗口外的字符数超过 k 时，
    var ans = n
    var l = 0
    for (r <- s.indices) {
      // 右窗口移动
      count(s(r)) -= 1
      // 为什么这里不需要判断 l 和 r 的大小？
      // 只有前面为 0 的情况，这里的循环才不会退出。只有前面出现次数 > k，才会进入循环，r 条件进入内存循环，l 会永远 <= r，因为次数累加 > k 退出内存循环，
      while (count('Q') <= k && count('W') <= k && count('E') <= k && count('R') <= k) {
        ans = Math.min(ans, r - l + 1)
        // 左窗口移动
        count(s(l)) += 1;
        l += 1;
      }

    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(balancedString("QWER") == 0)
    println(balancedString("QQER") == 1)
    println(balancedString("QQQW") == 2)
    println(balancedString("QQQQ") == 3)
    println(balancedString("QQWEWWQQ") == 3)

  }

}
