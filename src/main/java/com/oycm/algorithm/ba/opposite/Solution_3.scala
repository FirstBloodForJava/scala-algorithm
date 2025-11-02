package com.oycm.algorithm.ba.opposite

object Solution_3 {

  /**
   * 345.反转字符串中的元音字母
   * https://leetcode.cn/problems/reverse-vowels-of-a-string/
   *
   * 元音字母: a, e, i, o, u 大小写两种格式都有
   *
   * 仅反转字符串中的所有元音字母，并返回结果字符串。
   *
   * @param s 可打印的 ASCII 字符组成的字符串
   * @return
   */
  def reverseVowels(s: String): String = {
    /*
    反转就是：首-尾 交换 => 首+1-尾-1 知道 l >= r
    */
    val n = s.length
    val ans = s.toCharArray

    var l = 0
    var r = n - 1
    val vowels = scala.collection.mutable.ArrayBuffer[Int]()
    val vowel = "aeiouAEIOU"

    /*
    这里为什么要 l <= r
    当 l = 8, r = 10 时 进入循环，如果 l 符合条件 l = 9，r = 10 不符合 r = 9 最后有一个字符没有遍历到
    */
    while (l <= r) {
      if (vowels.isEmpty) {
        if (vowel.indexOf(s(l)) > -1) {
          vowels.append(l)
        }
        l += 1
      }
      if (vowels.size == 1) {
        if (vowel.indexOf(s(r)) > -1) {
          vowels.append(r)
        }
        r -= 1
      }
      if (vowels.size == 2) {
        ans(vowels(0)) = s(vowels(1))
        ans(vowels(1)) = s(vowels(0))
        vowels.clear
      }
    }
    String.valueOf(ans)
  }

  def main(args: Array[String]): Unit = {
    println(reverseVowels("IceCreAm") == "AceCreIm")
    println(reverseVowels("leetcode") == "leotcede")
    println(reverseVowels(" apG0i4maAs::sA0m4i0Gp0") == " ipG0A4mAas::si0m4a0Gp0")
  }
}
