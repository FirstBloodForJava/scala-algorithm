package com.oycm.algorithm.a.basic

/**
 * <p>
 * 1456.定长子串中元音的最大数目
 * </p>
 * https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
 *
 */
object Solution_1 {

  /**
   * 给你字符串 s 和整数 k
   * 返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数
   * 元音字母: a, e, i, o, u
   *
   * @param s 给定的字符串
   * @param k 子串长度
   * @return
   */
  def maxVowels(s: String, k: Int): Int = {
    (leftMaxVowels(s, k) + rightMaxVowels(s, k)) / 2
  }

  private def leftMaxVowels(s: String, k: Int): Int = {
    var ans = 0
    var temp = 0
    for (i <- s.indices) {
      // 进入窗口
      s(i) match {
        case 'a' | 'e' | 'i' | 'o' | 'u' => temp += 1
        case _ =>
      }
      if (i - k + 1 < 0) {
        // 1.入 窗口还未形成
      } else {
        // 2. 更新答案
        ans = math.max(ans, temp)

        // 3. 出 左
        val left = i - k + 1
        s(left) match {
          case 'a' | 'e' | 'i' | 'o' | 'u' => temp -= 1
          case _ =>
        }
      }
    }
    ans
  }

  private def rightMaxVowels(s: String, k: Int): Int = {
    var ans = 0
    var temp = 0
    val flag = s.length - k

    for (i <- s.length - 1 to 0 by -1) {
      // 1.入 窗口还未形成
      s(i) match {
        case 'a' | 'e' | 'i' | 'o' | 'u' => temp += 1
        case _ =>
      }
      if (i > flag) {
        //
      } else {
        // 2. 更新答案
        ans = math.max(ans, temp)

        // 3. 出 右
        val right = i + k - 1
        s(right) match {
          case 'a' | 'e' | 'i' | 'o' | 'u' => temp -= 1
          case _ =>
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxVowels("abciiidef", 3) == 3)
    println(maxVowels("aeiou", 2) == 2)
    println(maxVowels("leetcode", 3) == 2)
    println(maxVowels("rhythms", 4) == 0)
    println(maxVowels("tryhard", 4) == 1)
  }
}
